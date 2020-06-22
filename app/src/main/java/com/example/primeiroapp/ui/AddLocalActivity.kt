package com.example.primeiroapp.ui

import android.app.Activity
import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import com.example.primeiroapp.DB.Local
import com.example.primeiroapp.R
import com.google.android.libraries.places.api.Places
import com.google.android.libraries.places.api.model.Place
import com.google.android.libraries.places.widget.Autocomplete
import com.google.android.libraries.places.widget.model.AutocompleteActivityMode
import kotlinx.android.synthetic.main.activity_add_local.*

class AddLocalActivity : AppCompatActivity() {
    private lateinit var place: Place
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_add_local)
        setUpLocationInputClick()
        btDone.setOnClickListener {
            val local = Local(
                0, edtInputName.text.toString().toLowerCase(),
                place.latLng?.latitude ?: 0.0,
                place.latLng?.longitude ?: 0.0
            )
            val intent = Intent().apply {
                putExtra(ADD_LOCAL_EXTRA, local)
            }
            setResult(Activity.RESULT_OK, intent)
            finish()
        }
    }

    private fun setUpLocationInputClick() {
        edtInputLocal.setOnClickListener {

            val fields = listOf(Place.Field.ADDRESS, Place.Field.LAT_LNG)
            val intent =
                Autocomplete.IntentBuilder(AutocompleteActivityMode.FULLSCREEN, fields).build(this)
            startActivityForResult(intent, AUTO_COMPLETE_REQUEST_CODE)
        }
    }

    override fun onActivityResult(requestCode: Int, resultCode: Int, data: Intent?) {
        super.onActivityResult(requestCode, resultCode, data)
        if (requestCode == AUTO_COMPLETE_REQUEST_CODE && data != null) {
            if (resultCode == Activity.RESULT_OK) {
                place = Autocomplete.getPlaceFromIntent(data)
                edtInputLocal.setText(place.address)
            }
        }
    }

    companion object {
        const val ADD_LOCAL_EXTRA = "AddedLocal"
        const val AUTO_COMPLETE_REQUEST_CODE = 1
    }
}