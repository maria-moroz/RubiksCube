package com.example.rubiksart.program

class RubiksCube : Comparable<RubiksCube>
{
    private val n: Int = 3
    var presentFront: Array<Array<String>>
    var presentBack: Array<Array<String>>
    var presentUp: Array<Array<String>>
    var presentDown: Array<Array<String>>
    var presentRight: Array<Array<String>>
    var presentLeft: Array<Array<String>>
    private val frontResult: Array<Array<String>>

    var Parent:RubiksCube? = null //батьківський об'єкт
    var action: String? = null

    constructor(front: Array<Array<String>>, back: Array<Array<String>>, up: Array<Array<String>>, down: Array<Array<String>>, right: Array<Array<String>>, left: Array<Array<String>>, result: Array<Array<String>>) {
        presentFront = front.clone()
        presentBack = back.clone()
        presentUp = up.clone()
        presentDown = down.clone()
        presentRight = right.clone()
        presentLeft = left.clone()
        frontResult = result.clone()
    }

    constructor(front: Array<Array<String>>, back: Array<Array<String>>, up: Array<Array<String>>, down: Array<Array<String>>, right: Array<Array<String>>, left: Array<Array<String>>, result: Array<Array<String>>, act: String, parent: RubiksCube) {
        presentFront = front.clone()
        presentBack = back.clone()
        presentUp = up.clone()
        presentDown = down.clone()
        presentRight = right.clone()
        presentLeft = left.clone()
        frontResult = result.clone()
        action = act
        Parent = parent
    }

    override fun compareTo(other: RubiksCube):Int {
        val presentHeuristic: Int = heuristicFunc()
        val neighboringHeuristic: Int = other.heuristicFunc()
        return presentHeuristic - neighboringHeuristic
    }

    fun heuristicFunc(): Int {
        var numPositions = 0
        var numFront = 0
        var numBack = 0
        var numUp = 0
        var numDown = 0
        var numLeft = 0
        var numRight = 0
        for (i in 0..2) {
            for (j in 0..2) {
                if (presentFront[i][j] == frontResult[i][j])
                    numFront++
            }
        }
       for (i in 0..2) {
            for (j in 0..2) {
                if (presentBack[i][j] == frontResult[i][j])
                    numBack++
            }
        }
        for (i in 0..2) {
            for (j in 0..2) {
                if (presentUp[i][j] == frontResult[i][j])
                    numUp++
            }
        }
        for (i in 0..2) {
            for (j in 0..2) {
                if (presentDown[i][j] == frontResult[i][j])
                    numDown++
            }
        }
        for (i in 0..2) {
            for (j in 0..2) {
                if (presentRight[i][j] == frontResult[i][j])
                    numRight++
            }
        }
        for (i in 0..2) {
            for (j in 0..2) {
                if (presentLeft[i][j] == frontResult[i][j])
                    numLeft++
            }
        }
        numPositions = maxOf(numFront, numBack, numUp, numDown, numRight, numLeft)
        return numPositions
    }

    fun getChild(variation: Int): RubiksCube {
        val newFront: Array<Array<String>> = Copy(presentFront)
        val newBack: Array<Array<String>> = Copy(presentBack)
        val newUp: Array<Array<String>> = Copy(presentUp)
        val newDown: Array<Array<String>> = Copy(presentDown)
        val newRight: Array<Array<String>> = Copy(presentRight)
        val newLeft: Array<Array<String>> = Copy(presentLeft)

        var act = ""

        when (variation) {
            1 -> {
                UpRight(newFront, newBack, newDown, newRight, newLeft)
                RotateAntiClockwise(newUp)
                act = "UR"
            }
            2 -> {
                UpLeft(newFront, newBack, newDown, newRight, newLeft)
                RotateClockwise(newUp)
                act = "UL"
            }
            3 -> {
                DownRight(newFront, newBack, newUp, newRight, newLeft)
                RotateClockwise(newDown)
                act = "DR"
            }
            4 -> {
                DownLeft(newFront, newBack, newUp, newRight, newLeft)
                RotateAntiClockwise(newDown)
                act = "DL"
            }
            5 -> {
                RightUp(newFront, newBack, newUp, newDown, newLeft)
                RotateClockwise(newRight)
                act = "RU"
            }
            6 -> {
                RightDown(newFront, newBack, newUp, newDown, newLeft)
                RotateAntiClockwise(newRight)
                act = "RD"
            }
            7 -> {
                LeftUp(newFront, newBack, newUp, newDown, newRight)
                RotateAntiClockwise(newLeft)
                act = "LU"
            }
            8 -> {
                LeftDown(newFront, newBack, newUp, newDown, newRight)
                RotateClockwise(newLeft)
                act = "LD"
            }
            9 -> {
                FrontClockwise(newUp, newDown, newRight, newLeft)
                RotateClockwise(newFront)
                act = "FC"
            }
            10 -> {
                FrontAntiClockwise(newUp, newDown, newRight, newLeft)
                RotateAntiClockwise(newFront)
                act = "FAC"
            }
        }
        return RubiksCube(newFront, newBack, newUp, newDown, newRight, newLeft, frontResult, act, this)
    }

    fun Copy(state: Array<Array<String>>): Array<Array<String>> {
        val m = Array(n) { Array(n) { "" } }
        for (i in 0..2) {
            for (j in 0..2) {
                m[i][j] = state[i][j]
            }
        }
        return m
    }

