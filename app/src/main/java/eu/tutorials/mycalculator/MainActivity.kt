package eu.tutorials.mycalculator

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.view.View
import android.widget.Button
import android.widget.TextView
import android.widget.Toast

class MainActivity : AppCompatActivity() {
    private var tvInput: TextView? = null;

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)

        tvInput = findViewById(R.id.tvInput)
    }

    fun onDigit(view: View){
        //Toast.makeText(this,"뭐라고?", Toast.LENGTH_SHORT).show()
        tvInput?.append((view as Button).text)
    }

    fun onClear(view: View){
        tvInput?.text = ""
    }
}