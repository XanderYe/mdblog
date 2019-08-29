<div class="mc-register mdui-dialog mdui-dialog-open">
    <button class="mdui-btn mdui-btn-icon mdui-text-color-white close"><i class="mdui-icon material-icons">close</i>
    </button>
    <div class="mdui-dialog-title mdui-color-theme mdui-text-color-white">创建新账号</div>
    <form id="registerForm">
        <div class="mdui-textfield mdui-textfield-floating-label mdui-textfield-has-bottom">
            <label class="mdui-textfield-label">用户名</label>
            <input class="mdui-textfield-input" name="username" type="text" required="">
            <div class="mdui-textfield-error">用户名不能为空</div>
        </div>
        <div class="mdui-textfield mdui-textfield-floating-label mdui-textfield-has-bottom">
            <label class="mdui-textfield-label">昵称</label>
            <input class="mdui-textfield-input" name="nickname" type="text" required="">
            <div class="mdui-textfield-error">昵称不能为空</div>
        </div>
        <div class="mdui-textfield mdui-textfield-floating-label mdui-textfield-has-bottom">
            <label class="mdui-textfield-label">密码</label>
            <input class="mdui-textfield-input" name="password" type="password" required="">
            <div class="mdui-textfield-error">密码不能为空</div>
        </div>
        <div class="mdui-textfield mdui-textfield-floating-label mdui-textfield-has-bottom">
            <label class="mdui-textfield-label">确认密码</label>
            <input class="mdui-textfield-input" name="password2" type="password" required="">
            <div class="mdui-textfield-error">密码不能为空</div>
        </div>
        <div class="mdui-textfield mdui-textfield-floating-label mdui-textfield-has-bottom">
            <label class="mdui-textfield-label">验证码</label>
            <input class="mdui-textfield-input" name="verCode" type="text" required="">
            <img src="" class="captcha"/>
            <div class="mdui-textfield-error">验证码不能为空</div>
        </div>
        <div class="actions mdui-clearfix">
            <button type="button" class="mdui-btn mdui-btn-raised mdui-color-theme-accent mdui-float-right register-button">注册</button>
        </div>
    </form>
</div>