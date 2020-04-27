//操作对象,延迟时间，width，height
function createTooltip(dg, url, width, height){
	var tips = dg.datagrid('getPanel').find('.datagrid-row');
	tips.each(function(){
		var index = parseInt($(this).attr('datagrid-row-index'));
		$(this).tooltip({
			trackMouse: true,
			showDelay:1000,
			deltaX:10,
			content: $('<div></div>'),
			onUpdate: function(cc){
				var row = dg.datagrid('getRows')[index];
				var resultUrl = url + '?id=' + row.id;
				var content = getData(resultUrl);
				cc.panel({
					width:width,
					height:height,
					content:content
				});
			}, position:'bottom-right'
		});
	});
}

function getData(url) {
	var reData = null;
	$.ajax({
		url: url,
		type:'get',
		async:false,
		success:function(data){
			reData = data;
		},
		error:function () {
			reData = '出错了！';
		}
	});

	return reData;
}