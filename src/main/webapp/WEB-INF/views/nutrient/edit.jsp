<% String title = "Edit Nutrient"; %>
<%@include file="../inc/head.jsp" %>
<%@include file="../inc/nav.jsp" %>
<%@include file="../inc/messages.jsp" %>
<%@taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<%@taglib uri="http://www.springframework.org/tags/form" prefix="form" %>
  <div class="container d-flex justify-content-center ">
    <div  class="p-5 shadow">
      <form:form method="POST" modelAttribute="nutrient">
        <h3>Edit Nutrient</h3>
        <div class="mb-3">
          <label for="name" class="form-label">Nutrient Name</label>
          <form:input type="text" path="name" class="form-control" id="measurement_name" value="${measurement.name}"/>
          <form:errors path="name" class='error'/>
        </div>
        <div class="mb-3">
            <label>Measured in: </label>
            <c:forEach items="${measurements}" var="measurement">
            <fieldset>
                <label>${measurement.name}</label>
                <input type="radio"  name="measurement" value="${measurement.id}" ${nutrient.measurement.id==measurement.id ? "checked": ""} />
            </fieldset>
            </c:forEach>
        </div>
        <div class='row justify-content-between mb-3'>
          <div class="btn col-md-6"><a href='/nutrient'>Cancel</a> </div>
          <button type="submit" style="background: #4B042B; color: white" class="btn col-md-6">Update!</button>

        </div>
      </form:form>
  </div>
  </div>
<%@include file="../inc/foot.jsp" %>