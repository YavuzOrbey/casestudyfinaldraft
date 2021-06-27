<% String title = "Register"; %>
<%@include file="../inc/head.jsp" %>

<%@include file="../inc/nav.jsp" %>
<%@taglib uri="http://www.springframework.org/tags/form" prefix="form" %>
  <div class="container d-flex justify-content-center ">
    <div id="form" class="p-5 shadow">
      <form:form method="POST" modelAttribute="userForm">
        <h3>Register</h3>
        <div class="mb-3">
          <label for="email" class="form-label">Email</label>
          <form:input type="email" path="email"  id="email" cssClass="form-control"/>
                <form:errors path="email" class='error' />
        </div>
        <div class="mb-3">
          <label for="username" class="form-label">Username</label>
          <form:input type="text" path="username"  id="username" cssClass="form-control"/>
          <form:errors path="username" class='error' />
        </div>
        <div class="mb-3">
          <label for="password" class="form-label">Password</label>
          <form:password path="password" id="password" cssClass="form-control"/>
          <form:errors path="password" class='error' />
        </div>
        <div class="mb-3">
          <label for="password-again" class="form-label">Confirm Password</label>
          <form:password path="passwordConfirm" id="password-again" cssClass="form-control"/>
          <form:errors path="passwordConfirm" class='error' />
        </div>
        <div class='row justify-content-between mb-3'>
          <div class="btn col-md-6"><a href='login'>Already Registered? Sign In here</a> </div>
          <button type="submit" style="background: #4B042B; color: white" class="btn col-md-6">Sign Up</button>
        </div>
      </form:form>
  </div>
  </div>
<%@include file="../inc/foot.jsp" %>