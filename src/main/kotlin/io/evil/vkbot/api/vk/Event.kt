package io.evil.vkbot.api.vk

import com.vk.api.sdk.events.Events
import com.vk.api.sdk.objects.callback.MessageNew

typealias TEvent = Event<*>

open class Event<T : Any> {
    lateinit var v: String
    lateinit var type: Events
    lateinit var `object`: T
}

class MessageNewEvent : Event<MessageNew>()
