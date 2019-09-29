package com.scodeid.mobilecomputingcollage

/**
 * @author
 * Created by scode on 27,September,2019
 * Yogi Arif Widodo
 * www.dicoding.com/users/297307
 * www.github.com/yogithesymbian
 * SCODEID company,
 * Indonesia, East Borneo.
 * ==============================================================
Android Studio 3.4.2
Build #AI-183.6156.11.34.5692245, built on June 27, 2019
JRE: 1.8.0_152-release-1343-b16-5323222 amd64
JVM: OpenJDK 64-Bit Server VM by JetBrains s.r.o
Linux 5.2.0-kali2-amd64
 * ==============================================================
 */

import android.annotation.SuppressLint
import android.os.Bundle
import android.util.Log
import android.view.View
import androidx.appcompat.app.AppCompatActivity
import com.scodeid.mobilecomputingcollage.formula.CalculateFactorial
import com.scodeid.mobilecomputingcollage.formula.ExtendedDoubleEvaluator
import kotlinx.android.synthetic.main.activity_main_calc_science.*

class ScientificCal1 : AppCompatActivity() {

    private var count = 0
    private var expression = ""
    private var text = ""
    private var result: Double? = 0.0
//    private var dbHelper: DBHelper? = null

    private var toggleMode = 1
    private var angleMode = 1

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main_calc_science)
//        val toolbar = findViewById<View>(R.id.toolbar) as Toolbar
//        setSupportActionBar(toolbar)


