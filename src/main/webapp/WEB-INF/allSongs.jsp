<%@ include file="/WEB-INF/include.jsp"%>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
<title>Home</title>
</head>
<body>
	<div id="header">
		<h1>Welcome to PugPlaylist!</h1>
	</div>
	<div id="navBar">
		<a href="/allSongs">Home</a>
		<a href="/playlist">Playlist</a>
	</div>
	<img src="http://i.imgur.com/PugI0QN.jpg" />
	<ul class="songs">
		All Songs:
		<c:forEach var="song" items="${allSongs}">
			<div class="song" onClick="selectSong()">
				<h2 class="title" value="${ song.getTitle() }"></h2>
				<div>
					Tags:
					<ul class="genres">
						<c:forEach var="tag" items="${song.getTags()}">
							<li>${tag}</li>
						</c:forEach>
					</ul>
				</div>
			</div>
		</c:forEach>
	</ul>
</body>
</html>