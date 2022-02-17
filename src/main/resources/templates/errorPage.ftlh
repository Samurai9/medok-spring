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
        <div align="center"><h1>Ошибка</h1></div>
        <div align="center"><h3>${message}</h3></div>
    </div>
    <@footer.footer/>
</div>
</body>
</html>