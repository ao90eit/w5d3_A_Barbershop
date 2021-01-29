package com.aoinc.w5d3_a_barbershop.view

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.Button
import android.widget.EditText
import android.widget.Toast
import androidx.fragment.app.Fragment
import com.aoinc.w5d3_a_barbershop.R
import com.aoinc.w5d3_a_barbershop.data.Customer

class AddCustomerFragment : Fragment() {

    private lateinit var nameInput: EditText
    private lateinit var timeInput: EditText
    private lateinit var addButton: Button
    private lateinit var addBatchButton: Button

    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? =
        inflater.inflate(R.layout.add_customer_fragment, container, false)

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        nameInput = view.findViewById(R.id.customer_name_input_editText)
        timeInput = view.findViewById(R.id.customer_time_input_editText)
        addButton = view.findViewById(R.id.add_customer_button)
        addBatchButton = view.findViewById(R.id.add_batch_button)

        addButton.setOnClickListener {
            (context as MainActivity).let {
                val name = nameInput.text.toString().trim()
                val time = timeInput.text.toString().trim()

                if (!name.isNullOrBlank() && !time.isNullOrBlank()){
                    it.addSingleCustomer(name, time.toInt())
                    nameInput.text.clear()
                    timeInput.text.clear()
                    it.closeAddFragment()
                } else {
                    Toast.makeText(context, "fill in blanks", Toast.LENGTH_SHORT).show()
                }
            }
        }

        addBatchButton.setOnClickListener {
            (context as MainActivity).let {
                it.addCustomerBatch()
                it.closeAddFragment()
            }
        }
    }
}