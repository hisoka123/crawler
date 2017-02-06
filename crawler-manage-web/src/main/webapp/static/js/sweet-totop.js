
/*返回顶部功能代码*/
function showScroll(){
	var $totopDiv = $('.sr-zql-totop');
	var $topretupDiv = $('.sr-zql-topret-up');
	
	if ($totopDiv.html()!="undefined") {
		$(window).scroll( function() { 
			var scrollValue=$(window).scrollTop();
			scrollValue > 200 ? $('.sr-zql-totop').fadeIn():$('.sr-zql-totop').fadeOut();
		} );	
		$('.sr-zql-totop').click(function(){
			$("html,body").animate({scrollTop:0},300);
		});
	}
	
	if ($topretupDiv.html()!="undefined") {
		$(window).scroll( function() { 
			var scrollValue=$(window).scrollTop();
			scrollValue > 200 ? $('.sr-zql-topret-up').fadeIn():$('.sr-zql-topret-up').fadeOut();
		} );	
		$('.sr-zql-topret-up').click(function(){
			$("html,body").animate({scrollTop:0},300);
		});
	}
}

$(function(){
	showScroll();
});