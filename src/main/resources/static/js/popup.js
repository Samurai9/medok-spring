intervalIn = null;
intervalOut = null;
element = null;
speed = 70;

function showForm(div) {
    element = document.getElementById(div);
    openForm();
    setTimeout(closeForm, 1500);
}

function openForm() {
    element.style.display = "block";
    fadeIn();
}

function closeForm() {
    fadeOut();
}

function fadeIn() {
    step = 1 / speed;
    intervalIn = setInterval(function () {
        if (element.style.opacity >= 1) {
            clearInterval(intervalIn);
        }
        element.style.opacity = +element.style.opacity + step;
    }, 1 / 60);
}

function fadeOut() {
    step = 1 / speed;
    intervalOut = setInterval(function () {
        if (element.style.opacity <= 0) {
            clearInterval(intervalOut);
            element.style.display = "none";
        }
        element.style.opacity = element.style.opacity - step;
    }, 1 / 60);
}


function showSuccessAddingToBasket() {
    console.log('success');
}

function showSuccessDeleteFromBasket() {
    console.log('success');
}


function showMessage(message) {
    console.log(message);
}