package io.evil.vkbot

import io.evil.vkbot.api.ApiConfiguration
import io.evil.vkbot.api.VkEventListener
import org.springframework.context.annotation.AnnotationConfigApplicationContext
import java.util.*

fun main() {
    val ctx = AnnotationConfigApplicationContext(ApiConfiguration::class.java)
    val api = ctx.getBean(VkEventListener::class.java)
    api.listen()
    Scanner(System.`in`).nextLine()
    api.destroy()
}
