<meta name="layout" content="main"/>
<title>Page Not Found - QuickEvent</title>

<div class="min-h-screen flex items-center justify-center bg-gray-50 py-12 px-4 sm:px-6 lg:px-8">
    <div class="max-w-md w-full space-y-8 text-center">
        <div>
            <div class="mx-auto h-24 w-24 text-gray-400">
                <!-- Event calendar icon -->
                <svg class="w-full h-full" fill="none" stroke="currentColor" viewBox="0 0 24 24" xmlns="http://www.w3.org/2000/svg">
                    <path stroke-linecap="round" stroke-linejoin="round" stroke-width="2" d="M8 7V3m8 4V3m-9 8h10M5 21h14a2 2 0 002-2V7a2 2 0 00-2-2H5a2 2 0 00-2 2v12a2 2 0 002 2z"></path>
                </svg>
            </div>
            <h1 class="mt-6 text-6xl font-bold text-gray-900">404</h1>
            <h2 class="mt-2 text-3xl font-bold text-gray-900">Event Not Found</h2>
            <p class="mt-4 text-lg text-gray-600">
                Sorry, the page you're looking for doesn't exist or has been moved.
            </p>
            <g:if env="development">
                <div class="mt-4 p-4 bg-red-50 border border-red-200 rounded-lg">
                    <p class="text-sm text-red-600 font-mono">
                        <strong>Path:</strong> ${request.forwardURI}
                    </p>
                </div>
            </g:if>
        </div>
        
        <div class="space-y-4">
            <div class="flex flex-col sm:flex-row gap-4 justify-center">
                <g:link uri="/events" class="inline-flex items-center px-6 py-3 border border-transparent text-base font-medium rounded-md text-white bg-green-600 hover:bg-green-700 focus:outline-none focus:ring-2 focus:ring-offset-2 focus:ring-green-500 transition duration-150">
                    <svg class="w-5 h-5 mr-2" fill="none" stroke="currentColor" viewBox="0 0 24 24">
                        <path stroke-linecap="round" stroke-linejoin="round" stroke-width="2" d="M8 16l2.879-2.879m0 0a3 3 0 104.243-4.242 3 3 0 00-4.243 4.242zM21 12a9 9 0 11-18 0 9 9 0 0118 0z"></path>
                    </svg>
                    Browse Events
                </g:link>
                <g:link uri="/create-event" class="inline-flex items-center px-6 py-3 border border-gray-300 text-base font-medium rounded-md text-gray-700 bg-white hover:bg-gray-50 focus:outline-none focus:ring-2 focus:ring-offset-2 focus:ring-green-500 transition duration-150">
                    <svg class="w-5 h-5 mr-2" fill="none" stroke="currentColor" viewBox="0 0 24 24">
                        <path stroke-linecap="round" stroke-linejoin="round" stroke-width="2" d="M12 6v6m0 0v6m0-6h6m-6 0H6"></path>
                    </svg>
                    Create Event
                </g:link>
            </div>
            <g:link uri="/" class="inline-flex items-center text-green-600 hover:text-green-500 font-medium">
                <svg class="w-4 h-4 mr-1" fill="none" stroke="currentColor" viewBox="0 0 24 24">
                    <path stroke-linecap="round" stroke-linejoin="round" stroke-width="2" d="M10 19l-7-7m0 0l7-7m-7 7h18"></path>
                </svg>
                Go back home
            </g:link>
        </div>
        
        <div class="mt-8 text-sm text-gray-500">
            <p>Need help? Try searching for events or creating a new one.</p>
        </div>
    </div>
</div>
