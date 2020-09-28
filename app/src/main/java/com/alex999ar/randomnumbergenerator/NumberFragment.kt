package com.alex999ar.randomnumbergenerator

import android.content.ClipData
import android.content.ClipboardManager
import android.content.Context
import android.content.Context.CLIPBOARD_SERVICE
import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.Toast
import androidx.core.content.ContextCompat.getSystemService
import kotlinx.android.synthetic.main.fragment_number.*

class NumberFragment : Fragment() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
    }

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        // Inflate the layout for this fragment
        return inflater.inflate(R.layout.fragment_number, container, false)
    }


    override fun onStart() {
        super.onStart()

        //default quantity = 1
        quantity_numberFragment_editTextNumber.setText("1")
        from_numberFragment_editTextNumber.setText("0")
        to_numberFragment_editTextNumber.setText("10")

        generateNumbers_fragmentNumber_textView.setOnClickListener {
            //list where the results will be stored
            var  numberList = mutableListOf<Int>()
            //times we will flip the coin
            var quantity = quantity_numberFragment_editTextNumber.text.toString().toInt()
            //get from
            var from = from_numberFragment_editTextNumber.text.toString().toInt()
            //get to
            var to = to_numberFragment_editTextNumber.text.toString().toInt()

            //if quantity == 0 then don't do anything
            if(quantity == 0){
                val myToast = Toast.makeText(context,"You need at least 1 number", Toast.LENGTH_SHORT)
                myToast.show()
                return@setOnClickListener
            }

            if(from > to){
                Toast.makeText(context,"From needs to be smaller than to", Toast.LENGTH_SHORT).show()
                return@setOnClickListener
            }

            //add results to list
            for(i in 0 until quantity){
                numberList.add(MainActivity().rand(from, to))
            }
            //show that list
            showNumbers_fragmentNumber_textView.text = numberList.toString()
        }

        copy_fragmentNumber_textView.setOnClickListener {
            //if it is empty you don't copy anything
            if(showNumbers_fragmentNumber_textView.text.isEmpty()){
                return@setOnClickListener
            }
            //code to copy the text
            val clipboard: ClipboardManager = activity?.getSystemService(CLIPBOARD_SERVICE) as ClipboardManager
            val clip: ClipData = ClipData.newPlainText("Numbers", showNumbers_fragmentNumber_textView.text.toString())
            clipboard.setPrimaryClip(clip)
            //inform the user that they copied the text
            Toast.makeText(context,"Copied the numbers", Toast.LENGTH_SHORT).show()
        }

    }



}