<html>
<head>
    <title>Add Movie</title>
</head>
<body>
    <h1>Add new movie</h1>
    <#if flashError != "" >
        <p id="flash-error">${flashError}</p>
    </#if>
	<form id="addmovie">
        <input type="hidden" name="adding" value="true" />
        <p><span>Movie title:</span> <input type="text" name="title" value="${title}"/></p>
    	<input type="submit" value="Create" />
	</form>
</body>
</html>