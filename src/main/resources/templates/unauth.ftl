<!DOCTYPE html>
<html lang="en">
<#assign ctx=request.getContextPath()>
<head>
    <meta charset="UTF-8">
    <title>Unauthorized</title>
    <link rel="stylesheet" href="${ctx}/static/plugin/mdui/css/mdui.min.css">
    <script src="${ctx}/static/plugin/jquery-3.3.1.min.js"></script>
    <script src="${ctx}/static/plugin/mdui/js/mdui.min.js"></script>
</head>
<body>
<script>
    $(function () {
        mdui.alert("权限不足！请先登录", "警告", function () {
            setTimeout(function () {
                location.href =  "${ctx}/";
            }, 50)
        }, {confirmText: "确定"});
    })
</script>
</body>
</html>