package quickevent

class User {
    String firstName
    String lastName
    String email
    String password

    static constraints = {
        firstName blank: false, maxSize: 50
        lastName nullable: true, blank: true, maxSize: 50 //can be blank or null
        email blank: false, email: true, unique: true
        password blank: false, minSize: 6
    }


    String toString() {
        return lastName ? "${firstName} ${lastName}" : firstName
    }
}