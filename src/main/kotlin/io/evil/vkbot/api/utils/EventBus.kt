package io.evil.vkbot.api.utils

import java.util.concurrent.ExecutorService

open class EventBus<E>(private val executor: ExecutorService) {
    interface Subscriber<E> {
        fun canHandle(event: E): Boolean
        fun handle(event: E)
    }

    private val handlers: MutableSet<Subscriber<E>> = mutableSetOf()

    fun fire(event: E) {
        handlers.forEach {
            if (it.canHandle(event)) {
                executor.execute { it.handle(event) }
            }
        }
    }

    fun subscribe(handler: Subscriber<E>): Boolean {
        return handlers.add(handler)
    }

    fun unsubscribe(handler: Subscriber<E>): Boolean {
        return handlers.remove(handler)
    }

    fun destroy() {
        executor.shutdown()
    }
}
