package com.aoinc.w5d3_a_barbershop.util

import com.aoinc.w5d3_a_barbershop.data.Customer
import java.util.concurrent.BlockingQueue
import java.util.concurrent.LinkedBlockingQueue
import java.util.concurrent.ThreadPoolExecutor
import java.util.concurrent.TimeUnit

class HandlerUtil(
    private val handler: android.os.Handler
) {

    private val CORE_POOL_SIZE = 3
    private val MAX_POOL_SIZE = 8
    private val KEEP_ALIVE_TIME = 10L

    private lateinit var barbershopPoolManager: ThreadPoolExecutor
    private var customerQueue: BlockingQueue<Runnable> = LinkedBlockingQueue()

    private fun getPoolManager(): ThreadPoolExecutor {
        // already have pool executor
        if (this::barbershopPoolManager.isInitialized)
            return barbershopPoolManager

        // need fresh pool executor
        return ThreadPoolExecutor(
            CORE_POOL_SIZE, MAX_POOL_SIZE,
            KEEP_ALIVE_TIME, TimeUnit.SECONDS,
            customerQueue
        )
    }

    fun beginServicingCustomers(customerList: List<Customer>) {
        barbershopPoolManager = getPoolManager()

        customerList.forEach {
            barbershopPoolManager.execute(it)
        }
    }

    // TODO: write function to add new customers in the list to the queue
}