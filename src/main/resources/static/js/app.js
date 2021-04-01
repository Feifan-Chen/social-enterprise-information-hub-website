// Instanciate both classes
const eventBrite = new EventBrite();
const ui = new UI();



// Event Listner

document.getElementById('submitBtn').addEventListener('click', e =>{

    e.preventDefault();

    // Get Values from form
    const eventName = document.getElementById('event-name').value;
    const category = document.getElementById('category').value;

    // console.log (`${eventName}: ${category}`)
    // console.log(eventName)
    if(eventName !== ''){
        // Query Event Brite API
        eventBrite.queryAPI(eventName, category)
            .then(events =>{
                const eventList = events.events.events;
                if(eventList.length > 0){
                    // Print Events
                    ui.displayEvents(eventList)

                }else{
                    // If no Events are found
                    ui.printMessage('No Events Found', 'alert alert-danger mt-4 text-center')
                }
            })

    }else {
        // Print Message
        ui.printMessage('Add an Event or City', 'alert alert-danger mt-4 text-center');
    }

});