$(function () {
    getToken();

    getOwner();

    getTopics();

    getArticles();

    scrolltop();

    binds();

    var loginDialog = new mdui.Dialog('.mc-login');
    var registerDialog = new mdui.Dialog('.mc-register');
    $(".mc-login-btn").click(function () {
        var localUsername = localStorage.getItem("username");
        var localPassword = localStorage.getItem("password");
        if (localUsername != null && localPassword != null) {
            $("#loginForm input[name=username]").val(localUsername);
            $("#loginForm input[name=password]").val(localPassword);
            $("#loginForm #rememberMe").attr("checked", "checked");
            $("#loginForm .mdui-textfield").eq(0).addClass("mdui-textfield-not-empty");
            $("#loginForm .mdui-textfield").eq(1).addClass("mdui-textfield-not-empty");
        }
        loginDialog.open();
    });
});


function binds() {
    $(".mc-register-btn").click(function () {
        $(".captcha").trigger("click");
        registerDialog.open();
    });
    $(".mc-login .close").click(function () {
        loginDialog.close();
    });
    $(".mc-register .close").click(function () {
        registerDialog.close();
    });
    $(".mc-register-trigger").click(function () {
        loginDialog.close();
        $(".captcha").trigger("click");
        registerDialog.open();
    });

    //登录
    $(".mc-login").keyup(function (event) {
        if (event.keyCode == 13) {
            $(".login-button").trigger("click");
        }
    });

    $(".login-button").click(function () {
        if (validForm("#loginForm")) {
            if ($("#rememberMe").is(":checked")) {
                localStorage.setItem("username", $("#loginForm input[name=username]").val());
                localStorage.setItem("password", $("#loginForm input[name=password]").val());
            } else {
                localStorage.removeItem("username");
                localStorage.removeItem("password");
            }
            asyncPost("/user/login", $("#loginForm").serialize(), function (data) {
                snackbar("登录成功");

                setTimeout(function () {
                    location.reload();
                }, 1000)
            });
        } else {
            snackbar("各项不为空");
        }
    });

    //注册
    $(".register-button").click(function () {
        var inputs = $("#registerForm input");
        var login = true;
        inputs.remove
        for (var i = 1; i < inputs.length; i++) {
            if (inputs.eq(i).val() == "") {
                login = false;
            }
        }
        if (validForm("#registerForm")) {
            if ($("#registerForm input[name=password]").val() != $("#registerForm input[name=password2]").val()) {
                snackbar("两次密码不一致");
            } else {
                asyncPost("/user/register", $("#registerForm").serialize(), function (data) {
                    snackbar("注册成功");
                    registerDialog.close();
                    loginDialog.open();
                })
            }
        } else {
            snackbar("各项不为空");
        }
        $(".captcha").trigger("click");
    });

    $(".captcha").click(function () {
        $(this).prop("src", ajaxUrl + "/captcha?date=" + new Date());
    });


    //注销
    $(".bottom button").click(function () {
        asyncGet("/user/logout", null, function () {
            snackbar("注销成功");

            setTimeout(function () {
                location.href = ajaxUrl + "/";
            }, 1000);
        })
    });
    //上传
    $(".upload-btn").click(function () {
        $("input[type='file']").trigger("click");
    });

    $("input[type='file']").change(function () {
        var formData = new FormData($(this));
        $.ajaxFileUpload({
            type: "post",
            url: ajaxUrl + "/user/uploadAvatar",
            secureuri: false,
            fileElementId: 'avatar',
            dataType: "text",
            headers: {
                "X-User-Token": token
            },
            success: function (data) {
                //ajaxfileupload使用iframe+form的方式实现异步上传文件 无法直接解析body
                var json = $.parseJSON($(data).html());
                if (json.code == 0) {
                    location.reload();
                } else {
                    mdui.snackbar({
                        message: "上传失败",
                        position: "top"
                    });
                }
            }
        });
    });

    //返回顶部
    $("#scrolltop-btn").click(function (e) {
        $('body,html').animate({
            scrollTop: 0
        }, 600)
    });


    $(".nav-button").unbind().bind("click", function () {
        var pageName = $(this).attr("location");
        openPage(pageName);
    })
}