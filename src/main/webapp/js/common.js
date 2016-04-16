function menuGo(uri) {
	top.frames['mainFrame'].frames[1].location.href = uri;
}

function delCfm(action, id) {
	if (confirm('确定要删除？')) {
		location.href = 'del/' + action + '?bean.id=' + id;
	}
}