    fun UpRight(front: Array<Array<String>>, back: Array<Array<String>>, down: Array<Array<String>>, right: Array<Array<String>>, left: Array<Array<String>>) {
        for (j in 0..2) {
            val temp: String = front[0][j]
            front[0][j] = left[0][j]
            left[0][j] = back[0][j]
            back[0][j] = right[0][j]
            right[0][j] = temp
        }
    }

    fun UpLeft(front: Array<Array<String>>, back: Array<Array<String>>, down: Array<Array<String>>, right: Array<Array<String>>, left: Array<Array<String>>) {
        for (j in 0..2) {
            val temp: String = front[0][j]
            front[0][j] = right[0][j]
            right[0][j] = back[0][j]
            back[0][j] = left[0][j]
            left[0][j] = temp
        }
    }

    fun DownRight(front: Array<Array<String>>, back: Array<Array<String>>, up: Array<Array<String>>, right: Array<Array<String>>, left: Array<Array<String>>)
    {
        for (j in 0..2) {
            val temp: String = front[2][j]
            front[2][j] = left[2][j]
            left[2][j] = back[2][j]
            back[2][j] = right[2][j]
            right[2][j] = temp
        }
    }

    fun DownLeft(front: Array<Array<String>>, back: Array<Array<String>>, up: Array<Array<String>>, right: Array<Array<String>>, left: Array<Array<String>>)
    {
        for (j in 0..2) {
            val temp: String = front[2][j]
            front[2][j] = right[2][j]
            right[2][j] = back[2][j]
            back[2][j] = left[2][j]
            left[2][j] = temp
        }
    }

    fun RightUp(front: Array<Array<String>>, back: Array<Array<String>>, up: Array<Array<String>>, down: Array<Array<String>>, left: Array<Array<String>>)
    {
        var k = 2
        for (i in 0..2) {
            val temp: String = front[i][2]
            front[i][2] = down[i][2]
            down[i][2] = back[k][0]
            back[k][0] = up[i][2]
            up[i][2] = temp
            k--
        }
    }

    fun RightDown(front: Array<Array<String>>, back: Array<Array<String>>, up: Array<Array<String>>, down: Array<Array<String>>, left: Array<Array<String>>)
    {
        var k = 2
        for (i in 0..2) {
            val temp: String = front[i][2]
            front[i][2] = up[i][2]
            up[i][2] = back[k][0]
            back[k][0] = down[i][2]
            down[i][2] = temp
            k--
        }
    }

    fun LeftUp (front: Array<Array<String>>, back: Array<Array<String>>, up: Array<Array<String>>, down: Array<Array<String>>, right: Array<Array<String>>) {
        var k = 2
        for (i in 0..2) {
            val temp: String = front[i][0]
            front[i][0] = down[i][0]
            down[i][0] = back[k][2]
            back[k][2] = up[i][0]
            up[i][0] = temp
            k--
        }
    }

    fun LeftDown (front: Array<Array<String>>, back: Array<Array<String>>, up: Array<Array<String>>, down: Array<Array<String>>, right: Array<Array<String>>)
    {
        var k = 2
        for (i in 0..2) {
            val temp: String = front[i][0]
            front[i][0] = up[i][0]
            up[i][0] = back[k][2]
            back[k][2] = down[i][0]
            down[i][0] = temp
            k--
        }
    }

    fun FrontClockwise(up: Array<Array<String>>, down: Array<Array<String>>, right: Array<Array<String>>, left: Array<Array<String>>)
    {
        var k = 2
        val temp_up = Array(n){""}
        val temp_left = Array(n){""}
        val temp_right = Array(n){""}
        val temp_down = Array(n){""}
        for (i in 0..2) {
            temp_up[i] = up[2][i]
            temp_left[i] = left[i][2]
            temp_right[i] = right[i][0]
            temp_down[i] = down[0][i]
        }
        for (i in 0..2) {
            up[2][k] = temp_left[i]
            left[i][2] = temp_down[i]
            down[0][k] = temp_right[i]
            right[i][0] = temp_up[i]
            k--
        }
    }

    fun FrontAntiClockwise (up: Array<Array<String>>, down: Array<Array<String>>, right: Array<Array<String>>, left: Array<Array<String>>)
    {
        var k = 2
        val temp_up = Array(n){""}
        val temp_left = Array(n){""}
        val temp_right = Array(n){""}
        val temp_down = Array(n){""}
        for (i in 0..2) {
            temp_up[i] = up[2][i]
            temp_left[i] = left[i][2]
            temp_right[i] = right[i][0]
            temp_down[i] = down[0][i]
        }
        for (i in 0..2) {
            up[2][i] = temp_right[i]
            right[k][0] = temp_down[i]
            down[0][i] = temp_left[i]
            left[k][2] = temp_up[i]
            k--
        }
    }

    fun RotateClockwise(m: Array<Array<String>>)
    {
        val result = Array(n){Array(n){""} }
        for (i in 0..2) {
            for (j in 0..2) {
                result[i][j] = m[n - j - 1][i]
            }
        }
        for (i in 0..2) {
            for (j in 0..2) {
                m[i][j] = result[i][j]
            }
        }
    }

    fun RotateAntiClockwise(m: Array<Array<String>>)
    {
        val result = Array(n){Array(n){""} }
        for (i in 0..2) {
            for (j in 0..2) {
                result[i][j] = m[j][n - i - 1]
            }
        }
        for (i in 0..2) {
            for (j in 0..2) {
                m[i][j] = result[i][j]
            }
        }
    }

    fun Path (l:Int, ans: Answer){
        Parent?.Path(l+1, ans)
        ans.answer.add(presentFront)
        ans.action.add(action.toString())
    }
}