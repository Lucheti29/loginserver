package com.github.luksdlt92.loginserver.initializers

import io.netty.channel.ChannelInitializer
import io.netty.channel.socket.SocketChannel

class LoginInitializer : ChannelInitializer<SocketChannel>() {

    override fun initChannel(socketChannel: SocketChannel) {
        val pipeline = socketChannel.pipeline()


    }
}