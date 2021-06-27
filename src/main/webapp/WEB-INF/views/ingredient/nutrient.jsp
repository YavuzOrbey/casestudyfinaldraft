<% String title = "Create Ingredient"; %>
<%@include file="../inc/head.jsp" %>
<%@include file="../inc/nav.jsp" %>
<form method="POST" class='container'>
<input type="hidden" name="${_csrf.parameterName}" value="${_csrf.token}"/>
       <div class='form-check form-check-inline mb-3'>
           <c:forEach items="${nutrients}" var="nutrient">
               <label class='form-label'>${nutrient.name}</label>
               <input type="checkbox" name="chosen" value="${nutrient.id}">
           </c:forEach>
       </div>
    <button>Continue</button>
</form>
<%@include file="../inc/foot.jsp" %>