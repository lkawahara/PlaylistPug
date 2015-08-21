<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
<title>Search</title>
</head>
<body>
<nav>
<a href="/pugs/index">Home</a>
<a href="/pugs/upload">Upload</a>
<a href="/pugs/search">Search</a>
</nav>
<p>Search</p>
<form action='/pugs/song/search' method='post'>
<input type="text" name="searchFor"/>
<select name="genre">
<option value="country">Country</option>
<option value="rock">Rock</option>
<option value="pop">Pop</option>
<option value="electronic">Electronic</option>
<option value="folk">Folk</option>
</select>
<input type="submit" value="Search"/>
</form>
<div id="row1">${rowOne}</div>
<div id="row2">${rowTwo}</div>
<div id="row3">${rowThree}</div>
</body>
</html>