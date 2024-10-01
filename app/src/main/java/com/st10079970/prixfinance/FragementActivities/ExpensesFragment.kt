package com.st10079970.prixfinance.FragementActivities

import android.app.DatePickerDialog
import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.ArrayAdapter
import android.widget.Button
import android.widget.ListView
import android.widget.TextView
import android.widget.Toast
import androidx.appcompat.app.AlertDialog
import androidx.fragment.app.Fragment
import android.widget.EditText
import com.st10079970.prixfinance.Api.Models.Transaction
import com.st10079970.prixfinance.Api.Services.TransactionCreateDto
import com.st10079970.prixfinance.Api.Services.TransactionsApiService
import com.st10079970.prixfinance.FragementActivities.LoginTabFragment.userGuid
import com.st10079970.prixfinance.R
import retrofit2.Call
import retrofit2.Callback
import retrofit2.Response
import java.text.SimpleDateFormat
import java.util.*

class ExpensesFragment : Fragment() {

    private lateinit var transactionsApiService: TransactionsApiService
    private var userId: UUID = userGuid
    private var totalIncome: Double = 10000.0

    private lateinit var tvExpenses: TextView
    private lateinit var lvTransactions: ListView

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
    }

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        val view = inflater.inflate(R.layout.fragment_transactions, container, false)

        val btnExpenses = view.findViewById<Button>(R.id.btnExpenses)
        lvTransactions = view.findViewById<ListView>(R.id.list_transactions)

        btnExpenses.setOnClickListener {
            showExpenseDialog()
        }

        // Initialize ListView adapter
        updateListView()

        return view
    }

    private fun showExpenseDialog() {
        val dialogView = layoutInflater.inflate(R.layout.dialog_add_expense, null)
        val etExpenseName = dialogView.findViewById<EditText>(R.id.etExpenseName)
        val etExpenseAmount = dialogView.findViewById<EditText>(R.id.etExpenseAmount)
        val etExpenseLocation = dialogView.findViewById<EditText>(R.id.etExpenseLocation)
        val etExpenseDate = dialogView.findViewById<EditText>(R.id.etExpenseDate)

        etExpenseDate.setOnClickListener {
            showDatePicker(etExpenseDate)
        }

        val dialog = AlertDialog.Builder(requireContext())
            .setTitle("Add Expense")
            .setView(dialogView)
            .setPositiveButton("Add") { dialog, _ ->
                val expenseName = etExpenseName.text.toString()
                val expenseAmountText = etExpenseAmount.text.toString()
                val expenseLocation = etExpenseLocation.text.toString()
                val expenseDate = etExpenseDate.text.toString()

                if (expenseName.isNotEmpty() && expenseAmountText.isNotEmpty() && expenseLocation.isNotEmpty() && expenseDate.isNotEmpty()) {
                    try {
                        val expenseAmount = expenseAmountText.toDouble()
                        if (expenseAmount <= totalIncome) {
                            addExpense(expenseName, expenseLocation, expenseDate, expenseAmount)
                        } else {
                            Toast.makeText(requireContext(), "Insufficient income", Toast.LENGTH_SHORT).show()
                        }
                    } catch (e: NumberFormatException) {
                        Toast.makeText(requireContext(), "Invalid amount", Toast.LENGTH_SHORT).show()
                    }
                } else {
                    Toast.makeText(requireContext(), "All fields are required", Toast.LENGTH_SHORT).show()
                }
                dialog.dismiss()
            }
            .setNegativeButton("Cancel") { dialog, _ ->
                dialog.dismiss()
            }
            .create()

        dialog.show()
    }

    private fun addExpense(name: String, location: String, date: String, amount: Double) {
        totalIncome -= amount

        val transactionCreateDto = TransactionCreateDto(userId, amount, location, name)
        transactionsApiService.createTransaction(transactionCreateDto).enqueue(object : Callback<Transaction> {
            override fun onResponse(call: Call<Transaction>, response: Response<Transaction>) {
                if (response.isSuccessful) {
                    val transaction = response.body()!!
                    updateExpensesDisplay(transaction)
                    updateListView()
                    Toast.makeText(requireContext(), "Expense added successfully", Toast.LENGTH_SHORT).show()
                } else {
                    Toast.makeText(requireContext(), "Failed to add expense", Toast.LENGTH_SHORT).show()
                }
            }

            override fun onFailure(call: Call<Transaction>, t: Throwable) {
                Toast.makeText(requireContext(), "Error adding expense", Toast.LENGTH_SHORT).show()
            }
        })
    }

    private fun updateExpensesDisplay(transaction: Transaction) {
        tvExpenses.text = """
            ${transaction.user.userId}                     R ${transaction.amount}
            ${transaction.category}                 ${SimpleDateFormat("yyyy-MM-dd").format(transaction.date)}
                                                R ${totalIncome}
        """.trimIndent()
    }

    private fun updateListView() {
        transactionsApiService.getTransactions().enqueue(object : Callback<List<Transaction>> {
            override fun onResponse(call: Call<List<Transaction>>, response: Response<List<Transaction>>) {
                if (response.isSuccessful) {
                    val allTransactions = response.body() ?: emptyList()

                    // Filter transactions based on the global user ID
                    val userTransactions = allTransactions.filter { it.userId == userId }

                    val adapter = ArrayAdapter(requireContext(), android.R.layout.simple_list_item_1, userTransactions.map { "${it.category}: R${it.amount}" })
                    lvTransactions.adapter = adapter
                } else {
                    Toast.makeText(requireContext(), "Failed to fetch transactions", Toast.LENGTH_SHORT).show()
                }
            }

            override fun onFailure(call: Call<List<Transaction>>, t: Throwable) {
                Toast.makeText(requireContext(), "Error fetching transactions", Toast.LENGTH_SHORT).show()
            }
        })
    }

    private fun showDatePicker(etExpenseDate: EditText) {
        val calendar = Calendar.getInstance()
        val year = calendar.get(Calendar.YEAR)
        val month = calendar.get(Calendar.MONTH)
        val day = calendar.get(Calendar.DAY_OF_MONTH)

        val datePickerDialog = DatePickerDialog(requireContext(), { _, selectedYear, selectedMonth, selectedDay ->
            val formattedDate = SimpleDateFormat("yyyy-MM-dd", Locale.getDefault()).format(
                GregorianCalendar(selectedYear, selectedMonth, selectedDay).time
            )
            etExpenseDate.setText(formattedDate)
        }, year, month, day)

        datePickerDialog.show()
    }
}
