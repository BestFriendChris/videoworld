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
        <#if detailedMoviesFeatureEnabled??>
            <p><span>Director:</span> <input type="text" name="director" value="${director}"/></p>
            <p><span>Actor:</span> <input type="text" name="actor" value="${actor}"/></p>
            <p><span>Actress:</span> <input type="text" name="actress" value="${actress}"/></p>
            <p><span>Category:</span> <input type="text" name="category" value="${category}"/></p>
        </#if>
    	<input type="submit" value="Create" />
	</form>
</body>
</html>