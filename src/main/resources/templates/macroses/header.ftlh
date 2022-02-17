<#macro header>
    <header class="header">
        <div class="header__up">
            <div class="header__location">
                <a href="">
                    <span class="header__text">г.Временный, ул. Тестовая, д. 404</span>
                </a>
            </div>
            <div class="header__email">
                <a href="#">
                    <span class="header__text">med.ok@gmail.ru</span>
                </a>
            </div>
            <div class="header__tel">
                <a href="tel:88005553535">
                    <span class="header__text">8-(800)-555-35-35</span>
                </a>
            </div>
        </div>

        <div class="header__down">
            <form action="${springMacroRequestContext.getContextPath()}/search" method="get" class="search">
                <input type="search" placeholder="Поиск по товарам.." class="search__bar" name="text">
                <input type="submit" value="Найти" class="search__submit">
            </form>
            <div class="header__down__inner">
                <a href="${springMacroRequestContext.getContextPath()}/basket">
                    <div class="header__down__button" id="shopping__basket">
                        Корзина
                    </div>
                </a>
                <a href="${springMacroRequestContext.getContextPath()}/auth">

                    <#if !user??>
                        <#assign id = "login"/>
                    <#else>
                        <#assign id = "logout"/>
                    </#if>

                    <div class="header__down__button" id=${id}>
                        <#if !user??>
                            Регистрация/Войти
                        <#else>
                            Профиль/Выйти
                        </#if>
                    </div>
                </a>
            </div>
        </div>
    </header>
    <hr class="hr">
</#macro>