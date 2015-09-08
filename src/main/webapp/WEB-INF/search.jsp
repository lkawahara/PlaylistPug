<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<head>
<link rel="stylesheet" type="text/css" href="<%=request.getContextPath()%>/song/style">
<meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
<title>Search</title>
</head>
<body>
<div id="header">
<h1>Search</h1>
</div>
<div id="navBar">
<a href="/pugs/index" id="navLink">Home</a>
<a href="/pugs/upload"id="navLink">Upload</a>
<a href="/pugs/search"id="navLink">Search</a>
</div>
<div id="content">
<form action='/pugs/search' method='post' id="search">
<input type="text" name="searchFor"/>
<input type="submit" value="Search"/>
</form>
<div id="row1">${rowOne}</div>
<div id="row2">${rowTwo}</div>
<div id="row3">${rowThree}</div>
</div>

</body>
</html>