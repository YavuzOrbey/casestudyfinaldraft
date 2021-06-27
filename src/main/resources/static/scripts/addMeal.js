let recipeResults = document.getElementById("recipe-results-1");

let input = document.getElementById("recipe-input-1");
let day = document.getElementById("meals-day-1");

function showElement(name){
    document.getElementById(name).classList.toggle("show");
}
function removeAllChildNodes(parent) {
    while (parent.firstChild) {
        parent.removeChild(parent.firstChild);
    }
}


function addRecipe(recipe, number){
    let li = document.createElement("LI");

    let recipeLabel = document.createElement("LABEL")
    recipeLabel.innerText = recipe.name

    li.appendChild(recipeLabel);

    let button = document.createElement("BUTTON")
    button.innerHTML = '<i class="fas fa-minus-circle"></i>'
    button.addEventListener("click", function(){
        console.log("hi")
    })
    li.dataset.id=recipe.id +"-"+number
    li.appendChild(button)
    day.appendChild(li);
    document.getElementById("day-")
    <h6 onclick="makeRecipe() " class="btn btn-outline-primary" style="cursor: pointer; ">Make Meal!</h6>
}

function clearRecipeInput(){
    showElement("showAddMeal-1")
    removeAllChildNodes(recipeResults)
    input.value ="";
}
function showRecipes() {
    let  li, a, i;
    removeAllChildNodes(recipeResults)
    if(input.value.length < 2){
        recipeResults.classList.remove("show");
    }
    if(input.value.length >= 2){
        recipeResults.classList.add("show");
        //get the value of the input and check it against the database
        axios.get("/api/recipe?q=" + input.value)
        .then(function(response){
            let results = response.data;
            for(let i =0; i<results.length; i++){
               let li = document.createElement("LI");
               let recipe = results[i];
               li.innerHTML = "<a href='#'>" +recipe.name + "</a>";
               //what happens when you click on a list element that pops up
               li.addEventListener("click", function(){
                   addRecipe(recipe, 1);
                   clearRecipeInput();
               });
               recipeResults.appendChild(li);
            }
          });
    }
}

function makeRecipe(){
axios.get("/api/checkpantry?recipeId=1")
        .then(function(response){
             let messages = document.getElementById("messages");
                      messages.innerHTML = "Meal made. Pantry updated!"
                      messages.classList.add("alert", "alert-success")
          }).catch(error=>{
          let messages = document.getElementById("messages");
          messages.innerHTML = "Not enough ingredients!"
          messages.classList.add("alert", "alert-danger")
          });
  }