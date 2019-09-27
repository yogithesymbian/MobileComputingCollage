package com.scodeid.mobilecomputingcollage


import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.Button
import com.scodeid.common.debug
import kotlinx.android.synthetic.main.fragment_ordinary_calc.*
import kotlinx.android.synthetic.main.fragment_ordinary_calc.view.*
import net.objecthunter.exp4j.ExpressionBuilder


class OrdinaryCalcFragment : Fragment() {


    companion object{
        val TAG_LOG = OrdinaryCalcFragment::class.java.simpleName
        // Represent whether the lastly pressed key is numeric or not
        var lastNumeric: Boolean = false

        // Represent that current state is in error or not
        var stateError: Boolean = false

        // If true, do not allow to add another DOT
        var lastDot: Boolean = false

        var STATE_SCIENCE : Boolean = false
    }


    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        // Inflate the layout for this fragment
        val inflate =  inflater.inflate(R.layout.fragment_ordinary_calc, container, false)
        inflate.apply {

            // ORDINARY
            this.btn_9.setOnClickListener{onDigit(it)}
            this.btn_8.setOnClickListener{onDigit(it)}
            this.btn_7.setOnClickListener{onDigit(it)}
            this.btn_6.setOnClickListener{onDigit(it)}
            this.btn_5.setOnClickListener{onDigit(it)}
            this.btn_4.setOnClickListener{onDigit(it)}
            this.btn_3.setOnClickListener{onDigit(it)}
            this.btn_2.setOnClickListener{onDigit(it)}
            this.btn_1.setOnClickListener{onDigit(it)}
            this.btn_zero.setOnClickListener{onDigit(it)}



            this.btn_decimal.setOnClickListener{onDecimalPoint()}

            this.btn_divide.setOnClickListener{onOperator(it)}
            this.btn_multiply.setOnClickListener{onOperator(it)}
            this.btn_subtract.setOnClickListener{onOperator(it)}
            this.btn_add.setOnClickListener{onOperator(it)}

            this.btn_clear.setOnClickListener{onClear() }
            this.btn_equal.setOnClickListener{onEqual() }


            // SCIENCE
            this.btn_kur_1.setOnClickListener { onDigit(it) }
            this.btn_kur_2.setOnClickListener { onDigit(it) }

            this.btn_sin.setOnClickListener { onDigit(it) }
            this.btn_cos.setOnClickListener { onDigit(it) }
            this.btn_tan.setOnClickListener { onDigit(it) }

            this.btn_percent.setOnClickListener { onDigit(it) }

            this.btn_science.setOnClickListener{

                debug(TAG_LOG, "click btn science calculator mode")
                if (STATE_SCIENCE){
                    inflate.tbl_r_1.visibility = View.GONE
                    inflate.tbl_r_2.visibility = View.GONE
                    STATE_SCIENCE = false
                } else {
                    inflate.tbl_r_1.visibility = View.VISIBLE
                    inflate.tbl_r_2.visibility = View.VISIBLE
                    STATE_SCIENCE = true
                }
            }
        }

        return inflate
    }

    /**
     * Append the Button.text to the TextView
     */
    private fun onDigit(view: View) {
        if (stateError) {
            // If current state is Error, replace the error message
            txt_result.text = (view as Button).text
            stateError = false
        } else {
            // If not, already there is a valid expression so append to it
            txt_result.append((view as Button).text)
        }
        // Set the flag
        lastNumeric = true
    }

    /**
     * Append . to the TextView
     */
    private fun onDecimalPoint() {
        if (lastNumeric && !stateError && !lastDot) {
            txt_result.append(".")
            lastNumeric = false
            lastDot = true
        }
    }

    /**
     * Append +,-,*,/ operators to the TextView
     */
    private fun onOperator(view: View) {
        if (lastNumeric && !stateError) {
            txt_result.append((view as Button).text)
            lastNumeric = false
            lastDot = false    // Reset the DOT flag
        }
    }


    /**
     * Clear the TextView
     */
    private fun onClear() {
        this.txt_result.text = ""
        lastNumeric = false
        stateError = false
        lastDot = false
    }

    /**
     * Calculate the output using Exp4j
     */
    private fun onEqual() {
        // If the current state is error, nothing to do.
        // If the last input is a number only, solution can be found.
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
