package com.example.primeiroapp

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import com.google.android.gms.maps.CameraUpdateFactory
import com.google.android.gms.maps.SupportMapFragment
import com.google.android.gms.maps.model.BitmapDescriptorFactory
import com.google.android.gms.maps.model.LatLng
import com.google.android.gms.maps.model.MarkerOptions
import kotlinx.android.synthetic.main.activity_local_detail.*

class LocalDetailActivity : AppCompatActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_local_detail)
        intent.getParcelableExtra<Local>(LOCAL_EXTRA)?.let { local ->
            tvDetailName.text = local.name
            val mapFragment =
                supportFragmentManager.findFragmentById(R.id.mapFragment) as SupportMapFragment
            mapFragment.getMapAsync {
                it.uiSettings.isZoomControlsEnabled = true
                val latLng = LatLng(local.lat, local.lng)
                val marker =
                    MarkerOptions().position(latLng)
                it.addMarker(marker)
                it.moveCamera(CameraUpdateFactory.newLatLng(latLng))
                it.moveCamera(CameraUpdateFactory.zoomTo(15f))

            }
        }
    }

    companion object {
        const val LOCAL_EXTRA = "Local"
    }
}