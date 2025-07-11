package quickevent

class Event {
    String title
    String description
    String date //could be a Date type???
    String location //could be own location type
    User organizer

    static belongsTo = [organizer: User]
    static hasMany = [attendees: User]

    static constraints = {
        title blank: false, nullable: false, maxSize: 200
        description blank: false, nullable: false, maxSize: 1000
        date blank: false, nullable: false
        location blank: false, nullable: false, maxSize: 300
        organizer nullable: true
    }


    String toString() {
        return title
    }
}