var topics;
var mduiDialog;
$(function () {
    dataList();

    if (mduiDialog == undefined) {
        mduiDialog = new mdui.Dialog("#mduiDialog", {closeOnConfirm: false});
    }

    $("body").on("click", ".open-add-modal", function () {
        $(".mdui-dialog-title").html("添加主题");
        $("input[name=topicId]").val("");
        $("input[name=name]").val("");
        $("input[name=description]").val("");
        mduiDialog.open();
    });

    $("body").on("click", ".open-edit-modal", function () {
        $(".mdui-dialog-title").html("编辑主题");
        var index = $(this).attr("index");
        var topic = topics[index];
        $("input[name=topicId]").val(topic.tid);
        $("input[name=name]").val(topic.tname);
        $("input[name=description]").val(topic.tdesc);
        mduiDialog.open();
    });

    $(".ok-button").click(function () {
        var data = {
            tid: $("input[name=topicId]").val(),
            tname: $("input[name=name]").val(),
            tdesc: $("input[name=description]").val()
        }
        $.ajax({
            url: ctx + "/topic/updateTopic",
            data: data,
            type: "POST",
            success: function (res) {
                mdui.snackbar({
                    message: "保存成功",
                    position: "top"
                });
                mduiDialog.close();
                dataList();
            }
        });
    });

    $("body").on("click", ".delete-btn", function () {
        var tid = $(this).attr("tid");
        mdui.confirm("确认删除吗？", "删除确认", function () {
            $.ajax({
                url: ctx + "/topic/delTopic",
                data: {tid: tid},
                type: "POST",
                success: function (res) {
                    mdui.snackbar({
                        message: "删除成功",
                        position: "top"
                    });
                    dataList();
                }
            });
        }, function () {

        }, {"confirmText": "确认", "cancelText": "取消"});
    });
});

function dataList() {
    $.ajax({
        url: ctx + "/topic/getAllTopic",
        data: null,
        type: "GET",
        success: function (res) {
            if (res.code == 0) {
                $(".dataList").html("");
                topics = res.data;
                if (topics.length > 0) {
                    $.each(topics, function (index, value) {
                        var item = "<tr>" +
                            "<td>" + formatData(value.tname) + "</td>" +
                            "<td>" + formatData(value.tdesc) + "</td>" +
                            "<td>" + formatData(value.createTime) + "</td>" +
                            "<td>" + formatData(value.updateTime) + "</td>" +
                            "<td>" +
                            "<button class='open-edit-modal mdui-btn mdui-btn-raised mdui-color-theme-accent mdui-ripple' index='" + index + "'>修改</button>" +
                            "<button class='delete-btn mdui-btn mdui-btn-raised mdui-color-red mdui-ripple' style='margin-left:10px' topicId='+" + value.tid + "'>删除</button>" +
                            "</td>" +
                            "</tr>";
                        $(".dataList").append(item);
                    })
                } else {
                    var item = "<tr><td colspan='5' style='text-align: center'>无数据</td></tr>";
                    $(".dataList").append(item);
                }
            }
        }
    })
}

function formatData(data) {
    if (data == undefined || data == null || data == "") {
        return "无";
    } else {
        return data;
    }
};