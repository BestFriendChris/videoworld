<html>
<head>
    <title>Login</title>
</head>
<body>
    <h1>Login</h1>
	<form id="login">
        <p><span>Username:</span> <input type="text" name="username" /></p>
        <p><span>Password:</span> <input type ="password" name="password" /></p>
    	<input type="submit" value="login" />
	</form>

    <div id="existing-users">
        <p>Current valid users:</p>
        <ul>
            <#list customers as customer>
            <li>${customer.username}</li>
            </#list>
        </ul>
        <p>Password is: {initials}-password (e.g: "zt-password")</p>
    </div>
</body>
</html>