<html>
    <head>
        <title>Todo App- Sign In </title>
        <link href="webjars/bootstrap/5.1.3/css/bootstrap.min.css" rel="stylesheet"/>
    </head>
    <body class="container">
        <h1>CMS: Course Management System</h1>
        <hr/>
        <h3>Sign In</h3>
        <!-- <pre class="invalid">${error}</pre> -->
        <form action="users/signin" method="post" onsubmit="return validate()" class="form-group">
            <label>UserName: </label><input type="text" id="username" name="username" class="form-control" required/>
            <label>Name: </label><input type="text" name="name" class="form-control" required/>
            <label>Password: </label><input type="password" name="password" id="password" class="form-control" required/>
            <label>Confirm Password: </label><input type="password" name="confirmPassword" id="confirmPassword" class="form-control" required/>
            <label>Role:</label>
            <select name="role"  class="form-select">
                <option value="ADMIN">Admin</option>
                <option value="USER" selected>User</option>
            </select>
            <hr/>
            <input type="submit"   value="Submit" class="btn btn-dark"/>
        </form>
    </body>

    <script>
        function validate() {
            var username = document.getElementById("username").value;
            var usernameError = document.getElementById("usernameError");
        
            if (!/^\w+$/.test(username)) {
                alert( "Username must not contain special characters");
                return false;
            }

            var password = document.getElementById("password").value;
            var confirmPassword = document.getElementById("confirmPassword").value;
            
            if (password !== confirmPassword) {
                alert("Password and Confirm Password do not match");
                return false;
            }

            return true;
        }
    </script>
</html>