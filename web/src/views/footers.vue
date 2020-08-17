<template>
  <footer id="footer">
    <div class="footer-typo">
      <div class="footer-copyright">
        <p>Copyright © 2018 - 2020 <a href="http://xanderye.cn/">xanderye.cn</a> All rights reserved.</p>
        <p><a href="http://www.beian.miit.gov.cn/" target="_blank">浙ICP备xxx号</a></p></div>
    </div>
  </footer>
</template>

<script>
  export default {
    name: "footers"
  }
</script>

<style lang="less">
  #footer {
    background: white;
    color: white;
    text-align: center;
    position: relative;

    .footer-copyright {
      width: 100%;
      height: 64px;
      position: absolute;
      top: 50%;
      margin-top: -32px;
      line-height: 14px;
    }

    a {
      position: relative;
      display: inline-block;
      overflow: hidden;
      text-decoration: none;
      vertical-align: top;
      outline: 0;
      color: white;

      &:before {
        position: absolute;
        top: auto;
        bottom: 1px;
        left: 0;
        width: 100%;
        height: 1px;
        content: ' ';
        background-color: white;
        -webkit-transition: all .2s;
        transition: all .2s;
        -webkit-transform: scaleX(0);
        transform: scaleX(0);
        -webkit-backface-visibility: hidden;
        backface-visibility: hidden
      }

      &:focus:before, &:hover:before {
        -webkit-transform: scaleX(1);
        transform: scaleX(1);
      }
    }
  }
</style>
