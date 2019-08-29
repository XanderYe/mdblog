<!DOCTYPE html>
<html lang="en">
<#assign ctx=request.getContextPath()>
<head>
    <meta charset="UTF-8">
    <meta name="viewport" content="width=device-width, initial-scale=1.0,maximum-scale=1.0, user-scalable=no">
    <meta http-equiv="X-UA-Compatible" content="IE=edge,chrome=1">
    <meta name="renderer" content="webkit">
    <meta http-equiv="Cache-Control" content="no-siteapp">
    <title>教育管理信息系统</title>
    <link rel="stylesheet" href="${ctx}/static/plugin/mdui/css/mdui.min.css">
    <link rel="stylesheet" href="${ctx}/static/css/common.css">
    <script src="${ctx}/static/plugin/jquery-3.3.1.min.js"></script>
    <script src="${ctx}/static/plugin/mdui/js/mdui.min.js"></script>
    <script src="${ctx}/static/js/theme.js"></script>
</head>
<body class="mdui-drawer-body-left mdui-appbar-with-toolbar mdui-loaded mdui-theme-primary-${(user.themePrimary)!'teal'} mdui-theme-accent-${(user.themeAccent)!'teal'} mdui-theme-layout-${(user.themeLayout)!'teal'}">
<input name="ctx" id="ctx" value="${ctx}" hidden="hidden">
<input name="title" id="title" value="个人信息" hidden="hidden">
<!--header-->
<#include "/header.ftl">
<!--nav-->
<#include "/nav.ftl">
<!--body-->
<div class="mdui-container">
    <div class="main">

    </div>
</div>
<!--footer-->
<#include "/footer.ftl">
<script src="${ctx}/static/js/common.js"></script>
</body>
</html>