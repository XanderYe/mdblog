var ctx = $("#ctx").val();
$(function () {
    var E = window.wangEditor;
    var editor = new E('#toolbar', '#editor');
    editor.customConfig.uploadFileName = 'file';
    editor.customConfig.zIndex = '0';
    editor.customConfig.uploadImgServer = ctx + '/upload';
    editor.create();
    $(".w-e-toolbar").css("display", "block");
    $(".w-e-menu").css("float", "left");
    $(".w-e-text-container").css("height","400px");

    //发表
    $(".publish-btn").click(function () {
        var title = $("input[name='title']").val();
        var content = editor.txt.html();
        var select = $(".mdui-select").val();
        if (valid(title, content, select)) {
            $.ajax({
                url: ctx + "/article/addArticle",
                type: "POST",
                data: {"title": title, "tid": select, "content": content},
                success: function (data) {
                    if (data.code == 0) {
                        mdui.snackbar({
                            message: "发表成功",
                            position: "top"
                        });
                        setTimeout(function () {
                            location.href = ctx + "/";
                        }, 1000)

                    } else {
                        mdui.snackbar({
                            message: data.msg,
                            position: "top"
                        });
                    }
                }
            })
        }
    });
    //修改文章
    $(".edit-btn").click(function () {
        var title = $("input[name='title']").val();
        var content = editor.txt.html();
        var select = $(".mdui-select").val();
        var aid = $("input[name='aid']").val();
        if (valid(title, content, select)) {
            $.ajax({
                url: ctx + "/article/updateArticle",
                type: "POST",
                data: {"title": title, "tid": select, "content": content,"aid":aid},
                success: function (data) {
                    if (data.code == 0) {
                        mdui.snackbar({
                            message: "修改成功",
                            position: "top"
                        });
                        setTimeout(function () {
                            location.href = ctx + "/article/"+aid;
                        }, 1000)

                    } else {
                        mdui.snackbar({
                            message: data.msg,
                            position: "top"
                        });
                    }
                }
            })
        }
    });

    function valid(title, text, select) {
        var flag = true;
        if (title == null || title == "") {
            flag = false;
            mdui.snackbar({
                message: "标题不能为空",
                position: "top"
            });
        }
        if (flag && (text == null || text == "<p><br></p>")) {
            flag = false;
            mdui.snackbar({
                message: "不能提交空文章",
                position: "top"
            });
        }
        if (flag && (select == null || select == 0)) {
            flag = false;
            mdui.snackbar({
                message: "请选择主题",
                position: "top"
            });
        }
        return flag;
    }
});