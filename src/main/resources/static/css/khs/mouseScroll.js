var flag = false; // DOMMouseScroll event is for firefox // 이벤트 'e'는 마우스 이벤트를 의미한다. $("html, body").on('mousewheel DOMMouseScroll', function(e) { var E = e.originalEvent; eventValues = 0; // E.detail is for firefox if (E.detail) { eventValues = E.detail * -40; }else{ eventValues = E.wheelDelta; }; // minus nav height(50px) var scmove = $(this).height() - $(".l-header-pc").height(); // -120이면 마우스 휠을 아래로 내린 것 if(eventValues == -120 && !flag){ // roll down mouse wheel flag = true; $('html, body').animate( { scrollTop : '+='+scmove }, 2000 ,function(){ flag = false; }); }; if(eventValues == 120 && !flag){ // roll up mouse wheel flag = true; $('html, body').animate( { scrollTop : '-='+scmove }, 2000 ,function(){ flag = false; }); }; });
