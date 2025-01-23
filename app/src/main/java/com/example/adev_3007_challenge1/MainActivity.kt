package com.example.adev_3007_challenge1

/*
Name: Hanz Samonte
Date: 01/23/2025
Course: Mobile App Development
Description: Challenge 1
 */

import android.os.Bundle
import android.widget.Button
import android.widget.TextView
import android.widget.Toast
import androidx.activity.enableEdgeToEdge
import androidx.appcompat.app.AppCompatActivity
import androidx.core.view.ViewCompat
import androidx.core.view.WindowInsetsCompat

class MainActivity : AppCompatActivity() {
    // Declare the display view, input, operator, and result variables for the calculator
    private lateinit var display: TextView
    private var input = ""
    private var operator = ""
    private var result = 0.0


    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        enableEdgeToEdge()
        setContentView(R.layout.activity_main)
        ViewCompat.setOnApplyWindowInsetsListener(findViewById(R.id.main)) { v, insets ->
            val systemBars = insets.getInsets(WindowInsetsCompat.Type.systemBars())
            v.setPadding(systemBars.left, systemBars.top, systemBars.right, systemBars.bottom)
            insets
        }

        // Register our control
        display = findViewById(R.id.txt_result)
        initializeButtons()


    }

    /**
     * Initialize the buttons and their respective click events
     */
    private fun initializeButtons() {
        findViewById<Button>(R.id.btn_0).setOnClickListener { addNumber("0") }
        findViewById<Button>(R.id.btn_1).setOnClickListener { addNumber("1") }
        findViewById<Button>(R.id.btn_2).setOnClickListener { addNumber("2") }
        findViewById<Button>(R.id.btn_3).setOnClickListener { addNumber("3") }
        findViewById<Button>(R.id.btn_4).setOnClickListener { addNumber("4") }
        findViewById<Button>(R.id.btn_5).setOnClickListener { addNumber("5") }
        findViewById<Button>(R.id.btn_6).setOnClickListener { addNumber("6") }
        findViewById<Button>(R.id.btn_7).setOnClickListener { addNumber("7") }
        findViewById<Button>(R.id.btn_8).setOnClickListener { addNumber("8") }
        findViewById<Button>(R.id.btn_9).setOnClickListener { addNumber("9") }
        findViewById<Button>(R.id.btn_decimal).setOnClickListener { addDecimal() }
        findViewById<Button>(R.id.btn_add).setOnClickListener { setOperator("+") }
        findViewById<Button>(R.id.btn_subtract).setOnClickListener { setOperator("-") }
        findViewById<Button>(R.id.btn_multiply).setOnClickListener { setOperator("X") }
        findViewById<Button>(R.id.btn_divide).setOnClickListener { setOperator("÷") }
        findViewById<Button>(R.id.btn_clear).setOnClickListener { clear() }
        findViewById<Button>(R.id.btn_equals).setOnClickListener { calculate() }
        findViewById<Button>(R.id.btn_toggle).setOnClickListener { workInProgressMessage() }
        findViewById<Button>(R.id.btn_back).setOnClickListener { workInProgressMessage() }
    }

    /**
     * Clears the input, operator, and the result display
     */
    private fun clear() {
        input = ""
        operator = ""
        result = 0.0
        display.text = "0"
    }

    /**
     * Adds the user input's number to the display
     * @param number The number to add to the display
     */
    private fun addNumber(number: String) {
        input += number
        display.text = input
    }

    /**
     * Adds a decimal point "." to the input
     */
    private fun addDecimal() {
        // If the input doesn't contain a decimal point, add it
        if (!input.contains(".")) {
            input += "."
            display.text = input
        }
    }

    /**
     * Sets the operator for the calculation
     * @param op The operator to set
     */
    private fun setOperator(op: String) {
        // If the input is not empty and doesn't contain any operator, add the operator
        if (input.isNotEmpty() && !input.contains(Regex("[+\\-X÷]"))) {
            operator = op
            input += operator
            display.text = input
        }
    }

    /**
     * Displays a message that the button is not yet implemented
     */
    private fun workInProgressMessage() {
        Toast.makeText(this, "We're working on this button, come back later!", Toast.LENGTH_SHORT).show()
    }

    /**
     * Calculates the result based on the input and operator
     */
    private fun calculate() {
        // If there's no operator, return
        if (operator.isEmpty()) return

        // Split the input into two parts based on the operator
        val parts = input.split(operator)
        // If there are two parts, create variables for the two parts and convert them to double
        if (parts.size == 2) {
            val num1 = parts[0].toDouble()
            val num2 = parts[1].toDouble()

            // Perform the calculation based on the operator and the two parts/numbers
            result = when (operator) {
                "+" -> num1 + num2
                "-" -> num1 - num2
                "X" -> num1 * num2
                "÷" -> num1 / num2
                else -> 0.0
            }

            // Display the result, and reset the operator
            display.text = result.toString()
            input = result.toString()
            operator = ""
        }
    }

}