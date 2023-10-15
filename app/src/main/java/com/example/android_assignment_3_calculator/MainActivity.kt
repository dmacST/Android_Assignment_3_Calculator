package com.example.android_assignment_3_calculator

import android.os.Bundle
import android.view.View
import android.widget.Button
import android.widget.TextView
import androidx.appcompat.app.AppCompatActivity

class MainActivity : AppCompatActivity() {
    private lateinit var displayTextView: TextView
    private var currentInput = ""
    private var result = 0.0
    private var operator = ""
    private var newInput = true

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)
        displayTextView = findViewById(R.id.solution)
    }

    fun onButtonClick(view: View) {
        if (view is Button) {
            val buttonText = view.text.toString()

            if (newInput) {
                currentInput = ""
                newInput = false
            }

            if (buttonText == "." && currentInput.contains(".")) {
                return
            }

            currentInput += buttonText
            displayTextView.text = currentInput
        }
    }

    fun onAddClick(view: View) {
        performOperation()
        operator = "+"
    }

    fun onSubtractClick(view: View) {
        performOperation()
        operator = "-"
    }

    fun onMultiplyClick(view: View) {
        performOperation()
        operator = "*"
    }

    fun onDivideClick(view: View) {
        performOperation()
        operator = "/"
    }

    fun onEqualsClick(view: View) {
        performOperation()
        operator = ""
    }

    fun onClearClick(view: View) {
        currentInput = ""
        result = 0.0
        operator = ""
        displayTextView.text = "0"
        newInput = true
    }

    fun onPlusMinusClick(view: View) {
        if (currentInput.isNotEmpty() && currentInput != "0") {
            currentInput = if (currentInput.startsWith("-")) {
                currentInput.substring(1)
            } else {
                "-$currentInput"
            }
            displayTextView.text = currentInput
        }
    }

    fun onPercentageClick(view: View) {
        if (currentInput.isNotEmpty()) {
            val input = currentInput.toDouble()
            val percentage = input / 100.0
            currentInput = percentage.toString()
            displayTextView.text = currentInput
        }
    }

    fun Double.isInt() = this % 1 == 0.0

    private fun performOperation() {
        val input = currentInput.toDouble()
        when (operator) {
            "+" -> result += input
            "-" -> result -= input
            "*" -> result *= input
            "/" -> result /= input
            else -> result = input
        }

        if (result.isInt()) {
            currentInput = result.toInt().toString()
        } else {
            currentInput = result.toString()
        }

        displayTextView.text = currentInput
        newInput = true
    }
}