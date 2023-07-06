（显示‘参数’换行。）
赋予url、mime、enc以‘参数栈’。

如果‘url’正匹配 http://192.168.+ 那么先
	赋予1以‘mime’正匹配 video/+。
	赋予2以‘url’正匹配 +.mpeg。
	如果‘1’或者‘2’那么先
		弄个start、view、
		‘url’、
		-type、‘mime’、
		-组件、
		com.mxtech.videoplayer.ad、
		com.mxtech.videoplayer.ActivityScreen$WebDelegate。
		<script>history.go(-1)</script>。
		退出。
	了。
了。

如果‘url’等于http://192.168.99.216:4012/show.zs?i=6那么先
	弄个js、
	console.log("z ‘旧url’");
	window.setTimeout(function() {
		console.log("zz");
		（window.close();）
		window.location.href = "‘旧url’";
	}, 3000);
	。
	退出。
了。
赋予旧url【顶】以‘url’。