<%@ page language="java" contentType="text/html; charset=ISO-8859-1"
    pageEncoding="ISO-8859-1"%>
<!DOCTYPE html>
<html>
<head>
<meta charset="ISO-8859-1">
<title>Insert title here</title>
</head>
<body>
<form action="createuser" method="POST">

            <div class="field">
                <label for="email">Email</label>
                <input id="email" name="email" type="email" placeholder="Enter Your Email" />
            </div>
            <div class="field">
                <label for="password">Password</label>
                <input id="password" name="password" type="password" placeholder="Enter Your Password" />
            </div>
            <div class="field">
                <label for="confirm_pass">Confirm Password</label>
                <input id="confirm_pass" name="confirmPass" type="password" placeholder="Confirm Password" />
            </div>
            <div class="btn">
                <input id="sub" name="submit" type="submit" value="Submit Name" />
            </div>
        </form>
</body>
</html>