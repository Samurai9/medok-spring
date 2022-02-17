function validatorColorAuth() {
    array = [auth_email_form, auth_password_form]
    checkValues(array)
    document.addEventListener('keyup', (e) => {
        checkValues(array);
});
}

function validatorColorReg() {
    array = [reg_name_form, reg_email_form, reg_password_form, reg_repeat_password_form];
    checkValues(array);
    document.addEventListener('keyup', (e) => {
        checkValues(array);
});
}

function checkValues(array) {
    array.forEach(element => {
        if (element.value == "") {
        changeToRed(element);
    } else {
        if (element.name.includes("email")) {
            if (checkEmail(element.value)) {
                changeToOrange(element);
            } else {
                changeToRed(element);
            }
        } else if (element.name.includes("name")) {
            if (checkName(element.value)) {
                changeToOrange(element);
            } else {
                changeToRed(element);
            }
        } else if (element.name.includes("password") && !element.name.includes("repeat_password")) {
            if (checkPassword(element.value)) {
                changeToOrange(element);
            } else {
                changeToRed(element);
            }
        } else if (element.name.includes("repeat_password")) {
            if (checkPasswords(element.value, document.getElementsByName("password")[1].value)) {
                changeToOrange(element);
            } else {
                changeToRed(element);
            }
        }
    }
});
}

function changeToRed(element){
    element.style.backgroundColor = 'rgba(' + 255 + ',' + 0 + ',' + 0 + ',' + 0.2 + ')';
    element.style.borderColor = "red"
}
function changeToOrange(element){
    element.style.borderColor = "orange";
    element.style.backgroundColor = "white";
}


function checkName(name) {
    return name.length >= 2;
}

function checkEmail(email) {
    pattern = /^(([^<>()[\]\\.,;:\s@\"]+(\.[^<>()[\]\\.,;:\s@\"]+)*)|(\".+\"))@((\[[0-9]{1,3}\.[0-9]{1,3}\.[0-9]{1,3}\.[0-9]{1,3}\])|(([a-zA-Z\-0-9]+\.)+[a-zA-Z]{2,}))$/;
    return pattern.test(email);
}

function checkPassword(password) {
    return password.length >= 8
}

function checkPasswords(password, repeatPassword) {
    return password == repeatPassword;
}