package com.example.rubiksart

import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.widget.Button
import com.example.rubiksart.program.AStar
import java.io.Serializable

class PiecesActivity : AppCompatActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_pieces)
        var result: Array<Array<String>> = arrayOf(
            arrayOf("B","B","B"),
            arrayOf("R","R","R"),
            arrayOf("R","R","R"))

        val start = findViewById<Button>(R.id.piece)
        start.setOnClickListener{
            val solver = AStar(result)
            val picture = solver.Search()
            val intent = Intent(this, StartActivity::class.java)
            val bundle = Bundle()
            bundle.putSerializable("picture", picture as Serializable)
            intent.putExtras(bundle)
            startActivity(intent)
        }
    }
}



/*var result: Array<Array<String>> = arrayOf(
    arrayOf("B","B","B"),
    arrayOf("R","R","Y"),
    arrayOf("O","O","Y"))*/

/*var result: Array<Array<String>> = arrayOf(
            arrayOf("B","R","Y"),
            arrayOf("R","R","Y"),
            arrayOf("G","G","Y"))*/

/*var result: Array<Array<String>> = arrayOf(
    arrayOf("W","G","Y"),
    arrayOf("W","G","Y"),
    arrayOf("W","R","R"))*/

/*var result: Array<Array<String>> = arrayOf(
    arrayOf("R","R","Y"),
    arrayOf("R","R","Y"),
    arrayOf("B","B","B"))*/

/*var result: Array<Array<String>> = arrayOf(
    arrayOf("B","R","Y"),
    arrayOf("R","R","Y"),
    arrayOf("B","G","Y"))*/