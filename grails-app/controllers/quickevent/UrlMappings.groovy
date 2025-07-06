package quickevent

class UrlMappings {

    static mappings = {
        "/$controller/$action?/$id?(.$format)?"{ //url mapping
            constraints {
                // apply constraints here???
            }
        }

        "/"(view:"/index")
        "500"(view:'/error')
        "404"(view:'/notFound')
        "/events"(controller: "publicEventList", action: "index")
        "/create-event"(controller: "createEvent", action: "index")
    }
}
