# QuickieVent - Grails Event Planning Application

## Project Overview
QuickieVent is a Grails-based event planning application developed as part of a Junior Grails Developer assessment. The application allows users to create and manage events with full CRUD functionality.

## Technology Stack
- **Framework**: Grails 5.x (Spring Boot based)
- **Language**: Groovy
- **Database**: H2 (in-memory for dev/test, file-based for prod)
- **ORM**: GORM (Grails Object Relational Mapping) with Hibernate 5
- **Frontend**: GSP (Groovy Server Pages) with Bootstrap 5 and Tailwind CSS
- **Build Tool**: Gradle
- **Testing**: Spock Framework, Geb for functional testing

## Project Structure
```
QuickieVent/
├── grails-app/
│   ├── controllers/quickevent/     # Controllers handle HTTP requests
│   ├── domain/quickevent/         # Domain models (Event, User, Location)
│   ├── views/                     # GSP templates
│   ├── conf/                      # Configuration files
│   └── assets/                    # Static resources (CSS, JS, images)
├── src/
│   ├── main/groovy/              # Additional Groovy source
│   └── test/groovy/              # Test classes
└── build.gradle                   # Build configuration
```

## Domain Models

### Event (Event.groovy)
- **Properties**: name, description, date, location, organizer
- **Relationships**: belongsTo User (organizer), hasMany attendees
- **Constraints**: name (required, max 200), description (required, max 1000)

### User (User.groovy) 
- **Properties**: firstName, lastName, email, password
- **Relationships**: hasMany desiredEvents
- **Constraints**: email must be unique and valid, password min 6 chars

### Location (Location.groovy)
- **Properties**: name, address, city, state, zipCode, country
- **Constraints**: All fields required with size limits

## Controllers

### CreateEventController (CreateEventController.groovy)
- **index()**: Renders the create event form
- **Location**: `grails-app/controllers/quickevent/CreateEventController.groovy:5`
- **Note**: Currently incomplete - missing save action for form submission

### PublicEventListController
- Handles public event listing functionality

## Views (GSP Templates)

### createEvent/index.gsp
- Event creation form with Bootstrap/Tailwind styling
- **Form fields**: title, date, location, description
- **Issue**: Form submits to CreateEventController.save() which doesn't exist
- **Location**: `grails-app/views/createEvent/index.gsp`

## Configuration

### Database (application.yml)
- **Development**: H2 in-memory database (create-drop)
- **Test**: H2 in-memory database (update)
- **Production**: H2 file-based database

### Application Settings
- **Default package**: quickevent
- **Profile**: web
- **Encoding**: UTF-8
- **GORM events**: disabled for performance

## Common Commands
```bash
# Run application
./gradlew bootRun

# Run tests
./gradlew test

# Build application
./gradlew build

# Clean build
./gradlew clean

# Generate war file
./gradlew war
```

## Development Notes
- Uses H2 database for simplicity (data is lost on restart in dev mode)
- Bootstrap and jQuery included for frontend styling
- Asset pipeline configured for JS/CSS minification
- Devtools enabled for hot reloading in development

## Current Issues
1. **CreateEventController incomplete**: Missing save() action to handle form submission
2. **Domain relationships**: Event form doesn't handle Location or User relationships properly
3. **No authentication**: User management exists but no login/auth system implemented
4. **No event listing**: Public event list controller exists but functionality unclear

## Grails Concepts Used
- **MVC Architecture**: Models (Domain), Views (GSP), Controllers
- **GORM**: Object-relational mapping with constraints and relationships
- **GSP**: Groovy Server Pages for dynamic web content
- **Dependency Injection**: Spring-based IoC container
- **Convention over Configuration**: Grails naming conventions for URLs and file structure

## Next Steps for Development
1. Complete CreateEventController.save() method
2. Implement proper event listing functionality
3. Add user authentication and session management
4. Create event detail and edit pages
5. Add proper error handling and validation
6. Implement event attendance functionality