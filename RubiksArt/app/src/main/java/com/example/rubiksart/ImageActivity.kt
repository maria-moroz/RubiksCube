package com.example.rubiksart

import android.content.Intent
import android.content.UriMatcher
import android.graphics.Bitmap
import android.graphics.BitmapFactory
import android.os.Bundle
import android.widget.ImageView
import androidx.appcompat.app.AppCompatActivity
import com.example.rubiksart.program.BitmapHelper
import javax.xml.transform.URIResolver


class ImageActivity : AppCompatActivity() {
    var image: ImageView?=null
    var bitmap: Bitmap?=null

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_image)
        image = findViewById(R.id.picker_image)
        val check = findViewById<ImageView>(R.id.check)
        var intent = Intent().setType("image/*")
        intent.action = Intent.ACTION_GET_CONTENT
        startActivityForResult(Intent.createChooser(intent, "Pick an image"), 1)

        val plus = findViewById<ImageView>(R.id.plus)
        plus.setOnClickListener {
            var intent = Intent().setType("image/*")
            intent.action = Intent.ACTION_GET_CONTENT
            startActivityForResult(Intent.createChooser(intent, "Pick an image"), 1)
        }

        check.setOnClickListener {
            /*val pixelated: Bitmap = fromBitmap(
                bitmap!!,
                PixelateLayer.Builder(PixelateLayer.Shape.Square)
                    .setResolution(40f)
                    .build()
            )
            image?.setImageBitmap(pixelated)*/
            val intent = Intent(this, PixelationActivity::class.java)
            startActivity(intent)
        }


    }

    override fun onActivityResult(requestCode: Int, resultCode: Int, data: Intent?) {
        super.onActivityResult(requestCode, resultCode, data)
        if(resultCode == RESULT_OK && requestCode == 1){
            val inputStream = contentResolver.openInputStream(data?.data!!)
            bitmap = BitmapFactory.decodeStream(inputStream)
            image?.setImageBitmap(bitmap)
            BitmapHelper.instance.bitmap = bitmap
        }
    }
}

/*override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        supportActionBar!!.hide()
        setContentView(R.layout.activity_image)

        imagePicker = findViewById(R.id.picker_image)
        ImagePicker.with(this).galleryOnly().galleryMimeTypes(arrayOf("image/*")).crop().start()

        val plus = findViewById<ImageView>(R.id.plus)
        plus.setOnClickListener {
            ImagePicker.with(this).galleryOnly().galleryMimeTypes(arrayOf("image/*")).crop().start()
        }

        val check = findViewById<ImageView>(R.id.check)
        check.setOnClickListener {
            val intent = Intent(this, PixelationActivity::class.java)
            startActivity(intent)
        }
    }

    override fun onActivityResult(requestCode: Int, resultCode: Int, data: Intent?) {
        super.onActivityResult(requestCode, resultCode, data)
        if(resultCode == Activity.RESULT_OK && requestCode == ImagePicker.REQUEST_CODE){
            imagePicker?.setImageURI(data?.data)
        }
    }*/
    /*override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        supportActionBar!!.hide()
        setContentView(R.layout.activity_image)
        image = findViewById(R.id.picker_image)
        val check = findViewById<ImageView>(R.id.check)

        var intent = Intent().setType("image/*")
        intent.action = Intent.ACTION_GET_CONTENT
        startActivityForResult(Intent.createChooser(intent, "Pick an image"), 1)

        val plus = findViewById<ImageView>(R.id.plus)
        plus.setOnClickListener {
            var intent = Intent().setType("image/*")
            intent.action = Intent.ACTION_GET_CONTENT
            startActivityForResult(Intent.createChooser(intent, "Pick an image"), 1)
        }

        val text = findViewById<TextView>(R.id.bitmap)
        text.text = bitmap.toString()

        check.setOnClickListener {
            /*val pixelated: Bitmap = fromBitmap(
                bitmap!!,
                PixelateLayer.Builder(PixelateLayer.Shape.Square)
                    .setResolution(40f)
                    .build()
            )
            image?.setImageBitmap(pixelated)*/
            val intent = Intent(this, PixelationActivity::class.java)
            intent.putExtra("bitmap", bitmap)
            startActivity(intent)
            //val intent = Intent(this, PixelationActivity::class.java)
            //startActivity(intent)
        }


    }

    override fun onActivityResult(requestCode: Int, resultCode: Int, data: Intent?) {
        super.onActivityResult(requestCode, resultCode, data)
        if(resultCode == RESULT_OK && requestCode == 1){
            val inputStream = contentResolver.openInputStream(data?.data!!)
            bitmap = BitmapFactory.decodeStream(inputStream)
            image?.setImageBitmap(bitmap)
        }
    }
}*/