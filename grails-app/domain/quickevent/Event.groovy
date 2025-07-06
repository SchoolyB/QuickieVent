package quickevent


class Event {
    String title
    String description
    String date
    String location
    User organizer

    static belongsTo = [organizer: User]
    static hasMany = [attendees: User]
    
    static constraints = {
        organizer nullable: true
    }

    String toString() {
        return title
    }
}