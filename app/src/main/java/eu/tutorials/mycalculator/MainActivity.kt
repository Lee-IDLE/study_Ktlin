package eu.tutorials.mycalculator

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.view.View
import android.widget.Button
import android.widget.TextView
import android.widget.Toast
import java.lang.ArithmeticException
import java.lang.Exception

class MainActivity : AppCompatActivity() {
    private var tvInput: TextView? = null;
    var lastNumeric: Boolean = false;
    var lastDot : Boolean = false;
    var inputedDot : Boolean = false;
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)

        tvInput = findViewById(R.id.tvInput)
    }

    fun onDigit(view: View){
        //Toast.makeText(this,"뭐라고?", Toast.LENGTH_SHORT).show()
        tvInput?.append((view as Button).text)
        lastNumeric = true
    }

    fun onClear(view: View){
        tvInput?.text = ""
        lastNumeric = true
        lastDot = false
        inputedDot = false
    }

    fun onDecimalPoint(view: View){
        if(lastNumeric == true && !lastDot && !inputedDot){
            tvInput?.append(".")
            lastNumeric == false
            lastDot = true
            inputedDot = true
        }
    }

    fun onOperator(view: View){
        tvInput?.text?.let{
            if(lastNumeric && !isOperatorAdded(it.toString())){
                tvInput?.append((view as Button).text)
                lastNumeric = false
                lastDot = false
            }
        }
    }

    private fun isOperatorAdded(value: String) : Boolean{
        return if(value.startsWith("-")){
            false
        }
        else{
            value.contains("/") || value.contains("&")
                    || value.contains("+") || value.contains("-")
        }
    }

    private fun removeZeroAfterDot(result: String): String{
        var value = result

        if(result.substring(result.length-2, 2).equals(".0")){
            value = result.substring(0, result.length-2)
        }

        return value
    }

    fun onEqual(view: View){
        if(lastNumeric){
            var tvValue = tvInput?.text.toString()
            var prefix = ""
            try{
                if(tvValue.startsWith("-")){
                    prefix = "-"
                    tvValue = tvValue.substring(1)
                }

                when{
                    tvValue.contains("/") -> {
                        val splitedValue = tvValue.split("/")

                        var one = splitedValue[0]
                        var two = splitedValue[1]

                        if(prefix.isNotEmpty()){
                            one = prefix + one
                        }

                        tvInput?.text = removeZeroAfterDot((one.toDouble() / two.toDouble()).toString())
                    }
                    tvValue.contains("*") -> {
                        val splitedValue = tvValue.split("*")

                        var one = splitedValue[0]
                        var two = splitedValue[1]

                        if(prefix.isNotEmpty()){
                            one = prefix + one
                        }

                        tvInput?.text = removeZeroAfterDot((one.toDouble() * two.toDouble()).toString())
                    }
                    tvValue.contains("-") -> {
                        val splitedValue = tvValue.split("-")

                        var one = splitedValue[0]
                        var two = splitedValue[1]

                        if(prefix.isNotEmpty()){
                            one = prefix + one
                        }

                        tvInput?.text = removeZeroAfterDot((one.toDouble() - two.toDouble()).toString())
                    }
                    tvValue.contains("+") -> {
                        val splitedValue = tvValue.split("+")

                        var one = splitedValue[0]
                        var two = splitedValue[1]

                        if(prefix.isNotEmpty()){
                            one = prefix + one
                        }

                        tvInput?.text = removeZeroAfterDot((one.toDouble() + two.toDouble()).toString())
                    }
                }
            }catch (e: ArithmeticException){
                e.printStackTrace()
            }
        }
    }
}