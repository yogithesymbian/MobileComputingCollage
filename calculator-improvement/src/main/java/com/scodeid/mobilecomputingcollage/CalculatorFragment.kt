package com.scodeid.mobilecomputingcollage

import android.annotation.SuppressLint
import android.os.Bundle
import android.util.Log
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.Button
import com.scodeid.common.debug
import com.scodeid.mobilecomputingcollage.formula.CalculateFactorial
import com.scodeid.mobilecomputingcollage.formula.ExtendedDoubleEvaluator

import kotlinx.android.synthetic.main.fragment_calculator.*
import kotlinx.android.synthetic.main.fragment_calculator.edt_result_2
import kotlinx.android.synthetic.main.fragment_calculator.view.*
import kotlinx.android.synthetic.main.fragment_calculator.edt_result_1
import kotlinx.android.synthetic.main.fragment_calculator.view.btn_clear
import kotlinx.android.synthetic.main.fragment_calculator.view.btn_cos
import kotlinx.android.synthetic.main.fragment_calculator.view.btn_divide
import kotlinx.android.synthetic.main.fragment_calculator.view.btn_equal
import kotlinx.android.synthetic.main.fragment_calculator.view.btn_log
import kotlinx.android.synthetic.main.fragment_calculator.view.btn_multiply
import kotlinx.android.synthetic.main.fragment_calculator.view.btn_sin
import kotlinx.android.synthetic.main.fragment_calculator.view.btn_sqrt
import kotlinx.android.synthetic.main.fragment_calculator.view.btn_square
import kotlinx.android.synthetic.main.fragment_calculator.view.btn_tan
import kotlinx.android.synthetic.main.fragment_calculator.view.edt_result_1
import kotlinx.android.synthetic.main.fragment_calculator.view.edt_result_2
import net.objecthunter.exp4j.ExpressionBuilder

class CalculatorFragment : Fragment() {


    companion object {
        val TAG_LOG = CalculatorFragment::class.java.simpleName
        // Represent whether the lastly pressed key is numeric or not
        var lastNumeric: Boolean = false

        // Represent that current state is in error or not
        var stateError: Boolean = false

        // If true, do not allow to add another DOT
        var lastDot: Boolean = false

        var STATE_SCIENCE: Boolean = false
        private var count = 0
        private var text = ""
        private var expression = ""
        private var result: Double? = 0.0
        private var toggleMode = 1
        private var angleMode = 1
    }


    @SuppressLint("SetTextI18n")
    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        // Inflate the layout for this fragment
        val inflate = inflater.inflate(R.layout.fragment_calculator, container, false)

