package com.example.lessson17

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.util.Log
import android.view.View
import android.widget.Button
import android.widget.ImageView
import androidx.annotation.DrawableRes

class AnimalsActivity : AppCompatActivity() {

    private lateinit var cat: String
    private lateinit var dog: String
    private lateinit var parrot: String
    private lateinit var imagesMap : Map<String, Int>
    private lateinit var imgView : ImageView

    @DrawableRes
    private var currentImgRes = R.drawable.cat
    private var rotation = ROTATION_START_VALUE

    override fun onSaveInstanceState(outState: Bundle) {
        super.onSaveInstanceState(outState)
        outState.putInt(IMG_RES_KEY, currentImgRes)
        Log.d(TAG, "$currentImgRes - ${R.drawable.cat}")
    }

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_animals)

        imgView = findViewById(R.id.imageView)

        cat = resources.getString(R.string.btn_cat)
        dog = resources.getString(R.string.btn_dog)
        parrot = resources.getString(R.string.btn_parrot)
        imagesMap = mapOf(
            cat to R.drawable.cat,
            dog to R.drawable.dog,
            parrot to R.drawable.parrot,
        )

        val savedImgRes = savedInstanceState?.getInt(IMG_RES_KEY)
        if (savedImgRes != null) {
            imgView.setImageResource(savedImgRes)
            currentImgRes = savedImgRes
        }

        imgView.setOnClickListener{
            updateRotation(rotation + ROTATION_INCREASE_VALUE)
        }
    }

    fun animalBtnClick(view: View) {
        val btnText = (view as Button).text
        var imgRes = imagesMap[btnText]
        if (btnText == getString(R.string.btn_rnd_animal)) {
            imgRes = (imagesMap.values - currentImgRes).random()
        }
        updateRotation(ROTATION_START_VALUE)
        currentImgRes = requireNotNull(imgRes) { "Incorrect value ($btnText) in imagesMap ($imagesMap) " }
        imgView.setImageResource(imgRes)

    }

    private fun updateRotation(angle : Float){
        rotation = angle
        imgView.rotation = rotation
    }

    companion object {
        const val ROTATION_INCREASE_VALUE = 90F
        const val ROTATION_START_VALUE = 0F
        const val IMG_RES_KEY = "IMAGE"
        const val TAG = "AnimalsActivity";
    }
}