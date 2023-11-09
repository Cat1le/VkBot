package io.evil.vkbot.listener

import io.evil.vkbot.api.Event
import io.evil.vkbot.api.Listener
import org.springframework.stereotype.Component

@Component
class EchoListener : Listener {
    override fun isApplicable(event: Event): Boolean {
        return true
    }

    override fun apply(event: Event) {
        println("Event: $event")
    }
}
