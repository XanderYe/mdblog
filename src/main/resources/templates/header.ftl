<header class="mdui-appbar mdui-appbar-fixed">
    <div class="mdui-toolbar mdui-color-theme">
        <span class="mdui-btn mdui-btn-icon mdui-ripple mdui-ripple-white"
              mdui-drawer="{target: '#main-drawer', swipe: true}"><i class="mdui-icon material-icons">menu</i></span>
        <div class="mdui-typo-title web-title">首页</div>
        <div class="mdui-toolbar-spacer"></div>
        <#if user??>
            <#if (user.permission)==1>
                <span class="mdui-btn mdui-btn-icon mdui-ripple mdui-ripple-white" mdui-dialog="{target: '#dialog-docs-theme'}"
                      mdui-tooltip="{content: '设置主题'}">
                    <i class="mdui-icon material-icons">color_lens</i>
                </span>
            </#if>
            <div class="mc-appbar-user">
            <div class="mdui-btn mdui-btn-icon mdui-btn-dense user" title="username"
                 mdui-menu="{target: '#appbar-user-popover', covered: false}">
                <img src="${ctx}${(user.avatar)!''}" width="32" height="32">
            </div>
            <div class="mdui-menu popover" id="appbar-user-popover"
                 style="transform-origin: 100% 0px 0px; position: fixed;">
                <div class="info">
                    <div class="avatar">
                        <div class="mc-avatar-upload">
                            <button class="mdui-btn mdui-btn-icon mdui-ripple upload-btn" type="button" title="点击上传头像">
                                <i class="mdui-icon material-icons">photo_camera</i>
                            </button>
                            <input type="file" title=" " accept="image/*" name="avatar" id="avatar">
                        </div>
                        <img src="${ctx}${(user.avatar)!''}" width="96" height="96">
                    </div>
                    <div class="username">${(user.username)!''}</div>
                    <a href="${ctx}/user/userinfo"
                       class="mdui-btn mdui-btn-dense mdui-btn-raised mdui-ripple mdui-color-blue mdui-text-color-white personal">
                        个人资料
                    </a>
                </div>
                <div class="bottom">
                    <button class="mdui-btn mdui-btn-dense mdui-btn-raised mdui-ripple mdui-ripple-black mdui-color-white mdui-float-right">
                        退出
                    </button>
                </div>
            </div>
        <#else>
            <div class="mc-login-btn mdui-btn mdui-btn-dense mdui-ripple mdui-ripple-white">登录</div>
            <div class="mc-register-btn mdui-btn mdui-btn-dense mdui-ripple mdui-ripple-white">注册</div>
        </#if>
    </div>
