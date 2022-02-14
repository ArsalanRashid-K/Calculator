package com.arsalan.calculator

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.view.View
import android.widget.Button
import android.widget.TextView
import android.widget.Toast
import java.lang.ArithmeticException

class MainActivity : AppCompatActivity() {

    private var tvInput: TextView? = null
    var lastNumeric :Boolean= false
    var lastDot :Boolean= false
    // we are passing lastNumeric lastDot . these are noting just false values
    // as if else statement always take true value in .. when this goes into it if .. wont run ..then it goes into number .. which changes the value value to true .




    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)

        tvInput = findViewById(R.id.tvInput)
//        val btnSeven= findViewById<Button>(R.id.btnSeven)
//        btnSeven?.setOnClickListener{
//            Toast.makeText(this , "buthuhuhuhton clicked ", Toast.LENGTH_SHORT).show()
//        tvInput>.append("7")
//        }


    }

    fun onDigit(view: View) {
        tvInput?.append((view as Button).text)
        // view or t(vw: View) (vw a s Button) ...
        //view is taking the View the button so , we cant do view.text since it does not have it text property but button has tha t
        //setText delete the previous and prints the new value
        // append keeps the privious data and prints along with it

        lastNumeric=true
        lastDot=false



    }
    fun onClear(view:View){
        tvInput?.text=""
        // the data is flowing from here to  android:onClick="onClear"...
        //than after being clicked it run .text
    }
    fun onDecimlaPoint(view: View){
        if(lastNumeric && !lastDot){
            tvInput?.append(".")
            lastNumeric=false
            lastDot=true
        }
    }
    fun onOperator(view: View){
        tvInput?.text?.let {

            if (lastNumeric && !isOPeratorsAdded(it.toString())){
                tvInput?.append((view as Button).text)
                lastNumeric=false
                lastDot=false
            }

        }
    }

    fun onEqual(view: View){
        if(lastNumeric){
            var tvValue= tvInput?.text.toString()
            var prefix=""

            try {
                if (tvValue.startsWith("-")){
                    prefix="-"
                    tvValue=tvValue.substring(1)
                }
                if(tvValue.contains("-")) {
                    val splitValue = tvValue.split("-")
                    var one = splitValue[0]
                    var two = splitValue[1]

                    if (prefix.isNotEmpty()) {
                        one = prefix + one
                    }

                    tvInput?.text =  removeZeroAfterDot( (one.toDouble() - two.toDouble()).toString())
                } else if(tvValue.contains("+")) {
                    val splitValue = tvValue.split("+")
                    var one = splitValue[0]
                    var two = splitValue[1]

                    if (prefix.isNotEmpty()) {
                        one = prefix + one
                    }

                    tvInput?.text = removeZeroAfterDot( (one.toDouble() + two.toDouble()).toString())
                } else if(tvValue.contains("*")) {
                    val splitValue = tvValue.split("*")
                    var one = splitValue[0]
                    var two = splitValue[1]

                    if (prefix.isNotEmpty()) {
                        one = prefix + one
                    }

                    tvInput?.text =  removeZeroAfterDot( (one.toDouble() * two.toDouble()).toString())
                } else if(tvValue.contains("/")) {
                    val splitValue = tvValue.split("/")
                    var one = splitValue[0]
                    var two = splitValue[1]

                    if (prefix.isNotEmpty()) {
                        one = prefix + one
                    }

                    tvInput?.text = removeZeroAfterDot( (one.toDouble() / two.toDouble()).toString())
                }

            }catch (e:ArithmeticException){
                e.printStackTrace()
            }
        }
    }
    private fun removeZeroAfterDot(result: String):String{
        var value=result
        if(result.contains("0"))
            value=result.substring(0,result.length-2)
        return value
    }


    private fun isOPeratorsAdded(value:String):Boolean{
        return if(value.startsWith("-")){
            false
        }else{
            value.contains("/") || value.contains("*") || value.contains("+") ||value.contains("-")
        }
    }
}