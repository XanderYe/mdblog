<template>
  <footer id="footer">
    <div class="footer-copyright">
      Copyright © 2018 - 2020 <a href="http://xanderye.cn/">xanderye.cn</a> All rights reserved.
    </div>
    <div class="footer-info">
      <a href="http://www.beian.miit.gov.cn/" target="_blank">浙ICP备xxx号</a>
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
    height: 64px;
    background: white;
    color:white;
    text-align: center;
    padding-top: 15px;
    a {
      position: relative;
      display: inline-block;
      overflow: hidden;
      text-decoration: none;
      vertical-align: top;
      outline: 0;
      color:white;
    }

  }

  a:before {
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

  a:focus:before, a:hover:before {
    -webkit-transform: scaleX(1);
    transform: scaleX(1);
  }
</style>