        // DEGREE AND RADIAN
        inflate.btn_deg.tag = 1
        inflate.apply {


            // ORDINARY
            this.btn_9.setOnClickListener { onDigit(it) }
            this.btn_8.setOnClickListener { onDigit(it) }
            this.btn_7.setOnClickListener { onDigit(it) }
            this.btn_6.setOnClickListener { onDigit(it) }
            this.btn_5.setOnClickListener { onDigit(it) }
            this.btn_4.setOnClickListener { onDigit(it) }
            this.btn_3.setOnClickListener { onDigit(it) }
            this.btn_2.setOnClickListener { onDigit(it) }
            this.btn_1.setOnClickListener { onDigit(it) }
            this.btn_zero.setOnClickListener { onDigit(it) }

            this.btn_decimal.setOnClickListener { onDecimalPoint() }

            this.btn_divide.setOnClickListener { onOperator(it) }
            this.btn_multiply.setOnClickListener { onOperator(it) }
            this.btn_subtract.setOnClickListener { onOperator(it) }
            this.btn_add.setOnClickListener { onOperator(it) }

            this.btn_clear.setOnClickListener { onClear() }
            this.btn_equal.setOnClickListener { onEqual() }

            // SCIENCE
            this.btn_kur_1.setOnClickListener { onDigit(it) }
            this.btn_kur_2.setOnClickListener { onDigit(it) }

            this.btn_sin.setOnClickListener {
                sinOperation()
            }
            this.btn_cos.setOnClickListener {
                cosOperation()
            }
            this.btn_tan.setOnClickListener {
                tanOperation()
            }

            this.btn_percent.setOnClickListener {
                text = this.edt_result_2?.text.toString() //5
                //this.edt_result_1?.text = "($text)%" // 5%
                val percent = (text.toInt() / 100.0f)
                this.edt_result_2?.text = "$percent"
            }
            this.btn_log.setOnClickListener{
                text = edt_result_2?.text.toString()
                this.edt_result_2?.text = "log($text)"
                debug(TAG_LOG,"$it")
            }
            // bottom
            this.btn_epown.setOnClickListener {
                text = this.edt_result_2?.text.toString()
                this.edt_result_2?.text = "e^($text)"
            }
            this.btn_phi.setOnClickListener{
                text = this.edt_result_2?.text.toString()
                this.edt_result_2?.text = this.edt_result_2?.text.toString() + "pi"
            }
            this.btn_factorial.setOnClickListener {
                factorialOpr()
            }
            this.btn_natural_log.setOnClickListener {
                text = this.edt_result_2?.text.toString()
                this.edt_result_2?.text = "ln($text)"
            }
            this.btn_sqrt.setOnClickListener {
                text = this.edt_result_2?.text.toString()
                this.edt_result_2?.text = "sqrt($text)"
            }
            this.btn_xpown.setOnClickListener {
                text = this.edt_result_2?.text.toString()
                this.edt_result_2?.text = "($text)^"
            }
            this.btn_square.setOnClickListener {
                text = this.edt_result_2?.text.toString()
                this.edt_result_2?.text = "($text)^2"
            }
            this.btn_inv.setOnClickListener {
                text = this.edt_result_2?.text.toString()
                this.edt_result_2?.text = "inv($text)"
            }


            this.btn_deg.setOnClickListener {

                angleMode = this.btn_deg?.tag as Int
                if (angleMode == 1) {
                    this.btn_deg?.tag = 2
                    this.btn_deg?.setText(R.string.mode2)
                } else {
                    this.btn_deg?.tag = 1
                    this.btn_deg?.setText(R.string.mode1)
                }
            }
            this.btn_back_space.setOnClickListener {
                backSpaceClearTxt()
            }

            this.btn_science.setOnClickListener {

                debug(TAG_LOG, "click btn science calculator mode")
                if (STATE_SCIENCE) {
                    // ordinary mode
                    inflate.tbl_r_1.visibility = View.GONE
                    inflate.tbl_r_2.visibility = View.GONE

                    inflate.txt_result.visibility = View.VISIBLE
                    inflate.edt_result_1.visibility = View.INVISIBLE
                    inflate.edt_result_2.visibility = View.GONE

                    inflate.btn_epown.visibility = View.GONE
                    inflate.btn_phi.visibility = View.GONE
                    inflate.btn_inv.visibility = View.GONE

                    inflate.btn_square.visibility = View.GONE
                    inflate.btn_xpown.visibility = View.GONE
                    inflate.btn_sqrt.visibility = View.GONE
                    inflate.btn_xpown.visibility = View.GONE
                    inflate.btn_natural_log.visibility = View.GONE

                    STATE_SCIENCE = false
                } else {
                    // science mode
                    inflate.tbl_r_1.visibility = View.VISIBLE
                    inflate.tbl_r_2.visibility = View.VISIBLE

                    inflate.txt_result.visibility = View.GONE
                    inflate.edt_result_1.visibility = View.VISIBLE
                    inflate.edt_result_2.visibility = View.VISIBLE

                    inflate.btn_epown.visibility = View.VISIBLE
                    inflate.btn_phi.visibility = View.VISIBLE
                    inflate.btn_inv.visibility = View.VISIBLE

                    inflate.btn_square.visibility = View.VISIBLE
                    inflate.btn_sqrt.visibility = View.VISIBLE
                    inflate.btn_xpown.visibility = View.VISIBLE
                    inflate.btn_natural_log.visibility = View.VISIBLE

                    STATE_SCIENCE = true
                }
            }
        }
        return inflate
    }

    private fun backSpaceClearTxt() {
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

    private fun factorialOpr() {
        text = edt_result_2?.text.toString()
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
            edt_result_2?.text = res
        } catch (e: Exception) {
            if (e.toString().contains("ArrayIndexOutOfBoundsException")) {
                edt_result_2?.text = getString(R.string.ordinary_calc_fragment_factorial_big)
            } else
                edt_result_2?.text = getString(R.string.ordinary_calc_fragment_factorial_invalid)
            e.printStackTrace()
        }
    }

    @SuppressLint("SetTextI18n")
    private fun tanOperation() {
        text = edt_result_2?.text.toString()
        if (angleMode == 1) {
            val angle = Math.toRadians(ExtendedDoubleEvaluator().evaluate(text))
            when (toggleMode) {
                1 -> edt_result_2?.text = "tan($angle)"
                2 -> edt_result_2?.text = "atand($text)" //later's
                else -> edt_result_2?.text = "tanh($text)" //later's
            }
        } else {
            when (toggleMode) {
                1 -> edt_result_2?.text = "tan($text)"
                2 -> edt_result_2?.text = "atan($text)" //later's
                else -> edt_result_2?.text = "tanh($text)" //later's
            }
        }
    }

    @SuppressLint("SetTextI18n")
    private fun cosOperation() {
        text = edt_result_2?.text.toString()
        if (angleMode == 1) {
            val angle = Math.toRadians(ExtendedDoubleEvaluator().evaluate(text))
            when (toggleMode) {
                1 -> edt_result_2?.text = "cos($angle)"
                2 -> edt_result_2?.text = "acosd($text)"
                else -> edt_result_2?.text = "cosh($text)"
            }
        } else {
            when (toggleMode) {
                1 -> edt_result_2?.text = "cos($text)"
                2 -> edt_result_2?.text = "acos($text)"
                else -> edt_result_2?.text = "cosh($text)"
            }
        }
    }

    @SuppressLint("SetTextI18n")
    private fun sinOperation() {
        text = edt_result_2?.text.toString()
        if (angleMode == 1) {
            val angle = Math.toRadians(ExtendedDoubleEvaluator().evaluate(text))
            when (toggleMode) {
                1 -> edt_result_2?.text = "sin($angle)"
                2 -> edt_result_2?.text = "asind($text)"
                else -> edt_result_2?.text = "sinh($text)"
            }
        } else {
            when (toggleMode) {
                1 -> edt_result_2?.text = "sin($text)"
                2 -> edt_result_2?.text = "asin($text)"
                else -> edt_result_2?.text = "sinh($text)"
            }
        }
    }

    /**
     * Append the Button.text to the TextView
     * added state science mode
     */
    private fun onDigit(view: View) {
        if (STATE_SCIENCE) {
            if (stateError) {
                // If current state is Error, replace the error message
                edt_result_2.text = (view as Button).text
                stateError = false
            } else {
                // If not, already there is a valid expression so append to it
                edt_result_2.append((view as Button).text)
            }

        } else {
            if (stateError) {
                // If current state is Error, replace the error message
                txt_result.text = (view as Button).text
                stateError = false
            } else {
                // If not, already there is a valid expression so append to it
                txt_result.append((view as Button).text)
            }
        }
        // Set the flag
        lastNumeric = true
    }

    /**
     * Append . to the TextView
     * added scientific state check mode
     */
    private fun onDecimalPoint() {
        if (lastNumeric && !stateError && !lastDot) {
            if (STATE_SCIENCE) {
                edt_result_2.append(".")
            } else txt_result.append(".")
            lastNumeric = false
            lastDot = true
        }
    }

    /**
     * Append +,-,*,/ operators to the TextView
     * add scientific mode and advance operation
     */
    private fun onOperator(view: View) {
        if (lastNumeric && !stateError) {
            if (STATE_SCIENCE) {
                operationClicked((view as Button).text)
            } else {
                txt_result.append((view as Button).text)
            }
            lastNumeric = false
            lastDot = false    // Reset the DOT flag
        }
    }

    @SuppressLint("SetTextI18n")
    private fun operationClicked(op: CharSequence) {
        if (edt_result_2?.length() != 0) {
            val text = edt_result_2?.text.toString()
            edt_result_1?.text = edt_result_1?.text.toString() + text + op
            edt_result_2?.text = ""
            count = 0
        } else {
            val text = edt_result_1?.text.toString()
            if (text.isNotEmpty()) {
                val newText = text.substring(0, text.length - 1) + op
                edt_result_1?.text = newText
            }
        }
    }


    /**
     * Clear the TextView
     * added scientific mode
     */
    private fun onClear() {
        if (STATE_SCIENCE){
            this.edt_result_2.text = ""
            this.edt_result_1.text = ""
        }
        else
            this.txt_result.text = ""
        lastNumeric = false
        stateError = false
        lastDot = false
    }

    /**
     * Calculate the output using Exp4j
     * added scientific check state mode
     */
    @SuppressLint("SetTextI18n")
    private fun onEqual() {
        debug(TAG_LOG,"onEqual has clicked")
        // If the current state is error, nothing to do.
        // If the last input is a number only, solution can be found.

            if (STATE_SCIENCE) {
                if (edt_result_2?.length() != 0) {
                    text = edt_result_2?.text.toString()
                    expression = edt_result_1?.text.toString() + text
                    debug(TAG_LOG,"""
                        
                        text = $text
                        expression = $expression
                    """.trimIndent())
                }
                edt_result_1?.text = ""
                debug(TAG_LOG,"empty the edt_result_1")
                if (expression.isEmpty())
                    expression = "0.0"
                try {
                    debug(TAG_LOG,"evaluate the expression in try and catch")
                    //evaluate the expression
                    result = ExtendedDoubleEvaluator().evaluate(expression)
                    debug(TAG_LOG,"result = $result")
                    //insert expression and result in sqlite database if expression is valid and not 0.0
                    when {
                        result.toString() == "6.123233995736766E-17" -> {
                            result = 0.0
                            edt_result_2?.text = result?.toString() + ""
                        }
                        result.toString() == "1.633123935319537E16" -> edt_result_2?.text = "infinity"
                        else -> edt_result_2?.text = result?.toString() + ""
                    }
                    if (expression != "0.0")
//                        dbHelper?.insert("SCIENTIFIC", "$expression = $result")
                        Log.d(TAG_LOG, "del helper")
                } catch (e: Exception) {
                    edt_result_2?.text = "Invalid Expression"
                    edt_result_1?.text = ""
                    expression = ""
                    e.printStackTrace()
                    debug(TAG_LOG, e.printStackTrace().toString())
                }
            } else {
                if (lastNumeric && !stateError) {
                    // Read the expression
                    val txt = txt_result.text.toString()
                    // Create an Expression (A class from exp4j library)
                    val expression = ExpressionBuilder(txt).build()
                    try {
                        // Calculate the result and display
                        val result = expression.evaluate()
                        txt_result.text = result.toString()
                        lastDot = true // Result contains a dot
                    } catch (ex: ArithmeticException) {
                        // Display an error message
                        txt_result.text = getString(R.string.ordinary_calc_fragment_error)
                        stateError = true
                        lastNumeric = false
                    }
                }
            }
    }


}
