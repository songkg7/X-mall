function checkPattern(str, regExpObj){
    var result = regExpObj.test(str);
    return result;
}

function check_price(str){
    return checkPattern(str, new RegExp(/^[1-9][0-9]{0,7}$/));
}