</header>
<#if user??>
    <!--主题-->
    <div class="mdui-dialog" id="dialog-docs-theme">
        <div class="mdui-dialog-title">设置文档主题</div>
        <div class="mdui-dialog-content">

            <p class="mdui-typo-title">主题色</p>
            <div class="mdui-row-xs-1 mdui-row-sm-2 mdui-row-md-3 layout">
                <div class="mdui-col">
                    <label class="mdui-radio mdui-m-b-1">
                        <input type="radio" name="doc-theme-layout" value="light">
                        <i class="mdui-radio-icon"></i>
                        Light
                    </label>
                </div>
                <div class="mdui-col">
                    <label class="mdui-radio mdui-m-b-1">
                        <input type="radio" name="doc-theme-layout" value="dark">
                        <i class="mdui-radio-icon"></i>
                        Dark
                    </label>
                </div>
            </div>

            <p class="mdui-typo-title mdui-text-color-theme">主色</p>
            <form class="mdui-row-xs-1 mdui-row-sm-2 mdui-row-md-3 primary">
                <div class="mdui-col mdui-text-color-amber">
                    <label class="mdui-radio mdui-m-b-1">
                        <input type="radio" name="doc-theme-primary" value="amber">
                        <i class="mdui-radio-icon"></i>
                        Amber
                    </label>
                </div>
                <div class="mdui-col mdui-text-color-blue">
                    <label class="mdui-radio mdui-m-b-1">
                        <input type="radio" name="doc-theme-primary" value="blue">
                        <i class="mdui-radio-icon"></i>
                        Blue
                    </label>
                </div>
                <div class="mdui-col mdui-text-color-blue-grey">
                    <label class="mdui-radio mdui-m-b-1">
                        <input type="radio" name="doc-theme-primary" value="blue-grey">
                        <i class="mdui-radio-icon"></i>
                        Blue Grey
                    </label>
                </div>
                <div class="mdui-col mdui-text-color-brown">
                    <label class="mdui-radio mdui-m-b-1">
                        <input type="radio" name="doc-theme-primary" value="brown">
                        <i class="mdui-radio-icon"></i>
                        Brown
                    </label>
                </div>
                <div class="mdui-col mdui-text-color-cyan">
                    <label class="mdui-radio mdui-m-b-1">
                        <input type="radio" name="doc-theme-primary" value="cyan">
                        <i class="mdui-radio-icon"></i>
                        Cyan
                    </label>
                </div>
                <div class="mdui-col mdui-text-color-deep-orange">
                    <label class="mdui-radio mdui-m-b-1">
                        <input type="radio" name="doc-theme-primary" value="deep-orange">
                        <i class="mdui-radio-icon"></i>
                        Deep Orange
                    </label>
                </div>
                <div class="mdui-col mdui-text-color-deep-purple">
                    <label class="mdui-radio mdui-m-b-1">
                        <input type="radio" name="doc-theme-primary" value="deep-purple">
                        <i class="mdui-radio-icon"></i>
                        Deep Purple
                    </label>
                </div>
                <div class="mdui-col mdui-text-color-green">
                    <label class="mdui-radio mdui-m-b-1">
                        <input type="radio" name="doc-theme-primary" value="green">
                        <i class="mdui-radio-icon"></i>
                        Green
                    </label>
                </div>
                <div class="mdui-col mdui-text-color-grey">
                    <label class="mdui-radio mdui-m-b-1">
                        <input type="radio" name="doc-theme-primary" value="grey">
                        <i class="mdui-radio-icon"></i>
                        Grey
                    </label>
                </div>
                <div class="mdui-col mdui-text-color-indigo">
                    <label class="mdui-radio mdui-m-b-1">
                        <input type="radio" name="doc-theme-primary" value="indigo">
                        <i class="mdui-radio-icon"></i>
                        Indigo
                    </label>
                </div>
                <div class="mdui-col mdui-text-color-light-blue">
                    <label class="mdui-radio mdui-m-b-1">
                        <input type="radio" name="doc-theme-primary" value="light-blue">
                        <i class="mdui-radio-icon"></i>
                        Light Blue
                    </label>
                </div>
                <div class="mdui-col mdui-text-color-light-green">
                    <label class="mdui-radio mdui-m-b-1">
                        <input type="radio" name="doc-theme-primary" value="light-green">
                        <i class="mdui-radio-icon"></i>
                        Light Green
                    </label>
                </div>
                <div class="mdui-col mdui-text-color-lime">
                    <label class="mdui-radio mdui-m-b-1">
                        <input type="radio" name="doc-theme-primary" value="lime">
                        <i class="mdui-radio-icon"></i>
                        Lime
                    </label>
                </div>
                <div class="mdui-col mdui-text-color-orange">
                    <label class="mdui-radio mdui-m-b-1">
                        <input type="radio" name="doc-theme-primary" value="orange">
                        <i class="mdui-radio-icon"></i>
                        Orange
                    </label>
                </div>
                <div class="mdui-col mdui-text-color-pink">
                    <label class="mdui-radio mdui-m-b-1">
                        <input type="radio" name="doc-theme-primary" value="pink">
                        <i class="mdui-radio-icon"></i>
                        Pink
                    </label>
                </div>
                <div class="mdui-col mdui-text-color-purple">
                    <label class="mdui-radio mdui-m-b-1">
                        <input type="radio" name="doc-theme-primary" value="purple">
                        <i class="mdui-radio-icon"></i>
                        Purple
                    </label>
                </div>
                <div class="mdui-col mdui-text-color-red">
                    <label class="mdui-radio mdui-m-b-1">
                        <input type="radio" name="doc-theme-primary" value="red">
                        <i class="mdui-radio-icon"></i>
                        Red
                    </label>
                </div>
                <div class="mdui-col mdui-text-color-teal">
                    <label class="mdui-radio mdui-m-b-1">
                        <input type="radio" name="doc-theme-primary" value="teal">
                        <i class="mdui-radio-icon"></i>
                        Teal
                    </label>
                </div>
                <div class="mdui-col mdui-text-color-yellow">
                    <label class="mdui-radio mdui-m-b-1">
                        <input type="radio" name="doc-theme-primary" value="yellow">
                        <i class="mdui-radio-icon"></i>
                        Yellow
                    </label>
                </div>
            </form>

            <p class="mdui-typo-title mdui-text-color-theme-accent">强调色</p>
            <form class="mdui-row-xs-1 mdui-row-sm-2 mdui-row-md-3 accent">
                <div class="mdui-col mdui-text-color-amber">
                    <label class="mdui-radio mdui-m-b-1">
                        <input type="radio" name="doc-theme-accent" value="amber">
                        <i class="mdui-radio-icon"></i>
                        Amber
                    </label>
                </div>
                <div class="mdui-col mdui-text-color-blue">
                    <label class="mdui-radio mdui-m-b-1">
                        <input type="radio" name="doc-theme-accent" value="blue">
                        <i class="mdui-radio-icon"></i>
                        Blue
                    </label>
                </div>
                <div class="mdui-col mdui-text-color-cyan">
                    <label class="mdui-radio mdui-m-b-1">
                        <input type="radio" name="doc-theme-accent" value="cyan">
                        <i class="mdui-radio-icon"></i>
                        Cyan
                    </label>
                </div>
                <div class="mdui-col mdui-text-color-deep-orange">
                    <label class="mdui-radio mdui-m-b-1">
                        <input type="radio" name="doc-theme-accent" value="deep-orange">
                        <i class="mdui-radio-icon"></i>
                        Deep Orange
                    </label>
                </div>
                <div class="mdui-col mdui-text-color-deep-purple">
                    <label class="mdui-radio mdui-m-b-1">
                        <input type="radio" name="doc-theme-accent" value="deep-purple">
                        <i class="mdui-radio-icon"></i>
                        Deep Purple
                    </label>
                </div>
                <div class="mdui-col mdui-text-color-green">
                    <label class="mdui-radio mdui-m-b-1">
                        <input type="radio" name="doc-theme-accent" value="green">
                        <i class="mdui-radio-icon"></i>
                        Green
                    </label>
                </div>
                <div class="mdui-col mdui-text-color-indigo">
                    <label class="mdui-radio mdui-m-b-1">
                        <input type="radio" name="doc-theme-accent" value="indigo">
                        <i class="mdui-radio-icon"></i>
                        Indigo
                    </label>
                </div>
                <div class="mdui-col mdui-text-color-light-blue">
                    <label class="mdui-radio mdui-m-b-1">
                        <input type="radio" name="doc-theme-accent" value="light-blue">
                        <i class="mdui-radio-icon"></i>
                        Light Blue
                    </label>
                </div>
                <div class="mdui-col mdui-text-color-light-green">
                    <label class="mdui-radio mdui-m-b-1">
                        <input type="radio" name="doc-theme-accent" value="light-green">
                        <i class="mdui-radio-icon"></i>
                        Light Green
                    </label>
                </div>
                <div class="mdui-col mdui-text-color-lime">
                    <label class="mdui-radio mdui-m-b-1">
                        <input type="radio" name="doc-theme-accent" value="lime">
                        <i class="mdui-radio-icon"></i>
                        Lime
                    </label>
                </div>
                <div class="mdui-col mdui-text-color-orange">
                    <label class="mdui-radio mdui-m-b-1">
                        <input type="radio" name="doc-theme-accent" value="orange">
                        <i class="mdui-radio-icon"></i>
                        Orange
                    </label>
                </div>
                <div class="mdui-col mdui-text-color-pink">
                    <label class="mdui-radio mdui-m-b-1">
                        <input type="radio" name="doc-theme-accent" value="pink">
                        <i class="mdui-radio-icon"></i>
                        Pink
                    </label>
                </div>
                <div class="mdui-col mdui-text-color-purple">
                    <label class="mdui-radio mdui-m-b-1">
                        <input type="radio" name="doc-theme-accent" value="purple">
                        <i class="mdui-radio-icon"></i>
                        Purple
                    </label>
                </div>
                <div class="mdui-col mdui-text-color-red">
                    <label class="mdui-radio mdui-m-b-1">
                        <input type="radio" name="doc-theme-accent" value="red">
                        <i class="mdui-radio-icon"></i>
                        Red
                    </label>
                </div>
                <div class="mdui-col mdui-text-color-teal">
                    <label class="mdui-radio mdui-m-b-1">
                        <input type="radio" name="doc-theme-accent" value="teal">
                        <i class="mdui-radio-icon"></i>
                        Teal
                    </label>
                </div>
                <div class="mdui-col mdui-text-color-yellow">
                    <label class="mdui-radio mdui-m-b-1">
                        <input type="radio" name="doc-theme-accent" value="yellow">
                        <i class="mdui-radio-icon"></i>
                        Yellow
                    </label>
                </div>
            </form>

        </div>
        <div class="mdui-divider"></div>
        <div class="mdui-dialog-actions">
            <button class="mdui-btn mdui-ripple mdui-float-left" mdui-dialog-cancel="">恢复默认主题</button>
            <button class="mdui-btn mdui-ripple" mdui-dialog-close="">关闭</button>
            <button class="mdui-btn mdui-ripple" mdui-dialog-confirm="">保存</button>
        </div>
    </div>
</#if>
<script>
    $(function () {
        $('.layout input[name="doc-theme-layout"][value="${(user.themeLayout)!'teal'}"]').prop('checked', true);
        $('.primary input[name="doc-theme-primary"][value="${(user.themePrimary)!'teal'}"]').prop('checked', true);
        $('.accent input[name="doc-theme-accent"][value="${(user.themeAccent)!'teal'}"]').prop('checked', true);
    })
</script>