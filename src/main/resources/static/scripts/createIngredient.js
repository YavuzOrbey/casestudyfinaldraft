
let form = document.getElementById("form");
let token = document.querySelector('input[name="_csrf"]').value;
axios.defaults.headers.common['X-CSRF-TOKEN'] = token;

form.addEventListener("submit", function(event){
        //need to make sure the form doesn't get submitted for real
        event.preventDefault();
        const ingredient = {};
        ingredient.name = document.querySelector('input[name="name"]').value
        ingredient.servingSize = parseInt(document.querySelector('input[name="serving"]').value,10)
        ingredient.measurement =  parseInt(document.querySelector('input[name="measurement"]:checked').value,10)
        ingredient.calories =  parseInt(document.querySelector('input[name="calories"]').value,10)
        ingredient.nutrients = [];

        //go through each input in id nutrients and see populate object with if ex: nutrients { 3: 10, 4: 10 } where key is id of nutrient and value is amount
        let nutrientInputs = document.getElementsByName("nutrients");
        for(let i=0; i<nutrientInputs.length; i++){
            ingredient.nutrients.push({id: parseInt(nutrientInputs[i].dataset.nutrientId,10), amount: parseInt(nutrientInputs[i].value, 10)});
        }
        //ensure validation at this point
        let json = JSON.stringify(ingredient);
        console.log(json)
        axios.post("/api/ingredient", json,  {
            headers: {'Content-Type': 'application/json', }
        })
        .then(response=>{
        console.log(response.data)
        //try to send person back to ingredient index after this
            let messages = document.getElementById("messages");
            messages.innerHTML = "SUCCESS";
            messages.classList.remove("alert-danger");
            messages.classList.add("alert", "alert-success");
            window.location.reload();
        })
        .catch(error=> {
        messages.innerHTML = "FAILURE";
        messages.classList.remove("alert-success");
        messages.classList.add("alert", "alert-danger");});
        });


function removeAllChildNodes(parent) {
    while (parent.firstChild) {
        parent.removeChild(parent.firstChild);
    }
}

function showElement(name){
  document.getElementById(name).classList.toggle("show");
}

function showNutrients() {
    let input, filter, ul, li, a, i;
    input = document.getElementById("myInput");
    ul = document.getElementById("results");
    removeAllChildNodes(ul)

    if(input.value.length >= 3){
    //get the value of the input and check it against the database
    axios.get("/api/nutrient?q=" + input.value)
    .then(function(response){
        let results = response.data;
        for(let i =0; i<results.length; i++){
           let li = document.createElement("LI");
           let span = document.createElement("SPAN")
           span.innerHTML = results[i].name
           li.appendChild(span)
           let button = document.createElement("A");
           button.addEventListener("click", function(){
                let div = document.createElement("DIV");
                div.innerHTML = `<label>${results[i].name} (${results[i].measurement.name})</label>
                 <input class='form-control' type="number" data-nutrient-id="${results[i].id}" name="nutrients" placeholder="value"/>`;
                //trying to do this with form:input impossible
                document.getElementById("nutrients").appendChild(div);
           })
           let icon = document.createElement("I")
           icon.classList.add("fas")
           icon.classList.add("fa-plus-square")
           button.appendChild(icon)
           li.appendChild(button);
           li.classList.add("list-group-item", "result")
           ul.appendChild(li)
                 ul.classList.remove("animate__bounceOut")
            ul.classList.add("animate__bounceIn")
        }
        });
    }
    else{
    ul.classList.remove("animate__bounceIn")
      ul.classList.add("animate__bounceOut")
    }
}