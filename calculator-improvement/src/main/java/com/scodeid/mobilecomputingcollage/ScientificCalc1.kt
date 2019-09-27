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
import android.widget.Button
import android.widget.EditText
import androidx.appcompat.app.AppCompatActivity

class ScientificCal1 : AppCompatActivity() {

    private var e1: EditText? = null
    private var e2: EditText? = null
    private var count = 0
    private var expression = ""
    private var text = ""
    private var result: Double? = 0.0
//    private var dbHelper: DBHelper? = null

    private var mode: Button? = null
    private var toggle: Button? = null
    private var square: Button? = null
    private var xpowy: Button? = null
    private var log: Button? = null
    private var sin: Button? = null
    private var cos: Button? = null
    private var tan: Button? = null
    private var sqrt: Button? = null
    private var fact: Button? = null
    private var toggleMode = 1
    private var angleMode = 1

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.fragment_science_calc)
//        val toolbar = findViewById<View>(R.id.toolbar) as Toolbar
//        setSupportActionBar(toolbar)

        e1 = findViewById<View>(R.id.editText) as EditText
        e2 = findViewById<View>(R.id.editText2) as EditText
        mode = findViewById<View>(R.id.mode) as Button
        toggle = findViewById<View>(R.id.toggle) as Button
        square = findViewById<View>(R.id.square) as Button
        xpowy = findViewById<View>(R.id.xpowy) as Button
        log = findViewById<View>(R.id.log) as Button
        sin = findViewById<View>(R.id.sin) as Button
        cos = findViewById<View>(R.id.cos) as Button
        tan = findViewById<View>(R.id.tan) as Button
        sqrt = findViewById<View>(R.id.sqrt) as Button
        fact = findViewById<View>(R.id.factorial) as Button

