<%@page language="java" contentType="text/html; charset=ISO-8859-1"
    pageEncoding="ISO-8859-1"%>
<!DOCTYPE html>
<html>
<head>
<meta charset="ISO-8859-1">
<title>Insert title here</title>
    <link href="https://cdn.jsdelivr.net/npm/bootstrap@5.0.0/dist/css/bootstrap.min.css" rel="stylesheet" integrity="sha384-wEmeIV1mKuiNpC+IOBjI7aAzPcEZeedi5yW5f2yOq55WWLwNGmvvx4Um1vskeMj0"
    crossorigin="anonymous">
    <link rel="stylesheet" href="style.css">
    <link rel="stylesheet" href="https://cdnjs.cloudflare.com/ajax/libs/font-awesome/5.11.2/css/all.min.css">
        <!-- External Fonts -->
        <link rel="preconnect" href="https://fonts.gstatic.com">
        <link href="https://fonts.googleapis.com/css2?family=Oswald&display=swap" rel="stylesheet">
        <link href="https://fonts.googleapis.com/css2?family=Merriweather:wght@300;400;700&display=swap" rel="stylesheet">
</head>
<body>
<%@include file="../inc/nav.jsp" %>
  <div class="container d-flex justify-content-center ">
        <h1>Admin View</h1>
        <button id="addEntityBtn">Add New Measurement</button>
        <table class='table'>
            <thead>
                <tr>
                    <th>Measurement</th>
                    <th>Edit</th>
                    <th>Delete</th>
                </tr>
            </thead>
            <tbody>

                <tr>
                    <td><a href="showRecipe.html">Measurement 1</td>
                    <td><button onclick="alert('You deleted this entity....Well not really but you will soon! Under Construction!')"><i class="fas fa-trash"></i></button></td>
                    <td><button onclick="alert('You deleted this entity....Well not really but you will soon! Under Construction!')"><i class="fas fa-trash"></i></button></td>
                </tr>
                <tr>
                    <td>Nutrient 2</td>
                    <td><button onclick="alert('You deleted this entity....Well not really but you will soon! Under Construction!')"><i class="fas fa-trash"></i></button></td>
                	<td><button onclick="alert('You deleted this entity....Well not really but you will soon! Under Construction!')"><i class="fas fa-trash"></i></button></td>
                </tr>

            </tbody>
        </table>
  </div>
  <script src="https://cdn.jsdelivr.net/npm/bootstrap@5.0.1/dist/js/bootstrap.bundle.min.js" integrity="sha384-gtEjrD/SeCtmISkJkNUaaKMoLD0//ElJ19smozuHV6z3Iehds+3Ulb9Bn9Plx0x4" crossorigin="anonymous"></script>

</body>
</html>