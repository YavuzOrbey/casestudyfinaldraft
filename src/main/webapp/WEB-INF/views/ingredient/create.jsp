<% String title = "Create Ingredient"; %>
<%@include file="../inc/head.jsp" %>
<link rel="stylesheet" href="/styles/buttons.css" />
<link rel="stylesheet" href="/styles/dropdown.css" />
<style>
#nutrients-header{
display:none;
}
</style>
<%@include file="../inc/nav.jsp" %>
<%@include file="../inc/messages.jsp" %>

<%@taglib uri="http://www.springframework.org/tags/form" prefix="form" %>
<%-- I'm going to lose a lot of the functionality of the form: stuff--%>
<form class='container' id="form" method="POST" action="/ingredient/create2" >
<input type="hidden" name="${_csrf.parameterName}" value="${_csrf.token}"/>
    <div class="mb-3">
        <label for="name" class='form-label'>Ingredient Name</label>
        <input id="name" type="text" class='form-control' placeholder="Ingredient Name" name="name"/>
    </div>
    <div class="mb-3">
        <label for="serving"  class='form-label'>Serving Size</label>
        <div class='row'>
            <div class='col-md-4'>
                <input id="serving" class='form-control' name="serving" type="number" placeholder="Serving Size" />
            </div>
            <div class='col-md-8 mb-3'>
            <c:forEach items="${measurements}" var="measurement">
                <div class='form-check form-check-inline '>
                <input class="form-check-input" type="radio"  name="measurement" value="${measurement.id}">
                <label class="form-check-label">${measurement.name}</label><br>
                </div>
            </c:forEach>
            </div>
        </div>
    </div>
    <div class="mb-3">
        <label for="calories"  class='form-label'>Calories per Serving</label>
        <input id="calories" class='form-control' name="calories" type="number" placeholder="Calories" />
    </div>
    <div class="dropdown">
          <a onclick="showElement('showAddNutrient')" class="button secondary-button d-block dropdown-toggle">Add Nutrient</a>
          <div id="showAddNutrient" class="dropdown-content">
          <div class="form-floating" >
            <input class="form-control" placeholder="Nutrient Search" id="myInput" onkeyup="showNutrients()" autocomplete="off"></input>
            <i class="fas fa-search" style="position: absolute; right: 20px; top: calc(50% - 8px); color: #b5b5b5;"></i>
            <label for="floatingTextarea">Nutrient Search</label>
          </div>
           <%-- <input type="text" placeholder="Search.." id="myInput" onkeyup="showNutrients()" class='form-control'>--%>
            <ul id='results' class='list-group animate__animated' >
                <!-- populate this area with results from the database after an ajax request -->
            </ul>
          </div>
     </div>
    <div class="mb-3">
        <div id="nutrients">
        <span id="nutrients-header">Nutrients Per Serving</span>
        </div>
    </div>
    <button class="submit-button button d-block">Create!</button>
</form>

        <script src="https://cdn.jsdelivr.net/npm/axios/dist/axios.min.js"></script>
        <script src="/scripts/createIngredient.js"></script>
<%@include file="../inc/foot.jsp" %>