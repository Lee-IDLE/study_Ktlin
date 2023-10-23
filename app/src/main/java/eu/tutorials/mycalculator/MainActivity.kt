package eu.tutorials.mycalculator

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.view.View
import android.widget.Button
import android.widget.TextView
import android.widget.Toast

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
        tvInput?.text = "0"
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
}