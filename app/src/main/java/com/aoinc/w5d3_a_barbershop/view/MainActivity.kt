package com.aoinc.w5d3_a_barbershop.view

import android.os.Bundle
import android.os.Handler
import android.os.Looper
import android.os.Message
import android.view.View
import android.widget.Button
import android.widget.TextView
import androidx.appcompat.app.AppCompatActivity
import androidx.fragment.app.FragmentContainerView
import androidx.recyclerview.widget.RecyclerView
import androidx.recyclerview.widget.SimpleItemAnimator
import com.aoinc.w5d3_a_barbershop.R
import com.aoinc.w5d3_a_barbershop.data.Customer
import com.aoinc.w5d3_a_barbershop.util.Constants
import com.aoinc.w5d3_a_barbershop.util.HandlerUtil

class MainActivity : AppCompatActivity(), Handler.Callback {

    private lateinit var handler: Handler
    private lateinit var handlerUtil: HandlerUtil

    private val waitingList: MutableList<Customer> = mutableListOf()
    private lateinit var waitingRecyclerView: RecyclerView
    private val waitingListAdapter = CustomerCardAdapter()

    private val cuttingList: MutableList<Customer> = mutableListOf()
    private lateinit var cuttingRecyclerView: RecyclerView
    private val cuttingListAdapter = CustomerCardAdapter()

    private lateinit var startDayButton: Button
    private lateinit var newCustomerButton: Button
    private lateinit var earningsTextView: TextView
    var earnings: Double = 0.0
    private lateinit var addCustomerFragmentView: FragmentContainerView

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)

        waitingRecyclerView = findViewById(R.id.waiting_customer_recyclerView)
        cuttingRecyclerView = findViewById(R.id.active_customer_recyclerView)
        startDayButton = findViewById(R.id.start_day_button)
        newCustomerButton = findViewById(R.id.new_customer_button)
        earningsTextView = findViewById(R.id.main_earnings_textView)
        addCustomerFragmentView = findViewById(R.id.add_customer_fragment)

        handler = Handler(Looper.getMainLooper(), this)
        handlerUtil = HandlerUtil(handler)

        waitingRecyclerView.adapter = waitingListAdapter
        cuttingRecyclerView.adapter = cuttingListAdapter

        (waitingRecyclerView.itemAnimator as SimpleItemAnimator).supportsChangeAnimations = false
        (cuttingRecyclerView.itemAnimator as SimpleItemAnimator).supportsChangeAnimations = false

        newCustomerButton.setOnClickListener {
//            addCustomerBatch()
            addCustomerFragmentView.visibility = View.VISIBLE
            it.isEnabled = false
        }

        startDayButton.setOnClickListener {
            if (waitingList.size > 0) {
                handlerUtil.beginServicingCustomers(waitingList)
                it.isEnabled = false
            }
        }
    }

    override fun handleMessage(msg: Message): Boolean {
        when (msg.data.get(Constants.CUSTOMER_IS_WAITING_KEY)){
            true -> {   // is waiting, needs to be moved to cutting
                val pos = waitingList.indexOfFirst { it.id == msg.data.get(Constants.CUSTOMER_ID_KEY) }
                cuttingList.add(waitingList[pos])
                cuttingListAdapter.addItem(cuttingList[cuttingList.size - 1])
                waitingList.removeAt(pos)
                waitingListAdapter.removeItem(pos)
            }
            false -> {  // is already cutting
                val pos =
                    cuttingList.indexOfFirst { it.id == msg.data.get(Constants.CUSTOMER_ID_KEY) }

                if (cuttingList[pos].cutProgress > 99) { // because floats can't be trusted lol
                    earnings += (Constants.cutPricePerCount * cuttingList[pos].cuttingTime)
                    earningsTextView.text = String.format("Profit: $%.2f", earnings)
                    cuttingListAdapter.removeItem(pos)
                    cuttingList.removeAt(pos)
                    // TODO: play sound here
                } else
                    cuttingListAdapter.updateItem(pos, cuttingList[pos].cutProgress)
            }
        }

        // TODO: Hack -> Find out how to respond directly to thread pool execution completion.
        if (cuttingList.size < 1)
            startDayButton.isEnabled = true

        return true
    }

    fun addCustomerBatch(num: Int = 10) {
        for (i in 0..num) {
            Customer(handler = handler)
                .also { addSingleCustomer(it) }
        }
    }

    private fun addSingleCustomer(customer: Customer = Customer(handler = handler)) {
        waitingList.add(customer)
        waitingListAdapter.addItem(customer)

        if (handlerUtil.isRunning())
            handlerUtil.addCustomerToQueue(customer)
    }

    fun addSingleCustomer(name: String, time: Int) {
        addSingleCustomer(Customer(
            handler = handler,
            cuttingTime = time,
            name = name
        ))
    }

    fun closeAddFragment() {
        addCustomerFragmentView.visibility = View.GONE
        newCustomerButton.isEnabled = true
    }
}