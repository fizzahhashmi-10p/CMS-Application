<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>

<html>
    <head>
        <title>Todo App</title>
        <!-- <link  rel="stylesheet" href="webjars/bootstrap/5.1.3/css/bootstrap.min.css"/> -->
        <link rel="stylesheet" href="https://stackpath.bootstrapcdn.com/bootstrap/4.5.2/css/bootstrap.min.css">
    </head>
    <body class="container">
        <div class="col">
            <h1>CMS: Course Managament System</h1>
            <div class="col-md-6 text-right">
                <button onclick="goBack()" class="btn btn-dark">Go Back</button>
            </div>
        </div>
        <hr/>
        
        <form action="/todos/update/${todo.id}" method="post" class="form-group">
            <div class="form-group">
                <label for="id">ID: ${todo.id}</label>
                <input type="hidden" id="id" name="id" value="${todo.id}">
            </div>
            <div class="form-group">
                <label for="username">Username</label>
                <input type="text" id="username" name="username" value="${todo.username}">
            </div>
            <div class="form-group">
                <label for="description">Description</label>
                <input type="text" id="description" name="description" value="${todo.description}">
            </div>
            <div class="form-group">
                <label for="targetDate">TargetDate</label>
                <input type="date" id="targetDate" name="targetDate" value="${todo.targetDate}">
            </div>
            <div class="form-check">
                <input type="checkbox" id="done" name="done" class="form-check-input"/>
                <label for="done" class="form-check-label">Done</label>
            </div>
            <button type="submit" class="btn btn-dark">Submit</button>

        </form>
    </body>
    <script>
        function goBack() {
            window.history.back();
        }
    </script>
</html>