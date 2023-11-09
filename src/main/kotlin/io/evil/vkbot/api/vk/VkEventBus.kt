package io.evil.vkbot.api.vk

import io.evil.vkbot.api.utils.EventBus
import org.springframework.stereotype.Component
import java.util.concurrent.Executors
import javax.annotation.PreDestroy

@Component
class VkEventBus(listeners: Set<Subscriber<TEvent>>) : EventBus<TEvent>(Executors.newSingleThreadExecutor()) {
    init {
        listeners.forEach { subscribe(it) }
    }

    @PreDestroy
    private fun preDestroy() = destroy()
}
