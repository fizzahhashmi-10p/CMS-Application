<html>
    <head>
        <title>TODOs</title>
        <style>
            .form-group {
                margin-bottom: 10px;
            }
        </style>
    </head>
    <body>
        <h1>Add new Task</h1>
        <div>${reqBody}</div>
        <form action="/todos" method="post">

            <div class="form-group">
                <label for="id">ID:</label>
                <input type="text" id="id" name="id">
            </div>
            <div class="form-group">
                <label for="username">Username</label>
                <input type="text" id="username" name="username">
            </div>
            <div class="form-group">
                <label for="description">Description</label>
                <input type="text" id="description" name="description">
            </div>
            <div class="form-group">
                <label for="targetDate">TargetDate</label>
                <input type="date" id="targetDate" name="targetDate">
            </div>
            <div class="form-group">
                <label for="done">Done:</label>
                <input type="checkbox" id="done" name="done"/>
            </div>
            <button type="submit">Submit</button>

        </form>
        <hr/>
        <h1>List of Todos: </h1>
        <div>${todos}</div>
    </body>
</html>