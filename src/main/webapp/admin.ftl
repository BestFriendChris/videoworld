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

    <h1>Actions</h1>
    <ul>
        <li><a href="/admin/addcustomer">Add new customer</a></li>
        <li><a href="/admin/addmovie">Add new movie</a></li>
    </ul>
</body>
</html>