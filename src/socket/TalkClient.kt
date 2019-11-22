package socket

import java.net.Socket
import java.util.concurrent.TimeUnit
import kotlin.random.Random


/**
@author zzz
@date 2019/7/2 14:18
 **/

class TalkClient(private var account: String? = null,
                 private val sender: Boolean = true): Runnable{
    override fun run() {
        connect()
        if (sender) {
            sendMessage()
        } else {
            receiveMessage()
        }
    }

    private lateinit var connection: Socket

    fun connect() {
        connection = Socket("127.0.0.1", 13579)
        val writer = connection.getOutputStream().bufferedWriter();
        writer.run {
            write(account)
            write("\n")
            flush()
        }
    }

    fun sendMessage() {
        TimeUnit.MILLISECONDS.sleep(2000)
        var i = 10
        val random = Random(123)
        while (i > 0) {
            val writer = connection.getOutputStream().bufferedWriter()
            writer.run {
                val message = "ckx:消息${random.nextInt(100)}"
                println("${account}发送消息：$message")
                write(message)
                write("\n")
                flush()
            }
            TimeUnit.MILLISECONDS.sleep(1000)
            i--
        }
        connection.close()
    }

    fun receiveMessage() {
        val input = connection.getInputStream().bufferedReader()
        while (true) {
            val content = input.readLine()
            if (content != null) {
                println("${account}收到消息：$content")
            }
        }
    }
}