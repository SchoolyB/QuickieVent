package quickevent

import quickevent.Event
import quickevent.User
import grails.gorm.transactions.*

class CreateEventController {
    def index() { //TODO: Come back to this

    }

    // Save a new event
    @Transactional
    def save() {
        def event = new Event()
        event.title = params.title?.trim()
        event.description = params.description?.trim()

        //date stuff
        if (params.date_year && params.date_month && params.date_day) {
            def year = params.date_year
            def month = params.date_month
            def day = params.date_day
            event.date = "${year}-${month.toString().padLeft(2, '0')}-${day.toString().padLeft(2, '0')}"
        } else if (params.date && params.date != 'date.struct') {
            event.date = params.date.toString()
        }

        event.location = params.location?.trim()


        if (!event.title || !event.description || !event.location) {
            flash.error = "All fields are required (title, description, location, date)"
            render(view: "index", model: [event: event])
            return
        }

        // Set the user as the event organiner
        def organizerName = params.organizerName
        def organizerEmail = params.organizerEmail

        if (organizerName && organizerEmail) {
            // Check if user already exists in database
            def organizer = User.findByEmail(organizerEmail)
            if (!organizer) {
                // Create new user if they dont exist yet
                organizer = new User(
                    firstName: organizerName.split(' ')[0],
                    lastName: organizerName.split(' ').size() > 1 ? organizerName.split(' ')[1..-1].join(' ') : '',
                    email: organizerEmail,
                    password: 'temp123' // obviously not secure but good enough for now
                )
                organizer.save(flush: true)
            }
            event.organizer = organizer
        }

        if (event.save(flush: true)) {
            flash.message = "Event '${event.title}' created successfully!"
            redirect(uri: "/events")
            return
        } else { //throw err if cant save
            flash.error = "Error creating event: ${event.errors.allErrors.collect{it.defaultMessage}.join(', ')}"
            render(view: "index", model: [event: event])
            return
        }
    }

    //Hanlde editing an event,
    def edit() {
        def event = Event.get(params.id)
        if (!event) {
            flash.error = "Event not found"
            redirect(uri: "/events")
            return
        }

        // Make sure organizer can only edit their own events (basic security)
        def userEmail = params.userEmail
        if (!userEmail || event.organizer?.email != userEmail) {
            flash.error = "You can only edit events you created"
            redirect(uri: "/events")
            return
        }

        return [event: event]
    }


    @Transactional
    def update() {
        def event = Event.get(params.id)
        if (!event) {
            flash.error = "Event not found"
            redirect(uri: "/events")
            return
        }

        //only the organizer can edit an event they created
        def userEmail = params.userEmail
        if (!userEmail || event.organizer?.email != userEmail) {
            flash.error = "You can only edit events you created"
            redirect(uri: "/events")
            return
        }

        //take the params and update the event with them
        event.title = params.title
        event.description = params.description
        event.location = params.location


        if (params.date_year && params.date_month && params.date_day) {
            def year = params.date_year
            def month = params.date_month
            def day = params.date_day
            event.date = "${year}-${month.toString().padLeft(2, '0')}-${day.toString().padLeft(2, '0')}"
        } else if (params.date && params.date != 'date.struct') {
            event.date = params.date.toString()
        }

        if (event.save(flush: true)) {
            flash.message = "Event '${event.title}' updated successfully!"
            redirect(uri: "/events")
        } else {
            flash.error = "Error updating event: ${event.errors.allErrors.collect{it.defaultMessage}.join(', ')}"
            render(view: "edit", model: [event: event])
        }
    }

    //Handles deleting an event
    @Transactional
    def delete() {
        def event = Event.get(params.id)
        if (!event) {
            flash.error = "Event not found"
            redirect(uri: "/events")
            return
        }

        // authorization check - cant let people delete other users events
        def userEmail = params.userEmail
        if (!userEmail || event.organizer?.email != userEmail) {
            flash.error = "You can only delete events you created"
            redirect(uri: "/events")
            return
        }

        def eventTitle = event.title
        event.delete(flush: true)
        flash.message = "Event '${eventTitle}' deleted successfully!"
        redirect(uri: "/events")
    }
}