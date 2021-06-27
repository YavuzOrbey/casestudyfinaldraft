<% String title = "Create Nutrient"; %>
<%@include file="../inc/head.jsp" %>
<%@include file="../inc/nav.jsp" %>
<%@include file="../inc/messages.jsp" %>
<%@taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<%@taglib uri="http://www.springframework.org/tags/form" prefix="form" %>

<div class="container d-flex justify-content-center">
    <div  class="p-5 shadow">
      <form:form method="POST" modelAttribute="nutrient">
        <h3>Create New Nutrient</h3>
        <div class="mb-3">
          <label for="nutrient_name" class="form-label">Nutrient Name</label>
          <form:input type="text" path="name" class="form-control" id="nutrient_name"/>
        </div>
        <div class="mb-3">
            <label>Measured in: </label>
            <c:forEach items="${measurements}" var="measurement">
                     <form:radiobutton path="measurement.id" value="${measurement.id}" label="${measurement.name}" />
            </c:forEach>
        </div>
        <div class='row justify-content-between mb-3'>
          <div class="btn col-md-6"><a href='/nutrient'>Cancel</a> </div>
          <button type="submit" style="background: #4B042B; color: white" class="btn col-md-6">Create!</button>

        </div>
      </form:form>
  </div>
  </div>
<%@include file="../inc/foot.jsp" %>