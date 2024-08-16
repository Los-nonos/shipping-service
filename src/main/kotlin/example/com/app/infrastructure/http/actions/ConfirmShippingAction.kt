package example.com.app.infrastructure.http.actions

import example.com.app.application.commandHandlers.ConfirmShippingHandler
import example.com.app.application.commands.ConfirmShippingCommand

class ConfirmShippingAction(
    private val handler: ConfirmShippingHandler
) {
    fun execute(body: ConfirmShippingCommand) {

        body.validate().let {
            handler.handle(it)
        }
    }
}