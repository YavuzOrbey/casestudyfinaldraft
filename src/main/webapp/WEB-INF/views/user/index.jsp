<% String title = "Users"; %>
<%@include file="../inc/head.jsp" %>
<%@include file="../inc/nav.jsp" %>
<%@taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<%@taglib uri="http://www.springframework.org/tags/form" prefix="form" %>
  <div class="container  ">
        <h1>Admin View</h1>
        <table class='table'>
            <thead>
                <tr>
                    <th>User</th>
                    <th>Edit</th>
                    <th>Delete</th>
                </tr>
            </thead>
            <tbody>
            <c:forEach items="${users}" var="user">
                <tr>
                    <td><a href="${user.id}">${user.name}</td>
                    <td><a href="edit/${user.id}"><button ><i class="fas fa-edit text-primary"></i></button></a></td>
                     <td><a href="delete/${user.id}"><button ><i class="fas fa-trash text-danger"></i></button></a></td>
                </tr>
            </c:forEach>
            </tbody>
        </table>
  </div>
<%@include file="../inc/foot.jsp" %>