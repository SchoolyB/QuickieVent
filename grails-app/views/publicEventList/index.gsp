<meta name="layout" content="main"/>

<div class="bg-white shadow rounded-lg p-6">
    <h1 class="text-2xl font-bold mb-4">Public Events</h1>
    <div class="grid grid-cols-4 gap-4">
        <g:each in="${eventNames}" var="eventName" status="item"> 
        
            <div class="event-card mb-4 p-4 border rounded">
                <h3 class="text-lg font-semibold">${eventName}</h3>
                <p class="text-gray-600 mb-2">${eventDescriptions[item]}</p>
                <p class="text-gray-600">Date: <g:formatDate date="${eventDates[item]}" format="MMM dd, yyyy"/></p>
                <p class="text-gray-600">Location: ${eventLocations[item]}</p>
                <p class="text-gray-600">Organizer: ${eventOrganizers[item]}</p>
                <p class="text-gray-600">Attendees: ${eventAttendees[item].join(', ')}</p>
                <button class="btn btn-md ">RSVP</button> 
                <button class="btn btn-md ">Contact Organizer</button>
            </div>
        </g:each>
        
        <g:each in="${events}" var="event">
            <div class="event-card mb-4 p-4 border rounded bg-green-50">
                <h3 class="text-lg font-semibold">${event.title}</h3>
                <p class="text-gray-600 mb-2">${event.description}</p>
                <p class="text-gray-600">Date: ${event.date}</p>
                <p class="text-gray-600">Location: ${event.location}</p>
                <button class="btn btn-md ">RSVP</button> 
                <button class="btn btn-md ">Contact Organizer</button>
            </div>
        </g:each>
    </div>
    <div class="text-center mt-6">
        <g:link class="btn bg-green-500" controller="createEvent" action="create-event">Create New Event</g:link>
    </div>
</div>