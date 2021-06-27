<% String title = "View Recipe"; %>
<%@include file="../inc/head.jsp" %>
<%@include file="../inc/nav.jsp" %>
<%@taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<style>

</style>
<div class='container'>
     <div class='row'>
     <h1 class="text-center">${recipe.name}</h1>
     </div>
     <div class="row">
        <div class='col-md-9'>
            <div class="row">
                <img src="/images/recipe-${recipe.id}.jpg"  class="img-thumbnail" />
                 <a href='/upload?recipe=${recipe.id}'>Add Image</a>
            </div>
            <div class='row'>
                <h3>Ingredients</h3>
                <!-- List of ingredients in the recipe -->
                <ul id="ingredients">
                    <c:forEach items="${recipe.recipeIngredients}" var="recipeIngredient">
                    <li>${recipeIngredient.ingredient.name} ${recipeIngredient.quantity} ${recipeIngredient.measurement.name}</li>
                    </c:forEach>
                </ul>
            </div>
        </div>
         <div class="col-md-3 border border-dark border-2 p-3">
             <div class='nutrition'>
                 <h3 class="fw-bold">Nutrition (Entire Recipe)</h3>
                 <hr class='thick dark'>
                 <h6 class="fw-bold">Serving Size ${nutrition.servingSize.first} g</h6>
                 <hr  class='thick dark'>
                 <p>Amount per Serving</p>
                 <h4 class="fw-bold">Calories ${nutrition.calories.first}<span></span></h4> <!-- Recipe calories should be calculated from the ingredient calories -->
                 <hr>
                 <c:forEach items="${recipeInfo}" var="nutrient">
                  <p  class="fw-bold">${nutrient.key} ${nutrient.value.first} ${nutrient.value.second}</span></p><hr>
                 </c:forEach>
             </div>
         </div>
     </div>
     <hr>
     <div class='row'>
        <div class='col'>
             <h3>Steps</h3>
             <!-- List of steps in the recipe -->
             <ol id="steps">
             <c:forEach items="${recipe.recipeSteps}" var="recipeStep">
                 <li>${recipeStep.text}</li>
             </c:forEach>
             </ol>
        </div>
     </div>
</div>
<%@include file="../inc/foot.jsp" %>