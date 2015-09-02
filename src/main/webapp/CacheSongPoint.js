var cacheSongPosition = function (){
	var songTime = document.getElementById("audio").currentTime;
	$.ajax({
	  type: "POST",
	  url: "http://playlistpug.herokuapp.com/songtime",
	  dataType: "text",
	  data: { songTime:  songTime},
	  success: function(){
		alert("SUCCESS!! cache post request");
	  }
	});
}

var onLoadCacheSongPosition = function(){
//interval in milliseconds
	window.setInterval(cacheSongPosition, 10000);
}