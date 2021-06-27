<% String title = "View Ingredient"; %>
<%@include file="../inc/head.jsp" %>
<%@include file="../inc/nav.jsp" %>
<%@taglib uri="http://www.springframework.org/tags/form" prefix="form" %>

 <div class="container  ">
<h1>${ingredient.name}</h1>
 </div>

<div class='container'>
     <div class="row">
         <div class="col-md-3 offset-md-9 border border-dark border-2 p-3">
             <div class='nutrition'>
                 <h3 class="fw-bold">Nutrition Facts</h3>
                 <hr class='thick dark'>
                 <h6 class="fw-bold">Serving Size ${ingredient.servingSize} ${ingredient.measurement.name}</h6>
                 <hr  class='thick dark'>
                 <p>Amount per Serving</p>
                 <h4 class="fw-bold">Calories <span>${ingredient.calories} </span></h4>
                 <hr>
                 <c:forEach items="${ingredient.ingredientNutrients}" var="nutrient">
                  <p  class="fw-bold">${nutrient.nutrient.name} <span>${nutrient.amount} ${nutrient.nutrient.measurement.name}</span></p><hr>
                 </c:forEach>
             </div>
         </div>
     </div>
</div>
<%@include file="../inc/foot.jsp" %>