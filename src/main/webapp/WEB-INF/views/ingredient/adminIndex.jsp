<% String title = "Ingredients"; %>
<%@include file="../inc/head.jsp" %>
<%@include file="../inc/nav.jsp" %>
<%@taglib uri="http://www.springframework.org/tags/form" prefix="form" %>
  <div class="container  ">
        <h1>Admin View</h1>
        <a href="create"><button id="addEntityBtn">Add New Ingredient</button></a>
        <table class='table'>
            <thead>
                <tr>
                    <th>Ingredient</th>
                    <th>Edit</th>
                    <th>Delete</th>
                </tr>
            </thead>
            <tbody>
            <c:forEach items="${ingredients}" var="ingredient">
                <tr>
                    <td><a href="${ingredient.id}">${ingredient.name}</a></td>
                    <td><a href="edit/${ingredient.id}"><button ><i class="fas fa-edit text-primary"></i></button></a></td>
                     <td><a onlick="confirmDelete()" href="delete/${ingredient.id}"><button ><i class="fas fa-trash text-danger"></i></button></a></td>
                </tr>
            </c:forEach>
            </tbody>
        </table>
  </div>
  <script>
  function confirmDelete(){
    event.preventDefault();
  }

  </script>
<%@include file="../inc/foot.jsp" %>