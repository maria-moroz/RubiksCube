package com.example.rubiksart.program

class AStar (getResult: Array<Array<String>>) {
    val n = 3
    lateinit var front: Array<Array<String>>
    lateinit var back: Array<Array<String>>
    lateinit var up: Array<Array<String>>
    lateinit var down: Array<Array<String>>
    lateinit var right: Array<Array<String>>
    lateinit var left: Array<Array<String>>
    val result: Array<Array<String>> = getResult
    var answer = Answer()

    fun Search():Answer {
        var queue = PriorityQueue()
        var closed = ArrayList<RubiksCube>()
        chooseColour()
        answer.top=up[2][2]
        answer.front=front[2][2]
        var start_state = RubiksCube(front, back, up, down, right, left, result) //стартовий стан кубика
        queue.Enqueue(start_state) //додання до пріоритетного списку
        closed.add(start_state) //додання до пройдених вершин
        var l = 0
        if (start_state.heuristicFunc() == 9) //якщо ціль досягнута
        {
            queue.priorQueue.clear()
            start_state.Path(l, answer) //повертаємо шлях рекурсивно по батьківському об'єкту
        }

        while (!queue.isEmpty()) //допоки список пріоритетів не пустий
        {
            val state = queue.Dequeue() //вибарти перший елемент з PriorityQueue та видалити його зі списку

            for (i in 1..10) {
                var child = state!!.getChild(i)//знаходження похідний варіацій комбінацій з поточного
                if (child.heuristicFunc() == 9) //якщо ціль досягнута
                {
                    queue.priorQueue.clear()
                    child.Path(l, answer) //повертаємо шлях рекурсивно по батьківському об'єкту
                    break
                } else {
                    if (!isClosed(closed, child)) //якщо комбінація нова і не вивчена
                    {
                        queue.Enqueue(child) //додавання в PriorityQueue
                        closed.add(child)   //додавання в закриті варіації
                    }
                }
            }

            if (queue.isEmpty()) //якщо список пріоритетів пустий
            {
                break
            }
        }
        return answer
    }

    fun isClosed(closed: ArrayList<RubiksCube>, state: RubiksCube):Boolean {
        var check = false
        for(d:RubiksCube in closed)
        {
            var num = 0
            for (k in 0..2) {
                for (x in 0..2) {
                    if (d.presentFront[k][x].contentEquals(state.presentFront[k][x]))
                        num++
                }
            }
            if (num == 9)
                check = true
        }
        return check
    }

    fun chooseColour() {
        var variation = result[1][1]
        when (variation) {
            "R" -> fillRed()
            "B" -> fillBlue()
            "O" -> fillOrange()
            "G" -> fillGreen()
            "W" -> fillWhite()
            "Y" -> fillYellow()
        }
    }

    fun fillRed() {
        front = Array(n){Array(n){"R"} }
        back = Array(n){Array(n){"O"} }
        right = Array(n){Array(n){"B"} }
        left = Array(n){Array(n){"G"} }
        up = Array(n){Array(n){"W"} }
        down = Array(n){Array(n){"Y"} }
    }

    fun fillBlue() {
        front = Array(n){Array(n){"B"} }
        back = Array(n){Array(n){"G"} }
        right = Array(n){Array(n){"O"} }
        left = Array(n){Array(n){"R"} }
        up = Array(n){Array(n){"W"} }
        down = Array(n){Array(n){"Y"} }
    }

    fun fillOrange() {
        front = Array(n){Array(n){"O"} }
        back = Array(n){Array(n){"R"} }
        right = Array(n){Array(n){"G"} }
        left = Array(n){Array(n){"B"} }
        up = Array(n){Array(n){"W"} }
        down = Array(n){Array(n){"Y"} }
    }

    fun fillGreen() {
        front = Array(n){Array(n){"G"} }
        back = Array(n){Array(n){"B"} }
        right = Array(n){Array(n){"R"} }
        left = Array(n){Array(n){"O"} }
        up = Array(n){Array(n){"W"} }
        down = Array(n){Array(n){"Y"} }
    }

    fun fillWhite() {
        front = Array(n){Array(n){"W"} }
        back = Array(n){Array(n){"Y"} }
        right = Array(n){Array(n){"R"} }
        left = Array(n){Array(n){"O"} }
        up = Array(n){Array(n){"B"} }
        down = Array(n){Array(n){"G"} }
    }

    fun fillYellow() {
        front = Array(n){Array(n){"Y"} }
        back = Array(n){Array(n){"W"} }
        right = Array(n){Array(n){"O"} }
        left = Array(n){Array(n){"R"} }
        up = Array(n){Array(n){"B"} }
        down = Array(n){Array(n){"G"} }
    }
}