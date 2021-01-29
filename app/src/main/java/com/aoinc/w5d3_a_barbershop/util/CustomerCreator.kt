package com.aoinc.w5d3_a_barbershop.util

import com.aoinc.w5d3_a_barbershop.R
import kotlin.random.Random

abstract class CustomerCreator {
    companion object {
        fun randomName(): String = names[Random.nextInt(names.size - 1)]
        fun randomCuttingTime(): Int = Random.nextInt(10, 20)
        fun randomPhotoID(): Int = photoIDs[Random.nextInt(photoIDs.size - 1)]

        private val names: Array<String> = arrayOf(
            "Bridget",
            "Twila",
            "Jarred",
            "Evangeline",
            "Tameka",
            "Anderson",
            "Esperanza",
            "Augustine",
            "Earle",
            "Nathaniel",
            "Socorro",
            "Filiberto",
            "Lavern",
            "Juanita",
            "Cordell",
            "Patrick",
            "Harland",
            "Linwood",
            "Kristofer",
            "Adolph"
        )

        private val photoIDs: Array<Int> = arrayOf(
            R.drawable.profile_pic_1,
            R.drawable.profile_pic_2,
            R.drawable.profile_pic_3,
            R.drawable.profile_pic_4,
            R.drawable.profile_pic_5,
            R.drawable.profile_pic_6,
            R.drawable.profile_pic_7,
            R.drawable.profile_pic_8,
            R.drawable.profile_pic_9
        )
    }
}