package example.com.app.domain.events

interface DomainEvent {
    abstract val eventName: String
}