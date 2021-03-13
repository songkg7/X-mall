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
      nav_1.slideDown(400);
      blur.fadeIn();
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
      nav_2.slideDown(400);
      blur.fadeIn();
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
      nav_3.slideDown(400);
      blur.fadeIn();
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
      nav_4.slideDown(400);
      blur.fadeIn();
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
      nav_5.slideDown(400);
      blur.fadeIn();
    }
    // ,function(){nav_5.hide(); blur.hide()}
  );
  $(".searchBar").hover(function () {
    nav_1.slideUp(200);
    nav_2.slideUp(200);
    nav_3.slideUp(200);
    nav_4.slideUp(200);
    nav_5.slideUp(200);
    blur.hide();
  });
  $(".background_blur").hover(function () {
    nav_1.slideUp(200);
    nav_2.slideUp(200);
    nav_3.slideUp(200);
    nav_4.slideUp(200);
    nav_5.slideUp(200);
    blur.hide();
  });
  $(".main").hover(function () {
    nav_1.slideUp(200);
    nav_2.slideUp(200);
    nav_3.slideUp(200);
    nav_4.slideUp(200);
    nav_5.slideUp(200);
    blur.hide();
  });
});
