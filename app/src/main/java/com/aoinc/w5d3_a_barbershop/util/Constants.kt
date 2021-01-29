package com.aoinc.w5d3_a_barbershop.util

import com.aoinc.w5d3_a_barbershop.R
import kotlin.random.Random

abstract class Constants {
    companion object {
        const val cutPricePerCount: Double = 2.34
        const val CUSTOMER_ID_KEY : String = "customer_id"
        const val CUSTOMER_IS_WAITING_KEY : String = "customer_is_waiting"

        private var i = -1
        fun nextBarberName(): String{
            i++
            if (i >= names.size) i = 0
            return names[i]
        }

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
            "Connie",
            "Kamel2",
            "Raoul2",
            "Jonnie2",
            "Mikey2",
            "Pops2",
            "Sam2",
            "Sal2",
            "Mel2",
            "Dez2",
            "Connie2"
        )
    }
}