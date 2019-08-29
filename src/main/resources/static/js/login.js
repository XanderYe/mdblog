$(function () {
    mdui.mutation();

    resetForm("#loginForm");

    $(document).keyup(function (event) {
        if (event.keyCode == 13) {
            $(".login-button").trigger("click");
        }
    });
    $(".login-button").click(function () {
        // 登录加载动画
        $(this).css("display","none");
        $(".mdui-spinner").css("display","block");
        if (validForm("#loginForm")) {
            post("/user/login", $("#loginForm").serialize(), function (data) {
                snackbar("登录成功");
                setTimeout(function () {
                    location.href = ctx + "/";
                }, 1000)
            });
            setTimeout(function () {
                $(".login-button").css("display","block");
                $(".mdui-spinner").css("display","none");
            },500)
        }else{
            $(".login-button").css("display","block");
            $(".mdui-spinner").css("display","none");
        }
    });
});