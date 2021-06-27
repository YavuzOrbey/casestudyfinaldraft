</head>
<body>
  <nav class='navbar navbar-expand-lg bg-dark d-flex justify-content-end'>
    <div class='d-flex flex-row justify-content-evenly'>
      <form class="d-flex h-100">
        <!-- HTML INPUT REGEX only lets you search for lower case characters between 5 and 10 characters long-->
        <input class="form-control me-2 h-auto" id='search' type="search" pattern="[a-z]{5,10}" placeholder="Search" aria-label="Search">
        <button class="btn" id='search-button' type="submit"><i class="fas fa-search text-white"></i></button>
      </form>
      <ul class='navbar-nav me-auto mb-2 mb-lg-0 bg-transparent '>

        <li class="nav-item"><a class="nav-link link-light fs-5" href="${pageContext.request.contextPath}/home">Home</a></li>
        <sec:authorize access="!isAuthenticated()">
        <li class="nav-item"><a class="nav-link link-light fs-5" href="${pageContext.request.contextPath}/login">Login</a></li>
        <li class="nav-item"><a class="nav-link link-light fs-5" href="${pageContext.request.contextPath}/register">Register</a></li>
        </sec:authorize>
        <sec:authorize access="isAuthenticated()">
        <li class="nav-item ml-3">
        <div class="btn-group ">
          <button type="button" class="btn text-white dropdown-toggle " data-bs-toggle="dropdown" data-bs-display="static" aria-expanded="false">
            <i class="fas fa-user-circle fs-2"></i>

          </button>
          <ul class="dropdown-menu dropdown-menu-end dropdown-menu-right">
            <li><a class="dropdown-item" href="/dashboard">Dashboard</a></li>
            <li><a class="dropdown-item" href="/ingredient">My Pantry</a></li>
            <li>
                <c:if test="${pageContext.request.userPrincipal.name != null}">
                    <form id="logoutForm" method="POST" action="${contextPath}/logout">
                        <input type="hidden" name="${_csrf.parameterName}" value="${_csrf.token}"/>
                    </form>
                </c:if>
            <a class="dropdown-item" onclick="document.forms['logoutForm'].submit()">Logout</a></li>
          </ul>
        </div>
        </li>
         </sec:authorize>
      </ul>
    </div>
  </nav>
  <div class='row g-3' id="secondary-nav">
    <div class='col-md-3 p-3'>
        <h6>Recipes</h6>
        <ul class='list-unstyled'>
            <li ><a href="${pageContext.request.contextPath}/recipe" class='link-secondary text-decoration-none'>Browse</a></li>
            <li ><a href="${pageContext.request.contextPath}/recipe" class='link-secondary text-decoration-none'>Search</a></li>
        </ul>
    </div>
    <div class='col-md-3 p-3'>
        <h6>Ingredients</h6>
        <ul class='list-unstyled'>
            <li><a href="${pageContext.request.contextPath}/ingredient" class='link-secondary text-decoration-none'>Browse</a></li>
            <li ><a href="${pageContext.request.contextPath}/measurement" class='link-secondary text-decoration-none'>Search</a></li>
        </ul>
    </div>
    <div class='col-md-3 p-3'>
        <h6>Nutrients</h6>
          <ul class=' list-unstyled'>
             <li ><a href="${pageContext.request.contextPath}/nutrient" class='link-secondary text-decoration-none'>Browse</a></li>
          </ul>
    </div>
    <div class='col-md-3 p-3'>
        <h6>Measurements</h6>
        <ul class=' list-unstyled'>
            <li ><a href="${pageContext.request.contextPath}/measurement" class='link-secondary text-decoration-none'>Browse</a></li>
        </ul>
    </div>
  </div>
  <div class='main-content mt-3'>