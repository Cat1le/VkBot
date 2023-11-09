package io.evil.vkbot

import io.evil.vkbot.api.ApiConfiguration
import io.evil.vkbot.api.vk.VkEventsListener
import org.springframework.context.annotation.AnnotationConfigApplicationContext
import java.util.*

fun main() {
    val ctx = AnnotationConfigApplicationContext(ApiConfiguration::class.java)
    val api = ctx.getBean(VkEventsListener::class.java)
    api.listen()
    Scanner(System.`in`).nextLine()
}
