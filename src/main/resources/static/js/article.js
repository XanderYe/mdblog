var ctx = $("#ctx").val();
$(function () {
    var E = window.wangEditor;
    var editor = new E('#toolbar', '#editor');
    editor.customConfig.uploadFileName = 'file';
    editor.customConfig.zIndex = '0';
    editor.customConfig.uploadImgServer = ctx + '/upload';
    editor.customConfig.menus = [
        'bold',  // 粗体
        'fontName',  // 字体
        'italic',  // 斜体
        'underline',  // 下划线
        'strikeThrough',  // 删除线
        'link',  // 插入链接
        'justify',  // 对齐方式
        'foreColor',  // 文字颜色
        'quote',  // 引用
        'emoticon',  // 表情
        'code',  // 插入代码
        'undo',  // 撤销
        'redo'  // 重复
    ]
    editor.create();
    $(".w-e-toolbar").css("display", "block");
    $(".w-e-menu").css("float", "left");

    //发表评论
    $(".comment-btn").click(function () {
        var nickname = $("input[name='nickname']").val();
        var email = $("input[name='email']").val();
        var aid = $("input[name='aid']").val();
        var content = editor.txt.html();
       // if (valid(title, content, select)) {
            $.ajax({
                url: ctx + "/comment/addComment",
                type: "POST",
                data: {"nickname": nickname, "email": email, "content": content,"aid":aid},
                success: function (data) {
                    if (data.code == 0) {
                        mdui.snackbar({
                            message: "评论成功",
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
        //}
    });

    /*function valid(title, text, select) {
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
    }*/
});