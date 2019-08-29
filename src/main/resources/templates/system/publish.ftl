<!DOCTYPE html>
<html lang="en">
<#assign ctx=request.getContextPath()>
<head>
    <meta charset="UTF-8">
    <meta name="viewport" content="width=device-width, initial-scale=1.0,maximum-scale=1.0, user-scalable=no">
    <meta http-equiv="X-UA-Compatible" content="IE=edge,chrome=1">
    <meta name="renderer" content="webkit">
    <meta http-equiv="Cache-Control" content="no-siteapp">
    <title>发表文章</title>
    <link rel="stylesheet" href="${ctx}/static/plugin/mdui/css/mdui.min.css">
    <link rel="stylesheet" href="${ctx}/static/css/common.css">
    <script src="${ctx}/static/plugin/jquery-3.3.1.min.js"></script>
    <script src="${ctx}/static/plugin/mdui/js/mdui.min.js"></script>
    <link rel="stylesheet" href="${ctx}/static/plugin/wangEditor/wangEditor.min.css">
    <script src="${ctx}/static/plugin/ajaxfileupload.js"></script>
    <script src="${ctx}/static/js/theme.js"></script>
</head>
<body class="mdui-drawer-body-left mdui-appbar-with-toolbar mdui-loaded mdui-theme-primary-${(owner.themePrimary)!'teal'} mdui-theme-accent-${(owner.themeAccent)!'teal'} mdui-theme-layout-${(owner.themeLayout)!'light'}">

<input name="ctx" id="ctx" value="${ctx}" hidden="hidden">
<input name="title2" id="title" value="发表文章" hidden="hidden">
<!--header-->
<#include "/header.ftl">
<!--nav-->
<#include "/nav.ftl">
<!--body-->
<div class="mdui-container">
    <div class="main-spacer"></div>
    <div class="main-spacer"></div>
    <input hidden="hidden" id="aid" name="aid" value="${(article.aid)!'0'}">
    <div class="mdui-card mdui-shadow-3 mdui-hoverable">
        <div class="mdui-card-primary">
            <input type="text"class="mdui-textfield-input" name="title" style="font-size: 20px;" value="${(article.title)!''}"/>
            <div class="main-spacer"></div>
            <div>
                <div style="margin-top:9px;font-size: 16px;float:left">请选择主题：</div>
                <select class="mdui-select" mdui-select>
                    <option value="0">请选择</option>
                        <#if topics??>
                            <#list topics as topic>
                                <#if article??>
                                    <#if article.tid==topic.tid>
                                    <option value="${topic.tid}" selected="selected">${topic.tname}</option>
                                    <#else>
                                    <option value="${topic.tid}">${topic.tname}</option>
                                    </#if>
                                <#else>
                                    <option value="${topic.tid}">${topic.tname}</option>
                                </#if>
                            </#list>
                        </#if>
                </select>
            </div>
            <div class="main-spacer"></div>
            <div id="toolbar" class="toolbar"></div>
            <div class="main-spacer"></div>
            <div id="editor" name="content" class="text">
                <p>${(article.content)!''}</p>
            </div>
            <div class="mdui-clearfix" style="margin-top:30px;">
            <#if article??>
                <button class="edit-btn mdui-btn mdui-btn-raised mdui-color-theme-accent mdui-ripple mdui-float-right">修改</button>
            <#else>
                    <button class="publish-btn mdui-btn mdui-btn-raised mdui-color-theme-accent mdui-ripple mdui-float-right">发表</button>
            </#if>
            </div>
        </div>
    </div>

</div>
<div class="main-spacer"></div>
<!--login-->
<#include "/login.ftl">
<!--register-->
<#include "/register.ftl">
<script src="${ctx}/static/js/common.js"></script>
    <script src="${ctx}/static/plugin/wangEditor/wangEditor.min.js"></script>
<script src="${ctx}/static/js/system/publish.js"></script>
</body>
</html>