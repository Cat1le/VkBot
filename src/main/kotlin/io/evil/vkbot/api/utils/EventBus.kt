package io.evil.vkbot.api.utils

import io.evil.vkbot.api.Event
import io.evil.vkbot.api.Listener
import java.util.concurrent.ExecutorService

class EventBus(private val executor: ExecutorService) {
    private val handlers: MutableSet<Listener> = mutableSetOf()

    fun fire(event: Event) {
        handlers.forEach {
            if (it.isApplicable(event)) {
                executor.execute { it.apply(event) }
            }
        }
    }

    fun subscribe(handler: Listener): Boolean {
        return handlers.add(handler)
    }

    fun unsubscribe(handler: Listener): Boolean {
        return handlers.remove(handler)
    }

    fun destroy() {
        executor.shutdown()
    }
}
