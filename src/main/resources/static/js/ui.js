class UI{
    constructor(){
        // App incialization
        this.init()
    }

    // Methods start when app starts
    init(){
        // Display categories in <select>
        this.printCategories()

        // Select the results
        this.result = document.getElementById('result')
    }

    // Display Events from the API
    displayEvents(events){
        console.log(events);
        // Build the template

        let HTMLTemplate= '';

        // Loop through events and print result

        events.forEach(eventInfo =>{
            HTMLTemplate +=
                `
            <div class = "col-md-4 mt-4">
                <div class ="card">
                    <div class = "card-body">
                        <img class="img-fluid mb-2" src="${eventInfo.logo !== null ? eventInfo.logo.url :''}">
                    </div>
                    <div class = "card-text">
                        <h2 class="text-center card-title"> ${eventInfo.name.text}</h2>
                        <p class = "lead text-info">Event Information:</p>
                        <p>${eventInfo.description.text !== null ? eventInfo.description.text.substring(0,200):''}...</p>
                        <span class="badge badge-primary">Capacity:${eventInfo.capacity}</span>
                        <span class="badge badge-secondary">Date & Time:${eventInfo.start.local}</span>

                        <a href="${eventInfo.url}" target="_blank" class = "btn btn-primary btn-block mt-4">Get Tickets</a>

                    </div>
                </div>
            </div>
            
            
            
            
            
            
            `
        });
        this.result.innerHTML = HTMLTemplate;
    }

    printCategories(){
        const categoriesList = eventBrite.getCategoriesAPI()
            .then(categories =>{
                const categoryList = categories.categories.categories;
                const categoriesSelect = document.querySelector('#category');

                // Inserts categories into Category List
                categoryList.forEach(category =>{
                    const option = document.createElement('option');
                    option.value = category.id;
                    option.appendChild(document.createTextNode(category.name))
                    categoriesSelect.appendChild(option);
                })
            })
            .catch(error => console.log(error));
    }

    printMessage(message, classList){
        const div = document.createElement('div');
        div.className = classList;
        div.appendChild(document.createTextNode(message));

        document.getElementById('search-events').appendChild(div);

        setTimeout(() =>{
            this.removeMessage();
        },3000)

    }

    removeMessage(){
        const alert = document.querySelector('.alert')
        if (alert){
            alert.remove();
        }
    }
}