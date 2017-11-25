package com.github.luksdlt92.loginserver

import com.github.luksdlt92.loginserver.initializers.LoginInitializer
import io.netty.bootstrap.ServerBootstrap
import io.netty.channel.nio.NioEventLoopGroup
import io.netty.channel.socket.nio.NioServerSocketChannel
import mu.KotlinLogging

class LoginServer(val port : Int) {

    private val logger = KotlinLogging.logger {}

    fun run() {
        val bossGroup = NioEventLoopGroup()
        val workerGroup = NioEventLoopGroup()

        try {
            val server = ServerBootstrap()
                    .group(bossGroup, workerGroup)
                    .channel(NioServerSocketChannel::class.java)
                    .childHandler(LoginInitializer())

            val future = server.bind(port).sync()
            logger.info { "The server is listening the port $port" }

            future.channel().closeFuture().sync()
        } catch (exception : InterruptedException) {
            logger.info { "The server has been closed by an exception" }
        } finally {
            bossGroup.shutdownGracefully()
            workerGroup.shutdownGracefully()
        }
    }
}