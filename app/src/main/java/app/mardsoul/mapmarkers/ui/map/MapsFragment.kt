package app.mardsoul.mapmarkers.ui.map

import android.os.Bundle
import android.view.View
import android.widget.Toast
import app.mardsoul.mapmarkers.R
import app.mardsoul.mapmarkers.databinding.FragmentMapsBinding
import app.mardsoul.mapmarkers.domain.Place
import app.mardsoul.mapmarkers.ui.BaseFragment
import com.google.android.gms.maps.CameraUpdateFactory
import com.google.android.gms.maps.OnMapReadyCallback
import com.google.android.gms.maps.SupportMapFragment
import com.google.android.gms.maps.model.LatLng
import com.google.android.gms.maps.model.MarkerOptions

class MapsFragment : BaseFragment<FragmentMapsBinding>(FragmentMapsBinding::inflate) {

    private val callback = OnMapReadyCallback { googleMap ->
        /**
         * Manipulates the map once available.
         * This callback is triggered when the map is ready to be used.
         * This is where we can add markers or lines, add listeners or move the camera.
         * In this case, we just add a marker near Sydney, Australia.
         * If Google Play services is not installed on the device, the user will be prompted to
         * install it inside the SupportMapFragment. This method will only be triggered once the
         * user has installed Google Play services and returned to the app.
         */
        val sydney = LatLng(-34.0, 151.0)
        googleMap.addMarker(MarkerOptions().position(sydney).title("Marker in Sydney"))
        googleMap.moveCamera(CameraUpdateFactory.newLatLng(sydney))
    }


    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        val mapFragment = childFragmentManager.findFragmentById(R.id.map) as SupportMapFragment?
        mapFragment?.getMapAsync(callback)
        viewModel.markerLiveData.observe(viewLifecycleOwner) { refreshMarkers(it) }
    }

    private fun refreshMarkers(placeList: List<Place>) {
        Toast.makeText(requireContext(), "Received places list", Toast.LENGTH_SHORT).show()
    }

    companion object {
        @JvmStatic
        fun newInstance() = MapsFragment()
    }
}