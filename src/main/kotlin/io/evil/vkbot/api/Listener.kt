package io.evil.vkbot.api

interface Listener {
    fun isApplicable(event: Event): Boolean
    fun apply(event: Event)
}
