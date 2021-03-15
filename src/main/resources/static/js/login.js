function checkPattern(str, regExpObj) {
  var result = regExpObj.test(str);
  return result;
}

function checkID(str) {
  return checkPattern(str, new RegExp(/^[a-zA-Z0-9_@]{5,14}$/));
}

function checkPwd(str) {
  return checkPattern(
    str,
    new RegExp(
        // /^(?=.*[a-z])(?=.*[A-Z])(?=.*[0-9])(?=.*[!@#$%^*()\-_=+\\\|\[\]{};:\'",.<>\/?]).{8,16}$/
        // test pattern
        /^[A-Za-z0-9]{8,16}$/
    )
  );
}

function checkName(str) {
  return checkPattern(str, new RegExp(/^[가-힣a-z]{2,8}$/));
}

function checkPhone(str) {
  return checkPattern(str, new RegExp(/^[0-9]+$/));
}
