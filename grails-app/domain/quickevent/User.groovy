package quickevent

class User {
    String firstName
    String lastName
    String email
    String password
    
    
    static constraints = {
        firstName blank: false, maxSize: 50
        lastName blank: false, maxSize: 50
        email blank: false, email: true, unique: true
        password blank: false, minSize: 6
    }
    
    String toString() {
        return "${firstName} ${lastName}"
    }
}