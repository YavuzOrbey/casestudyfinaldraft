<% String title = "Login"; %>
<%@include file="../inc/head.jsp" %>
<%@include file="../inc/nav.jsp" %>
<%@taglib uri="http://www.springframework.org/tags/form" prefix="form" %>
<div class="container d-flex justify-content-center ">
    <div id="form" class="p-5 shadow">
    <form method="POST" action="/login" class="form-signin">
        <h3>Login</h3>
        <div class="form-group ${error != null ? 'has-error' : ''} ">
            <div class="mb-3">
                <span>${message}</span>
                <label class="form-label">Username</label>
                <input name="username" type="text" class="form-control" placeholder="Username"
                       autofocus="true"/>
            </div>
            <div class="mb-3">
                <label class="form-label">Password</label>
                <input name="password" type="password" class="form-control" placeholder="Password"/>
                <span class='error'>${error}</span>
            </div>
            <div class='row justify-content-between mb-3'>
                <div class="form-check col-md-6">
                    <input class="form-check-input" type="checkbox" value="" >
                    <label class="form-check-label" >
                      Remember me
                    </label>
                </div>
                <div class="col-md-6"><a href='#'>Forgot your password?</a></div>
            </div>
            <input type="hidden" name="${_csrf.parameterName}" value="${_csrf.token}"/>
            <div class='row justify-content-between mb-3'>
                <div class="btn col-md-6"><a href='/register'>Create an account</a> </div>
                <button type="submit" style="background: #4B042B; color: white" class="btn col-md-6">Log in</button>
            </div>
        </div>
    </form>
    </div>
</div>
<%@include file="../inc/foot.jsp" %>