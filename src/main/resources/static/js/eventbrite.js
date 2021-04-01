class EventBrite{

    constructor(){
        this.auth_token = 'O5G4A7PGSULDXXO7EI4B';
        this.sortby = 'date';
    }

    // Get Categories from API
    async getCategoriesAPI(){
        // Query API
        const categoriesResponse = await fetch(`https://www.eventbriteapi.com/v3//categories/?token=${this.auth_token}`);

        // Hold for response

        const categories = await categoriesResponse.json()

        return {
            categories
        }

    }
    // Get the events from API
    async queryAPI(eventName, category){
        const eventsResponse = await  fetch(`https://www.eventbriteapi.com/v3/events/49216045517/`)

        // Wait for response and return as .JSON
        const events = await eventsResponse.json();

        return{
            events
        }
        console.log(eventsResponse );
        console.log(events);
    }
}