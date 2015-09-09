<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<head>
<link rel="stylesheet" type="text/css" href="<%=request.getContextPath()%>/song/style">
<meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
<title>Upload Song</title>
</head>
<body>
<nav>
<div id="header">
<h1>Upload Songs Here</h1>
</div>
<div id="navBar">
<a href="/pugs/index" id="navLink">Home</a>
<a href="/pugs/upload"id="navLink">Upload</a>
<a href="/pugs/search"id="navLink">Search</a>
</div>
<div id="content">
<form action="pug/upload" method="post" enctype="multipart/form-data">
  <select name="genre">
  <option value="Asian">Asian</option>
  <option value="AvantGarde">AvantGarde</option>
  <option value="Blues">Blues</option>
  <option value="Classical">Classical</option>
<option value="Country">Country</option>
<option value="EasyListening">EasyListening</option>
<option value="Electronic">Electronic</option>
<option value="HipHop">HipHop</option>
<option value="Instrumental">Instrumental</option>
<option value="Jazz">Jazz</option>
<option value="Latin">Latin</option>
<option value="Pop">Pop</option>
<option value="Punk">Punk</option>
<option value="Rap">Rap</option>
<option value="Reggae">Reggae</option>
<option value="Rock">Rock</option>
<option value="Ska">Ska</option>
<option value="Soul">Soul</option>
</select>
<input type="text" name="lyrics" value="lyrics">
  <input type="file" name="song">
  <input type="submit">
</form>
</div>

</body>
</html>