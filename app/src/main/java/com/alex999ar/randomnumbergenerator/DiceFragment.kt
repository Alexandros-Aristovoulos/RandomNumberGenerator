package com.alex999ar.randomnumbergenerator

import android.content.ClipData
import android.content.ClipboardManager
import android.content.Context
import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.RadioButton
import android.widget.Toast
import kotlinx.android.synthetic.main.fragment_dice.*


class DiceFragment : Fragment() {

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
    }

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        // Inflate the layout for this fragment
        return inflater.inflate(R.layout.fragment_dice, container, false)
    }

    override fun onStart() {
        super.onStart()

        //default quantity = 1
        quantity_diceFragment_editTextNumber.setText("1")

        tossDice_fragmentDice_textView.setOnClickListener {
            //times we will toss the dice
            var quantity = quantity_diceFragment_editTextNumber.text.toString().toInt()

            //if quantity == 0 then don't do anything
            if(quantity == 0){
                val myToast = Toast.makeText(context,"You need at least 1 dice", Toast.LENGTH_SHORT)
                myToast.show()
                return@setOnClickListener
            }

            //check if user entered a way too big quantity
            if(quantity > 9999){
                Toast.makeText(context,"The quantity you entered is too big", Toast.LENGTH_SHORT).show()
                //reset quantity to 1
                quantity_diceFragment_editTextNumber.setText("1")
                return@setOnClickListener
            }

            val sides = getSides()
            if(sides == -1){
                Toast.makeText(context,"You need to select the sides of the dice", Toast.LENGTH_SHORT).show()
                return@setOnClickListener
            }

            //list where the results will be stored
            var  diceTossList = mutableListOf<Int>()

            //add results to list
            for(i in 0 until quantity){
                diceTossList.add(diceToss(sides))
            }
            //show that list
            showDice_fragmentDice_textView.text = diceTossList.toString()
            //calculate and show the sum
            val sum = diceTossList.sum()
            showTotalSum_fragmentDice_textView.text = sum.toString()
        }

        copyDice_fragmentDice_textView.setOnClickListener {
            //if it is empty you don't copy anything
            if(showDice_fragmentDice_textView.text.isEmpty()){
                return@setOnClickListener
            }
            //code to copy the text
            val clipboard: ClipboardManager = activity?.getSystemService(Context.CLIPBOARD_SERVICE) as ClipboardManager
            val clip: ClipData = ClipData.newPlainText("Dice", showDice_fragmentDice_textView.text.toString())
            clipboard.setPrimaryClip(clip)
            //inform the user that they copied the text
            Toast.makeText(context,"Copied the dice", Toast.LENGTH_SHORT).show()
        }

    }

    private fun diceToss(sides:Int):Int{
        return MainActivity().rand(0, sides)
    }

    private fun getSides():Int{
        //get the number of sides for the dice
        var id: Int = sides_diceFragment_radioGroup.checkedRadioButtonId
        var sides: Int

        sides = if (id!=-1){
            // If any radio button checked from radio group
            // Get the instance of radio button using id
            val radio: RadioButton = activity?.findViewById(id)!!
            radio.text.toString().toInt()
        }else{
            // If no radio button checked in this radio group
            -1
        }
        return sides
    }

}