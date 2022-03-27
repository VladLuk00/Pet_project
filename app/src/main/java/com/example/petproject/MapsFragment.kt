package com.example.petproject

import android.Manifest
import android.annotation.SuppressLint
import android.content.pm.PackageManager
import android.location.Location
import android.os.Bundle
import android.util.Log
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.Toast
import androidx.appcompat.app.AppCompatActivity
import androidx.core.app.ActivityCompat
import androidx.core.content.ContextCompat
import androidx.navigation.fragment.findNavController
import com.example.petproject.utils.PermissionUtils.isPermissionGranted
import com.example.petproject.utils.PermissionUtils.requestPermission
import com.example.petproject.databinding.FragmentMapsBinding
import com.example.petproject.ui.base.BaseFragment
import com.google.android.gms.maps.GoogleMap
import com.google.android.gms.maps.OnMapReadyCallback
import com.google.android.gms.maps.SupportMapFragment
import com.google.android.gms.maps.model.Marker
import com.google.android.gms.maps.model.MarkerOptions
import com.google.android.gms.maps.model.PointOfInterest
import dagger.hilt.android.AndroidEntryPoint

@AndroidEntryPoint
open class MapsFragment(
    override val bindingInflater: (LayoutInflater, ViewGroup?, Boolean) -> FragmentMapsBinding = FragmentMapsBinding::inflate
) : BaseFragment<FragmentMapsBinding>(), OnMapReadyCallback,
    GoogleMap.OnMyLocationButtonClickListener,
    GoogleMap.OnMyLocationClickListener,
    ActivityCompat.OnRequestPermissionsResultCallback,
    GoogleMap.OnPoiClickListener, GoogleMap.OnMarkerClickListener {

    var marker: MutableList<Marker> = mutableListOf()

    private var permissionDenied = false
    private lateinit var map: GoogleMap

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        val mapFragment = childFragmentManager.findFragmentById(R.id.map) as SupportMapFragment?
        mapFragment?.getMapAsync(this)
    }

    override fun init() {
        requireActivity().onBackPressedDispatcher.addCallback(onBackPressed {
            view?.post { findNavController().popBackStack() }
        })
    }

    override fun onMapReady(p0: GoogleMap) {
        map = p0
        p0.setOnMyLocationButtonClickListener(this)
        p0.setOnMyLocationClickListener(this)

        p0.setOnMarkerClickListener(this);
        enableMyLocation()
        map.setOnMapClickListener {
            marker.add(map.addMarker(MarkerOptions().position(it)) as Marker)
        }
    }

    override fun onMyLocationButtonClick(): Boolean {
        Toast.makeText(requireContext(), "MyLocation button clicked", Toast.LENGTH_SHORT).show()
        return false
    }

    override fun onMyLocationClick(p0: Location) {
        Toast.makeText(requireContext(), "Current location:\n$p0", Toast.LENGTH_LONG).show()
    }

    override fun onPoiClick(p0: PointOfInterest) {
        Log.d("zxc", "onPoiClick")
        Toast.makeText(
            requireContext(), """Clicked: ${(p0.name)}
                    Place ID:${(p0.placeId)}
                    Latitude:${(p0.latLng.latitude)} Longitude:${(p0.latLng.longitude)}""",
            Toast.LENGTH_SHORT
        ).show()
    }

    override fun onMarkerClick(p0: Marker): Boolean {
        marker.remove(p0)
        p0.remove()
        return true
    }

    override fun onRequestPermissionsResult(
        requestCode: Int,
        permissions: Array<String>,
        grantResults: IntArray
    ) {
        if (requestCode != LOCATION_PERMISSION_REQUEST_CODE) {
            super.onRequestPermissionsResult(requestCode, permissions, grantResults)
            return
        }
        if (isPermissionGranted(
                permissions,
                grantResults,
                Manifest.permission.ACCESS_FINE_LOCATION
            )
        ) {
            enableMyLocation()
        } else {
            permissionDenied = true
            // [END_EXCLUDE]
        }
    }

    override fun onResume() {
        super.onResume()
        if (permissionDenied) {
            permissionDenied = false
        }
    }

    @SuppressLint("MissingPermission")
    private fun enableMyLocation() {
        if (!::map.isInitialized) return
        // [START maps_check_location_permission]
        if (ContextCompat.checkSelfPermission(
                requireContext(),
                Manifest.permission.ACCESS_COARSE_LOCATION
            )
            == PackageManager.PERMISSION_GRANTED
        ) {
            map.isMyLocationEnabled = true
        } else {
            requestPermission(
                requireActivity() as AppCompatActivity, LOCATION_PERMISSION_REQUEST_CODE,
                Manifest.permission.ACCESS_FINE_LOCATION, true
            )
        }
    }

    companion object {
        /**
         * Request code for location permission request.
         *
         * @see .onRequestPermissionsResult
         */
        private const val LOCATION_PERMISSION_REQUEST_CODE = 1
    }
}