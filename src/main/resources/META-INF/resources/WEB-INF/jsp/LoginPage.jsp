<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<html>
    <head>
        <title>Todo App</title>
        <link href="webjars/bootstrap/5.1.3/css/bootstrap.min.css" rel="stylesheet"/>
        <style>
            .invalid{
                color:red;
                font-size: small;
            }
        </style>
    </head>
    <body class="container">
        <h1>CMS: Course Management System</h1>
        <hr/>
        <h2>Login</h3>
        <div>
        <c:choose>
            <c:when test="${not empty error}">
                <pre class="invalid">Error: ${error}</pre>
            </c:when>
            <c:when test="${not empty flash.error}">
                <pre class="invalid">Error: ${flash.error}</pre>
            </c:when>
        </c:choose>
        </div>
            <form method="post" class="form-group">
                <label>Name: </label><input type="text" name="username" class="form-control" required/>
                <label>Password: </label><input type="password" name="password" class="form-control" required/>
                <hr/>
                <div class="col justify-content-center">
                    <input type="submit" class="btn btn-dark"/>
                    <a href="signin" class="btn btn-light">Sign Up</a>
                </div>
            </form>
        </div>
    </body>
</html>