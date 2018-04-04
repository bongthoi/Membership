/**
 * 
 */
$(document).ready(function() {
		$(".boxHP").hover(function() {
			$(".nameHP").css({
				"bottom" : "50px",
				"padding" : "20px 5px",
				"opacity" : "1"
			});
			$(".muangayHP").css("visibility", "visible");
		}, function() {
			$(".nameHP").css({
				"bottom" : "0px",
				"padding" : "5px 5px",
				"opacity" : "0.85"
			});
			$(".muangayHP").css("visibility", "hidden");
		});
		$(".muangayHP").mouseenter(function() {

			$(".muangaybtnHP").css("box-shadow", "none");
		});
		$(".muangayHP").mouseleave(function() {

			$(".muangaybtnHP").css("box-shadow", "3px 3px 2px #888888");
	
		});
});

	