//        dbHelper = DBHelper(this)

        edt_result_2!!.text = "0"

        //tags to change the btn_mode from degree to radian and vice versa
        btn_mode!!.tag = 1
        //tags to change the names of the buttons performing different operations
        btn_toggle!!.tag = 1
    }

    @SuppressLint("SetTextI18n")
    fun onClick(v: View) {
        toggleMode = btn_toggle!!.tag as Int
        angleMode = btn_mode!!.tag as Int
        when (v.id) {

            R.id.btn_toggle ->
                //change the button text if switch button is clicked
                when (toggleMode) {
                    1 -> {
                        btn_toggle!!.tag = 2
                        btn_square!!.setText(R.string.cube)
                        btn_xpowy!!.setText(R.string.tenpow)
                        btn_log!!.setText(R.string.naturalLog)
                        btn_sin!!.setText(R.string.sininv)
                        btn_cos!!.setText(R.string.cosinv)
                        btn_tan!!.setText(R.string.taninv)
                        btn_sqrt!!.setText(R.string.cuberoot)
                        btn_factorial!!.setText(R.string.Mod)
                    }
                    2 -> {
                        btn_toggle!!.tag = 3
                        btn_square!!.setText(R.string.square)
                        btn_xpowy!!.setText(R.string.epown)
                        btn_log!!.setText(R.string.log)
                        btn_sin!!.setText(R.string.hyperbolicSine)
                        btn_cos!!.setText(R.string.hyperbolicCosine)
                        btn_tan!!.setText(R.string.hyperbolicTan)
                        btn_sqrt!!.setText(R.string.inverse)
                        btn_factorial!!.setText(R.string.factorial)
                    }
                    3 -> {
                        btn_toggle!!.tag = 1
                        btn_sin!!.setText(R.string.sin)
                        btn_cos!!.setText(R.string.cos)
                        btn_tan!!.setText(R.string.tan)
                        btn_sqrt!!.setText(R.string.sqrt)
                        btn_xpowy!!.setText(R.string.xpown)
                    }
                }

            R.id.btn_mode ->
                //change the angle property for trignometric operations if btn_mode button is clicked
                if (angleMode == 1) {
                    btn_mode!!.tag = 2
                    btn_mode!!.setText(R.string.mode2)
                } else {
                    btn_mode!!.tag = 1
                    btn_mode!!.setText(R.string.mode1)
                }

            R.id.btn_num0 -> edt_result_2!!.text = edt_result_2!!.text.toString() + "0"

            R.id.btn_num1 -> edt_result_2!!.text = edt_result_2!!.text.toString() + "1"

            R.id.btn_num2 -> edt_result_2!!.text = edt_result_2!!.text.toString() + "2"

            R.id.btn_num3 -> edt_result_2!!.text = edt_result_2!!.text.toString() + "3"


            R.id.btn_num4 -> edt_result_2!!.text = edt_result_2!!.text.toString() + "4"

            R.id.btn_num5 -> edt_result_2!!.text = edt_result_2!!.text.toString() + "5"

            R.id.btn_num6 -> edt_result_2!!.text = edt_result_2!!.text.toString() + "6"

            R.id.btn_num7 -> edt_result_2!!.text = edt_result_2!!.text.toString() + "7"

            R.id.btn_num8 -> edt_result_2!!.text = edt_result_2!!.text.toString() + "8"

            R.id.btn_num9 -> edt_result_2!!.text = edt_result_2!!.text.toString() + "9"

            R.id.btn_pi -> edt_result_2!!.text = edt_result_2!!.text.toString() + "pi"

            R.id.btn_dot -> if (count == 0 && edt_result_2!!.length() != 0) {
                edt_result_2!!.text = edt_result_2!!.text.toString() + "."
                count++
            }

            R.id.btn_clear -> {
                edt_result_1!!.text = ""
                edt_result_2!!.text = ""
                count = 0
                expression = ""
            }

            R.id.btn_back_space -> {
                text = edt_result_2!!.text.toString()
                if (text.isNotEmpty()) {
                    if (text.endsWith(".")) {
                        count = 0
                    }
                    var newText = text.substring(0, text.length - 1)
                    //to delete the data contained in the brackets at once
                    if (text.endsWith(")")) {
                        val a = text.toCharArray()
                        var pos = a.size - 2
                        var counter = 1
                        //to find the opening bracket position
                        for (i in a.size - 2 downTo 0) {
                            when {
                                a[i] == ')' -> counter++
                                a[i] == '(' -> counter--
                                a[i] == '.' -> count = 0
                            }//if decimal is deleted b/w brackets then count should be zero
                            //if opening bracket pair for the last bracket is found
                            //if decimal is deleted b/w brackets then count should be zero
                            //if opening bracket pair for the last bracket is found
                            if (counter == 0) {
                                pos = i
                                break
                            }
                        }
                        newText = text.substring(0, pos)
                    }
                    //if edt_result_2 edit text contains only - sign or btn_sqrt or any other text functions
                    // at last then clear the edit text edt_result_2
                    if (newText == "-" || newText.endsWith("sqrt") || newText.endsWith("log") || newText.endsWith("ln")
                        || newText.endsWith("sin") || newText.endsWith("asin") || newText.endsWith("asind") || newText.endsWith(
                            "sinh"
                        )
                        || newText.endsWith("cos") || newText.endsWith("acos") || newText.endsWith("acosd") || newText.endsWith(
                            "cosh"
                        )
                        || newText.endsWith("tan") || newText.endsWith("atan") || newText.endsWith("atand") || newText.endsWith(
                            "tanh"
                        )
                        || newText.endsWith("cbrt")
                    ) {
                        newText = ""
                    } else if (newText.endsWith("^") || newText.endsWith("/"))
                        newText = newText.substring(0, newText.length - 1)
                    else if (newText.endsWith("pi") || newText.endsWith("e^"))
                        newText =
                            newText.substring(0, newText.length - 2)//if pow sign is left at the last or divide sign
                    edt_result_2!!.text = newText
                }
            }

            R.id.btn_plus -> operationClicked("+")

            R.id.btn_minus -> operationClicked("-")

            R.id.btn_divide -> operationClicked("/")

            R.id.btn_multiply -> operationClicked("*")

            R.id.btn_sqrt -> if (edt_result_2!!.length() != 0) {
                text = edt_result_2!!.text.toString()
                toggleMode = btn_toggle!!.tag as Int
                when (toggleMode) {
                    1 -> edt_result_2!!.text = "sqrt($text)"
                    2 -> edt_result_2!!.text = "cbrt($text)"
                    else -> edt_result_2!!.text = "1/($text)"
                }
            }

            R.id.btn_square -> if (edt_result_2!!.length() != 0) {
                text = edt_result_2!!.text.toString()
                if (toggleMode == 2)
                    edt_result_2!!.text = "($text)^3"
                else
                    edt_result_2!!.setText("($text)^2")
            }

            R.id.btn_xpowy -> if (edt_result_2!!.length() != 0) {
                text = edt_result_2!!.text.toString()
                when (toggleMode) {
                    1 -> edt_result_2!!.setText("($text)^")
                    2 -> edt_result_2!!.setText("10^($text)")
                    else -> edt_result_2!!.setText("e^($text)")
                }
            }

            R.id.btn_log -> if (edt_result_2!!.length() != 0) {
                text = edt_result_2!!.text.toString()
                if (toggleMode == 2)
                    edt_result_2!!.setText("ln($text)")
                else
                    edt_result_2!!.setText("log($text)")
            }

            R.id.btn_factorial -> if (edt_result_2!!.length() != 0) {
                text = edt_result_2!!.text.toString()
                if (toggleMode == 2) {
                    edt_result_1!!.text = "($text)%"
                    val percent = (text.toInt() / 100)
                    edt_result_2!!.text = "$percent"
                } else {
                    var res = ""
                    try {
                        val cf = CalculateFactorial()
                        val arr =
                            cf.factorial(java.lang.Double.parseDouble(ExtendedDoubleEvaluator().evaluate(text).toString()).toInt())
                        val resSize = cf.res
                        if (resSize > 20) {
                            for (i in resSize - 1 downTo resSize - 20) {
                                if (i == resSize - 2)
                                    res += "."
                                res += arr[i]
                            }
                            res += "E" + (resSize - 1)
                        } else {
                            for (i in resSize - 1 downTo 0) {
                                res += arr[i]
                            }
                        }
                        edt_result_2!!.setText(res)
                    } catch (e: Exception) {
                        if (e.toString().contains("ArrayIndexOutOfBoundsException")) {
                            edt_result_2!!.setText("Result too big!")
                        } else
                            edt_result_2!!.setText("Invalid!!")
                        e.printStackTrace()
                    }

                }
            }

            R.id.btn_sin -> if (edt_result_2!!.length() != 0) {
                text = edt_result_2!!.text.toString()
                if (angleMode == 1) {
                    val angle = Math.toRadians(ExtendedDoubleEvaluator().evaluate(text))
                    when (toggleMode) {
                        1 -> edt_result_2!!.setText("sin($angle)")
                        2 -> edt_result_2!!.setText("asind($text)")
                        else -> edt_result_2!!.setText("sinh($text)")
                    }
                } else {
                    when (toggleMode) {
                        1 -> edt_result_2!!.setText("sin($text)")
                        2 -> edt_result_2!!.setText("asin($text)")
                        else -> edt_result_2!!.setText("sinh($text)")
                    }
                }
            }

            R.id.btn_cos -> if (edt_result_2!!.length() != 0) {
                text = edt_result_2!!.text.toString()
                if (angleMode == 1) {
                    val angle = Math.toRadians(ExtendedDoubleEvaluator().evaluate(text))
                    when (toggleMode) {
                        1 -> edt_result_2!!.setText("cos($angle)")
                        2 -> edt_result_2!!.setText("acosd($text)")
                        else -> edt_result_2!!.setText("cosh($text)")
                    }
                } else {
                    when (toggleMode) {
                        1 -> edt_result_2!!.setText("cos($text)")
                        2 -> edt_result_2!!.setText("acos($text)")
                        else -> edt_result_2!!.setText("cosh($text)")
                    }
                }
            }

            R.id.btn_tan -> if (edt_result_2!!.length() != 0) {
                text = edt_result_2!!.text.toString()
                if (angleMode == 1) {
                    val angle = Math.toRadians(ExtendedDoubleEvaluator().evaluate(text))
                    when (toggleMode) {
                        1 -> edt_result_2!!.text = "tan($angle)"
                        2 -> edt_result_2!!.text = "atand($text)"
                        else -> edt_result_2!!.text = "tanh($text)"
                    }
                } else {
                    when (toggleMode) {
                        1 -> edt_result_2!!.text = "tan($text)"
                        2 -> edt_result_2!!.text = "atan($text)"
                        else -> edt_result_2!!.text = "tanh($text)"
                    }
                }
            }

            R.id.btn_posneg -> if (edt_result_2!!.length() != 0) {
                val s = edt_result_2!!.text.toString()
                val arr = s.toCharArray()
                if (arr[0] == '-')
                    edt_result_2!!.setText(s.substring(1, s.length))
                else
                    edt_result_2!!.setText("-$s")
            }

            R.id.btn_equal -> {

                if (edt_result_2!!.length() != 0) {
                    text = edt_result_2!!.text.toString()
                    expression = edt_result_1!!.text.toString() + text
                }
                edt_result_1!!.setText("")
                if (expression.isEmpty())
                    expression = "0.0"
                try {
                    //evaluate the expression
                    result = ExtendedDoubleEvaluator().evaluate(expression)
                    //insert expression and result in sqlite database if expression is valid and not 0.0
                    when {
                        result.toString() == "6.123233995736766E-17" -> {
                            result = 0.0
                            edt_result_2!!.setText(result!!.toString() + "")
                        }
                        result.toString() == "1.633123935319537E16" -> edt_result_2!!.setText("infinity")
                        else -> edt_result_2!!.setText(result!!.toString() + "")
                    }
                    if (expression != "0.0")
//                        dbHelper!!.insert("SCIENTIFIC", "$expression = $result")
                        Log.d("RESULT","del helper")
                } catch (e: Exception) {
                    edt_result_2!!.setText("Invalid Expression")
                    edt_result_1!!.setText("")
                    expression = ""
                    e.printStackTrace()
                }

            }

//            R.id.openBracket -> edt_result_1!!.setText(edt_result_1!!.text.toString() + "(")
//
//            R.id.closeBracket -> if (edt_result_2!!.length() != 0)
//                edt_result_1!!.setText(edt_result_1!!.text.toString() + edt_result_2!!.text.toString() + ")")
//            else
//                edt_result_1!!.setText(edt_result_1!!.text.toString() + ")")

        }
    }

    @SuppressLint("SetTextI18n")
    private fun operationClicked(op: String) {
        if (edt_result_2!!.length() != 0) {
            val text = edt_result_2!!.text.toString()
            edt_result_1!!.setText(edt_result_1!!.text.toString() + text + op)
            edt_result_2!!.setText("")
            count = 0
        } else {
            val text = edt_result_1!!.text.toString()
            if (text.isNotEmpty()) {
                val newText = text.substring(0, text.length - 1) + op
                edt_result_1!!.setText(newText)
            }
        }
    }
}