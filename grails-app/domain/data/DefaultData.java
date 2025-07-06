package data;

import java.util.Calendar;
import java.util.Date;

// Just using this to store some default data as opposed to a DB
public class DefaultData {
        public static final String[] DEFAULT_EVENT_NAMES = {
                        "Local Art Gallery Opening",
                        "Tech Meetup: Innovations in AI",
                        "Community Gardening Day",
                        "Game Night at the Library",
                        "Basketball Game: Bulldogs vs. Eagles",
                        "Outdoor Movie Night: Classic Films of the 60s",
        };

        public static final String[] DEFAULT_EVENT_DESCRIPTIONS = {
                        "An exhibition showcasing local artists and their work.",
                        "A meetup for tech enthusiasts to discuss the latest in AI.",
                        "A day for the community to come together and garden.",
                        "An evening of board games and socializing at the library.",
                        "A high school basketball game between two rival teams.",
                        "A screening of classic films under the stars."
        };

        public static final Date[] DEFAULT_EVENT_DATE = {
                        Calendar.getInstance().getTime(), // tadoy
                        new Date(Calendar.getInstance().getTimeInMillis() + 86400000),
                        new Date(Calendar.getInstance().getTimeInMillis() + 172800000),
                        new Date(Calendar.getInstance().getTimeInMillis() + 259200000),
                        new Date(Calendar.getInstance().getTimeInMillis() + 345600000),
                        new Date(Calendar.getInstance().getTimeInMillis() + 432000000)
        };

        public static final String[] DEFAULT_EVENT_LOCATIONS = {
                        "Downtown Art Gallery, 123 Main St, Springfield, USA",
                        "Tech Hub, 456 Tech Ave, Springfield, USA",
                        "Community Park, 789 Greenway Dr, Springfield, USA",
                        "Springfield Public Library, 101 Library Ln, Springfield, USA",
                        "Springfield High School Gymnasium, 202 School Rd, Springfield, USA",
                        "Central Park Amphitheater, 303 Parkside Blvd, Springfield, USA"
        };

        public static final String[] DEFAULT_EVENT_ORGANIZERS = {
                        "Ted Lasso",
                        "Dennis Reynolds",
                        "George Costanza",
                        "Lucille Bluth",
                        "Eric Cartman",
                        "Homer Simpson"
        };

        public static final String[][] DEFAULT_EVENT_ATTENDEES = {
                        { "Alice Smith", "Bob Johnson", "Charlie Brown" },
                        { "Diana Prince", "Ethan Hunt", "Fiona Gallagher" },
                        { "Alice Smith", "Diana Prince", "Ethan Hunt" },
                        { "Bob Johnson", "Charlie Brown", "Fiona Gallagher" },
                        { "Alice Smith", "Charlie Brown", "Diana Prince" },
                        { "Bob Johnson", "Ethan Hunt", "Fiona Gallagher" }
        };

}
