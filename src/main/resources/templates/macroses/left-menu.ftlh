<#macro leftMenu>
<div class="left__menu">
    <div class="left__menu__inner">
        <div class="logo" align="center">
            <a href="${springMacroRequestContext.getContextPath()}/welcome"><img src="/img/logo.jpg" alt=""></a>
        </div>
        <nav class="navigation">
            <a href="${springMacroRequestContext.getContextPath()}/product/liquid">
                <div class="orange__element">Жидкий мёд</div>
            </a>
            <a href="${springMacroRequestContext.getContextPath()}/product/honeycomb">
                <div class="orange__element">Сотовый мёд</div>
            </a>
        </nav>
    </div>
</div>
</#macro>