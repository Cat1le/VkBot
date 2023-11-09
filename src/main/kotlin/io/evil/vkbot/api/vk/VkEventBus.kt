package io.evil.vkbot.api.vk

import io.evil.vkbot.api.utils.EventBus
import org.springframework.stereotype.Component
import java.util.concurrent.Executors
import javax.annotation.PreDestroy

@Component
class VkEventBus : EventBus<Event>(Executors.newSingleThreadExecutor()) {
    @PreDestroy
    private fun preDestroy() = destroy()
}
