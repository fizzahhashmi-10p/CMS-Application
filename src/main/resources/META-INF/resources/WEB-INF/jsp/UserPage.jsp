<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<%@ page import="org.example.todoapp.model.User" %>
<%@ page import="org.example.todoapp.Role" %>

<html>
    <head>
        <title>Todo App - Users</title>
        <link href="webjars/bootstrap/5.1.3/css/bootstrap.min.css" rel="stylesheet"/>
        <link rel="stylesheet" href="https://cdnjs.cloudflare.com/ajax/libs/font-awesome/5.15.4/css/all.min.css" integrity="sha512-..." crossorigin="anonymous" />
    </head>

    <body class="container">
        <h1>CMS: Course Managament System</h1>
        <hr/> 
        <div class="col">
            <h3>Users</h3>
            <div class="col-md-6 text-right">
                <!-- <button onclick="goBack()" class="btn btn-dark">Go Back</button> -->
            </div>
        </div>
        <table class="table">
            <tr>
                <th></th>
                <th>Username</th>
                <th>Name</th>
                <th>Role</th>
                <th></th>
            </tr>
        <c:forEach items="${userlist}" var="user">
            <tr>
                <td><i class="fas fa-user"></i></td>
                <td>${user.username}</td>
                <td>${user.name}</td>
                <td>${user.role}</td>
                <td>
                <c:if test="${user.role eq Role.USER}">
                    <a href="users/delete/${user.username}" class="btn btn-light">Delete</a>
                </c:if>
                </td>
            </tr>   
        </c:forEach>
        </table>
    </body>
    <script>
        function goBack() {
            window.history.back();
        }
    </script>
</html>