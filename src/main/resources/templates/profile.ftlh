<#import "macroses/head.ftlh" as head/>
<#import "macroses/header.ftlh" as header/>
<#import "macroses/left-menu.ftlh" as leftMenu/>
<#import "macroses/footer.ftlh" as footer/>


<@head.head title=title/>

<body>
<@leftMenu.leftMenu/>
<div class="main__content">
    <div class="main__content__inner">
        <@header.header/>
        <div class="profile" align="center">
            <a href="${springMacroRequestContext.getContextPath()}/logout">
                <div class="profile__logout" align="center">
                    <div>Выйти из аккаунта</div>
                </div>
            </a>
            <div class="profile__greetings">
                <span>Здраствуйте,</span>
                <span>${user.getName()}!</span>
            </div>
            <div class="profile__image">
                <img src=${user.getProfilePicturePath()} alt="">
            </div>
            <div class="profile__information">
                <div class="profile__name">
                    <span>Имя:</span>
                    <span>${user.getName()}</span>
                </div>
                <div class="profile__email">
                    <span>Электронная почта:</span>
                    <span>${user.getEmail()}</span>
                </div>
                <div class="profile__discount">
                    <span>Скидка:</span>
                    <span>${user.getDiscount()}</span>
                    <span>&#8381</span>
                </div>
                <a href="${springMacroRequestContext.getContextPath()}/profile/edit" class="profile__logout">Редактировать</a>
            </div>
        </div>
    </div>
    <@footer.footer/>
</div>
</body>
</html>