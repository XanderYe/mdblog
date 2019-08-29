<div class="mdui-drawer" id="main-drawer">
    <div class="mdui-list" mdui-collapse="{accordion: true}" style="margin-bottom: 76px;">
        <div class="mdui-card" style="margin-top:-8px;">
            <div class="mdui-card-media">
                <img src="${ctx}/static/img/card.jpg"/>
                <div class="mdui-card-media-covered">
                    <div class="mdui-card-primary">
                        <img src="${ctx}${(owner.avatar)!''}" class="card-img">
                        <div class="mdui-card-primary-title">
                            ${owner.owner}
                        </div>
                        <div class="mdui-card-primary-subtitle">${owner.description}</div>
                    </div>
                </div>
            </div>
            <div class="mdui-card-actions">
                <a href="${owner.github}" target="_blank" class="mdui-btn mdui-ripple mdui-float-left">github</a>
                <a href="mailto:${owner.email}" class="mdui-btn mdui-ripple mdui-float-right">邮件</a>
            </div>
        </div>
        <div class="mdui-collapse-item">
            <div class="mdui-list-item mdui-ripple">
                <i class="mdui-list-item-icon mdui-icon material-icons">home</i>
                <a class="mdui-list-item-content" href="${ctx}/">首页</a>
            </div>
        </div>
        <div class="mdui-collapse-item">
            <div class="mdui-collapse-item-header mdui-list-item mdui-ripple">
                <i class="mdui-list-item-icon mdui-icon material-icons">list</i>
                <div class="mdui-list-item-content">主题</div>
                <i class="mdui-collapse-item-arrow mdui-icon material-icons">keyboard_arrow_down</i>
            </div>
            <div class="mdui-collapse-item-body mdui-list">
                <#list topicList as topic>
                <a href="${ctx}/topic/${topic.tid}/page/1" class="mdui-list-item mdui-ripple">${topic.tname}</a>
                </#list>
            </div>
        </div>

        <#if user??>
            <div class="mdui-collapse-item">
                <div class="mdui-collapse-item-header mdui-list-item mdui-ripple">
                    <i class="mdui-list-item-icon mdui-icon material-icons">account_circle</i>
                    <div class="mdui-list-item-content">个人信息</div>
                    <i class="mdui-collapse-item-arrow mdui-icon material-icons">keyboard_arrow_down</i>
                </div>
                <div class="mdui-collapse-item-body mdui-list">
                    <a href="#" class="mdui-list-item mdui-ripple">修改信息</a>
                    <#--<a href="#" class="mdui-list-item mdui-ripple">选项二</a>
                    <a href="#" class="mdui-list-item mdui-ripple">选项三</a>-->
                </div>
            </div>

            <#if (user.permission)==1>
                <div class="mdui-collapse-item">
                    <div class="mdui-collapse-item-header mdui-list-item mdui-ripple">
                        <i class="mdui-list-item-icon mdui-icon material-icons">settings</i>
                        <div class="mdui-list-item-content">博客管理</div>
                        <i class="mdui-collapse-item-arrow mdui-icon material-icons">keyboard_arrow_down</i>
                    </div>
                    <div class="mdui-collapse-item-body mdui-list">
                        <a href="${ctx}/system/topic" class="mdui-list-item mdui-ripple">修改主题</a>
                        <a href="${ctx}/system/publish" class="mdui-list-item mdui-ripple">发表文章</a>
                    </div>
                </div>
            </#if>
        </#if>
    </div>
</div>
<script>
    //导航栏状态
    var title=$("#title").val();
    if(null!=title){
        $(".web-title").html(title);
    }
    var lists=$(".mdui-list-item");
    $.each(lists,function (index,value) {
        if($(value).html()==title){
            $(value).addClass("mdui-list-item-active");
            $(value).parents(".mdui-collapse-item").addClass("mdui-collapse-item-open");
        }
    })
</script>