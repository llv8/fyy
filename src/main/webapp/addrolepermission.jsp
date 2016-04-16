<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>
<%@ include file="common.jsp"%>
<!DOCTYPE html PUBLIC "-//W3C//DTD XHTML 1.0 Transitional//EN" "http://www.w3.org/TR/xhtml1/DTD/xhtml1-transitional.dtd">
<html xmlns="http://www.w3.org/1999/xhtml">
<head>
<meta http-equiv="Content-Type" content="text/html; charset=utf-8" />
<script src="js/jstree.min.js"></script>
<link rel="stylesheet" href="themes/jstree/default/style.min.css" />

<title></title>

</head>

<body>
	<div style="margin: 0 auto; width: 800px; padding: 10px;">
		<%@ include file="message.jsp"%>
		<div>
			<input id="roleid" type="hidden" value="${rolebean.id }">
				<h3>角色名：${rolebean.name }</h3> 
		</div>
		<div id="content"></div>
		<div>
			<input id="submit" type="button" value="设置" />
		</div>
	</div>
	<script>
	$(document).ready(function() {
		$('#content').on("changed.jstree", function (e, data) {
			
			var originArray = $.jstree.reference('#content').settings.core.data;
			for(var j=0;j<originArray.length;j++){
					originArray[j].state.selected=false;
			}
			
			var array = data.selected;
			console.log(array);
			for(var i=0;i<data.selected.length;i++){
				for(var j=0;j<originArray.length;j++){
					if(array[i]==originArray[j].id){
						originArray[j].state.selected=true;
					}
				}
			}
		}).jstree({
			"core" : {
				'data' : ${permjson}
			},
			"checkbox" : {
				"keep_selected_style" : false
			},
			"plugins" : [ "wholerow", "checkbox" ]
		});
	});
	
	$("#submit").click(function(){
		$.ajax({ 
            type: "post", 
            url: "updatePerm/RolePermission.Action", 
            data: {'permjson':JSON.stringify($.jstree.reference('#content').settings.core.data),'bean.roleId':$('#roleid').val()},
            dataType:"script",
            success: function (data) { 
                   
            }, 
            error: function (XMLHttpRequest, textStatus, errorThrown) { 
                  
            } 
    });
	});
	
</script>
</body>
</html>
