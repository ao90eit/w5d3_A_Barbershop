package com.aoinc.w5d3_a_barbershop.util

import com.aoinc.w5d3_a_barbershop.R
import kotlin.random.Random

abstract class Constants {
    companion object {
        const val cutPricePerCount: Double = 2.34
        const val CUSTOMER_ID_KEY : String = "customer_id"
        const val CUSTOMER_IS_WAITING_KEY : String = "customer_is_waiting"

        fun randomBarberName(): String = names[Random.nextInt(names.size - 1)]

        private val names: Array<String> = arrayOf(
            "Kamel",
            "Raoul",
            "Jonnie",
            "Mikey",
            "Pops",
            "Sam",
            "Sal",
            "Mel",
            "Dez",
            "Connie"
        )
    }
}