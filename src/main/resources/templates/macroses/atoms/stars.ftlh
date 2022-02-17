<#macro stars num>
    <#list 1..5 as i>
        <#if i <= num>
            <span class="active"></span>
        <#else>
            <span></span>
        </#if>
    </#list>
</#macro>