<% String title = "Recipes"; %>
<%@include file="../inc/head.jsp" %>
<%@include file="../inc/nav.jsp" %>
<%@taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<%@taglib uri="http://www.springframework.org/tags/form" prefix="form" %>
<div class="container  ">
    <a href="create"><button id="addEntityBtn">Add New Recipe</button></a>
    <table class='table'>
        <thead>
            <tr>
                <th>Recipe</th>
                <th>Edit</th>
                <th>Delete</th>
            </tr>
        </thead>
        <tbody>
        <c:forEach items="${recipes}" var="recipe">
            <tr>
                <td><a href="${recipe.id}">${recipe.name}</td>
                <td><a href="edit/${recipe.id}"><button ><i class="fas fa-edit text-primary"></i></button></a></td>
                 <td><a href="delete/${recipe.id}"><button ><i class="fas fa-trash text-danger"></i></button></a></td>
            </tr>
            </c:forEach>
        </tbody>
    </table>
</div>
<%@include file="../inc/foot.jsp" %>