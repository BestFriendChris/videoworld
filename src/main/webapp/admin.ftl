<html>
<head>
    <title>Administration</title>
</head>
<body>
    <h1>Customers</h1>
    <ul>
	<#list users as user>
	<li>${user.displayName} (${user.username})</li>
	</#list>
	</ul>
</body>
</html>