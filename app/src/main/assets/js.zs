下原样
<script>
function log__(s) {
	console.log(s)
}
function e__(id) {
	switch(typeof(id)) {
	case 'string':
	case 'number':
		return document.getElementById(id);
	}
	return id
}
function a__(name) {
	return document.getElementsByClassName(name)
}
function a2__(name) {
	return document.getElementsByTagName(name)
}
function q__(s) {
	return document.querySelector(s)
}
function qa__(s) {
	return document.querySelectorAll(s)
}

function htm__(id, val, op) {
	var e = e__(id);
	if(val == undefined)
		return e.innerHTML;
	switch(op) {
	case true:
		e.innerHTML += val;
		return;
	case "top":
		e.innerHTML = val + e.innerHTML;
		return;
	}
	e.innerHTML = val;
}
function show__(id, s) {
	var e = e__(id);
	if(!s) {
		if(e.tagName == 'DIV')
			s = 'block';
		else s = 'inline';
	}
	e.style.display = s;
}
function hide__(id) {
	e__(id).style.display = 'none'
}
function del__(id) {
	var e = e__(id);
	e.parentNode.removeChild(e)
}

function new__(s) {
	return document.createElement(s);
}
function add__(e, e2) {
	e2.appendChild(e); 
}
</script>
上原样。
