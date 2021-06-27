<% String title = "Measurements"; %>
<%@include file="../inc/head.jsp" %>
<%@include file="../inc/nav.jsp" %>
<%@include file="../inc/messages.jsp" %>
<%@taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
  <div class="container">
        <h1>Admin View</h1>
        <a href="create"><button id="addEntityBtn">Add New Measurement</button></a>
        <table class='table'>
            <thead>
                <tr>
                    <th>Measurement</th>
                    <th>Edit</th>
                    <th>Delete</th>
                </tr>
            </thead>
            <tbody>
                <c:forEach items="${measurements}" var="measurement">
                <tr>
                    <td>${measurement.name}</td>
                    <td><a href="edit/${measurement.id}"><button ><i class="fas fa-edit text-primary"></i></button></a></td>
                     <td><a href="delete/${measurement.id}"><button ><i class="fas fa-trash text-danger"></i></button></a></td>
                </tr>
                </c:forEach>
            </tbody>
        </table>
  </div>
<%@include file="../inc/foot.jsp" %>