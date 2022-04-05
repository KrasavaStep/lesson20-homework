package com.example.lessson17

import android.content.Intent
import android.net.Uri
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.view.View
import android.widget.Button

class MenuActivity : AppCompatActivity() {

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_menu)
    }

    fun btnClick(view: View) {
        when ((view as Button).text) {
            getString(R.string.btn_animals) -> {
                val animalsIntent = Intent(this, AnimalsActivity::class.java)
                startActivity(animalsIntent)
            }
            getString(R.string.btn_info) -> {
                val infoIntent = Intent(this, InfoActivity::class.java)
                startActivity(infoIntent)
            }
            getString(R.string.btn_counter) -> {
                val counterIntent = Intent(this, CounterActivity::class.java)
                startActivity(counterIntent)
            }
            getString(R.string.btn_create_counter) -> {
                val createCounterIntent = Intent(this, CreateCounterActivity::class.java)
                startActivity(createCounterIntent)
            }
            getString(R.string.btn_maps) -> {
                val mapsIntent = Intent()
                mapsIntent.action = Intent.ACTION_VIEW
                mapsIntent.data = Uri.parse(GEO)
                startActivity(mapsIntent)
            }
            getString(R.string.btn_browser) -> {
                val webIntent = Intent(Intent.ACTION_VIEW, Uri.parse(WEB))
                startActivity(webIntent)
            }
        }
    }

    companion object {
        const val GEO = "geo:52.42436452390642, 31.009136448838415"
        const val WEB = "https://www.youtube.com/watch?v=O91DT1pR1ew"
    }

}