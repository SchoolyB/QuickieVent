package quickevent

import quickevent.Event
import quickevent.User
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

        // Handle date picker - Grails date picker creates individual fields
        if (params.date_year && params.date_month && params.date_day) {
            def year = params.date_year
            def month = params.date_month
            def day = params.date_day
            event.date = "${year}-${month.toString().padLeft(2, '0')}-${day.toString().padLeft(2, '0')}"
        } else if (params.date && params.date != 'date.struct') {
            event.date = params.date.toString()
        }

        event.location = params.location

        // Find or create user as organizer
        def organizerName = params.organizerName
        def organizerEmail = params.organizerEmail

        if (organizerName && organizerEmail) {
            // Check if user already exists
            def organizer = User.findByEmail(organizerEmail)
            if (!organizer) {
                // Create new user
                organizer = new User(
                    firstName: organizerName.split(' ')[0],
                    lastName: organizerName.split(' ').size() > 1 ? organizerName.split(' ')[1..-1].join(' ') : '',
                    email: organizerEmail,
                    password: 'temp123' //Assing BS password
                )
                organizer.save(flush: true)
            }
            event.organizer = organizer
        }

        if (event.save(flush: true)) {
            flash.message = "Event '${event.title}' created successfully!"
            redirect(uri: "/events")
        } else {
            flash.error = "Error creating event: ${event.errors.allErrors.collect{it.defaultMessage}.join(', ')}"
            render(view: "index", model: [event: event])
        }
    }
}