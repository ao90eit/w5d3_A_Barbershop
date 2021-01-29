package com.aoinc.w5d3_a_barbershop.view

import android.content.Context
import android.util.AttributeSet
import android.view.LayoutInflater
import android.widget.ImageView
import android.widget.ProgressBar
import android.widget.TextView
import androidx.cardview.widget.CardView
import com.aoinc.w5d3_a_barbershop.R

class CustomerCard(context: Context, attributeSet: AttributeSet)
    : CardView(context, attributeSet) {

    private val layoutInflater = context
        .getSystemService(Context.LAYOUT_INFLATER_SERVICE)
            as LayoutInflater

    private lateinit var nameTextView: TextView
    private lateinit var cutProgressBarView: ProgressBar
    private lateinit var photoImageView: ImageView
    private lateinit var barberTextView: TextView

    // exposed properties
    private var customerName: String
        get() = nameTextView.text.toString().trim()
        set(value) { nameTextView.text = value }

    private var cutProgress: Int
        get() = cutProgressBarView.progress
        set(value) { cutProgressBarView.progress = value }

    private var photoID: Int
        get() = photoImageView.id
        set(value) { photoImageView.setImageResource(value) }

//    private var barberName: String
//        get() = barberTextView.text.toString().removePrefix("cut by ").trim()
//        set(value) { barberTextView.text = "cut by $value" }

    init {
        layoutInflater.inflate(R.layout.customer_card_layout, this, true)

        nameTextView = rootView.findViewById(R.id.customer_name_textView)
        cutProgressBarView = rootView.findViewById(R.id.customer_progressBar)
        photoImageView = rootView.findViewById(R.id.customer_photo_imageView)
        barberTextView = rootView.findViewById(R.id.customer_barber_textView)

        val attrArray = context.obtainStyledAttributes(attributeSet, R.styleable.CustomerCard)

        customerName = attrArray.getString(R.styleable.CustomerCard_CustomerName) ?: "Janine"
        cutProgress = attrArray.getInt(R.styleable.CustomerCard_CutProgress, 0)
        photoID = attrArray.getInt(R.styleable.CustomerCard_PhotoSrcID, R.drawable.profile_pic_1)
//        barberName = attrArray.getString(R.styleable.CustomerCard_BarberName) ?: "Mikey"
    }
}