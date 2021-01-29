package com.aoinc.w5d3_a_barbershop.data

import android.os.Handler
import android.os.Message
import androidx.core.os.bundleOf
import com.aoinc.w5d3_a_barbershop.util.Constants
import com.aoinc.w5d3_a_barbershop.util.CustomerCreator

data class Customer(
    val handler: Handler,
    val name: String = CustomerCreator.randomName(),
    val cuttingTime: Int = CustomerCreator.randomCuttingTime(),
    val photoResourceID: Int = CustomerCreator.randomPhotoID()
) : Runnable {

    var cutProgress: Float = 0f
    var customerID = this.hashCode()

    override fun run() {
        for (i in 1..cuttingTime) {
            Thread.sleep(500)

            cutProgress = (i / cuttingTime.toFloat()) * 100
            // TODO: test if toFloat() is needed

            handler.sendMessage(Message.obtain().also { msg ->
                msg.data = bundleOf(
                    Constants.CUSTOMER_ID_KEY to customerID
//                    Constants.CUT_PROGRESS_KEY to cutProgress
                )
            })
        }
    }
}
