/*function fullHeight(){
	var windowHeight = $(window).height();
	alert(windowHeight);
	$('.fullHeight').css('min-height', windowHeight - 200 + 'px');
}*/

function checkChange() {
    var t = $(this).textbox();
    debugger
}

//头部跑马灯
function headerMarquee(){
	var speed = 25;
    var wall_text2=document.getElementById("marquee-hide");  
    var wall_parents=document.getElementById("header-marquee");  
    var wall_text1=document.getElementById("marquee-txt");  
  
    wall_text2.innerHTML = wall_text1.innerHTML; 
    wall_text1.scrollLeft = wall_parents.scrollWidth;
    function Marquee() {  
        if (wall_parents.scrollLeft <= wall_text2.offsetWidth){
            
            wall_parents.scrollLeft++;  
        }
        else {  
            wall_parents.scrollLeft = 0;
        }  
    }  
    /*if(wall_text1.offsetWidth < wall_parents.offsetWidth) {
    	//alert(wall_text1.offsetWidth);
    	//alert(wall_parents.offsetWidth);
         clearInterval(MyMar);
          wall_text2.innerHTML = ""; 
    }*/
    var MyMar = setInterval(Marquee, speed);
    wall_parents.onmouseover = function() {  
        clearInterval(MyMar);
    }  ;
    wall_parents.onmouseout = function() {  
        MyMar = setInterval(Marquee, speed);
    } ;
}

