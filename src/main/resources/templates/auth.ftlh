<#import "macroses/head.ftlh" as head/>
<#import "macroses/header.ftlh" as header/>
<#import "macroses/left-menu.ftlh" as leftMenu/>
<#import "macroses/footer.ftlh" as footer/>

<#import "/spring.ftl" as spring />

<@head.head title=title/>

<body>
<@leftMenu.leftMenu/>
<div class="main__content">
    <div class="main__content__inner">
        <@header.header/>
        <div class="reg__auth">
            <div class="auth">
                <h2>Авторизация</h2>
                <#if error??>
                    <span>${error}</span>
                </#if>
                <form action="${springMacroRequestContext.getContextPath()}/auth" method="post" name="auth">
                    <h3>E-mail</h3>
                    <input type="text" placeholder="example@gmail.ru" name="email" class="base__input" size="30" id="auth_email_form" onfocus="validatorColorAuth()" required>
                    <h3>Пароль</h3>
                    <input type="password" placeholder="Пароль" name="password" class="base__input" size="30" id="auth_password_form" onfocus="validatorColorAuth()" required minlength="8">
                    <p><input type="submit" class="base__submit"></p>
                </form>

                <div align="center" class="github_auth">
                    <a href="${github}">
                        <div>
                            <img src="/img/github_logo.png" alt="">
                        </div>
                        <div>
                            <span>SignIn with Github</span>
                        </div>
                    </a>
                </div>
            </div>


            <div class="registration">
                <h2>Регистрация</h2>
                <#if message??>
                    <div class="success">
                        ${message}
                    </div>
                </#if>
                <form action="${springMacroRequestContext.getContextPath()}/reg" method="POST">
                    <div>
                        <@spring.bind 'userRegisterDto.name'/>
                        <h3>Имя</h3>
                        <input type="text" placeholder="Иван" name="name" class="base__input" size="30" required id="reg_name_form" onfocus="validatorColorReg()" minlength="2" value="${userRegisterDto.name?if_exists}">
                        <span><@spring.showErrors "" "auth_error"/></span>
                    </div>


                    <div>
                        <@spring.bind 'userRegisterDto.email'/>
                        <h3>E-mail</h3>
                        <input type="text" placeholder="example@gmail.ru" name="email" class="base__input" size="30" required id="reg_email_form" onfocus="validatorColorReg()" value="${userRegisterDto.email?if_exists}">

                        <span><@spring.showErrors "" "auth_error"/></span>
                    </div>

                    <div>
                        <@spring.bind 'userRegisterDto.password'/>
                        <h3>Пароль (не менее 8 символов)</h3>
                        <input type="password" placeholder="Пароль" name="password" class="base__input" size="30" required id="reg_password_form" onfocus="validatorColorReg()" minlength="8">

                        <span><@spring.showErrors "" "auth_error"/></span>
                    </div>


                    <div>
                        <@spring.bind 'userRegisterDto.repeat_password'/>
                        <h3>Повтор пароля</h3>
                        <input type="password" placeholder="Повтор пароля" name="repeat_password" class="base__input" size="30" required id="reg_repeat_password_form" onfocus="validatorColorReg()" minlength="8">

                        <span><@spring.showErrors "" "auth_error"/></span>
                    </div>

                    <div>
                        <@spring.bind 'userRegisterDto.licence_agreement'/>
                        <p><input type="checkbox" name="licence_agreement" required id="reg_licence_form">Согласен с <a href="">условиями пользования сайтом</a></p>

                        <span><@spring.showErrors "" "auth_error"/></span>
                    </div>

                    <p><input type="submit" class="base__submit"></p>
                </form>
            </div>
        </div>
    </div>
    <@footer.footer/>
</div>
</body>
</html>