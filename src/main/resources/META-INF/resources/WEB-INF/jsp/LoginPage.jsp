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
            <pre class="invalid">${error}</pre>
        </div>
            <form method="post" class="form-group">
                <label>Name: </label><input type="text" name="username" class="form-control" required/>
                <label>Password: </label><input type="password" name="password" class="form-control" required/>
                <hr/>
                <div class="col justify-content-center">
                    <input type="submit" class="btn btn-dark" onclick="clearForm()"/>
                    <a href="signin" class="btn btn-light">Sign In</a>
                </div>
            </form>
        </div>
    </body>
</html>