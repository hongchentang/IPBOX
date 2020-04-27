// JavaScript Document
$(function(){
    placeholders('u-ipttxt','c-ipt','ph-txt');
    logregLayer();  
    selectAddress();  
})


/*---start----html5 placeholder效果----start---*/
function placeholders(prts,hipt,htxt){
    var $prts = $("." + prts);
    $prts.each(function(){
        var _ts = $(this),
            $htxt = _ts.find("." + htxt),
            $hipt = _ts.find("." + hipt);
        $hipt.on("focus",function(){
            $htxt.fadeOut("solw");
        })
        $hipt.on("blur",function(){
             $htxt.fadeIn("solw");
        })
    })
}
/*---end----html5 placeholder效果----end---*/

/*---start----登录注册弹出框----start---*/
function logregLayer(){
    var $logBtn = $("#u-log-btn"),
        $regBtn = $("#u-reg-btn"),
        $lrLayer = $(".m-log-layer"),
        $blackBg = $(".m-blackbg"),
        $showTxt = $("#showText"),
        $mTfScroll = $("#mTfScroll");
    $logBtn.on("click",function(){
        $lrLayer.show().animate({opacity: 1},300);
        $blackBg.show().animate({opacity: 0.6},300);
        $mTfScroll.css('top', '0');
        $showTxt.text("登录");
    })
    $regBtn.on("click",function(){
        $lrLayer.show().animate({opacity: 1},300);
        $mTfScroll.css('top', '-240px');
        $blackBg.show().animate({opacity: 0.6},300);
        $showTxt.text("注册");
    })
    logregTf($lrLayer,$blackBg,$mTfScroll);
}
function logregTf($lrLayer,$blackBg,$mTfScroll){
    var $lrlayout = $("#g-logreg-layout"),
        $tfReg = $("#u-tf-reg"),
        $showTxt = $("#showText"),
        $tfLog = $("#u-tf-log");

    $tfReg.on("click",function(){
        $mTfScroll.animate({top: 0},200);
        $showTxt.text("登录");
    })
    $tfLog.on("click",function(){
        $mTfScroll.animate({top: -240},200);
        $showTxt.text("注册");
    })
    $blackBg.on("click",function(){
        $(this).animate({opacity: 0},function(){
            $(this).hide();
        })
        $lrLayer.animate({opacity: 0},function(){
            $lrLayer.hide();
        })
        //$lrLayer.remove();
    })
}
/*---end----登录注册弹出框----end---*/

/* 固定定位弹出框1  */
function oJumpLayer(clickBtn,layer){
    var width = layer.innerWidth(),
        height = layer.innerHeight();

    clickBtn.bind('click',function(){
        layer.show().css({'margin-top':-height/2+'px','margin-left':-width/2+'px'});
        $('.m-blackbg').show().css("opacity","0.5");
        
    })
    layer.find('.u-confirm-btn').bind('click',function(){
        layer.hide();
        $('.m-blackbg').hide().css("opacity","1");
    })
    layer.find('.u-cancel-btn').bind('click',function(){
        layer.hide();
        $('.m-blackbg').hide().css("opacity","1");
    })
    layer.find('.u-close-btn').bind('click',function(){
        layer.hide();
        $('.m-blackbg').hide().css("opacity","1");
    })
}


function turns($turnItem,turnWidth,turnTime){
    $turnItem.each(function(){
        var _ts = $(this),
            $turnBefore = _ts.children(".turn-before"),
            $turnAfter = _ts.children(".turn-after"); 
        _ts.on("mouseover",function(){
            $turnBefore.stop().hide().animate({"width": 0}, turnTime);
            $turnAfter.stop().show().animate({"width": turnWidth + "px"}, turnTime);
        })
         _ts.on("mouseout",function(){
            $turnAfter.stop().hide().animate({"width": 0}, turnTime);
            $turnBefore.stop().show().animate({"width": turnWidth + "px"}, turnTime);
        })
    })
}


function selectAddress(){
	//添加选择框的html代码
	var lyHtml = '<div class="ly" id="addressLy">'+
					'<i class="trg1"></i>'+
					'<i class="trg2"></i>'+
					'<div class="m-slt-region">'+
						'<select name="addressFreight_p" class="province"></select>'+
						'<select name="addressFreight_c" class="city"></select>'+
						'<select name="addressFreight_a" class="area"></select>'+
					'</div>'+
					'<div class="btn-row">'+
						'<a href="javascript:void(0);" class="u-confirm-btn">确定</a>'+
					'</div>'+
				'</div>';

	$(document).on('click','.m-slt-area .show',function(event){
		var _ts = $(this);
		var addrBox = _ts.parents(".m-slt-area");
		var ly = null;

		//获取运送地址
		//var addressName = $('#addressFreightData').val();
//    	var addressArr = addressName.split(",");
    	//判断是否打开地址选择框
		if(_ts.hasClass('z-crt')){
			addressFreightClose(_ts,ly);

		}else {
			_ts.addClass('z-crt');
			//增加html代码片段
			addrBox.append(lyHtml);
			ly = $("#addressLy");
			//设置打开动画
			ly.show().css('overflow','visible').stop().animate({
				'height': 100 + 'px'
			},100);
			//设置默认地址
			//添加遮罩层
			$("body").append('<div id="opacityShare"></div>');
			//点击其他地方关闭
			$("#opacityShare").on('click',function(){
				addressFreightClose(_ts,ly);
			});
            /*$(document).on("click",function(e){
                var target = $(e.target);
                if(target.closest(ly).length == 0 && target.closest(_ts).length == 0){

                }
            });*/
		}
		//确定选择地址按钮
		ly.find('.u-confirm-btn').on("click",function(event){
			//阻止事件冒泡
			event.stopPropagation();
			//判断是否选择了地址
			if(ly.find(".area").val() == '市辖区'){
				alert("请选择地址");
			}else {
				//显示地址
				_ts.text(ly.find(".area").val());
				//保存运送地址
				$('#addressFreightData').val(ly.find(".province").val() + ',' + ly.find(".city").val() + ',' + ly.find(".area").val());
				//关闭地址选择框
				addressFreightClose(_ts,ly);
				//点击确定按钮后执行方法
				//confirmFn();
			}
		});
	});

	/*--关闭选择地址框--*/
	addressFreightClose = function(showText,ly){
		showText.removeClass('z-crt');
		//移除遮罩层
		$("#opacityShare").remove();
		//移除地址选择框
		ly.remove();
	};
}