//        dbHelper = DBHelper(this)

        e2!!.setText("0")

        //tags to change the mode from degree to radian and vice versa
        mode!!.tag = 1
        //tags to change the names of the buttons performing different operations
        toggle!!.tag = 1
    }

    @SuppressLint("SetTextI18n")
    fun onClick(v: View) {
        toggleMode = toggle!!.tag as Int
        angleMode = mode!!.tag as Int
        when (v.id) {

            R.id.toggle ->
                //change the button text if switch button is clicked
                when (toggleMode) {
                    1 -> {
                        toggle!!.tag = 2
                        square!!.setText(R.string.cube)
                        xpowy!!.setText(R.string.tenpow)
                        log!!.setText(R.string.naturalLog)
                        sin!!.setText(R.string.sininv)
                        cos!!.setText(R.string.cosinv)
                        tan!!.setText(R.string.taninv)
                        sqrt!!.setText(R.string.cuberoot)
                        fact!!.setText(R.string.Mod)
                    }
                    2 -> {
                        toggle!!.tag = 3
                        square!!.setText(R.string.square)
                        xpowy!!.setText(R.string.epown)
                        log!!.setText(R.string.log)
                        sin!!.setText(R.string.hyperbolicSine)
                        cos!!.setText(R.string.hyperbolicCosine)
                        tan!!.setText(R.string.hyperbolicTan)
                        sqrt!!.setText(R.string.inverse)
                        fact!!.setText(R.string.factorial)
                    }
                    3 -> {
                        toggle!!.tag = 1
                        sin!!.setText(R.string.sin)
                        cos!!.setText(R.string.cos)
                        tan!!.setText(R.string.tan)
                        sqrt!!.setText(R.string.sqrt)
                        xpowy!!.setText(R.string.xpown)
                    }
                }

            R.id.mode ->
                //change the angle property for trignometric operations if mode button is clicked
                if (angleMode == 1) {
                    mode!!.tag = 2
                    mode!!.setText(R.string.mode2)
                } else {
                    mode!!.tag = 1
                    mode!!.setText(R.string.mode1)
                }

            R.id.num0 -> e2!!.setText(e2!!.text.toString() + "0")

            R.id.num1 -> e2!!.setText(e2!!.text.toString() + "1")

            R.id.num2 -> e2!!.setText(e2!!.text.toString() + "2")

            R.id.num3 -> e2!!.setText(e2!!.text.toString() + "3")


            R.id.num4 -> e2!!.setText(e2!!.text.toString() + "4")

            R.id.num5 -> e2!!.setText(e2!!.text.toString() + "5")

            R.id.num6 -> e2!!.setText(e2!!.text.toString() + "6")

            R.id.num7 -> e2!!.setText(e2!!.text.toString() + "7")

            R.id.num8 -> e2!!.setText(e2!!.text.toString() + "8")

            R.id.num9 -> e2!!.setText(e2!!.text.toString() + "9")

            R.id.pi -> e2!!.setText(e2!!.text.toString() + "pi")

            R.id.dot -> if (count == 0 && e2!!.length() != 0) {
                e2!!.setText(e2!!.text.toString() + ".")
                count++
            }

            R.id.clear -> {
                e1!!.setText("")
                e2!!.setText("")
                count = 0
                expression = ""
            }

            R.id.backSpace -> {
                text = e2!!.text.toString()
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
                    //if e2 edit text contains only - sign or sqrt or any other text functions
                    // at last then clear the edit text e2
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
                    e2!!.setText(newText)
                }
            }

            R.id.plus -> operationClicked("+")

            R.id.minus -> operationClicked("-")

            R.id.divide -> operationClicked("/")

            R.id.multiply -> operationClicked("*")

            R.id.sqrt -> if (e2!!.length() != 0) {
                text = e2!!.text.toString()
                toggleMode = toggle!!.tag as Int
                when (toggleMode) {
                    1 -> e2!!.setText("sqrt($text)")
                    2 -> e2!!.setText("cbrt($text)")
                    else -> e2!!.setText("1/($text)")
                }
            }

            R.id.square -> if (e2!!.length() != 0) {
                text = e2!!.text.toString()
                if (toggleMode == 2)
                    e2!!.setText("($text)^3")
                else
                    e2!!.setText("($text)^2")
            }

            R.id.xpowy -> if (e2!!.length() != 0) {
                text = e2!!.text.toString()
                when (toggleMode) {
                    1 -> e2!!.setText("($text)^")
                    2 -> e2!!.setText("10^($text)")
                    else -> e2!!.setText("e^($text)")
                }
            }

            R.id.log -> if (e2!!.length() != 0) {
                text = e2!!.text.toString()
                if (toggleMode == 2)
                    e2!!.setText("ln($text)")
                else
                    e2!!.setText("log($text)")
            }

            R.id.factorial -> if (e2!!.length() != 0) {
                text = e2!!.text.toString()
                if (toggleMode == 2) {
                    e1!!.setText("($text)%")
                    e2!!.setText("")
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
                        e2!!.setText(res)
                    } catch (e: Exception) {
                        if (e.toString().contains("ArrayIndexOutOfBoundsException")) {
                            e2!!.setText("Result too big!")
                        } else
                            e2!!.setText("Invalid!!")
                        e.printStackTrace()
                    }

                }
            }

            R.id.sin -> if (e2!!.length() != 0) {
                text = e2!!.text.toString()
                if (angleMode == 1) {
                    val angle = Math.toRadians(ExtendedDoubleEvaluator().evaluate(text))
                    when (toggleMode) {
                        1 -> e2!!.setText("sin($angle)")
                        2 -> e2!!.setText("asind($text)")
                        else -> e2!!.setText("sinh($text)")
                    }
                } else {
                    when (toggleMode) {
                        1 -> e2!!.setText("sin($text)")
                        2 -> e2!!.setText("asin($text)")
                        else -> e2!!.setText("sinh($text)")
                    }
                }
            }

            R.id.cos -> if (e2!!.length() != 0) {
                text = e2!!.text.toString()
                if (angleMode == 1) {
                    val angle = Math.toRadians(ExtendedDoubleEvaluator().evaluate(text))
                    when (toggleMode) {
                        1 -> e2!!.setText("cos($angle)")
                        2 -> e2!!.setText("acosd($text)")
                        else -> e2!!.setText("cosh($text)")
                    }
                } else {
                    when (toggleMode) {
                        1 -> e2!!.setText("cos($text)")
                        2 -> e2!!.setText("acos($text)")
                        else -> e2!!.setText("cosh($text)")
                    }
                }
            }

            R.id.tan -> if (e2!!.length() != 0) {
                text = e2!!.text.toString()
                if (angleMode == 1) {
                    val angle = Math.toRadians(ExtendedDoubleEvaluator().evaluate(text))
                    when (toggleMode) {
                        1 -> e2!!.setText("tan($angle)")
                        2 -> e2!!.setText("atand($text)")
                        else -> e2!!.setText("tanh($text)")
                    }
                } else {
                    when (toggleMode) {
                        1 -> e2!!.setText("tan($text)")
                        2 -> e2!!.setText("atan($text)")
                        else -> e2!!.setText("tanh($text)")
                    }
                }
            }

            R.id.posneg -> if (e2!!.length() != 0) {
                val s = e2!!.text.toString()
                val arr = s.toCharArray()
                if (arr[0] == '-')
                    e2!!.setText(s.substring(1, s.length))
                else
                    e2!!.setText("-$s")
            }

            R.id.equal -> {

                if (e2!!.length() != 0) {
                    text = e2!!.text.toString()
                    expression = e1!!.text.toString() + text
                }
                e1!!.setText("")
                if (expression.isEmpty())
                    expression = "0.0"
                try {
                    //evaluate the expression
                    result = ExtendedDoubleEvaluator().evaluate(expression)
                    //insert expression and result in sqlite database if expression is valid and not 0.0
                    when {
                        result.toString() == "6.123233995736766E-17" -> {
                            result = 0.0
                            e2!!.setText(result!!.toString() + "")
                        }
                        result.toString() == "1.633123935319537E16" -> e2!!.setText("infinity")
                        else -> e2!!.setText(result!!.toString() + "")
                    }
                    if (expression != "0.0")
//                        dbHelper!!.insert("SCIENTIFIC", "$expression = $result")
                        Log.d("RESULT","del helper")
                } catch (e: Exception) {
                    e2!!.setText("Invalid Expression")
                    e1!!.setText("")
                    expression = ""
                    e.printStackTrace()
                }

            }

//            R.id.openBracket -> e1!!.setText(e1!!.text.toString() + "(")
//
//            R.id.closeBracket -> if (e2!!.length() != 0)
//                e1!!.setText(e1!!.text.toString() + e2!!.text.toString() + ")")
//            else
//                e1!!.setText(e1!!.text.toString() + ")")

        }
    }

    @SuppressLint("SetTextI18n")
    private fun operationClicked(op: String) {
        if (e2!!.length() != 0) {
            val text = e2!!.text.toString()
            e1!!.setText(e1!!.text.toString() + text + op)
            e2!!.setText("")
            count = 0
        } else {
            val text = e1!!.text.toString()
            if (text.isNotEmpty()) {
                val newText = text.substring(0, text.length - 1) + op
                e1!!.setText(newText)
            }
        }
    }
}