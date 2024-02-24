package com.example.dodo

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.widget.ImageView
import android.widget.TextView

class ItemActivity : AppCompatActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_item)

        val title: TextView = findViewById(R.id.item_list_title_one)
        val text:  TextView = findViewById(R.id.item_list_text)
        val price: TextView = findViewById(R.id.item_list_price_one)
        val image: ImageView = findViewById(R.id.item_list_image_one)


        title.text = intent.getStringExtra("itemTitle")
        text.text = intent.getStringExtra("itemText")
        price.text = intent.getStringExtra("itemPrice")
        image.setImageResource(intent.getIntExtra("itemImageResourceId", R.drawable.default_image))



    }
}