<!DOCTYPE html>
<html lang="en">
<#assign ctx=request.getContextPath()>
<head>
    <meta charset="UTF-8">
    <meta name="viewport" content="width=device-width, initial-scale=1.0,maximum-scale=1.0, user-scalable=no">
    <meta http-equiv="X-UA-Compatible" content="IE=edge,chrome=1">
    <meta name="renderer" content="webkit">
    <meta http-equiv="Cache-Control" content="no-siteapp">
    <title>${owner.owner}的博客</title>
    <link rel="stylesheet" href="${ctx}/static/plugin/mdui/css/mdui.min.css">
    <link rel="stylesheet" href="${ctx}/static/css/common.css">
    <script src="${ctx}/static/plugin/jquery-3.3.1.min.js"></script>
    <script src="${ctx}/static/plugin/mdui/js/mdui.min.js"></script>
    <script src="${ctx}/static/plugin/ajaxfileupload.js"></script>
    <script src="${ctx}/static/js/theme.js"></script>
</head>
<body class="mdui-drawer-body-left mdui-appbar-with-toolbar mdui-loaded mdui-theme-primary-${(owner.themePrimary)!'teal'} mdui-theme-accent-${(owner.themeAccent)!'teal'} mdui-theme-layout-${(owner.themeLayout)!'light'}">

<input name="ctx" id="ctx" value="${ctx}" hidden="hidden">
<input name="title" id="title" value="${owner.owner}的博客" hidden="hidden">
<!--header-->
<#include "/header.ftl">
<!--nav-->
<#include "/nav.ftl">
<!--body-->
<div class="mdui-container">
    <div class="main-spacer"></div>
    <#if articles.list??>
        <#list articles.list as article>
            <div class="main-spacer"></div>
            <div class="mdui-card mdui-shadow-3 mdui-hoverable">
                <div class="mdui-card-primary">
                    <div class="mdui-card-primary-title mdui-typo"><a class="mdui-text-color-theme-accent" href="${ctx}/article/${article.aid}">${article.title}</a></div>
                    <div class="mdui-card-primary-subtitle">${article.createTime?date}</div>
                </div>
                <div class="mdui-card-content mdui-typo">
                    ${article.content}
                </div>
                <div class="mdui-card-actions">
                    <a class="mdui-btn mdui-ripple mdui-text-color-theme-accent" href="${ctx}/article/${article.aid}">继续阅读</a>
                </div>
            </div>
        </#list>
    </#if>
    <div class="main-spacer"></div>
    <div class="page-nav">
        <div class="prev-page mdui-col-xs-6">
            <#if articles??>
                <#if (articles.pageNum > 1)>
                    <a class="next" title="" href="${ctx}/page/${articles.prePage}">
                        <button name="lastPage" class="mdui-btn mdui-btn-icon mdui-ripple mdui-float-left" mdui-tooltip="{content: '上一页'}">
                            <i class="mdui-icon material-icons mdui-text-color-theme-accent">navigate_before</i>
                        </button>
                    </a>
                </#if>
            </#if>
        </div>
        <div class="next-page mdui-col-xs-6">
            <#if articles??>
                <#if (articles.pageNum < articles.pages)>
                    <a class="next" title="" href="${ctx}/page/${articles.nextPage}">
                        <button name="nextPage" class="mdui-btn mdui-btn-icon mdui-ripple mdui-float-right" mdui-tooltip="{content: '下一页'}">
                            <i class="mdui-icon material-icons mdui-text-color-theme-accent">navigate_next</i>
                        </button>
                    </a>
                </#if>
            </#if>
        </div>
    </div>
</div>
<div class="main-spacer"></div>
<!--login-->
<#include "/login.ftl">
<!--register-->
<#include "/register.ftl">
<!--footer-->
<#--<#include "/footer.ftl">-->
<button class="mdui-fab mdui-fab-fixed mdui-ripple mdui-color-theme-accent mdui-fab-hide" id="scrolltop-btn">
    <i class="mdui-icon material-icons">keyboard_arrow_up</i>
</button>
<script src="${ctx}/static/js/common.js"></script>
</body>
</html>