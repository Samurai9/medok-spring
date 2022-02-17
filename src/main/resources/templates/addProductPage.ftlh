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
        <div class="auth">
            <h2>Добавление продукта</h2>
            <form action="${springMacroRequestContext.getContextPath()}/product/add" method="post">
                <h3>Название товара</h3>
                <input type="text" placeholder="Цветочный мёд" name="name" class="base__input" size="30" required>
                <h3>Описание товара</h3>
                <input type="text" placeholder="Очень хороший мёд" name="description" class="base__input" size="30" required>
                <h3>Категория</h3>

                <select name="category">
                    <#list categories as category>
                        <option value="${category}">${category}</option>
                    </#list>
                </select>

                <h3>Количество</h3>
                <input typeс="text" placeholder="20" name="remaining" class="base__input" size="30" required>
                <h3>Объём</h3>
                <input typeс="text" placeholder="1.5" name="size" class="base__input" size="30" required>
                <h3>Цена</h3>
                <input typeс="text" placeholder="1999" name="price" class="base__input" size="30" required>
                <p><input type="submit" class="base__submit"></p>
            </form>
        </div>
    </div>
    <@footer.footer/>
</div>
</body>
</html>