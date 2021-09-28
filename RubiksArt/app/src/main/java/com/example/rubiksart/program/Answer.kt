package com.example.rubiksart.program

import java.io.Serializable

class Answer: Serializable {
    var answer = ArrayList<Array<Array<String>>>()
    var action = ArrayList<String>()
    var top = String()
    var front = String()
}