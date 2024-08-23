package example.com.app.infrastructure.eventBus

import example.com.app.domain.contracts.EventBus
import example.com.app.domain.events.DomainEvent
import kotlinx.coroutines.CoroutineScope
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.channels.Channel
import kotlinx.coroutines.launch
import kotlinx.coroutines.runBlocking

class InMemoryEventBus : EventBus  {

    private val subscribers = mutableMapOf<Class<out DomainEvent>, MutableList<suspend (DomainEvent) -> Unit>>()
    private val scope = CoroutineScope(Dispatchers.Default)
    private val channel = Channel<DomainEvent>()

    init {
        // Iniciar una coroutine para procesar eventos
        scope.launch {
            for (event in channel) {
                subscribers[event::class.java]?.forEach { handler ->
                    launch {
                        handler(event)
                    }
                }
            }
        }
    }

    override fun publish(events: List<DomainEvent>) = runBlocking {
        events.forEach {
            channel.send(it)
        }
    }

    override fun subscribe(eventType: Class<out DomainEvent>, handler: suspend (DomainEvent) -> Unit) {
        subscribers.computeIfAbsent(eventType) { mutableListOf() }.add(handler)
    }

}