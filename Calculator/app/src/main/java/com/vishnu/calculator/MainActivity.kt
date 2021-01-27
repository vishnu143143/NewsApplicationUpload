package com.vishnu.calculator

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.view.View
import android.widget.Button
import android.widget.Toast
import kotlinx.android.synthetic.main.activity_main.*

class MainActivity : AppCompatActivity() {
    var lastNumeric=false
    var lastDot=false

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)
    }

    fun onDigit(view: View) {
        tvInput.append((view as Button).text)
        lastNumeric=true

    }
    fun onEqual(view:View){
        if(lastNumeric){
            var tvValue =tvInput.text.toString()
            var preFix=""



            try{
                if(tvValue.startsWith("-")){
                    preFix="-"
                    tvValue=tvValue.substring(1)

                }

                if(tvValue.contains("-")){
                    val splitValue=tvValue.split("-")
                    var one =splitValue[0]
                    var two =splitValue[1]
                    if(!preFix.isEmpty()){
                        one =preFix+one
                    }
                    tvInput.text=(one.toDouble()-two.toDouble()).toString()
                }

                    else if(tvValue.contains("/")){
                        val splitValue=tvValue.split("/")
                        var one =splitValue[0]
                        var two =splitValue[1]
                        if(!preFix.isEmpty()){
                            one =preFix+one
                        }
                        tvInput.text=(one.toDouble()/two.toDouble()).toString()
                    }


                    else if(tvValue.contains("+")){
                        val splitValue=tvValue.split("+")
                        var one =splitValue[0]
                        var two =splitValue[1]
                        if(!preFix.isEmpty()){
                            one =preFix+one
                        }
                        tvInput.text=(one.toDouble()+two.toDouble()).toString()
                    }


                    else if(tvValue.contains("*")){
                        val splitValue=tvValue.split("*")
                        var one =splitValue[0]
                        var two =splitValue[1]
                        if(!preFix.isEmpty()){
                            one =preFix+one
                        }
                        tvInput.text=(one.toDouble()*two.toDouble()).toString()
                    }

                else{

                }


            }
            catch (e:ArithmeticException){
                e.printStackTrace()

            }
        }
    }


    fun onClear(view: View) {
        tvInput.text=""
         lastNumeric=false
        lastDot=false
    }
    fun dotFun(view :View){
        if(lastNumeric && !lastDot){
            tvInput.append(".")
            lastNumeric=false
            lastDot=true

        }
    }

}
