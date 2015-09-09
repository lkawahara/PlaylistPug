<%@ include file="/WEB-INF/include.jsp"%>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=ISO-8859-1">
</head>
<body>
	<div class="song" onClick="selectSong()">
		<h2 class="title">${ model.getTitle() }</h2>
		<div>
			Tags:
			<ul class="genres">
				<c:forEach var="tag" items="${model.getTags()}">
					<li>${tag}</li>
				</c:forEach>
			</ul>
		</div>
	</div>
</body>
<script>
	var songTitle = document.getElementById("title");
	function selectSong() {
		alert("you are selecting song: " + songTitle);
		//TODO add functionality to redirect to playlistcontroller
	};
</script>
</html>