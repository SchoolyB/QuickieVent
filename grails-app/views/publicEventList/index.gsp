<meta name="layout" content="main"/>

<script>
document.addEventListener('DOMContentLoaded', function() {
    // Check if user info is stored
    const userName = sessionStorage.getItem('userName');
    const userEmail = sessionStorage.getItem('userEmail');
    
    if (!userName || !userEmail) {
        alert('Please enter your information first.');
        window.location.href = '/';
        return;
    }
    
    // Add RSVP functionality to all RSVP buttons
    document.querySelectorAll('.rsvp-btn').forEach(button => {
        button.addEventListener('click', function() {
            const eventId = this.dataset.eventId;
            if (eventId) {
                // Create form and submit
                const form = document.createElement('form');
                form.method = 'POST';
                form.action = '/publicEventList/rsvp';
                
                const eventIdInput = document.createElement('input');
                eventIdInput.type = 'hidden';
                eventIdInput.name = 'eventId';
                eventIdInput.value = eventId;
                
                const nameInput = document.createElement('input');
                nameInput.type = 'hidden';
                nameInput.name = 'attendeeName';
                nameInput.value = userName;
                
                const emailInput = document.createElement('input');
                emailInput.type = 'hidden';
                emailInput.name = 'attendeeEmail';
                emailInput.value = userEmail;
                
                form.appendChild(eventIdInput);
                form.appendChild(nameInput);
                form.appendChild(emailInput);
                
                document.body.appendChild(form);
                form.submit();
            }
        });
    });
});
</script>

<div class="bg-white shadow rounded-lg p-6">
    <g:if test="${flash.message}">
        <div class="alert alert-success mb-4">${flash.message}</div>
    </g:if>
    <g:if test="${flash.error}">
        <div class="alert alert-error mb-4">${flash.error}</div>
    </g:if>
    
    <h1 class="text-2xl font-bold mb-4">Public Events</h1>
    <div class="grid grid-cols-4 gap-4">
        <g:each in="${events}" var="event">
            <div class="event-card mb-4 p-4 border rounded">
                <h3 class="text-lg font-semibold">${event.title}</h3>
                <p class="text-gray-600 mb-2">${event.description}</p>
                <p class="text-gray-600">Date: ${event.date}</p>
                <p class="text-gray-600">Location: ${event.location}</p>
                <p class="text-gray-600">Organizer: ${event.organizer?.toString()}</p>
                <p class="text-gray-600">Attendees: 
                    <g:if test="${event.attendees}">
                        ${event.attendees.collect{it.toString()}.join(', ')}
                    </g:if>
                    <g:else>
                        None yet
                    </g:else>
                </p>
                <button class="btn btn-md rsvp-btn" data-event-id="${event.id}">RSVP</button> 
                <button class="btn btn-md">Contact Organizer</button>
            </div>
        </g:each>
    </div>
    <div class="text-center mt-6">
        <g:link class="btn bg-green-500" controller="createEvent" action="create-event">Create New Event</g:link>
    </div>
</div>