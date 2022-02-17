<#import "macroses/head.ftlh" as head/>
<#import "macroses/header.ftlh" as header/>
<#import "macroses/left-menu.ftlh" as leftMenu/>
<#import "macroses/footer.ftlh" as footer/>

<#import "macroses/atoms/stars.ftlh" as stars>

<@head.head title=title/>

<body>
<@leftMenu.leftMenu/>
<div class="main__content">
    <div class="main__content__inner">
        <@header.header/>
        <div class="leader__text" align="center">
            <h1>Лидеры продаж</h1>
        </div>
        <div class="leaders">
            <#list products as product>
                <a href="${springMacroRequestContext.getContextPath()}/product/${product.getProductId()}">
                    <div class="leader__element">
                        <div class="leader__element__name">
                            <h2>${product.getName()}</h2>
                            <hr class="hr_black">
                        </div>
                        <div class="leader__element__img">
                            <img src="${product.getPicture()}" alt="">
                        </div>
                        <div class="leader__element__rating">
                            <div class="product__element__rating">
                                <@stars.stars product.getRating()/>
                            </div>
                        </div>
                        <span><b>${product.getRating()}</b></span>
                        <div class="leader__element__price">
                            <b><h3>${product.getPrice()}&#8381</h3></b>
                        </div>
                    </div>
                </a>
            </#list>
        </div>
    </div>
    <@footer.footer/>
</div>
</body>
</html>