<meta name="layout" content="main"/>
<title>Create Event - QuickEvent</title>

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
    
    // Pre-fill hidden fields with user info
    document.getElementById('organizerName').value = userName;
    document.getElementById('organizerEmail').value = userEmail;
});
</script>

<div class="flex items-center justify-center min-h-screen px-6">
   <div class="w-full max-w-md">
       <h1 class="text-5xl font-bold mb-6 text-center">Create Your Event!</h1>
       <g:form controller="createEvent" action="save" method="post" class="bg-white shadow-xl rounded-lg p-8">
           <input type="hidden" name="organizerName" id="organizerName" />
           <input type="hidden" name="organizerEmail" id="organizerEmail" />
           
           <div class="mb-4">
               <label for="title" class="block text-xl font-medium text-gray-700">Event Title</label>
               <g:textField name="title" id="title" class="mt-1 block w-full border-gray-300 rounded-md shadow-md focus:ring-blue-500 focus:border-blue-500"/>
           </div>
           
           <div class="mb-4">
               <label for="date" class="block text-xl font-medium text-gray-700">Event Date</label>
               <g:datePicker name="date" id="date" class="mt-1 block w-full border-gray-300 rounded-md shadow-md focus:ring-blue-500 focus:border-blue-500"/>
           </div>
           
           <div class="mb-4">
               <label for="location" class="block text-xl font-medium text-gray-700">Location</label>
               <g:textField name="location" id="location" class="mt-1 block w-full border-gray-300 rounded-md shadow-md focus:ring-blue-500 focus:border-blue-500"/>
           </div>
           <div class="mb-4">
               <label for="description" class="block text-xl font-medium text-gray-700">Description</label>
               <g:textArea name="description" id="description" rows="4" class="mt-1 block w-full border-gray-300 rounded-md shadow-md focus:ring-blue-500 focus:border-blue-500"></g:textArea>
           </div>  
           <g:submitButton name="create" value="Create Event" class="btn bg-green-500 flex"/>
       </g:form>
   </div>
</div>