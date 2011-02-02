<html>
<head>
    <title>Add Customer</title>
</head>
<body>
    <h1>Add new customer</h1>
    <#if flashError != "" >
        <p id="flash-error">${flashError}</p>
    </#if>
	<form id="addcustomer">
        <input type="hidden" name="adding" value="true" />
        <p><span>Display name:</span> <input type="text" name="displayname" value="${displayname}"/></p>
        <p><span>Username:</span> <input type="text" name="username" value="${username}" /></p>
        <p><span>Password:</span> <input type ="password" name="password1" /></p>
        <p><span>Password (again):</span> <input type ="password" name="password2" /></p>
    	<input type="submit" value="Create" />
	</form>
</body>
</html>