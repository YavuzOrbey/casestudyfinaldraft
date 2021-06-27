<% String title = "Edit Ingredient"; %>
<%@include file="../inc/head.jsp" %>
<style>
/* Dropdown Button */
.dropbtn {
  background-color: #60043a;
  color: white;
  padding: 16px;
  font-size: 16px;
  border: none;
  cursor: pointer;
}

/* Dropdown button on hover & focus */
.dropbtn:hover, .dropbtn:focus {
  background-color: #7f43e7;
  color: white;
}

/* The search field */
#myInput {
  box-sizing: border-box;
  background-image: url('searchicon.png');
  background-position: 14px 12px;
  background-repeat: no-repeat;
  font-size: 16px;
  padding: 14px 20px 12px 45px;
  border: none;
  border-bottom: 1px solid #ddd;
}

/* The search field when it gets focus/clicked on */
#myInput:focus {outline: 3px solid #ddd;}

/* The container <div> - needed to position the dropdown content */
.dropdown {
  position: relative;
  display: inline-block;
}

/* Dropdown Content (Hidden by Default) */
.dropdown-content {
  display: none;
  background-color: #f6f6f6;
  min-width: 230px;
  border: 1px solid #ddd;
  z-index: 1;
}

/* Links inside the dropdown */
.dropdown-content a {
  color: black;
  padding: 12px 16px;
  text-decoration: none;
  display: block;
}

/* Change color of dropdown links on hover */
.dropdown-content a:hover {background-color: #f1f1f1}

/* Show the dropdown menu (use JS to add this class to the .dropdown-content container when the user clicks on the dropdown button) */
.show {display:block;}
</style>
<%@include file="../inc/nav.jsp" %>
<%@include file="../inc/messages.jsp" %>
<%@taglib uri="http://www.springframework.org/tags/form" prefix="form" %>
<%-- I'm going to lost a lot of the functionality of the form: stuff--%>
<form class='container' id="form" method="POST" action="/ingredient/create2" >
<input type="hidden" name="${_csrf.parameterName}" value="${_csrf.token}"/>
    <div class="mb-3">
        <label for="name" class='form-label'>Ingredient Name</label>
        <input id="name" type="text" class='form-control' placeholder="Ingredient Name" name="name"/>
    </div>
    <div class="mb-3">
        <label for="serving"  class='form-label'>Serving Size</label>
        <input id="serving" class='form-control' name="serving" type="number" placeholder="Serving Size" />
    </div>

    <div class='form-check form-check-inline mb-3'>
        <c:forEach items="${measurements}" var="measurement">
        <input type="radio"  name="measurement" value="${measurement.id}">
        <label >${measurement.name}</label><br>
        </c:forEach>
    </div>
    <div class="mb-3">
        <label for="calories"  class='form-label'>Calories per Serving</label>
        <input id="calories" class='form-control' name="calories" type="number" placeholder="Calories" />
    </div>
    <div class="dropdown">
          <a onclick="showElement('showAddNutrient')" class="dropbtn d-block">Add Nutrient</a>
          <div id="showAddNutrient" class="dropdown-content">
            <input type="text" placeholder="Search.." id="myInput" onkeyup="showNutrients()">
            <ul id='results'>
                <!-- populate this area with results from the database after an ajax request -->
            </ul>
          </div>
     </div>
    <div class="mb-3">
        <div id="nutrients"></div>
    </div>
    <button>Submit</button>
</form>

        <script src="https://cdn.jsdelivr.net/npm/axios/dist/axios.min.js"></script>
        <script src="/scripts/nutrientInput.js"></script>
<%@include file="../inc/foot.jsp" %>