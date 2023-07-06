别名“删除”以“参数1”。

如果‘删除’等于“”那么
	赋予“删除”以下代码
		弄个“删除文件”、‘参数1’
	上代码。

赋予“项宽”、“边宽”以“120”、“2”。

赋予“区宽”以弄个“宽”。
赋予“区宽”以算术“‘区宽’-40”。
赋予“式”以“‘区宽’/‘项宽’”。
赋予“个”以算术‘式’、“0”。
赋予“项宽2”以算术“‘区宽’/‘个’”、“0”。
显示“个‘个’=‘式’#‘项宽2’项宽”。
别名“项宽”以“项宽2”。

赋予“图宽”以算术“‘项宽’-‘边宽’”。
“<style>
img {
	max-width:‘图宽’px;
	max-height:‘图宽’px;
}
.item {
	float:left;
	display:inline-table;
	padding:‘边宽’px;
}
.item-1 {
	width:‘项宽’px;
	height:‘项宽’px;
	border:1px dashed #000;
	text-align:center;
	vertical-align:middle;
	display:table-cell;
}
.title {
	word-break:break-all;
}
.bar {
	z-index:1;
	background-color:rgba(255,255,255,0.96);
	padding:10px;
	border:1px dashed #000;
}
#bar {
	position:fixed;
	left:50%;
	top:50%;
	width:200px;
	height:70px;
	margin:-40px 0px 0px -110px;
}
#bar2 {
	position:absolute;
}
</style>”。
加载“js.zs”。
下原样<script>
function onlongclick__(id, from, url) {
	log__(id + ', ' + from + url);
	if(id == "no") return;
	var e3 = e__(id), i = e3.dataset.i,
		e = e__('item' + i),
		e2 = e__('bar2'), s2 = e2.style;
	s2.top = (e.offsetTop + e3.offsetHeight) + 'px';
	s2.left = e.offsetLeft + 'px';
	e2.dataset.i = id;
	show__(e2);
}
function select__(act) {
	var a = qa__('input[type=checkbox]:checked');
	var hide_bar = a.length > 0;
	var zs;
	switch(act) {
	case 'fx':
		hide_bar = false;
		//              SEND_MULTIPLE
		zs = '弄个start、send_multiple、-type、*/*、-数文件';
		break;
	}
	for(var i = 0; i < a.length; i++) {
		var e = a[i];
		var path = e.value;
		switch(act) {
		case 'rm':
			var name = e.dataset.n;
			if(confirm('删除 (' + (i + 1) + '/' + a.length + ') ' + name + '\n' + path)) {
				var item_i = e.dataset.i;
				var s = z$.f4('上原样先弄个“换码”、‘删除’了下原样', path, name, item_i);
				if(s) {
					var e2 = e__('item' + item_i);
					del__(e2);
				} else {
					alert('失败');
					hide_bar = false;
				}
			} else {
				e.checked = false;
				hide_bar = false;
			}
			break;
		case 'fx':
			zs += '、下原样'  + path + '上原样';
			break;
		default:
			alert(act + ' 待实现');
			hide_bar = false;
			break;
		}
	}
	if(zs)
		z$.f1(zs);
	if(hide_bar)
		hide__('bar')
}
function open__(id) {
	var e = e__(id), path = e.value, typ = e.dataset.t;
	log__(path + ' ' + typ);
	switch(typ) {
	case 'url':
		z$.f2('弄个“新网页”、‘参数1’', path);
		break;
	case 'js':
		z$.f1(path);
		break;
	default:
		path = 'file://' + path;
		if(typ.match(/^(text|image|audio)\//)
		|| typ == 'video/mp4')
			z$.f2('弄个“打开”、‘参数1’', path);
		else
			z$.f3('弄个“打开”、‘参数1’、‘参数2’', path, typ);
		break;
	}
	return false
}
function bar2__(act) {
	var e2 = e__('bar2'), id = e2.dataset.i;
	var e = e__(id), path = e.value, url, typ = e.dataset.t;
	switch(typ) {
	case 'url':
		url = path;
		break;
	default:
		url = 'file://' + path;
		break;
	}
	switch(act) {
	case 'o':
		z$.f2('弄个“打开”、‘参数1’', url);
		break;
	case 'o2':
		z$.f3('弄个“打开”、‘参数1’、‘参数2’', url, typ);
		break;
	case 'i':
		var url = 'p=' + encodeURIComponent(url) +
			'&n=' + encodeURIComponent(e.dataset.n) +
			'&t=' + typ;
		var m = e.dataset.m;
		if(m)
			url += '&m=' + encodeURIComponent(m);
		var url2 = window.location.href;
		url2 += (url2.indexOf('?') > 0 ? '&' : '?') + url;
		log__(url2);
		z$.f2('弄个“新网页”、‘参数1’', url2);
		break;
	case 'e':
		if(typ.match(/^(text)\//)) {
			var i2;
			i2 = path.indexOf('?'); if(i2 > 0) path = path.substr(0, i2);
			i2 = path.indexOf('#'); if(i2 > 0) path = path.substr(0, i2);
			z$.f2('弄个编辑、‘参数1’、-注意为‘参数1’', path);
		} else {
			alert(typ + ' 无法编辑');
			return
		}
		break;
	case 's':
		e.checked = !e.checked;
		show__('bar');
		break;
	}
	hide__(e2)
}
function add_item__(i, sel_id, sel_tag, path, typ, name, attr2, htm2) {
	var d = new__("div");
	d.className = "item";
	d.id = "item" + i;
	htm__(d,
		'<div><input type=checkbox id=' + sel_id + ' value="' + path + '" onclick="show__(' + "'" + 'bar' + "'" + ')" data-i=' + i + ' data-t="' + typ + '" data-n="' + name + '"' + attr2 +
		'></div>' +
		'<a href="' + sel_tag + '" onclick="return open__(' + "'" + sel_id + "'" + ')">' +
		'<div class=item-1>' + htm2 + '</div>' +
		'</a>'
		);
	add__(d, e__("list"));
}
</script>上原样。
定义“加链”以下代码
	加标签“a”、“href="javascript:‘参数2’"”、‘参数1’。
	如果‘参数3’那么“<br><br>”否则“ &nbsp; &nbsp; ”。
上代码。
“<div id=bar2 class=bar data-i=0>”先
	弄个“迭代”、“-个”为“2”、下代码
		别名“码”以“参数2”。
		加链‘参数1’、“bar2__('‘码’')”、分支‘码’先“o2”、“e”：“1”。了。
	上代码、
	“打开”、“o”、
	“他打开”、“o2”、
	“详情”、“i”、
	“编辑”、“e”、
	“选中”、“s”
了“</div>
<div id=bar class=bar>”先
	加链“x”、“hide__('bar')”。
	弄个“迭代”、“-个”为“2”、下代码
		别名“码”以“参数2”。
		加链‘参数1’、“select__('‘码’')”如果‘码’等于“ren”那么、“1”。
	上代码、
	“删除”、“rm”、
	“移动”、“mv”、
	“重命名”、“ren”、
	“分享”、“fx”。
了“</div>
<div id=list></div>
<script>
hide__('bar');
hide__('bar2');
</script>”。
赋予“代码-1”以下代码
	别名“路径”、“目录”、“名”、“类型”、“序号”、“标签”、“无选”以
		“参数1”、“参数2”、“参数3”、“参数4”、“参数5”、“参数6”、“参数7”。
	分支‘类型’先
		“content/unknown”再来。
		“url”先
			赋予“封面”以。
		了。
		“js”先
			赋予“封面”以。
		了。
		先
			赋予“找封面”以。
			圈子先
				分支‘标签’先
					“文”、“文2”先
						分支‘类型’先
							“text/plain”、
							“text/html”先
								赋予“找封面”以“1”。
								遁出。
							了。
							“application/pdf”、
							“application/vnd.ms-excel”
								遁出。
						了。
						如果‘类型’正匹配“image/+”那么再来。
					了。
					“图”、“图+影”先
						如果‘类型’正匹配“image/+”那么遁出。
					了。
					“乐”先
						如果‘类型’正匹配“audio/+”那么先
							赋予“找封面”以“1”。
							遁出。
						了。
					了。
					“影”、“图+影”先
						如果‘类型’正匹配“video/+”那么先
							赋予“找封面”以“1”。
							遁出。
						了。
					了。
					先
						如果‘名’正匹配“.+”那么再来。
						遁出。
					了。
				了。
				（（弄个闪屏、‘参数’。）
				显示未处理‘参数’换行。
				再来。）
			了。
			赋予“封面”以。
			如果‘找封面’那么
				弄个“遍历外目录”、“-同名”为‘路径’、“-类型”、“-代码”为下代码
					别名“路径”、“类型”以“参数1”、“参数2”。
					分支‘类型’先
						“image/png”、
						“image/jpeg”、
						“image/gif”先
							赋予“封面”【上】【上】【上】以“file://‘路径’”。
							跳出。
						了。
					了。
				上代码。
			赋予“路径”以弄个“换码”、‘路径’。
			分支‘标签’先
				“文2”赋予“路径”以“‘路径’#‘目录’”。
			了。
		了
	了。
	赋予“选id”以“val‘序号’”。
	赋予“选标记”以“#”分支‘无选’先
		先“longclicktag”先如果不‘无选’那么‘选id’否则‘无选’了“#”了。
		“1”、“true”先了
	了。
上代码。
赋予“代码”【上】以‘代码-1’下代码
	“<div class=item id=item‘序号’><div><input type=checkbox ”
    “id=‘选id’ value="‘路径’" onclick="show__('bar')"”
    “ data-i=‘序号’ data-t="‘类型’" data-n="‘名’"”先如果‘封面’那么“ data-m="‘封面’"”了
	先如果‘无选’那么“ disabled”了
	“></div><a href="‘选标记’" onclick="return open__('‘选id’')">”
	“<div class=item-1>”先如果‘封面’
        那么“<img src="‘封面’‘选标记’">”
        否则“<div class=title>‘名’</div>”
	了“</div></a></div>”。
上代码。
赋予“啪代码”【上】以‘代码-1’下代码
	弄个“js2”、“add_item__(‘序号’,"‘选id’","‘选标记’","‘路径’","‘类型’","‘名’",'”
		先如果‘封面’那么“ data-m="‘封面’"”了“','”先如果‘封面’
		那么“<img src="‘封面’‘选标记’">”
		否则“<div class=title>‘名’</div>”
		了“')”。
上代码。
