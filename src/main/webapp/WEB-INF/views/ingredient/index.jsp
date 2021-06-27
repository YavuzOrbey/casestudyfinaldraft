<% String title = "Ingredients"; %>
<%@include file="../inc/head.jsp" %>
<link href='buttons.css' rel="stylesheet" >
<%@include file="../inc/nav.jsp" %>
<%@taglib uri="http://www.springframework.org/tags/form" prefix="form" %>
<%@include file="../inc/messages.jsp" %>
<div class="container  ">
    <a href="create"><button id="addEntityBtn" class='button submit-button'>Add New Ingredient</button></a>
    <table class='table'>
        <thead>
            <tr>
                <th>Ingredient</th>
                <th>Amount in Pantry</th>
                <th>Quantity to Add (Negative to Remove)</th>
                <th>Measurement</th>
                <th>Add to Pantry</th>
            </tr>
        </thead>
        <tbody>
        <c:forEach items="${ingredients}" var="ingredient">
            <tr>
                <td><a href="${ingredient.id}">${ingredient.name}</a></td>
                <td>${pantryMap[ingredient.id].first} ${pantryMap[ingredient.id].second.name}</td>
                <td><input type="number" data-ingredient-id="${ingredient.id}" name="quantity[${ingredient.id}]" min="1" ></i></td>
                <td>
                <c:forEach items="${measurements}" var="measurement">
                    <label class='form-label'>${measurement.name}</label>
                    <input type="radio" name="measurement[${ingredient.id}]" value="${measurement.id}" placeholder="Serving Size" />
                </c:forEach>
                </td>
                <td><a href="#"><button onclick="addToPantry(${ingredient.id})"><i class="fas fa-plus-square"></i></button></a></td>
            </tr>
        </c:forEach>
            <%--<tr>
                <td><a href="showRecipe.html">Measurement 1</td>
                <td><button onclick="alert('You deleted this entity....Well not really but you will soon! Under Construction!')"><i class="fas fa-trash"></i></button></td>
                <td><button onclick="alert('You deleted this entity....Well not really but you will soon! Under Construction!')"><i class="fas fa-trash"></i></button></td>
            </tr>
            <tr>
                <td>Nutrient 2</td>
                <td><button onclick="alert('You deleted this entity....Well not really but you will soon! Under Construction!')"><i class="fas fa-trash"></i></button></td>
                <td><button onclick="alert('You deleted this entity....Well not really but you will soon! Under Construction!')"><i class="fas fa-trash"></i></button></td>
            </tr>--%>

        </tbody>
    </table>
</div>
<input type="hidden" name="${_csrf.parameterName}" value="${_csrf.token}"/>
<script src="https://cdn.jsdelivr.net/npm/axios/dist/axios.min.js"></script>
<script src="/scripts/addIngredientsToPantry.js"></script>
<%@include file="../inc/foot.jsp" %>