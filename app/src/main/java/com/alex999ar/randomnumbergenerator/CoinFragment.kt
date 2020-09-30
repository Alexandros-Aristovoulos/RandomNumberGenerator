package com.alex999ar.randomnumbergenerator

import android.content.ClipData
import android.content.ClipboardManager
import android.content.Context
import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.Toast
import kotlinx.android.synthetic.main.fragment_coin.*

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

        flipCoin_fragmentCoin_textView.setOnClickListener {
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

            //check if user entered a way too big quantity
            if(quantity > 9999){
                Toast.makeText(context,"The quantity you entered is too big", Toast.LENGTH_SHORT).show()
                //reset quantity to 1
                quantity_coinFragment_editTextNumber.setText("1")
                return@setOnClickListener
            }


            //add results to list
            for(i in 0 until quantity){
                coinTossList.add(coinToss())
            }
            //show that list
            showCoin_fragmentCoin_textView.text = coinTossList.toString()
            //count heads and tails
            val heads = coinTossList.count { it == "heads" }.toString()
            showNumHeads_fragmentCoin_textView.text = heads
            val tails = coinTossList.count { it == "tails" }.toString()
            showNumTails_fragmentCoin_textView.text = tails
        }

        copy_fragmentCoin_textView.setOnClickListener {
            //if it is empty you don't copy anything
            if(showCoin_fragmentCoin_textView.text.isEmpty()){
                return@setOnClickListener
            }
            //code to copy the text
            val clipboard: ClipboardManager = activity?.getSystemService(Context.CLIPBOARD_SERVICE) as ClipboardManager
            val clip: ClipData = ClipData.newPlainText("Coins", showCoin_fragmentCoin_textView.text.toString())
            clipboard.setPrimaryClip(clip)
            //inform the user that they copied the text
            Toast.makeText(context,"Copied the coins", Toast.LENGTH_SHORT).show()
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