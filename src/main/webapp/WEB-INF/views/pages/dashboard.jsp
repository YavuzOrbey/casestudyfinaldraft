<% String title = "Dashboard"; %>
<%@include file="../inc/head.jsp" %>
<link rel="stylesheet" href="/styles/dashboard.css" >
<link rel="stylesheet" href="/styles/dropdown.css" >
<%@include file="../inc/nav.jsp" %>
<%@include file="../inc/messages.jsp" %>
    <c:if test="${pageContext.request.userPrincipal.name != null}">
            <form id="logoutForm" method="POST" action="${contextPath}/logout">
                <input type="hidden" name="${_csrf.parameterName}" value="${_csrf.token}"/>
            </form>
    </c:if>
    <div class="container">
        <div class="calendar-header">
            <i class="fas fa-arrow-circle-left" style="float: left; "></i>
            <h4>${month_name} 2021</h4>
            <i class="fas fa-arrow-circle-right" style="float: right;"></i>
        </div>
        <div class="main-area">
            <div class='container'>
            <% boolean start=false; %>
            <div class='row'>
            <c:forEach items="${daysOfWeek}" var="dayOfWeek" >
                <div class="col-12 col-md m-1">
                    <h4>${dayOfWeek}</h4>
                </div>
            </c:forEach>
            </div>
			<c:forEach begin="1" end="${numWeeks}" var="weekNumber" varStatus="row">
				<div class='row'>
				<c:forEach begin="1" end="7" var="dayOfWeek" varStatus="column">
	                <div class="col-12 col-md m-1 ${(dayOfWeek>=firstDay || weekNumber > 1) && (weekNumber < numWeeks  || dayOfWeek<=lastDay) ? "": "out-of-month"}" id="day-${(column.index+(row.index-1)*7)-(firstDay-1)}">
                        <h4>${(column.index+(row.index-1)*7)-(firstDay-1)}</h4>
                        <p> <a onclick="showElement('showAddMeal-1')" class=" ">Add Recipe</a></p>
                        <div id="showAddMeal-${(column.index+(row.index-1)*7)-(firstDay-1)}" class="dropdown-content">
                            <input type="text" placeholder="Search.." id="recipe-input-${(column.index+(row.index-1)*7)-(firstDay-1)}" class='input' onkeyup="showRecipes()">
                            <ul id='recipe-results-${(column.index+(row.index-1)*7)-(firstDay-1)}' class='results list-group'>
                            </ul>
                        </div>
                        <ul id="meals-day-${(column.index+(row.index-1)*7)-(firstDay-1)}" class=' list-group'></ul>
	                </div>
	            </c:forEach>
				</div>
            </c:forEach>
        </div>
        </div>
    </div>
    <script src="https://cdn.jsdelivr.net/npm/bootstrap@5.0.1/dist/js/bootstrap.bundle.min.js" integrity="sha384-gtEjrD/SeCtmISkJkNUaaKMoLD0//ElJ19smozuHV6z3Iehds+3Ulb9Bn9Plx0x4" crossorigin="anonymous"></script>
    <script src="https://cdn.jsdelivr.net/npm/axios/dist/axios.min.js"></script>
    <script src="/scripts/addMeal.js" ></script>
</body>
</html>