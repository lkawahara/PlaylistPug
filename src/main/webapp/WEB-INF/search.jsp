<%@ include file="/WEB-INF/include.jsp"%>
<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
<title>Search</title>
</head>
<body>
	<nav> <a href="/newpug/home">Home</a> </nav>
	<p>Search</p>
	<form action='/newpug/song/search' method='post'>
		<input type="text" name="searchFor" /> <select name="genre">
			<option value="country">Country</option>
			<option value="rock">Rock</option>
			<option value="pop">Pop</option>
			<option value="electronic">Electronic</option>
			<option value="folk">Folk</option>
		</select> <input type="submit" value="Search" />
	</form>
	<div id="row1">${rowOne}</div>
	<div id="row2">${rowTwo}</div>
	<div id="row3">${rowThree}</div>
</body>
</html>