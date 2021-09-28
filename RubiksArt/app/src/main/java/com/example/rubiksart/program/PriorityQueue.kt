package com.example.rubiksart.program

class PriorityQueue
{
    var priorQueue = ArrayList<RubiksCube>() //список з найбільшим значенням евристики

    fun Dequeue(): RubiksCube? {
        if (priorQueue.isEmpty()) {
            return null
        }
        val cube:RubiksCube = priorQueue[0]
        priorQueue.removeAt(0)
        return cube
    }

    fun Enqueue(cube: RubiksCube)
    {
        var i = 0
        for(element in priorQueue) {
            if (element < cube) //якщо новий варіант має більшу евристику
                break
            i++
        }
        priorQueue.add(i, cube) //додати до списку
    }

    fun isEmpty():Boolean
    {
        return priorQueue.isEmpty()
    }
}