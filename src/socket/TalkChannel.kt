package socket

import java.net.Socket


/**
@author zzz
@date 2019/7/2 16:56
 **/

class TalkChannel(val socket: Socket,
                  val server: TalkServer): Runnable{

    override fun run() {
        val input = socket.getInputStream().bufferedReader()
        while (true) {
            val content = input.readLine()
            if (content != null) {
                val ss = content.split(":")
                val account = ss[0]
                val message = ss[1]
                server.sendMessage(account, message)
            }
        }
    }
}