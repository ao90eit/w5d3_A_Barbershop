package com.aoinc.w5d3_a_barbershop.view

import android.os.Bundle
import android.os.Handler
import android.os.Looper
import android.os.Message
import android.util.Log
import androidx.appcompat.app.AppCompatActivity
import androidx.recyclerview.widget.RecyclerView
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

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)

        waitingRecyclerView = findViewById(R.id.waiting_customer_recyclerView)
        cuttingRecyclerView = findViewById(R.id.active_customer_recyclerView)

        handler = Handler(Looper.getMainLooper(), this)
        handlerUtil = HandlerUtil(handler)

        waitingList.addAll(listOf(
            Customer(handler = handler),
            Customer(handler = handler),
            Customer(handler = handler),
            Customer(handler = handler)
        ))

        waitingListAdapter.updateList(waitingList)
        waitingRecyclerView.adapter = waitingListAdapter

        cuttingRecyclerView.adapter = cuttingListAdapter
        cuttingListAdapter.addItem(waitingList[0])

//        handlerUtil.beginServicingCustomers(customerList)
    }

    override fun handleMessage(msg: Message): Boolean {
        msg.data.apply {
            waitingList.indexOfFirst { it.id == get(Constants.CUSTOMER_ID_KEY) }
                .also { pos ->
                    Log.d("TAG_X", "customer $pos progress = ${waitingList[pos].cutProgress}")
//                    thing.updateItem(pos, customerList[pos].cutProgress)
                }
        }

        return true
    }
}