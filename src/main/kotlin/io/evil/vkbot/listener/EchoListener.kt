package io.evil.vkbot.listener

import io.evil.vkbot.api.utils.EventBus
import io.evil.vkbot.api.vk.Event
import org.springframework.stereotype.Component

@Component
class EchoListener : EventBus.Subscriber<Event> {
    override fun canHandle(event: Event): Boolean {
        return true
    }

    override fun handle(event: Event) {
        println("Event: $event")
    }
}

