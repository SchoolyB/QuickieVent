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
        event.title = params.title?.trim()
        event.description = params.description?.trim()

        // Handle date picker - Grails date picker creates individual fields
        if (params.date_year && params.date_month && params.date_day) {
            def year = params.date_year
            def month = params.date_month
            def day = params.date_day
            event.date = "${year}-${month.toString().padLeft(2, '0')}-${day.toString().padLeft(2, '0')}"
        } else if (params.date && params.date != 'date.struct') {
            event.date = params.date.toString()
        }

        event.location = params.location?.trim()

        // Check for required fields
        if (!event.title || !event.description || !event.location) {
            flash.error = "All fields are required (title, description, location, date)"
            render(view: "index", model: [event: event])
            return
        }

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
                    password: 'temp123'
                )
                organizer.save(flush: true)
            }
            event.organizer = organizer
        }

        if (event.save(flush: true)) {
            flash.message = "Event '${event.title}' created successfully!"
            redirect(uri: "/events")
            return
        } else {
            flash.error = "Error creating event: ${event.errors.allErrors.collect{it.defaultMessage}.join(', ')}"
            render(view: "index", model: [event: event])
            return
        }
    }
    
    def edit() {
        def event = Event.get(params.id)
        if (!event) {
            flash.error = "Event not found"
            redirect(uri: "/events")
            return
        }
        
        // Check if current user is the organizer
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
        
        // Check if current user is the organizer
        def userEmail = params.userEmail
        if (!userEmail || event.organizer?.email != userEmail) {
            flash.error = "You can only edit events you created"
            redirect(uri: "/events")
            return
        }
        
        // Update event properties
        event.title = params.title
        event.description = params.description
        event.location = params.location
        
        // Handle date picker
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
    
    @Transactional
    def delete() {
        def event = Event.get(params.id)
        if (!event) {
            flash.error = "Event not found"
            redirect(uri: "/events")
            return
        }
        
        // Check if current user is the organizer
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