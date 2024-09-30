package com.st10079970.prixfinance

import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.Button

class BudgetManagementFragment : Fragment() {

    private lateinit var btnView : Button
    private lateinit var btnCreate : Button
    private lateinit var btnUpdate : Button
    private lateinit var btnDelete : Button

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        // Inflate the layout for this fragment
        val view = inflater.inflate(R.layout.fragment_budget_management, container, false)

        btnView = view.findViewById(R.id.btnViewBudgets)
        btnCreate = view.findViewById(R.id.btnCreateBudgets)
        btnUpdate = view.findViewById(R.id.btnUpdateBudgets)
        btnDelete = view.findViewById(R.id.btnDeleteBudgets)

        return view
    }

}