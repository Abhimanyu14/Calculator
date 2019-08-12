package com.appz.abhi.calculator

import android.os.Bundle
import android.util.Log
import android.view.View
import androidx.appcompat.app.AppCompatActivity
import kotlinx.android.synthetic.main.activity_home.*
import kotlin.math.sqrt

class MainActivity : AppCompatActivity(), View.OnClickListener {


    //  Data Variables
    private var result: Float = 0f
    private var temp1: Float = 0f
    private var temp2: Float = 0f
    private var decimalValue: Float = 0f
    private var tempDecimalValue: Float = 0f
    private var decimalFlag: Boolean = false
    private var operator: Char = '#'
    private var prevChar: Char = '#'
    private var decimalDigits: Int = 0

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)

        //  Set activity layout
        setContentView(R.layout.activity_home)

        //  Hide Action Bar
        supportActionBar?.hide()

        //  Event listeners
        digit_0_tv_id.setOnClickListener(this)
        digit_1_tv_id.setOnClickListener(this)
        digit_2_tv_id.setOnClickListener(this)
        digit_3_tv_id.setOnClickListener(this)
        digit_4_tv_id.setOnClickListener(this)
        digit_5_tv_id.setOnClickListener(this)
        digit_6_tv_id.setOnClickListener(this)
        digit_7_tv_id.setOnClickListener(this)
        digit_8_tv_id.setOnClickListener(this)
        digit_9_tv_id.setOnClickListener(this)
        equal_tv_id.setOnClickListener(this)
        plus_tv_id.setOnClickListener(this)
        minus_tv_id.setOnClickListener(this)
        mul_tv_id.setOnClickListener(this)
        div_tv_id.setOnClickListener(this)
        mod_tv_id.setOnClickListener(this)
        ac_tv_id.setOnClickListener(this)
        c_tv_id.setOnClickListener(this)
        dec_tv_id.setOnClickListener(this)
    }

    override fun onClick(v: View) {

        when (v.id) {
            R.id.digit_0_tv_id -> {
                digitPressed(0)
            }
            R.id.digit_1_tv_id -> {
                digitPressed(1)
            }
            R.id.digit_2_tv_id -> {
                digitPressed(2)
            }
            R.id.digit_3_tv_id -> {
                digitPressed(3)
            }
            R.id.digit_4_tv_id -> {
                digitPressed(4)
            }
            R.id.digit_5_tv_id -> {
                digitPressed(5)
            }
            R.id.digit_6_tv_id -> {
                digitPressed(6)
            }
            R.id.digit_7_tv_id -> {
                digitPressed(7)
            }
            R.id.digit_8_tv_id -> {
                digitPressed(8)
            }
            R.id.digit_9_tv_id -> {
                digitPressed(9)
            }
            R.id.plus_tv_id -> {
                if (operator != '#') {
                    calculate()
                    operator = '#'
                }
                operator = '+'
                prevChar = '+'
                temp2 = result
                temp1 = 0f
                result = 0f
                disp_tv_id.text = "+"
            }
            R.id.minus_tv_id -> {
                if (operator != '#') {
                    calculate()
                    operator = '#'
                }
                operator = '-'
                prevChar = '-'
                temp2 = result
                temp1 = 0f
                result = 0f
                disp_tv_id.text = "-"
            }
            R.id.mul_tv_id -> {
                if (operator != '#') {
                    calculate()
                    operator = '#'
                }
                operator = '*'
                prevChar = '*'
                temp2 = result
                temp1 = 0f
                result = 0f
                disp_tv_id.text = "*"
            }
            R.id.div_tv_id -> {
                if (operator != '#') {
                    calculate()
                    operator = '#'
                }
                operator = '/'
                prevChar = '/'
                temp2 = result
                temp1 = 0f
                result = 0f
                disp_tv_id.text = "/"
            }
            R.id.c_tv_id -> {
                if (prevChar == '#') {
                    if (!decimalFlag) {
                        result /= 10
                        updateDisplay()
                    } else {
                        if (decimalDigits == 1) {
                            decimalFlag = false
                            result = result.toInt().toFloat()
                        } else {
                            Log.e("Log:", "" + decimalValue)
                            decimalValue = (decimalValue / 10).toInt().toFloat()
                            decimalDigits--
                            Log.e("Log:", "" + decimalValue)
                            tempDecimalValue = decimalValue
                            for (i in 0 until decimalDigits) {
                                tempDecimalValue /= 10
                            }
                            Log.e("Log:", "" + tempDecimalValue)
                            result = result.toInt() + tempDecimalValue
                            Log.e("Log:", "" + result)
                        }
                    }
                }
                updateDisplay()
            }
            R.id.ac_tv_id -> {
                temp1 = 0f
                temp2 = 0f
                result = 0f
                decimalDigits = 0
                decimalFlag = false
                decimalValue = 0f
                operator = '#'
                prevChar = '#'
                updateDisplay()
            }
            R.id.mod_tv_id -> {
                operator = '%'
                prevChar = '%'
                result = sqrt(result)
                decimalCheck(result)
                updateDisplay()
                temp2 = result
                temp1 = 0f
                result = 0f
            }
            R.id.equal_tv_id -> {
                prevChar = '='
                calculate()
            }
            R.id.dec_tv_id -> {
                decimalFlag = true
                decimalValue = 0f
                decimalDigits = 0
            }
        }
        Log.e("result", "" + result)
        Log.e("temp1", "" + temp1)
        Log.e("temp2", "" + temp2)
        Log.e("decimalValue", "" + decimalValue)
        Log.e("tempDecimalValue", "" + tempDecimalValue)
        Log.e("decimalFlag", "" + decimalFlag)
        Log.e("operator", "" + operator)
        Log.e("prevChar", "" + prevChar)
        Log.e("decimalDigits", "" + decimalDigits)
        Log.e("**********", "************************************************************")
    }

    private fun digitPressed(t: Int) {
        if (prevChar == '#') {
            if (decimalFlag) {
                decimalValue = decimalValue * 10 + t
                decimalDigits++
                tempDecimalValue = decimalValue
                for (i in 0 until decimalDigits) {
                    tempDecimalValue /= 10
                }
                temp1 = temp1.toInt() + tempDecimalValue
                result = temp1
            } else {
                temp1 = temp1 * 10 + t
                result = temp1
            }
        } else {
            temp1 = t.toFloat()
            result = temp1
            prevChar = '#'
        }
        updateDisplay()
    }

    private fun updateDisplay() {
        if (!decimalFlag) {
            disp_tv_id.text = result.toInt().toString()
        } else {
            disp_tv_id.text = result.toString()
        }
    }

    private fun decimalCheck(x: Float): Boolean {
        if (x % 1.0 == 0.0) {
            decimalFlag = false
            decimalValue = 0f
            decimalDigits = 0
        } else {
            decimalFlag = true
            var t = x
            decimalValue = t - t.toInt()
            decimalDigits = 0
            while (((t - t.toInt()) != 0f)) {
                decimalDigits++
                t *= 10
            }
        }
        return decimalFlag
    }

    private fun calculate() {
        when (operator) {
            '+' -> {
                result = temp2 + temp1
                decimalCheck(result)
            }
            '-' -> {
                result = temp2 - temp1
                decimalCheck(result)
            }
            '*' -> {
                result = temp2 * temp1
                decimalCheck(result)
            }
            '/' -> {
                result = temp2 / temp1
                decimalCheck(result)
            }
            '%' -> {
                result = temp2 % temp1
                decimalCheck(result)
            }
        }
        operator = '#'
        updateDisplay()
    }

    private fun isOperator(ch: Char): Boolean {
        return ch == '+' || ch == '-' || ch == '*' || ch == '/'
    }
}
