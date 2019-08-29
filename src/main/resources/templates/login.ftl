<div class="mc-login mdui-dialog">
    <button class="mdui-btn mdui-btn-icon mdui-text-color-white close"><i class="mdui-icon material-icons">close</i>
    </button>
    <div class="mdui-dialog-title mdui-color-theme">登录</div>
    <form id="loginForm">
        <div class="mdui-textfield mdui-textfield-floating-label mdui-textfield-has-bottom">
            <label class="mdui-textfield-label">用户名</label>
            <input class="mdui-textfield-input" name="username" type="text" required="">
            <div class="mdui-textfield-error">用户名不能为空</div>
        </div>
        <div class="mdui-textfield mdui-textfield-floating-label mdui-textfield-has-bottom">
            <label class="mdui-textfield-label">密码</label>
            <input class="mdui-textfield-input" name="password" type="password" required="">
            <div class="mdui-textfield-error">密码不能为空</div>
        </div>
        <div class="mdui-textfield mdui-textfield-floating-label mdui-textfield-has-bottom">
            <label class="mdui-checkbox">
                <input type="checkbox" id="rememberMe" name="rememberMe" value="true"/>
                <i class="mdui-checkbox-icon"></i>
                记住密码
            </label>
        </div>
        <div class="actions mdui-clearfix">
            <button class="mdui-btn mdui-ripple more-option" type="button" mdui-menu="{target: '#mc-login-menu', position: 'top', covered: true}">
                更多选项
            </button>
            <ul class="mdui-menu" id="mc-login-menu">
                <li class="mdui-menu-item">
                    <a class="mdui-ripple mc-password-reset-trigger">忘记密码</a>
                </li>
                <li class="mdui-menu-item">
                    <a class="mdui-ripple mc-register-trigger">创建新账号</a>
                </li>
            </ul>
            <button type="button" class="mdui-btn mdui-btn-raised mdui-color-theme-accent mdui-float-right login-button">登录</button>
        </div>
    </form>
</div>