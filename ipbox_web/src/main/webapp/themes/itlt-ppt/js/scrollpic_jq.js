(function(){
	$.fn.rollpic = function(o){
		var o = $.extend({
			prev : ".prev", //像上滚动按钮
			next : ".next", //像下滚动按钮
			cont : ".sp-lst", //滚动列表
			li : "li", //滚动块
			pause:5000, //多少秒滚动一次
			nspd:1000, //滚动速度
			uspd:300, //点击左右滚动按钮时滚动速度
			vnum:9, //多于多少张的时候才会滚动
			snum:1, //每一次滚动多少张图片
			start:0, //从第几张图片开始滚动
			isH:true, //自动高度
			auto:false //是否自动滚动
		}, o||{});
		
		return this.each(function(){
			var $cont = $(o.cont, this), 
			$prev = $(o.prev, this), $next = $(o.next, this),
			$a = $cont.children(o.li), len = $a.length, v=o.vnum;
			//判断模块个数少于等于一屏显示个数的时候，不滚动而且隐藏左右滚动按钮
			if(len<v){
				$prev.hide();
				$next.hide();
				return false;
			}
			$cont.prepend($a.slice(len-v-1+1).clone(true)).append($a.slice(0,v).clone(true));
			o.start += v;
			var curr = o.start;
			
			var interval = null, a_dir = o.isH ? "marginLeft":"marginTop", c_dir = "left",
			aSize = o.isH ? $a.outerWidth(true) : $a.outerHeight(true), contDS = o.isH ? "width" : "height",
			itemLength = $cont.children("li").size();
			$cont.css(contDS, itemLength*aSize).css(a_dir, -(curr*aSize));
			
			var isOver = true;
			
			if(o.auto){
				$cont.hover(function(){
					clearInterval(interval);
				}, function(){
					if(c_dir == "left"){
						interval = setInterval(function(){ roll(curr+o.snum)}, o.pause);
					}else if(c_dir == "right"){
						interval = setInterval(function(){ roll(curr-o.snum)}, o.pause);
					}
				});
			}
			if($prev){
				$prev.click(function(){
					if(o.auto){clearInterval(interval)};
					if(isOver==true){roll(curr-o.snum, o.uspd);c_dir = "right";};
					if(o.auto){interval = setInterval(function(){ roll(curr-o.snum)}, o.pause)};
				});
			}
			if($next){
				$next.click(function(){
					if(o.auto){clearInterval(interval)};
					if(isOver==true){roll(curr+o.snum, o.uspd); c_dir = "left";};
					if(o.auto){interval = setInterval(function(){ roll(curr+o.snum)}, o.pause)};
				});
			}
			if(o.auto){ interval = setInterval(function(){ roll(curr+o.snum)}, o.pause);}
			function roll(to, spd){
				if(isOver){
					var spd = spd || o.nspd
					isOver = false;
					if(to<=o.start-v-1){
							$cont.css(a_dir, -((v+(len-v)+curr)*aSize)+"px");
							curr = (v+(len-v)+curr)-o.snum;
					}else if(to>=itemLength-v+1) {
							$cont.css(a_dir, -( (v-(itemLength-v-curr)) * aSize ) + "px" );
							curr = (v-(itemLength-v-curr))+o.snum;
					}else curr = to;
					
					$cont.animate(
							a_dir == "marginLeft" ? {"marginLeft": -(curr*aSize) } : {"marginTop": -(curr*aSize) } , spd, function(){isOver = true;}
					);
				}
				return false;
			};
			
		});
	}
})(jQuery);