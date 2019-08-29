function getToken() {
    token = localStorage.getItem("token");
    if (token == null) {
        $("#appbar-userinfo").css("display", "none");
        $("#appbar-login").css("display", "block");
    }
}

function getOwner() {
    owner = JSON.parse(sessionStorage.getItem("owner"));
    if (owner == null) {
        get("/user/getOwner", "", function (res) {
            sessionStorage.setItem("owner", JSON.stringify(res));
            owner = res;
        })
    }
    setOwnerData();
}

function getTopics() {
    asyncGet("/topic/getAll", null, function (res) {
        $(".topic-list").html("");
        if (res.length > 0) {
            $.each(res, function (index, topic) {
                $(".topic-list").append("<a href='index.html?id=" + topic.id + "' class='mdui-list-item mdui-ripple topic-button'>" + topic.name + "</a>");
            });
            $(".topic-button").unbind().bind("click",function () {
                $(".web-title").html($(this).html());
                $("#main-drawer .mdui-list-item-active").removeClass("mdui-list-item-active");
                $(this).addClass("mdui-list-item-active");
            })
        }
    })
}

function getArticles() {
    asyncGet("/article/getRecentArticles", {topicId: topicId, page: articlePage}, function (res) {
        $(".article-list").html("");
        if (res.length > 0) {
            $.each(res, function (index, article) {
                $(".article-list").append("");
            })
        }
    })
}

function setOwnerData() {
    $("body").addClass("mdui-theme-primary-" + owner.themePrimary + " mdui-theme-accent-" + owner.themeAccent + " mdui-theme-layout-" + owner.themeLayout);
    $("title").html(owner.owner + "的博客");
    $("#drawer-owner").html(owner.owner);
    $("#drawer-description").html(owner.description);
    $("#drawer-avatar").attr("src", ajaxUrl + owner.avatar);
    $("#drawer-github").attr("href", owner.github);
    $("#drawer-email").attr("href", "mailto:" + owner.email);
}

//返回顶部
function scrolltop() {
    $(window).scroll(function (e) {
        if ($(window).scrollTop() > 80 && $(document).height() != $(window).height() + $(window).scrollTop())
            document.getElementById("scrolltop-btn").classList.remove("mdui-fab-hide");
        else
            document.getElementById("scrolltop-btn").classList.add("mdui-fab-hide")
    })
}

// 封装异步post方法
function asyncPost(url, data, success) {
    $.ajax({
        url: ajaxUrl + url,
        type: "POST",
        data: data,
        traditional: true,
        headers: {
            "X-User-Token": token
        },
        success: function (res) {
            if (res.code == 0) {
                if (success) {
                    success(res.data);
                }
            } else {
                snackbar(res.msg);
            }
        }
    });
}

// 封装异步post方法
function jsonPost(url, data, success) {
    $.ajax({
        url: ajaxUrl + url,
        dataType: "json",
        contentType: "application/json",
        type: "POST",
        data: data,
        traditional: true,
        headers: {
            "X-User-Token": token
        },
        success: function (res) {
            if (res.code == 0) {
                if (success) {
                    success(res.data);
                }
            } else {
                snackbar(res.msg);
            }
        }
    });
}

// 封装异步get方法
function asyncGet(url, data, success) {
    $.ajax({
        url: ajaxUrl + url,
        type: "GET",
        data: data,
        traditional: true,
        headers: {
            "X-User-Token": token
        },
        success: function (res) {
            if (res.code == 0) {
                if (success) {
                    success(res.data);
                }
            } else {
                snackbar(res.msg);
            }
        }
    });
}

// 封装post方法
function post(url, data, success) {
    $.ajax({
        url: ajaxUrl + url,
        type: "POST",
        data: data,
        async: false,
        traditional: true,
        headers: {
            "X-User-Token": token
        },
        success: function (res) {
            if (res.code == 0) {
                if (success) {
                    success(res.data);
                }
            } else {
                snackbar(res.msg);
            }
        }
    });
}

// 封装get方法
function get(url, data, success) {
    $.ajax({
        url: ajaxUrl + url,
        type: "GET",
        data: data,
        async: false,
        traditional: true,
        headers: {
            "X-User-Token": token
        },
        success: function (res) {
            if (res.code == 0) {
                if (success) {
                    success(res.data);
                }
            } else {
                snackbar(res.msg);
            }
        }
    });
}

// 封装MDUI通知
function snackbar(message) {
    mdui.snackbar({
        message: message,
        position: "top"
    });
}

/*获取URL中参数*/
var getParam = function (name) {
    var search = document.location.search;
    var pattern = new RegExp("[?&]" + name + "\=([^&]+)", "g");
    var matcher = pattern.exec(search);
    var items = null;
    if (null != matcher) {
        try {
            items = decodeURIComponent(decodeURIComponent(matcher[1]));
        } catch (e) {
            try {
                items = decodeURIComponent(matcher[1]);
            } catch (e) {
                items = matcher[1];
            }
        }
    }
    return items;
};

// 封装表单验证
function validForm(formId) {
    var flag = true;
    var inputs = $(formId).find(".mdui-textfield-input");
    if (inputs.length > 0) {
        $.each(inputs, function (index, value) {
            if ($(value).val() == "") {
                flag = false;
                $(value).parent().addClass("mdui-textfield-invalid-html5");
            } else {
                $(value).parent().removeClass("mdui-textfield-invalid-html5");
            }
        });
    }
    return flag;
}

// 封装重置表单状态
function resetForm(formId) {
    var inputs = $(formId).find(".mdui-textfield-input");
    if (inputs.length > 0) {
        $.each(inputs, function (index, value) {
            $(value).parent().removeClass("mdui-textfield-invalid-html5 mdui-textfield-focus mdui-textfield-not-empty");
            if ($(value).val() != "") {
                $(value).parent().addClass("mdui-textfield-not-empty");
            }
        });
    }
}

// 清空表单数据
function resetFormData(formId) {
    var inputs = $(formId).find(".mdui-textfield-input");
    if (inputs.length > 0) {
        $.each(inputs, function (index, value) {
            $(value).parent().removeClass("mdui-textfield-invalid-html5 mdui-textfield-focus mdui-textfield-not-empty");
            $(value).val("");
        });
    }
}
