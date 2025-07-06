package quickevent

import data.DefaultData

class PublicEventListController {

    def index() {
        def eventNames = DefaultData.DEFAULT_EVENT_NAMES
        def eventDescriptions = DefaultData.DEFAULT_EVENT_DESCRIPTIONS
        def eventDates = DefaultData.DEFAULT_EVENT_DATE
        def eventLocations = DefaultData.DEFAULT_EVENT_LOCATIONS
        def eventOrganizers = DefaultData.DEFAULT_EVENT_ORGANIZERS
        def eventAttendees = DefaultData.DEFAULT_EVENT_ATTENDEES

        def events = Event.list()

        return [
            eventNames: eventNames,
            eventDescriptions: eventDescriptions,
            eventDates: eventDates,
            eventLocations: eventLocations,
            eventOrganizers: eventOrganizers,
            eventAttendees: eventAttendees,
            events: events
        ]
    }


    //Handle logic for RSVPing.
    def rsvp(){



    }
}


