package io.evil.vkbot.api

import com.google.gson.Gson
import com.google.gson.GsonBuilder
import com.vk.api.sdk.client.VkApiClient
import com.vk.api.sdk.client.actors.GroupActor
import com.vk.api.sdk.httpclient.HttpTransportClient
import org.springframework.context.annotation.Bean
import org.springframework.context.annotation.ComponentScan
import org.springframework.context.annotation.Configuration
import org.springframework.context.annotation.PropertySource
import org.springframework.core.env.Environment
import org.springframework.core.env.get

@Configuration
@ComponentScan("io.evil.vkbot")
@PropertySource("classpath:application.properties")
open class ApiConfiguration {
    @Bean
    open fun vkClient(): VkApiClient {
        return VkApiClient(HttpTransportClient.getInstance())
    }

    @Bean
    open fun vkActor(env: Environment): GroupActor {
        return GroupActor(
            env["vk.group.id"]!!.toLong(),
            env["vk.group.token"]!!
        )
    }

    @Bean
    open fun gson(): Gson {
        return Gson()
    }
}
