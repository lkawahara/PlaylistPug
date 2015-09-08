<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<head>
<link rel="stylesheet" type="text/css" href="<%=request.getContextPath()%>/song/style">
<meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
<title>Home</title>
</head>
<body>
<div id="header">
<h1>Welcome to PugPlaylist!</h1>
</div>
<div id="navBar">
<a href="/pugs/index" id="navLink">Home</a>
<a href="/pugs/upload"id="navLink">Upload</a>
<a href="/pugs/search"id="navLink">Search</a>
</div>
<div id="content">
<img src="http://i.imgur.com/PugI0QN.jpg" id="image"/>
<p id="frontPage">Lorem ipsum dolor sit amet, consectetur adipiscing elit. Integer sodales ut tellus et faucibus. Nulla in accumsan lectus. Nam sed faucibus neque, a gravida neque. Aenean nisl risus, pretium ac commodo quis, blandit in odio. Nam commodo vulputate libero, sit amet facilisis velit. Mauris luctus leo ipsum, vel fermentum nunc consectetur imperdiet. Quisque eget enim non elit placerat cursus. Sed vel metus lacinia, porta diam nec, finibus augue. Cras posuere lorem ut tortor condimentum feugiat. Nullam suscipit ultricies tortor ut laoreet. Nam malesuada urna ac metus ullamcorper dignissim.</p>
</div>
</body>
</html>