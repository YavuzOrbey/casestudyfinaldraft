
  let token = document.querySelector('input[name="_csrf"]').value;
  axios.defaults.headers.common['X-CSRF-TOKEN'] = token;
  function addToPantry(id){
    let quantity = document.getElementsByName(`quantity[${id}]`)[0].value
    let measurement = document.getElementsByName(`measurement[${id}]`)[0].value
    ingredientToAdd = {
        id: id,
        quantity: quantity,
        measurement: measurement
    }
    let json = JSON.stringify(ingredientToAdd);
    let messages = document.getElementById("messages");
    axios.post("/api/addIngredients", json,  { headers: {'Content-Type': 'application/json', }})
    .then(response=>{
      console.log(response.data);
      messages.innerHTML = "Added!"
      messages.classList.add("alert", "alert-success")
      window.location.reload();
    }).catch(error=>{
    messages.innerHTML = error
    messages.classList.add("alert", "alert-success")
    });;
    }

let inputs = document.querySelectorAll('input[type=number]')
console.log(inputs)

