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
    <script src="${ctx}/static/js/system/topic.js"></script>
</head>
<body class="mdui-drawer-body-left mdui-appbar-with-toolbar mdui-loaded mdui-theme-primary-${(owner.themePrimary)!'teal'} mdui-theme-accent-${(owner.themeAccent)!'teal'} mdui-theme-layout-${(owner.themeLayout)!'light'}">

<input name="ctx" id="ctx" value="${ctx}" hidden="hidden">
<input name="title2" id="title" value="修改主题" hidden="hidden">
<!--header-->
<#include "/header.ftl">
<!--nav-->
<#include "/nav.ftl">
<!--body-->
<div class="mdui-container">
    <div class="main-spacer"></div>
    <button class='open-add-modal mdui-btn mdui-btn-raised mdui-color-theme-accent mdui-ripple'>添加</button>
    <div class="main-spacer"></div>
    <div class="mdui-table-fluid">
        <table class="mdui-table mdui-table-hoverable">
            <thead>
                <tr>
                    <th>名称</th>
                    <th>介绍</th>
                    <th>创建时间</th>
                    <th>修改时间</th>
                    <th>操作</th>
                </tr>
            </thead>
            <tbody class="dataList">

            </tbody>
        </table>
    </div>
</div>
<div class="main-spacer"></div>
<div class="mdui-dialog" id="mduiDialog">
    <div class="mdui-dialog-title">添加主题</div>
    <div class="mdui-dialog-content">
        <input name="tid" type="text" hidden>
        <div class="mdui-textfield mdui-textfield-floating-label mdui-textfield-has-bottom mdui-textfield-not-empty">
            <label class="mdui-textfield-label">名称</label>
            <input class="mdui-textfield-input" name="name" type="text" required="">
            <div class="mdui-textfield-error">名称不能为空</div>
        </div>
        <div class="mdui-textfield mdui-textfield-floating-label mdui-textfield-has-bottom mdui-textfield-not-empty">
            <label class="mdui-textfield-label">介绍</label>
            <input class="mdui-textfield-input" name="desc" type="text" required="">
            <div class="mdui-textfield-error">介绍不能为空</div>
        </div>
    </div>
    <div class="mdui-dialog-actions">
        <button class="mdui-btn mdui-ripple" mdui-dialog-close>取消</button>
        <button class="mdui-btn mdui-ripple ok-button" mdui-dialog-confirm>确定</button>
    </div>
</div>
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