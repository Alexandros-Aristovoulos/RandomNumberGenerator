package com.alex999ar.randomnumbergenerator

import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.Toast
import kotlinx.android.synthetic.main.fragment_coin.*
import kotlin.system.exitProcess

class CoinFragment : Fragment() {


    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
    }

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        // Inflate the layout for this fragment
        return inflater.inflate(R.layout.fragment_coin, container, false)
    }


    override fun onStart() {
        super.onStart()

        //default quantity = 1
        quantity_coinFragment_editTextNumber.setText("1")

        textView3.setOnClickListener {
            //list where the results will be stored
            var  coinTossList = mutableListOf<String>()
            //times we will flip the coin
            var quantity = quantity_coinFragment_editTextNumber.text.toString().toInt()

            //if quantity == 0 then don't do anything
            if(quantity == 0){
                val myToast = Toast.makeText(context,"You need at least 1 coin",Toast.LENGTH_SHORT)
                myToast.show()
                return@setOnClickListener
            }

            //add results to list
            for(i in 0 until quantity){
                coinTossList.add(coinToss())
            }
            //show that list
            textView3.text = coinTossList.toString()
        }

    }

    //calculate the result of a coin toss
    private fun coinToss():String{
        var number = MainActivity().rand(0, 1)
        var toss = "tails"

        if(number == 1){
            toss = "heads"
        }
        return toss;
    }

}