package io.evil.vkbot.listener

import com.vk.api.sdk.events.Events
import io.evil.vkbot.api.utils.EventBus
import io.evil.vkbot.api.vk.Event
import io.evil.vkbot.api.vk.TEvent
import org.springframework.stereotype.Component

@Component
class EchoListener : EventBus.Subscriber<Event<*>> {
    override fun canHandle(event: TEvent): Boolean {
        return event.type == Events.MESSAGE_NEW
    }

    override fun handle(event: TEvent) {
        println("Event: $event")
    }
}

