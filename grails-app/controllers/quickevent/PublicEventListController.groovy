package quickevent

import data.DefaultData
import grails.gorm.transactions.*
import quickevent.Event
import quickevent.User


class PublicEventListController {

    def index() {
        create_default_events()

        // get all events sorted by id so they appear in consistent order
        def events = Event.list(sort: 'id', order: 'asc')

        return [events: events]
    }



    //Handle creatoing the default events
    @Transactional
    private void create_default_events() {
        if (!Event.findByTitle(DefaultData.DEFAULT_EVENT_NAMES[0])) {
            for (int i = 0; i < DefaultData.DEFAULT_EVENT_NAMES.length; i++) {
                def organizerName = DefaultData.DEFAULT_EVENT_ORGANIZERS[i]
                def organizer = User.findByFirstName(organizerName.split(' ')[0])
                if (!organizer) {
                    organizer = new User(
                        firstName: organizerName.split(' ')[0],
                        lastName: organizerName.split(' ').size() > 1 ? organizerName.split(' ')[1..-1].join(' ') : '',
                        email: "${organizerName.replaceAll(' ', '').toLowerCase()}@example.com",
                        password: 'temp123'
                    )
                    organizer.save(flush: true)
                }

                def event = new Event(
                    title: DefaultData.DEFAULT_EVENT_NAMES[i],
                    description: DefaultData.DEFAULT_EVENT_DESCRIPTIONS[i],
                    date: new java.text.SimpleDateFormat('yyyy-MM-dd').format(DefaultData.DEFAULT_EVENT_DATE[i]),
                    location: DefaultData.DEFAULT_EVENT_LOCATIONS[i],
                    organizer: organizer
                )
                event.save(flush: true)

                //add the attendees array for each event
                DefaultData.DEFAULT_EVENT_ATTENDEES[i].each { attendeeName ->
                    def attendee = User.findByFirstName(attendeeName.split(' ')[0])
                    if (!attendee) {
                        attendee = new User(
                            firstName: attendeeName.split(' ')[0],
                            lastName: attendeeName.split(' ').size() > 1 ? attendeeName.split(' ')[1..-1].join(' ') : '',
                            email: "${attendeeName.replaceAll(' ', '').toLowerCase()}@example.com",
                            password: 'temp123'
                        )
                        attendee.save(flush: true)
                    }
                    event.addToAttendees(attendee)
                }
                event.save(flush: true)
            }
        }
    }


    //RSPVing logic
    @Transactional
    def rsvp(){
        def eventId = params.eventId
        def attendeeName = params.attendeeName
        def attendeeEmail = params.attendeeEmail

        if (eventId && attendeeName && attendeeEmail) {
            def event = Event.get(eventId)
            if (event) {
                def attendee = User.findByEmail(attendeeEmail)
                if (!attendee) {
                    attendee = new User(
                        firstName: attendeeName.split(' ')[0],
                        lastName: attendeeName.split(' ').size() > 1 ? attendeeName.split(' ')[1..-1].join(' ') : '',
                        email: attendeeEmail,
                        password: 'temp123' // temp password since we dont have real auth
                    )
                    attendee.save(flush: true)
                }

                if (!event.attendees.contains(attendee)) {
                    event.addToAttendees(attendee)
                    event.save(flush: true)
                    flash.message = "Successfully RSVP'd to '${event.title}'!"
                } else {
                    //handle if a useers is already rsvp, wont add them again. EZ
                    flash.error = "You have already RSVP'd to this event."
                }
            } else {
                flash.error = "Event not found."
            }
        } else {
            flash.error = "Missing required information."
        }
        redirect(action: "index")
    }
}


