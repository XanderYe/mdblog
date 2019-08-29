/**
 * 设置文档主题
 */
(function () {
    var DEFAULT_PRIMARY = "teal";
    var DEFAULT_ACCENT = "teal";
    var DEFAULT_LAYOUT = "light";

    var userPrimary = "teal";
    var userAccent = "teal";
    var userLayout = "light";

    var setDocsTheme = function (theme) {
        if (typeof theme.primary === "undefined") {
            theme.primary = false;
        }
        if (typeof theme.accent === "undefined") {
            theme.accent = false;
        }
        if (typeof theme.layout === "undefined") {
            theme.layout = false;
        }

        var i, len;
        var $body = $("body");

        var classStr = $body.attr("class");
        var classes = classStr.split(" ");

        // 设置主色
        if (theme.primary !== false) {
            for (i = 0, len = classes.length; i < len; i++) {
                if (classes[i].indexOf("mdui-theme-primary-") === 0) {
                    $body.removeClass(classes[i])
                }
            }
            $body.addClass("mdui-theme-primary-" + theme.primary);
            //setCookie("docs-theme-primary", theme.primary);
            $("input[name='doc-theme-primary'][value=" + theme.primary + "]").prop("checked", true);
        }

        // 设置强调色
        if (theme.accent !== false) {
            for (i = 0, len = classes.length; i < len; i++) {
                if (classes[i].indexOf("mdui-theme-accent-") === 0) {
                    $body.removeClass(classes[i]);
                }
            }
            $body.addClass("mdui-theme-accent-" + theme.accent);
            //setCookie("docs-theme-accent", theme.accent);
            $("input[name='doc-theme-accent'][value=" + theme.accent + "]").prop("checked", true);
        }

        // 设置主题色
        if (theme.layout !== false) {
            for (i = 0, len = classes.length; i < len; i++) {
                if (classes[i].indexOf("mdui-theme-layout-") === 0) {
                    $body.removeClass(classes[i]);
                }
            }
            if (theme.layout !== "") {
                $body.addClass("mdui-theme-layout-" + theme.layout);
            }
            //setCookie("docs-theme-layout", theme.layout);
            $("input[name='doc-theme-layout'][value=" + theme.layout + "]").prop("checked", true);
        }
    };

    // 切换主色
    $(document).on("change", "input[name='doc-theme-primary']", function () {
        userPrimary=$(this).val();
        setDocsTheme({
            primary: $(this).val()
        });
    });

    // 切换强调色
    $(document).on("change", "input[name='doc-theme-accent']", function () {
        userAccent=$(this).val();
        setDocsTheme({
            accent: $(this).val()
        });
    });

    // 切换主题色
    $(document).on("change", "input[name='doc-theme-layout']", function () {
        userLayout=$(this).val();
        setDocsTheme({
            layout: $(this).val()
        });
    });

    // 恢复默认主题
    $(document).on("cancel.mdui.dialog", "#dialog-docs-theme", function () {
        changeTheme(DEFAULT_PRIMARY,DEFAULT_ACCENT,DEFAULT_LAYOUT);
        setDocsTheme({
            primary: DEFAULT_PRIMARY,
            accent: DEFAULT_ACCENT,
            layout: DEFAULT_LAYOUT
        });
    });

    //保存
    $(document).on("confirm.mdui.dialog","#dialog-docs-theme",function () {
        changeTheme(userPrimary,userAccent,userLayout);
    });

    function changeTheme(primary, accent, layout) {
        asyncPost("/user/theme", {
            "primary": primary,
            "accent": accent,
            "layout": layout
        }, function (data) {
            snackbar("修改成功");
        })
    }
})();

