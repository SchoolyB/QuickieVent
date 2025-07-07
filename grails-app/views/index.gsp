<!doctype html>
<html>
<head>
    <meta name="layout" content="main"/>
    <title>QuickEvent - Easy Event RSVPs</title>
</head>
<body>

<!-- User Identity Modal -->
<div id="userModal" class="modal">
  <div class="modal-box">
    <h3 class="font-bold text-lg">Welcome to QuickEvent!</h3>
    <p class="py-4">Please enter your information to continue:</p>
    <form id="userForm">
      <div class="form-control">
        <label class="label">
          <span class="label-text">Name</span>
        </label>
        <input type="text" id="userName" placeholder="Enter your name" class="input input-bordered w-full" required />
      </div>
      <div class="form-control mt-4">
        <label class="label">
          <span class="label-text">Email</span>
        </label>
        <input type="email" id="userEmail" placeholder="Enter your email" class="input input-bordered w-full" required />
      </div>
      <div class="modal-action">
        <button type="submit" class="btn btn-primary">Continue</button>
      </div>
    </form>
  </div>
</div>

<!-- Hero-->
<div class="bg-gradient-to-r from-blue-600 to-purple-600 text-white">
    <div class="container mx-auto px-6 py-20">
        <div class="text-center">
            <h1 class="text-5xl font-bold mb-6">QuickEvent</h1>
            <p class="text-xl mb-8 max-w-2xl mx-auto">
                The fastest way to RSVP to events, see who's going, and connect with other attendees
            </p>
            <div class="flex justify-center space-x-4">
                <g:link uri="/events" class="bg-white text-blue-600 px-8 py-3 rounded-lg font-semibold hover:bg-gray-100 transition duration-200">
                    Browse Events
                </g:link>
                <button class="border-2 border-white text-white px-8 py-3 rounded-lg font-semibold hover:bg-white hover:text-blue-600 transition duration-200">
                    Create Event
                </button>
            </div>
        </div>
    </div>
</div>

<!-- Features -->
<div class="py-20 bg-gray-50">
    <div class="container mx-auto px-6">
        <h2 class="text-3xl font-bold text-center mb-12">Why Choose QuickEvent?</h2>
        <div class="grid md:grid-cols-3 gap-8">
            <div class="text-center">
                <div class="bg-blue-100 w-16 h-16 rounded-full flex items-center justify-center mx-auto mb-4">
                    <svg class="w-8 h-8 text-blue-600" fill="currentColor" viewBox="0 0 20 20">
                        <path d="M9 12l2 2 4-4m6 2a9 9 0 11-18 0 9 9 0 0118 0z"/>
                    </svg>
                </div>
                <h3 class="text-xl font-semibold mb-2">Quick RSVP</h3>
                <p class="text-gray-600">RSVP to events in seconds with just one click</p>
            </div>
            <div class="text-center">
                <div class="bg-green-100 w-16 h-16 rounded-full flex items-center justify-center mx-auto mb-4">
                    <svg class="w-8 h-8 text-green-600" fill="currentColor" viewBox="0 0 20 20">
                        <path d="M13 6a3 3 0 11-6 0 3 3 0 016 0zM18 8a2 2 0 11-4 0 2 2 0 014 0zM14 15a4 4 0 00-8 0v3h8v-3z"/>
                    </svg>
                </div>
                <h3 class="text-xl font-semibold mb-2">See Who's Going</h3>
                <p class="text-gray-600">View attendee lists and connect with other guests</p>
            </div>
            <div class="text-center">
                <div class="bg-purple-100 w-16 h-16 rounded-full flex items-center justify-center mx-auto mb-4">
                    <svg class="w-8 h-8 text-purple-600" fill="currentColor" viewBox="0 0 20 20">
                        <path d="M8 9a3 3 0 100-6 3 3 0 000 6zM8 11a6 6 0 016 6H2a6 6 0 016-6zM16 7a1 1 0 10-2 0v1a1 1 0 102 0V7zM12 7a1 1 0 112 0v1a1 1 0 11-2 0V7zM16 3a1 1 0 10-2 0v1a1 1 0 102 0V3zM12 3a1 1 0 112 0v1a1 1 0 11-2 0V3z"/>
                    </svg>
                </div>
                <h3 class="text-xl font-semibold mb-2">Easy Management</h3>
                <p class="text-gray-600">Organize events effortlessly with our intuitive interface</p>
            </div>
        </div>
    </div>
</div>

<!-- CTA -->
<div class="bg-white py-20">
    <div class="container mx-auto px-6 text-center">
        <h2 class="text-3xl font-bold mb-6">Ready to Get Started?</h2>
        <p class="text-xl text-gray-600 mb-8">Join thousands of users who trust QuickEvent for their event management</p>
        <div class="flex justify-center space-x-4">
            <g:link uri="/events" class="bg-blue-600 text-white px-8 py-3 rounded-lg font-semibold hover:bg-blue-700 transition duration-200">
                View Events
            </g:link>
            <button class="border-2 border-blue-600 text-blue-600 px-8 py-3 rounded-lg font-semibold hover:bg-blue-600 hover:text-white transition duration-200">
                Sign Up Free
            </button>
        </div>
    </div>
</div>

<script>
document.addEventListener('DOMContentLoaded', function() {
    // Check if user info is already stored
    const userName = sessionStorage.getItem('userName');
    const userEmail = sessionStorage.getItem('userEmail');
    
    if (!userName || !userEmail) {
        // Show modal if no user info
        document.getElementById('userModal').classList.add('modal-open');
    }
    
    // Handle form submission
    document.getElementById('userForm').addEventListener('submit', function(e) {
        e.preventDefault();
        
        const name = document.getElementById('userName').value.trim();
        const email = document.getElementById('userEmail').value.trim();
        
        if (name && email) {
            // Store user info in session storage
            sessionStorage.setItem('userName', name);
            sessionStorage.setItem('userEmail', email);
            
            // Close modal
            document.getElementById('userModal').classList.remove('modal-open');
        }
    });
    
    // Update create event button to redirect to create-event page
    document.querySelectorAll('button').forEach(button => {
        if (button.textContent.includes('Create Event')) {
            button.addEventListener('click', function() {
                window.location.href = '/create-event';
            });
        }
    });
});
</script>

</body>
</html>