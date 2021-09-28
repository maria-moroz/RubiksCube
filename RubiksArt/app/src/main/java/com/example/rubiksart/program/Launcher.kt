package com.example.rubiksart.program
import java.io.BufferedReader
import java.util.ArrayList

class Launcher {
    var width = 0
    var height = 0
    var picture: Array<Array<String?>>
    fun launch():Array<Array<String?>> {
        val fileName = "program/fragment.txt"
        picture = readFile()
        /*var solver = AStar(picture)
        solver.Search()*/
        return picture
    }

    constructor()
    {
        this.picture = readFile()
    }

    fun readFile(): Array<Array<String?>> {
        val fileName = ""
        val reader = BufferedReader(java.io.FileReader(fileName))
        val lines: MutableList<String> = ArrayList()
        while (reader.ready()) {
            lines.add(reader.readLine())
        }
        width = lines[0].split(" ".toRegex()).toTypedArray().size
        height = lines.size

        var picture = Array(height) {
            arrayOfNulls<String>(width)
        }

        for (i in 0 until height) {
            for (j in 0 until width) {
                val line = lines[i].split(" ".toRegex()).toTypedArray()
                picture[i][j] = line[j]
            }
        }
        return picture
    }
}