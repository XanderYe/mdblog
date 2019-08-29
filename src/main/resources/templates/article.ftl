<!DOCTYPE html>
<html lang="en">
<#assign ctx=request.getContextPath()>
<head>
    <meta charset="UTF-8">
    <meta name="viewport" content="width=device-width, initial-scale=1.0,maximum-scale=1.0, user-scalable=no">
    <meta http-equiv="X-UA-Compatible" content="IE=edge,chrome=1">
    <meta name="renderer" content="webkit">
    <meta http-equiv="Cache-Control" content="no-siteapp">
    <title>${article.title}</title>
    <link rel="stylesheet" href="${ctx}/static/plugin/mdui/css/mdui.min.css">
    <link rel="stylesheet" href="${ctx}/static/css/common.css">
    <link rel="stylesheet" href="${ctx}/static/plugin/wangEditor/wangEditor.min.css">
    <script src="${ctx}/static/plugin/jquery-3.3.1.min.js"></script>
    <script src="${ctx}/static/plugin/mdui/js/mdui.min.js"></script>
    <script src="${ctx}/static/plugin/ajaxfileupload.js"></script>
    <script src="${ctx}/static/js/theme.js"></script>
</head>
<body class="mdui-drawer-body-left mdui-appbar-with-toolbar mdui-loaded mdui-theme-primary-${(owner.themePrimary)!'teal'} mdui-theme-accent-${(owner.themeAccent)!'teal'} mdui-theme-layout-${(owner.themeLayout)!'light'}">

<input name="ctx" id="ctx" value="${ctx}" hidden="hidden">
<input name="title" id="title" value="文章" hidden="hidden">
<!--header-->
<#include "/header.ftl">
<!--nav-->
<#include "/nav.ftl">
<!--body-->
<div class="mdui-container">
    <input hidden="hidden" name="aid" value="${(article.aid)!'0'}">
    <div class="main-spacer"></div>
    <div class="mdui-card mdui-shadow-3 mdui-hoverable">
        <div class="mdui-card-primary">
            <div class="mdui-card-primary-title mdui-typo"><a class="mdui-text-color-theme-accent"
                                                              href="#">${article.title}</a></div>
            <div class="mdui-card-primary-subtitle">${article.createTime?date}</div>
        <#--<div class="mdui-card-primary-subtitle">作者：${article.creator}</div>-->
        </div>
        <div class="mdui-card-content mdui-typo">
        ${article.content}
        </div>
    </div>
    <div class="main-spacer"></div>
    <div class="mdui-chip mdui-typo">
        <span class="mdui-chip-icon">
           <i class="mdui-icon material-icons">message</i>
        </span>
        <span class="mdui-chip-title">已${comments?size}有条评论</span>
    </div>
    <#list comments as comment>
        <div class="main-spacer"></div>
        <div class="mdui-card mdui-shadow-3 mdui-hoverable">
            <div class="mdui-card-primary">
                <div class="mdui-card-header">
                    <div class="mdui-card-primary-title mdui-typo"><a class="mdui-text-color-theme-accent"
                                                                      href="">${comment.creator}</a></div>
                    <div class="mdui-card-primary-subtitle">${comment.createTime?datetime}</div>
                </div>
            </div>
            <div class="mdui-card-content">
                <div class="mdui-card-content mdui-typo">
                    ${comment.content}
                </div>
            </div>
        </div>
    </#list>
    <div class="main-spacer"></div>
    <div class="main-spacer"></div>
    <div class="mdui-card mdui-shadow-3 mdui-hoverable">
        <div class="mdui-card-primary">
            <#if user??>
            <#else>
            <form>
                <div class="mdui-textfield mdui-textfield-floating-label mdui-textfield-has-bottom">
                    <label class="mdui-textfield-label">称呼</label>
                    <input class="mdui-textfield-input" name="nickname" type="text" required="">
                </div>
                <div class="mdui-textfield mdui-textfield-floating-label mdui-textfield-has-bottom">
                    <label class="mdui-textfield-label">邮箱</label>
                    <input class="mdui-textfield-input" name="email" type="text" required="">
                </div>
            </form>
            </#if>
            <div id="toolbar" class="toolbar"></div>
            <div class="main-spacer"></div>
            <div id="editor" name="content" class="text"></div>
        </div>
        <div class="mdui-card-content mdui-typo">
            <div class="mdui-clearfix">
                <button class="comment-btn mdui-btn mdui-btn-raised mdui-color-theme-accent mdui-ripple mdui-float-right">评论</button>
                <#if user??>
                    <#if (user.permission)==1>
                        <form action="${ctx}/system/publish" method="post">
                            <input hidden="hidden" name="articleId" value="${(article.aid)!'0'}">
                            <button type="submit" class="edit-btn mdui-btn mdui-btn-raised mdui-color-theme-accent mdui-ripple mdui-float-right" style="margin-right:5px;">编辑</button>
                        </form>

                    </#if>
                </#if>
            </div>
        </div>
    </div>
    <div class="main-spacer"></div>
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
<script>

</script>
<script src="${ctx}/static/js/common.js"></script>
<script src="${ctx}/static/plugin/wangEditor/wangEditor.min.js"></script>
<script src="${ctx}/static/js/article.js"></script>
</body>
</html>