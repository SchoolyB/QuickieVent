package quickevent

import quickevent.Event
import grails.gorm.transactions.*


class CreateEventController {

    def index() {

    }

    //Note: Editors scream at you about this attribute not being resolvable but it is needed
    @Transactional
    def save() {
        def event = new Event()
        event.title = params.title
        event.description = params.description
        event.date = params.date

        event.location = params.location

        if (event.save(flush: true)) {
            flash.message = "Event '${event.title}' created successfully!"
            redirect(controller: "publicEventList", action: "index")
        } else {
            flash.error = "Error creating event. Please check your form."
            render(view: "index", model: [event: event]) //Move the new event to the Pub events list page
        }
    }
}