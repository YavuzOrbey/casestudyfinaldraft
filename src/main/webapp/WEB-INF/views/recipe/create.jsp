<% String title = "Create Recipe"; %>
<%@include file="../inc/head.jsp" %>
<link rel="stylesheet" href="/styles/dropdown.css" />
<link rel="stylesheet" href="/styles/buttons.css" />
<style>
/* Dropdown Button */


/* Links inside the dropdown */
.dropdown-content a {
  color: black;
  padding: 12px 16px;
  text-decoration: none;
  display: block;
}
#ingredientInput{
width:100%;
}

.ingredient-amount{
    width: 60px;
}
#ingredients{
margin-top: 10px;
}
/* Change color of dropdown links on hover */
.dropdown-content a:hover {background-color: #f1f1f1}

/* Show the dropdown menu (use JS to add this class to the .dropdown-content container when the user clicks on the dropdown button) */
.show {display:block;}

.input{
  border-top-left-radius: 4px;
  border-top-right-radius: 4px;
  background: rgba(223, 225, 229, 0);
}

.error{
color: red;
}
.add-button{
    text-align:center;
    cursor: pointer;
      background-color: #7f43e7;
}
.add-button:hover{
    background-color: black;
}
</style>
<%@include file="../inc/nav.jsp" %>
<%@taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<%@taglib uri="http://www.springframework.org/tags/form" prefix="form" %>

<form method="POST" class='container shadow-lg p-3 mb-5 bg-body rounded' id="form" >
<input type="hidden" name="${_csrf.parameterName}" value="${_csrf.token}"/>
    <div class="mb-3">
        <label for="name" class='form-label'>Recipe Name</label>
        <input id="name" type="text" class='form-control' placeholder="Recipe Name" name="name"/>
        <!-- figure out how to do some validation errors without form:error EDIT:6/20 got it!-->
        <span id="name-error" class='error'></span>
    </div>
    <div class="mb-3">
        <label for="cuisine"  class='form-label'>Cuisine</label>
        <input id="cuisine" class='form-control' name="cuisine" type="text" placeholder="Cuisine" />
    </div>

              <a onclick="showElement('showAddIngredient')" class=" d-block button secondary-button">Add Ingredient</a>

    <h4>Ingredients</h4>
    <span id="ingredient-error" class='error'></span>
    <div id="showAddIngredient" class="dropdown-content">
        <input type="text" placeholder="Search.." id="ingredientInput" class='input' onkeyup="showIngredients()" autocomplete="off">
        <ul id='ingredient-results' class='results'>
        </ul>
    </div>
    <ul id="ingredients" style="min-height: 300px">

    </ul>
     <h4>Steps</h4>
     <span id="step-error" class='error'></span>
    <a onclick="showElement('showAddStep')"  class=" button secondary-button d-block">Add New Step</a>
        <div id="showAddStep" class="dropdown-content row">
            <div class="form-floating" >
              <textarea class="form-control" placeholder="Step Instruction" id="stepInput" ></textarea>
              <i class="fas fa-search" style="position: absolute; right: 20px; top: calc(50% - 8px); color: #b5b5b5;"></i>
              <label for="floatingTextarea">Step Instruction</label>
            </div>
            <div >
                <a onclick="createNewStep()" class="add-button button animate__animated animate__pulse animate__infinite	infinite">ADD</i></a>
            </div>
        </div>
    <ol id="steps"></ol>

    <%--<div class='new-step' >
        <div class="mb-3">
                    <label  class='form-label'>Quanity</label>
                    <form:input class='form-control' path="recipe.recipeIngredients[]" type="text" placeholder="Ingredient Name" />
                    <form:errors path="recipe.recipeIngredients[]" class='form-error' />
        </div>
        <div class='form-check form-check-inline mb-3'>
            <c:forEach items="${measurements}" var="measurement">
             <form:radiobutton path="recipe.recipeIngredients[].measurement.id" value="${measurement.id}" label="${measurement.name}" />
             </c:forEach>
        </div>
    </div>--%>

    <button>Submit</button>
</form>


<script src="https://cdn.jsdelivr.net/npm/axios/dist/axios.min.js"></script>
<script>
let measurements = [];
// at the startup of the jsp page parse the measurements recieved from the controller into a javascript object
<c:forEach items="${measurements}" var="measurement">
    measurements.push({id: ${measurement.id}, name: '${measurement.name}' });
</c:forEach>
console.log(measurements);
</script>
<script src="/scripts/createRecipe.js"></script>

<%@include file="../inc/foot.jsp" %>