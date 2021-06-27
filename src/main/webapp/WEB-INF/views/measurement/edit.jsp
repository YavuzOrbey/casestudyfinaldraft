<% String title = "Edit Measurement"; %>
<%@include file="../inc/head.jsp" %>
<%@include file="../inc/nav.jsp" %>
<%@include file="../inc/messages.jsp" %>
<%@taglib uri="http://www.springframework.org/tags/form" prefix="form" %>
  <div class="container d-flex justify-content-center ">
    <div  class="p-5 shadow">
      <form:form method="POST" modelAttribute="measurement">
        <h3>Edit Measurement</h3>
        <div class="mb-3">
          <label for="name" class="form-label">Measurement Name</label>
          <form:input type="text" path="name" class="form-control" id="measurement_name" value="${measurement.name}"/>
          <form:errors path="name" class='error'/>
        </div>
        <div class='row justify-content-between mb-3'>
          <div class="btn col-md-6"><a href='/measurement'>Cancel</a> </div>
          <button type="submit" style="background: #4B042B; color: white" class="btn col-md-6">Update!</button>

        </div>
      </form:form>
  </div>
  </div>
<%@include file="../inc/foot.jsp" %>