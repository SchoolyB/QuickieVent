<meta name="layout" content="main"/>
<title>Edit Event - QuickEvent</title>

<script>
document.addEventListener('DOMContentLoaded', function() {
    // Check if user info is stored, if not redirect to home
    const userName = sessionStorage.getItem('userName');
    const userEmail = sessionStorage.getItem('userEmail');
    
    if (!userName || !userEmail) {
        alert('Please enter your information first.');
        window.location.href = '/';
        return;
    }
    
    // Pre-fill hidden field with user email for authorization
    document.getElementById('userEmail').value = userEmail;
    
    // Parse and set date values if event has a date
    const eventDate = '${event.date}';
    if (eventDate && eventDate !== 'null') {
        const parts = eventDate.split('-');
        if (parts.length === 3) {
            document.getElementById('date_year').value = parts[0];
            document.getElementById('date_month').value = parseInt(parts[1]);
            document.getElementById('date_day').value = parseInt(parts[2]);
        }
    }
});
</script>

<div class="flex items-center justify-center min-h-screen px-6">
   <div class="w-full max-w-md">
       <h1 class="text-5xl font-bold mb-6 text-center">Edit Event</h1>
       <g:form controller="createEvent" action="update" method="post" class="bg-white shadow-xl rounded-lg p-8">
           <input type="hidden" name="id" value="${event.id}" />
           <input type="hidden" name="userEmail" id="userEmail" />
           
           <div class="mb-4">
               <label for="title" class="block text-xl font-medium text-gray-700">Event Title</label>
               <g:textField name="title" id="title" value="${event.title}" class="mt-1 block w-full border-gray-300 rounded-md shadow-md focus:ring-blue-500 focus:border-blue-500"/>
           </div>
           
           <div class="mb-4">
               <label for="date" class="block text-xl font-medium text-gray-700">Event Date</label>
               <g:datePicker name="date" id="date" class="mt-1 block w-full border-gray-300 rounded-md shadow-md focus:ring-blue-500 focus:border-blue-500"/>
           </div>
           
           <div class="mb-4">
               <label for="location" class="block text-xl font-medium text-gray-700">Location</label>
               <g:textField name="location" id="location" value="${event.location}" class="mt-1 block w-full border-gray-300 rounded-md shadow-md focus:ring-blue-500 focus:border-blue-500"/>
           </div>
           
           <div class="mb-4">
               <label for="description" class="block text-xl font-medium text-gray-700">Description</label>
               <g:textArea name="description" id="description" rows="4" class="mt-1 block w-full border-gray-300 rounded-md shadow-md focus:ring-blue-500 focus:border-blue-500">${event.description}</g:textArea>
           </div>
           
           <div class="flex gap-4">
               <g:submitButton name="update" value="Update Event" class="btn bg-blue-500 flex"/>
               <g:link uri="/events" class="btn bg-gray-500 flex">Cancel</g:link>
           </div>
       </g:form>
   </div>
</div>