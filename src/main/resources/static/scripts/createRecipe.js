
let form = document.getElementById("form");

let input = document.getElementById("ingredientInput");
let ingredientResults = document.getElementById("ingredient-results");
let recipeIngredients = document.getElementById("ingredients");
let recipeSteps =  document.getElementById("steps");
let steps = [];
let numberOfIngredients = 0;
let numberOfSteps = 0;

let token = document.querySelector('input[name="_csrf"]').value;
axios.defaults.headers.common['X-CSRF-TOKEN'] = token;
form.addEventListener("submit", function(event){
    event.preventDefault();
    //go through each input in id nutrients and see populate object with if ex: nutrients { 3: 10, 4: 10 } where key is id of nutrient and value is amount
    const recipe = {};
    recipe.name = document.querySelector('input[name="name"]').value
    recipe.cuisine =  document.querySelector('input[name="cuisine"]').value

    //was originally named recipe.ingredients but want to keep it in line with model's member name
    recipe.recipeIngredients = [];
    recipe.recipeSteps = steps;
    let ingredientInputs = document.getElementsByName("ingredients");
    console.log(ingredientInputs)
     for(let i=0; i<ingredientInputs.length; i++){
             let id = parseInt(ingredientInputs[i].dataset.ingredientId,10)
             console.log(id)
            recipe.recipeIngredients.push({id: id,
                                    amount: parseInt(ingredientInputs[i].value, 10),
                                    measurement: parseInt(document.querySelector(`input[name='measurements[${id}]']:checked`).value,10)});
     }
    console.log(recipe)

    let json = JSON.stringify(recipe);
    console.log(json)
       axios.post("/api/recipe", json,  { headers: {'Content-Type': 'application/json', }})
        .then(response=>{ console.log(response.data);
         //response.data are the validation errors from the server. Try to display them underneath the respective fields
            if(response.data["name"]) document.getElementById("name-error").innerText = response.data["name"];
            else document.getElementById("name-error").innerText = "";

            if(response.data["recipeIngredients"]) document.getElementById("ingredient-error").innerText = response.data["recipeIngredients"];
            else document.getElementById("ingredient-error").innerText = "";

            if(response.data["recipeSteps"]) document.getElementById("step-error").innerText = response.data["recipeSteps"];
            else document.getElementById("step-error").innerText = "";
            //if there are no errors you can reload
            if(Object.entries(response.data).length==0)
                 window.location.href="/recipe"
         }).catch(error=>console.log(error));
})



function removeAllChildNodes(parent) {
    while (parent.firstChild) {
        parent.removeChild(parent.firstChild);
    }
}

function showElement(name){
  document.getElementById(name).classList.toggle("show");
}
function createNewStep(){
    let li = document.createElement("LI");
    let span = document.createElement("SPAN")
    span.innerText = document.getElementById("stepInput").value
    li.appendChild(span)
    recipeSteps.appendChild(li) ;
    steps.push({
       stepOrder: ++numberOfSteps,
       text: document.getElementById("stepInput").value
       });
    }
function clearIngredientInput(){
    showElement("showAddIngredient")
    removeAllChildNodes(ingredientResults)
    input.value ="";
}

function createIngredient(ingredient){ //ingredient should be an single ingredient object from an api call

     /*example:
     ingredient = {
      "created_on" : "2021-06-19T02:54:59.000+00:00",
      "updated_on" : "2021-06-19T02:54:59.000+00:00",
      "id" : 3,
      "servingSize" : 2,
      "calories" : 12,
      "name" : "Yeast",
      "category" : null
    }*/
    let li = document.createElement("LI");
    li.classList.add("ingredient-list-item");

    let labelAndQuantity = document.createElement("DIV")
    labelAndQuantity.classList.add("row")

    let labelColumn = document.createElement("DIV");
        labelColumn.classList.add("col-sm-8")

    let ingredientLabel = document.createElement("LABEL")
        ingredientLabel.innerText = ingredient.name

    labelColumn.appendChild(ingredientLabel)

    let amountColumn = document.createElement("DIV");
        amountColumn.classList.add("col-sm")

    let amountInput = document.createElement("INPUT");
        amountInput.setAttribute("type", "number");
        amountInput.setAttribute("name", "ingredients")
        amountInput.setAttribute("data-ingredient-id", ingredient.id)
        amountInput.setAttribute("placeholder", "value")
        amountInput.classList.add(...["ingredient-amount", "form-control", "form-control-sm"])

    amountColumn.appendChild(amountInput)

    labelAndQuantity.appendChild(labelColumn)
    labelAndQuantity.appendChild(amountColumn)
    li.appendChild(labelAndQuantity);

    let measurementsRow = document.createElement("DIV")
    measurementsRow.classList.add("ingredient-list-item-measurements")

    let fragment = document.createDocumentFragment();
    measurements.forEach((measurement)=> {
        let div = document.createElement("DIV");
        div.classList.add(...["form-check", "form-check-inline"])
        let measurementLabel = document.createElement("LABEL");
        measurementLabel.innerText = measurement.name
        measurementLabel.classList.add("form-check-label")

        let radioButton = document.createElement("INPUT")
        radioButton.setAttribute("type", "radio")
        radioButton.setAttribute("name", `measurements[${ingredient.id}]` )
        radioButton.setAttribute("data-nutrient-id", measurement.id)
        radioButton.setAttribute("value", measurement.id)
        radioButton.classList.add("form-check-input")

        div.appendChild(measurementLabel)
        div.appendChild(radioButton)
        fragment.appendChild(div);
        });
    measurementsRow.appendChild(fragment)
    li.appendChild(measurementsRow)
    recipeIngredients.appendChild(li)
    numberOfIngredients++
}
function showIngredients() {
    let  li, a, i;
    removeAllChildNodes(ingredientResults)
    if(input.value.length < 2){
        ingredientResults.classList.remove("show");
    }
    if(input.value.length >= 2){
        ingredientResults.classList.add("show");
        //get the value of the input and check it against the database
        axios.get("/api/ingredient?q=" + input.value)
        .then(function(response){
            let results = response.data;
            console.log(results)
            let li = document.createElement("LI");
            for(let i =0; i<results.length; i++){
               let ingredient = results[i];
               li.innerHTML = "<a href='#'>" +ingredient.name + "</a>";
               //what happens when you click on a list element that pops up
               li.addEventListener("click", function(){
                   /*abstract this away...for readability and also because createElement is faster than innerHTML
                   when you click a search result create a new li element and inside of that create a label and input
                   inside of*/
                   createIngredient(ingredient);
                   clearIngredientInput();
               });
            }
            if(results.length <1){
            li.innerHTML = "<a href='#'>No Results Found</a>"
            }
           ingredientResults.appendChild(li);
          });
    }
}