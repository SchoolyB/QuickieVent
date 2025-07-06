package quickevent

class Location {
    String name
    String address
    String city
    String state
    String zipCode
    String country
    
    static constraints = {
        name blank: false, maxSize: 200
        address blank: false, maxSize: 300
        city blank: false, maxSize: 100
        state blank: false, maxSize: 50
        zipCode blank: false, maxSize: 20
        country blank: false, maxSize: 100
    }
    
    String toString() {
        return "${name}, ${address}, ${city}, ${state} ${zipCode}, ${country}"
    }
}