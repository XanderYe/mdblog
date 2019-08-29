var Dw = new DateWeek(),
    dt = new Date();
var year = dt.getFullYear();
var month = dt.getMonth() - 0 + 1;
var date = dt.getDate();
var today = year + "-" + month + "-" + date;
var changeYear = year;
var changeMonth = month;
$(function () {
    changeCalendar(year, month);
    $(".c-left").click(function () {
        changeMonth = changeMonth - 1 > 0 ? changeMonth - 1 : 12;
        if (changeMonth == 12) {
            changeYear = changeYear - 1;
        }
        changeCalendar(changeYear, changeMonth);
    });
    $(".c-right").click(function () {
        changeMonth = changeMonth + 1 > 12 ? 1 : changeMonth + 1;
        if (changeMonth == 1) {
            changeYear = changeYear + 1;
        }
        changeCalendar(changeYear, changeMonth);
    });
    $(".schedule-date").html(date + "日");
    $(".schedule-week").html("周" + "日一二三四五六".charAt(new Date().getDay()));

});

function changeCalendar(year, month) {
    Dw.setDate(year, month);
    var list = Dw.getDayList(false);
    var days = $(".day");
    $(".c-month").html(year + "年" + month + "月");
    $.each(days, function (index, day) {
        var span = $(day).children("span");
        if (!list[index].siblings) {
            if (list[index].date == today) {
                span.addClass("mdui-color-theme-accent");
            } else {
                span.removeClass("mdui-color-theme-accent");
            }
        } else {
            span.addClass("not-this-month");
        }
        span.attr("date", list[index].date);
        span.html(list[index].day);
    });
}