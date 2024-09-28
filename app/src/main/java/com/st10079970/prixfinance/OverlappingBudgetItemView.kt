package com.st10079970.prixfinance

import android.content.Context
import android.view.View
import android.widget.FrameLayout
import android.widget.TextView
import androidx.cardview.widget.CardView
import org.w3c.dom.Text

class OverlappingBudgetItemView(context: Context): FrameLayout(context)
{
    private lateinit var floater : CardView
    private lateinit var setBudgetName : TextView
    private lateinit var setBudgetBalance : TextView
    private lateinit var setBudgetStatus : TextView



    init {

        inflate(context, R.layout.floatingitem, this)

        floater = findViewById<CardView>(R.id.cardFloater)
        setBudgetName = findViewById<TextView>(R.id.floaterBudgetName)
        setBudgetBalance = findViewById<TextView>(R.id.floaterBudgetBalance)
        setBudgetStatus = findViewById<TextView>(R.id.floaterBudgetStatus)

        floater.post(){

        }
    }

    private fun reset(){
        floater.visibility =  View.GONE
    }

    //setting floater values
    fun setFloater(name:String, status:String, balance: Double){
        reset()
        setBudgetName.text = name
        setBudgetStatus.text = status
        setBudgetBalance.text = balance.toString()

    }

}