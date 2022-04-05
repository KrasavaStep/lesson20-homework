package com.example.lessson17

import android.content.Intent
import android.graphics.drawable.Drawable
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.view.View
import android.widget.Button
import android.widget.EditText
import android.widget.TextView
import java.lang.NumberFormatException

class CreateCounterActivity : AppCompatActivity() {

    private lateinit var editCounterValue: EditText
    private lateinit var editTextColorValue: EditText
    private lateinit var editBgColorValue: EditText
    private lateinit var errorView: TextView

    private var errorString: String = getString(R.string.error_string_default)

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_create_counter)

        editCounterValue = findViewById(R.id.counter_digit_edittext)
        editTextColorValue = findViewById(R.id.color_edittext)
        editBgColorValue = findViewById(R.id.bg_edittext)
        errorView = findViewById(R.id.error_text)

        val savedErrMsg = savedInstanceState?.getString(ERROR_MSG_KEY)
        if (savedErrMsg != null) {
            errorView.text = savedErrMsg
            errorString = savedErrMsg
        }

        val savedCounterValue = savedInstanceState?.getString(COUNTER_VALUE_KEY)
        if (savedCounterValue != null) {
            editCounterValue.setText(savedCounterValue)
        }

        val savedTextColor = savedInstanceState?.getString(TEXT_COLOR_VALUE_KEY)
        if (savedTextColor != null) {
            editTextColorValue.setText(savedTextColor)
        }

        val savedBgColor = savedInstanceState?.getString(BG_COLOR_VALUE_KEY)
        if (savedBgColor != null){
            editBgColorValue.setText(savedBgColor)
        }
    }

    override fun onSaveInstanceState(outState: Bundle) {
        super.onSaveInstanceState(outState)
        outState.putString(ERROR_MSG_KEY, errorString)
        outState.putString(COUNTER_VALUE_KEY, editCounterValue.text.toString())
        outState.putString(TEXT_COLOR_VALUE_KEY, editTextColorValue.text.toString())
        outState.putString(BG_COLOR_VALUE_KEY, editBgColorValue.text.toString())
    }

    fun createCounter(view: View) {
        if ((view as Button).text == getString(R.string.btn_create)) {
            val intent = Intent(this, CounterActivity::class.java)
            val counterDigit = createCounterDigit()
            val textColor = createTextColor()
            val bgColor = createBgColor()

            if (counterDigit != null && textColor != null && bgColor != null) {
                intent.putExtra(COUNTER_VALUE_KEY, counterDigit)
                intent.putExtra(TEXT_COLOR_VALUE_KEY, textColor)
                intent.putExtra(BG_COLOR_VALUE_KEY, bgColor)
                errorView.text = getString(R.string.error_string_default)
                zeroFields()
                startActivity(intent)
            }
            else {
                errorView.text = errorString
                zeroFields()
            }
        }
    }

    private fun createCounterDigit(): Int? {
        var counterValue: Int? = null

        try {
            counterValue = editCounterValue.text.toString().toInt()
        } catch (ex: NumberFormatException) {
            errorString += "${getString(INCORRECT_VALUE_FORMAT_STRING_ID)}\n"
            editCounterValue.setText("0")
        }

        return counterValue
    }

    private fun createTextColor(): Int? {
        var textColorValue: Int? = null
        val text = editTextColorValue.text.toString().lowercase()

        when (text) {
            getString(BTN_TXT_COLOR_RED_STRING_ID) -> textColorValue = R.color.text_red
            getString(BTN_TXT_COLOR_GREEN_STRING_ID) -> textColorValue = R.color.text_green
            getString(BTN_TXT_COLOR_BLUE_STRING_ID) -> textColorValue = R.color.text_blue
            getString(BTN_TXT_COLOR_MAGENTA_STRING_ID) -> textColorValue = R.color.text_magenta
            else -> {
                errorString += "${getString(INCORRECT_TXT_COLOR_STRING_ID)}\n"
                editTextColorValue.setText("r")
            }
        }

        return textColorValue
    }

    private fun createBgColor(): Int? {
        var bgColorValue: Int? = null
        val text = editBgColorValue.text.toString().lowercase()

        when (text) {
            getString(BTN_BG_COLOR1_STRING_ID) ->  bgColorValue = R.color.bg_light_blue
            getString(BTN_BG_COLOR2_STRING_ID) -> bgColorValue = R.color.bg_light_green
            getString(BTN_BG_COLOR3_STRING_ID) -> bgColorValue = R.color.bg_light_pink
            getString(BTN_BG_COLOR4_STRING_ID) -> bgColorValue = R.color.bg_orange
            else -> {
                errorString += "${getString(INCORRECT_BG_COLOR_STRING_ID)}\n"
                editBgColorValue.setText("1")
            }
        }

        return bgColorValue
    }

    private fun zeroFields(){
        editCounterValue.setText("0")
        editTextColorValue.setText("r")
        editBgColorValue.setText("1")
        errorString = getString(R.string.error_string_default)
    }

    companion object {
        const val COUNTER_VALUE_KEY = "COUNTER_VALUE_KEY"
        const val TEXT_COLOR_VALUE_KEY = "TEXT_COLOR_VALUE"
        const val BG_COLOR_VALUE_KEY = "BG_COLOR_VALUE"
        const val ERROR_MSG_KEY = "ERROR_MSG_KEY"

        const val INCORRECT_VALUE_FORMAT_STRING_ID = R.string.incorrect_number_format_msg
        const val INCORRECT_TXT_COLOR_STRING_ID = R.string.incorrect_txt_color_format_msg
        const val INCORRECT_BG_COLOR_STRING_ID = R.string.incorrect_bg_format_msg

        const val BTN_BG_COLOR1_STRING_ID = R.string.btn_bg_color_1
        const val BTN_BG_COLOR2_STRING_ID = R.string.btn_bg_color_2
        const val BTN_BG_COLOR3_STRING_ID = R.string.btn_bg_color_3
        const val BTN_BG_COLOR4_STRING_ID = R.string.btn_bg_color_4

        const val BTN_TXT_COLOR_RED_STRING_ID = R.string.btn_txt_color_red
        const val BTN_TXT_COLOR_GREEN_STRING_ID = R.string.btn_txt_color_green
        const val BTN_TXT_COLOR_BLUE_STRING_ID = R.string.btn_txt_color_blue
        const val BTN_TXT_COLOR_MAGENTA_STRING_ID = R.string.btn_txt_color_magenta

    }
}