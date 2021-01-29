package com.aoinc.w5d3_a_barbershop.view

import android.os.Bundle
import android.os.Handler
import android.os.Looper
import android.os.Message
import android.util.Log
import androidx.appcompat.app.AppCompatActivity
import com.aoinc.w5d3_a_barbershop.R
import com.aoinc.w5d3_a_barbershop.data.Customer
import com.aoinc.w5d3_a_barbershop.util.Constants
import com.aoinc.w5d3_a_barbershop.util.HandlerUtil

class MainActivity : AppCompatActivity(), Handler.Callback {

    private lateinit var handler: Handler
    private lateinit var handlerUtil: HandlerUtil

    private val customerList: MutableList<Customer> = mutableListOf()

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)

        handler = Handler(Looper.getMainLooper(), this)
        handlerUtil = HandlerUtil(handler)

        customerList.addAll(listOf(
            Customer(handler = handler),
            Customer(handler = handler),
            Customer(handler = handler),
            Customer(handler = handler)
        ))

        handlerUtil.beginServicingCustomers(customerList)
    }

    override fun handleMessage(msg: Message): Boolean {
        msg.data.apply {
            customerList.indexOfFirst { it.customerID == get(Constants.CUSTOMER_ID_KEY) }
                .also { pos ->
                    Log.d("TAG_X", "customer $pos progress = ${customerList[pos].cutProgress}")
//                    thing.updateItem(pos, customerList[pos].cutProgress)
                }
        }

        return true
    }
}