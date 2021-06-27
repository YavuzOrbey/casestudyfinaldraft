<% String title = "Edit Recipe"; %>
<%@include file="../inc/head.jsp" %>
<%@include file="../inc/nav.jsp" %>
<%@taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<%@taglib uri="http://www.springframework.org/tags/form" prefix="form" %>
<input type="text" class='ingredient' onkeypress='doSomething'>
<script>
        function doSomething(){
            console.log("something done");
        }
        let btn = document.getElementById("printStuff");
        btn.addEventListener("click", function(){

            var xhr = new XMLHttpRequest();

            xhr.open("GET", "https://jsonplaceholder.typicode.com/posts/");

            xhr.onload = function(){
                let arr = JSON.parse(this.responseText);
                let obj = arr[Math.floor(Math.random()*arr.length)];
                document.getElementById("response").innerHTML =
                `<h1>${obj.title}</h1>
                <h2>${obj.id}</h2>
                <p>${obj.body}</p>
                `;
            }

            xhr.send();
        })
    </script>
<%@include file="../inc/foot.jsp" %>