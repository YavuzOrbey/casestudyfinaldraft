<% String title = "Create Measurement"; %>
<%@include file="../inc/head.jsp" %>
<%@include file="../inc/nav.jsp" %>
<%@include file="../inc/messages.jsp" %>
  <div class="container d-flex justify-content-center ">
    <div  class="p-5 shadow">
      <form method="POST" action="create">
          <input type="hidden" name="${_csrf.parameterName}" value="${_csrf.token}"/>
        <h3>Create New Measurement</h3>
        <div class="mb-3">
          <label for="measurement_name" class="form-label">Measurement Name</label>
          <input type="text" name="measurement_name" class="form-control" id="measurement_name">
        </div>
        <div class='row justify-content-between mb-3'>
          <div class="btn col-md-6"><a href='/measurement'>Cancel</a> </div>
          <button type="submit" style="background: #4B042B; color: white" class="btn col-md-6">Create!</button>

        </div>
      </form>
  </div>
  </div>
  <%@include file="../inc/foot.jsp" %>
