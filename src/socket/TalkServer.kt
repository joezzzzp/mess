package socket

import java.net.InetSocketAddress
import java.net.ServerSocket
import java.net.Socket
import java.util.concurrent.Executors


/**
@author zzz
@date 2019/7/2 10:28
 **/

class TalkServer: Runnable{

    private val executor = Executors.newCachedThreadPool()

    override fun run() {
        start()
    }

    private val server: ServerSocket = ServerSocket(13579)

    private val connections: MutableMap<String, Socket?> = hashMapOf()

    fun start() {
        while (true) {
            val current = server.accept()
            val input = current.getInputStream().bufferedReader()
            var line: String?
            line = input.readLine()
            if (line == null) {
                return
            }
            connections[line] = current
            executor.execute(TalkChannel(current, this))
            println("${line}连接server成功")
        }
    }

    fun sendMessage(account: String, message: String) {
        val writer = connections[account]?.getOutputStream()?.bufferedWriter()
        writer?.run {
            write(message)
            write("\n")
            flush()
        }
    }
}