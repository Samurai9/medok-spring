<#import "macroses/head.ftlh" as head/>
<#import "macroses/header.ftlh" as header/>
<#import "macroses/left-menu.ftlh" as leftMenu/>
<#import "macroses/footer.ftlh" as footer/>

<#import "macroses/atoms/stars.ftlh" as stars>
<#import "macroses/atoms/textarea.ftlh" as textarea>
<#import "macroses/atoms/button.ftlh" as button>

<@head.head title=title/>

<body>
<@leftMenu.leftMenu/>
<div class="main__content">
    <div class="main__content__inner">
        <@header.header/>

        <div class="form-popup_fail" id="failure_add" align="center">
            <h5><span>Опрация доступна только авторизованным пользователям</span></h5>
        </div>

        <div class="form-popup_success" id="success_add" align="center">
            <h5><span>Товар успешно добавлен в корзину</span></h5>
        </div>

        <div class="product__page">
            <div class="product__page__header">
                <div class="product__page__header__img" align="center">
                    <img src="${product.getPicture()}" alt="">
                    <div class="product__element__rating">
                        <@stars.stars product.getRating()/>
                    </div>
                    <div class="product__element__rating__inner" align="center">${product.getRating()}</div>
                </div>

                <div class="product__page__header__info" align="center">
                    <div class="product__page__header__info__name">
                        <b><span>${product.getName()}</span></b>
                    </div>
                    <hr class="hr__black">
                    <div class="product__page__header__info__size">
                        <span>Масса:</span>
                        <span>${product.getSize()} кг.</span>
                    </div>
                    <div class="product__page__header__info__remainig">
                        <span>В наличии:</span>
                        <span>${product.getRemaining()}</span>
                    </div>
                </div>

                <div class="product__page__header__buy" align="center">
                    <div class="product__page__header__buy__price">
                        <b>
                            <span>${product.getPrice()}</span>
                            <span>&#8381</span>
                        </b>
                    </div>
                    <#if (product.getRemaining() > 0)>
                        <div class="product__page__header__buy__button">
                            <#if user??>
                                <form action="${springMacroRequestContext.getContextPath()}/basket" method="POST" id="put_to_basket_form">
                                    <input type="hidden" name="product_id" value="${product.getProductId()}">
                                    <input type="hidden" name="method" value="PUT">
                                    <button class="product__page__header__buy__button" onclick="showForm('success_add')" >В корзину</button>
                                </form>
                            <#else>
                                <button class="product__page__header__buy__button" onclick="showForm('failure_add')">В корзину</button>
                            </#if>
                        </div>
                    <#else>
                        <div class="product__page__header__buy__button__disable">
                            <span>Товара нет в наличии</span>
                        </div>
                    </#if>
                </div>
            </div>
            <hr class="hr__black">
            <div class="product__page__description">
                <b><span>Описание:</span></b>
                <br>
                <span>${product.getDescription()}</span>
            </div>

            <#if user??>
                <div>
                <br>
                <b><span>Оставьте свой отзыв</span></b>
                <form action="${springMacroRequestContext.getContextPath()}/product/${product.getProductId()}/review" method="post">
                    <@textarea.textarea "text" 5></@textarea.textarea>
                    <div>
                    <span>Рейтинг:</span>
                    <select name="rating">
                        <option value="0">0</option>
                        <option value="1">1</option>
                        <option value="2">2</option>
                        <option value="3">3</option>
                        <option value="4">4</option>
                        <option value="5">5</option>
                    </select>
                    </div>
                    <@button.button "submit" "Отправить"/>
                </form>
                <br>
                </div>
            </#if>

            <hr class="hr__black">
            <div class="product__page__reviews">
                <#if product.getReviews()??>
                    <b><span>Отзывы: ${product.reviews?size}</span></b>
                <#else>
                    <b><span>Отзывы: 0</span></b>
                </#if>
            </div>
            <br>


            <#list product.reviews as review>
                <div class="product__page__review__element">
                    <div class="product__page__review__element__img">
                        <div class="product__page__review__element__img__name" align="center">
                            <span>${review.getAuthor().getName()}, ${review.getCreatedAt()}</span>
                        </div>
                        <img src="${review.getAuthor().getProfilePicturePath()}" alt="">
                    </div>
                    <div class="product__page__review__element__inner">
                        <div class="product__element__rating">
                            <@stars.stars review.getRating()/>
                        </div>
                        <div class="product__element__rating__inner">${review.getRating()}</div>
                        <hr class="hr">
                        <div class="product__page__review__element__text">
                            ${review.getText()}
                        </div>
                    </div>
                </div>
            </#list>
        </div>

    </div>
    <@footer.footer/>
</div>
</body>
</html>