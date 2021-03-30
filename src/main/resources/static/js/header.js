// // 스크롤바를 내리면..
// $(window).on('scroll',function(){
//
//     // 만약 지금 스크롤을 217px 했을 때
//     if ( $(window).scrollTop()>38) {
//
//         $('.background_blur').addClass();
//         $('.nav2').addClass('nav2_scroll');
//         $('.detail_category1, .detail_category2, .detail_category3, .detail_category4, .detail_category5').addClass('nav2_category_scroll');
//     }else{
//         $('.nav2').removeClass('nav2_scroll');
//         $('.detail_category1, .detail_category2, .detail_category3, .detail_category4, .detail_category5').removeClass('nav2_category_scroll');
//     }
// })


$(document).ready(function () {
    // 네비게이션 바 디테일 카테고리
    var nav_1 = $(".detail_category1");
    var nav_2 = $(".detail_category2");
    var nav_3 = $(".detail_category3");
    var nav_4 = $(".detail_category4");
    var nav_5 = $(".detail_category5");

    var main = $(".main");
    var blur = $(".background_blur");

    nav_1.hide();
    nav_2.hide();
    nav_3.hide();
    nav_4.hide();
    nav_5.hide();

    $("#at1").hover(
        function () {
            //숨길것
            nav_2.hide();
            nav_3.hide();
            nav_4.hide();
            nav_5.hide();
            // 보여줄것
            nav_1.slideDown(180);
            blur.fadeIn(500);
        }
        // ,function(){nav_1.hide(); blur.hide()}
    );

    $("#at2").hover(
        function () {
            //숨길것
            nav_3.hide();
            nav_1.hide();
            nav_4.hide();
            nav_5.hide();
            // 보여줄것
            nav_2.slideDown(180);
            blur.fadeIn(500);
        }
        // ,function(){nav_2.hide(); blur.hide()}
    );

    $("#at3").hover(
        function () {
            //숨길것
            nav_1.hide();
            nav_2.hide();
            nav_4.hide();
            nav_5.hide();
            // 보여줄것
            nav_3.slideDown(180);
            blur.fadeIn(500);
        }
        // ,function(){nav_3.hide(); blur.hide()}
    );

    $("#at4").hover(
        function () {
            //숨길것
            nav_1.hide();
            nav_2.hide();
            nav_3.hide();
            nav_5.hide();
            // 보여줄것
            nav_4.slideDown(180);
            blur.fadeIn(500);
        }
        // ,function(){nav_4.hide(); blur.hide()}
    );

    $("#at5").hover(
        function () {
            //숨길것
            nav_1.hide();
            nav_2.hide();
            nav_3.hide();
            nav_4.hide();
            // 보여줄것
            nav_5.slideDown(180);
            blur.fadeIn(500);
        }
        // ,function(){nav_5.hide(); blur.hide()}
    );
    $(".searchBar").hover(function () {
        nav_1.slideUp(180);
        nav_2.slideUp(180);
        nav_3.slideUp(180);
        nav_4.slideUp(180);
        nav_5.slideUp(180);
        blur.hide();
    });
    $(".background_blur").hover(function () {
        nav_1.slideUp(180);
        nav_2.slideUp(180);
        nav_3.slideUp(180);
        nav_4.slideUp(180);
        nav_5.slideUp(180);
        blur.hide();
    });
    $(".main").hover(function () {
        nav_1.slideUp(180);
        nav_2.slideUp(180);
        nav_3.slideUp(180);
        nav_4.slideUp(180);
        nav_5.slideUp(180);
        blur.hide();
    });
    $("#btn3").click(function () {
        $('#black-background').css({display: 'block'});
        $('#right-menu').animate({marginRight: '0px'});


    });


    $("#black-background").click(function () {
        $('#black-background').css({display: 'none'});
        $('#right-menu').animate({marginRight: '-350px'});

    });
});
