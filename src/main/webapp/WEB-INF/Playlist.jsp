<%@ include file="/WEB-INF/include.jsp"%>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
<title>Playlist</title>
</head>
<body>
	<div id="header">
		<h1>Here is your playlist!</h1>
	</div>
	<div id="navBar">
		<a href="/allSongs">Home</a>
		<a href="/playlist">Playlist</a>
	</div>
	<audio controls>
		<source id="player" src="tempFiles/110BPM.mp3" type="audio/mpeg">
	</audio>
	<button onClick="pauseVid()">pause</button>
	<button onClick="playVid()">play</button>
	<button>play</button>
	
	<ul class="songs">
		songs
		<c:forEach var="song" items="${allSongs}">
			<li onClick="selectSong()">
				<h2 class="title">${ model.getTitle() }</h2>
				<div>
					Tags:
					<ul class="genres">
						<c:forEach var="tag" items="${model.getTags()}">
							<li>${tag}</li>
						</c:forEach>
					</ul>
				</div>
			</li>
		</c:forEach>
	</ul>
</body>
<script>
var player = document.getElementById("player"); 

function playVid() { 
	player.play(); 
} 

function pauseVid() { 
	player.pause(); 
}
</script>
</html>