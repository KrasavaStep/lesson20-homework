package com.example.lessson17

import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.view.View
import android.widget.EditText
import android.widget.TextView
import java.lang.NumberFormatException

class EditCounter : AppCompatActivity() {
    private lateinit var editCounter : EditText
    private lateinit var editError: TextView

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_edit_counter)

        editCounter = findViewById(R.id.edit_counter)
        editError = findViewById(R.id.edit_error)
    }

    fun editCounter(view: View) {
        val intent = Intent()
        var counterValue = 0
        try {
            counterValue = editCounter.text.toString().toInt()
        } catch (ex: NumberFormatException) {
            editError.text = getString(ERROR_MSG_STRING_ID)
            return
        }

        intent.putExtra(CounterActivity.COUNTER, counterValue)
        setResult(RESULT_OK, intent)
        finish()
    }

    companion object {
        const val ERROR_MSG_STRING_ID = R.string.incorrect_number_format_msg
    }
}