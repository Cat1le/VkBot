package io.evil.vkbot.api

import com.vk.api.sdk.actions.LongPoll
import com.vk.api.sdk.client.VkApiClient
import com.vk.api.sdk.client.actors.GroupActor
import io.evil.vkbot.api.utils.EventBus
import org.springframework.stereotype.Component
import java.util.concurrent.Executors
import javax.annotation.PreDestroy
import kotlin.concurrent.thread

@Component
class VkEventListener(
    private val vkApiClient: VkApiClient,
    private val vkActor: GroupActor,
    private val listeners: Set<Listener>
) {
    private val bus = EventBus(Executors.newSingleThreadExecutor()).also { bus ->
        listeners.forEach {
            bus.subscribe(it)
        }
    }
    private var destroyed = false

    fun listen() {
        thread {
            val lp = LongPoll(vkApiClient)
            val lpServer = vkApiClient
                .groups()
                .getLongPollServer(vkActor)
                .execute()
            var ts = lpServer.ts
            while (!destroyed) {
                val response = lp.getEvents(lpServer.server, lpServer.key, ts).execute()
                response.updates.forEach {
                    bus.fire(it)
                }
                ts = response.ts
            }
        }
    }

    @PreDestroy
    fun destroy() {
        destroyed = true
        bus.destroy()
    }
}
