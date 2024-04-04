<%@ page import="org.example.todoapp.model.Todo" %>
<%@ page import="org.example.todoapp.model.User" %>
<%@ page import="org.example.todoapp.Role" %>

<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>


<html>
    <head>
        <title>TODOs</title>
        <link href="webjars/bootstrap/5.1.3/css/bootstrap.min.css" rel="stylesheet"/>
        <link rel="stylesheet" href="https://cdnjs.cloudflare.com/ajax/libs/font-awesome/5.15.4/css/all.min.css" integrity="sha512-..." crossorigin="anonymous" />
        <style>
            input[type="checkbox"]:disabled {
            /* Change the background color */
            background-color: black; /* Example color */
            }
            a{
                color:cadetblue;
            }
        </style>

    </head>
    <body class="container">  
        <h1>CMS: Course Management System</h1>
        <hr/>
        <div class="row">
            <div class="col"><h3>Courses:</h3></div> <!-- Empty column to push content to the right -->
            <div class="col-md-6 text-right">  
                <i class="fas fa-user"></i> ${user.name}
                | <a href="logout">Logout <i class="fas fa-sign-out-alt"></i>
                </a> 
            </div>
        </div>   
        
        <table class="table">
            <thead>
                <tr>
                    <th>Course</th>
                    <th>Author
                        <c:if test="${user.role eq Role.ADMIN}">
                            <a href="/users"><i class="fas fa-external-link-alt"></i> </a>
                        </c:if>
                        
                    </th>
                    <th>Target Date</th>
                    <th>Status</th>
                    <th></th>
                    <th></th>
                </tr>
            </thead>
            <tbody>
                <c:forEach items="${todos}" var="todo">
                    <tr>
                        <!-- <td>${todo.id}</td> -->
                        <td>${todo.description}</td>
                        <td>${todo.username}</td>
                        <td>${todo.targetDate}</td>
                        <td>
                            <!--${todo.done}-->
                            <input type="checkbox" id="done" name="done" <c:if test="${todo.done}">checked</c:if> disabled>


                        </td>
                        <td> <a id="deleteLink" href="todos/delete/${todo.id}" class="btn btn-light">Delete</a></td>
                        <td>
                            <c:if test="${user.role eq Role.USER}">
                                <a href="todos/update/${todo.id}" class="btn btn-light">Update</a>
                            </c:if>
                        </td>
                    </tr>
                </c:forEach>
                <tr>
                    <form action="/todos" method="post" class="form-group" >
                        <!-- <td><h6>Add TODO:</h6>input type="number" id="id" name="id" class="form-control"</td> -->
                        <td><input type="text" id="description" name="description" class="form-control"></td>
                        <td>
                            <c:choose>
                                <c:when test="${not empty usersList}">
                                    <!-- <input type="text" id="username" name="username" class="form-control"> -->
                                    <select name="username" id="username"  class="form-select">
                                        <c:forEach var="u" items="${usersList}">
                                            <c:if test="${u.role eq Role.USER}">
                                                <option value="${u.username}">${u.username}</option>
                                            </c:if>
                                        </c:forEach>
                                    </select>
                                </c:when>
                                <c:otherwise>
                                    <select name="username" id="username">
                                            <option value="${user.username}" selected>${user.username}</option>
                                    </select>
                                </c:otherwise>
                            </c:choose>
                        </td>
                        <td><input type="date" id="targetDate" name="targetDate" class="form-control"></td>
                        <td><input type="checkbox" id="done" name="done" class="form-check-input"/></td>
                        <td><button type="submit"  class="btn btn-dark">Submit</button></td>
                    </form>
                </tr>
            </tbody>
        </table>
    </body>
    <script src="webjars/bootstrap/5.1.3/js/bootstrap.min.js"></script>
    <script src="webjars/jquery/3.6.0/jquery.min.js"></script>
    <script>

        document.getElementById("deleteLink").addEventListener("click", function(event) {
            // Prevent the default action of following the link
            event.preventDefault();
            // Display confirmation dialog
            if (confirm("Are you sure you want to delete?")) {
                // If user clicks "OK", navigate to the link's href
                window.location.href = event.target.href;
            }
        });

    </script>
</html>