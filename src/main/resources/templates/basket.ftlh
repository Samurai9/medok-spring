<#import "macroses/head.ftlh" as head/>
<#import "macroses/header.ftlh" as header/>
<#import "macroses/left-menu.ftlh" as leftMenu/>
<#import "macroses/footer.ftlh" as footer/>

<#import "macroses/atoms/stars.ftlh" as stars/>

<@head.head title=title/>

<body>
<@leftMenu.leftMenu/>
<div class="main__content">
    <div class="main__content__inner">
        <@header.header/>

        <h2>Корзина</h2>
        <div class="basket">
            <div class="basket__products__inner">
                <#if basket?keys?size == 0>
                    <div class="basket__products__empty">
                        <b><span>В корзине нет товаров</span></b>
                    </div>

                <#else>
                    <#list basket as product, count>
                        <div class="products">
                            <a href="${springMacroRequestContext.getContextPath()}/product/${product.getProductId()}" class="product__element__basket">
                                <div class="product__element__inner1">
                                    <img src="${springMacroRequestContext.getContextPath()}${product.getPicture()}" alt="">
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
                                        <span>${product.getReviews()?size}</span>
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
                                    <div>
                                        <b><span>${count}</span></b>
                                        <span> шт.</span>
                                    </div>
                                    <#if product.getRemaining() == 0>
                                        <div class="product__element__not__found" align="center">Товара нет в наличии</div>
                                    <#else>
                                        <form action="${springMacroRequestContext.getContextPath()}/basket" method="POST" id="delete_from_basket_form">
                                            <input type="hidden" name="product_id" value="${product.getProductId()}">
                                            <input type="hidden" name="method" value="DELETE">
                                            <button class="product__element__button">Удалить</button>
                                        </form>
                                    </#if>
                                </div>
                            </a>
                        </div>
                    </#list>
                </#if>
            </div>
            <div class="basket__info">
                <span>Информация</span>
                <hr class="hr__black">
                <div class="basket__info__total__products" align="center">
                    <span>Количество продуктов:</span>
                    <br>
                    <b><span>${productsCount}</span></b>
                </div>
                <div class="basket__info__total_price" align="center">
                    <span>Итоговая цена:</span>
                    <br>
                    <b><span>${price} &#8381</span></b>
                </div>
                <div class="basket__info__buy">
                    <button class="product__element__button" id="buy"><b><span>Купить</span></b></button>
                </div>
            </div>
        </div>

    </div>
    <@footer.footer/>
</div>
</body>
</html>