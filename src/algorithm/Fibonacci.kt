package algorithm

import java.math.BigInteger


/**
@author zzz
@date 2019/7/29 10:09
 **/

class Fibonacci {

    fun calFibonacci(num: Int): BigInteger {
        if (num < 1) {
            return BigInteger("0")
        }
        return if (num == 1 || num == 2) {
            BigInteger("1")
        } else {
            var n = 3
            var lastOne = BigInteger("1")
            var lastTwo = BigInteger("1")
            var current = BigInteger("0")
            while (n <= num) {
                current = lastOne + lastTwo
                lastTwo = lastOne
                lastOne = current
//                println("第${n}位：$current")
                n++
            }
            return current
        }
    }

    fun calLength(length: Int) {
        var n = 1
        var current = BigInteger("0")
        var currentLength = 0
        while (currentLength < length) {
            current = calFibonacci(n)
            currentLength = current.toString().length
//            println("第${n}项：长度$currentLength")
            n++
        }
        println(n - 1)
        println(current)
    }
}