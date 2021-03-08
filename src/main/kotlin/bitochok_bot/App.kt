package bitochok_bot

import com.elbekD.bot.Bot

fun main() {
    val token = System.getenv("BITOCHOK_BOT_TOKEN")
    val username = "bitochok_bot"
    val bot = Bot.createPolling(username, token) {
        // below is optional parameters
        // limit = 50
        // timeout = 30
        // allowedUpdates = listOf(AllowedUpdate.Message)
        // removeWebhookAutomatically = true
        // period = 1000
    }

    bot.onCommand("/start") { msg, _ ->
        println("User ${msg.chat.username} met the bot")
        bot.sendMessage(msg.chat.id, "Hello ${msg.chat.username}")
    }

    bot.onCommand("/echo") { msg, opts ->
        println("User ${msg.chat.username} invoked echo")
        bot.sendMessage(msg.chat.id, "${opts ?: msg.text}")
    }

    bot.onMessage { msg ->
        println(msg.chat.id)
        bot.sendMessage(msg.chat.id, "fuck off")
    }

    bot.start()
}
