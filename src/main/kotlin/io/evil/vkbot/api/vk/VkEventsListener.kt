package io.evil.vkbot.api.vk

import com.google.gson.Gson
import com.vk.api.sdk.actions.LongPoll
import com.vk.api.sdk.client.VkApiClient
import com.vk.api.sdk.client.actors.GroupActor
import org.springframework.stereotype.Component
import kotlin.concurrent.thread

@Component
class VkEventsListener(
    private val vkClient: VkApiClient,
    private val vkActor: GroupActor,
    private val bus: VkEventBus,
    private val gson: Gson
) {
    fun listen() {
        thread(isDaemon = true) {
            val serverResponse = vkClient.groups().getLongPollServer(vkActor).execute()
            val longPoll = LongPoll(vkClient)
            while (true) {
                longPoll
                    .getEvents(
                        serverResponse.server,
                        serverResponse.key,
                        serverResponse.ts
                    )
                    .waitTime(60)
                    .execute()
                    .run {
                        updates.forEach {
                            bus.fire(
                                when (it["type"].asString) {
                                    "message_new" -> gson.fromJson(it, MessageNewEvent::class.java)
                                    else -> return@forEach
                                }
                            )
                        }
                        serverResponse.ts = ts
                    }
            }
        }
    }
}
