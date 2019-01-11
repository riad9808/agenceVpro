(function($){
  $(function(){

    $('.sidenav').sidenav();
    $('.parallax').parallax();
    window.history.pushState(null, "", window.location.href);        
	window.onpopstate = function() {
	    window.history.pushState(null, "", window.location.href);
	};
  }); // end of document ready
})(jQuery); // end of jQuery name space
