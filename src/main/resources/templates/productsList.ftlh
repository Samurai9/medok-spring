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

        <div class="form-popup" id="success_add" align="center">
            <h5><span>Продукт успешно добавлен</span></h5>
        </div>

        <div class="form-popup_fail" id="failure_add" align="center">
            <h5><span>Опрация доступна только авторизованным пользователям</span></h5>
        </div>

            <#list products as product>
                <div class="products">
                    <a href="${springMacroRequestContext.getContextPath()}/product/${product.getProductId()}" class="product__element">
                        <div class="product__element__inner1">
                            <img src="${product.getPicture()}" alt="">
                        </div>
                        <div class="product__element__inner2">
                            <div class="product__element__title">
                                <b><span>${product.getName()}</span></b>
                                <hr class="hr_black">
                            </div>
                            <div class="product__element__size">
                                <span>Масса: </span>
                                <span>${product.getSize()} кг.</span>
                            </div>
                            <div class="product__element__review">
                                <span>Отзывы:</span>
                                <#if product.reviews??>
                                    <span>${product.reviews?size}</span>
                                <#else>
                                    <span>0</span>
                                </#if>
                            </div>
                            <div class="product__element__rating">
                                <@stars.stars product.getRating()/>
                                <div class="product__element__rating__inner">${product.getRating()}</div>
                            </div>
                        </div>

                        <div class="product__element__inner3">
                            <div class="product__element__price">
                                <b><span>${product.getPrice()}</span></b>
                                <b><span>&#8381</span></b>
                            </div>

                        </div>
                    </a>
                </div>
            </#list>
    </div>
    <@footer.footer/>
</div>
</body>
</html>