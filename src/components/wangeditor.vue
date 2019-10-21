<template>
  <div ref="editor" style="text-align:left" v-highlight></div>
</template>

<script>
  import Editor from 'wangeditor';
  export default {
    name: "wangeditor",
    data() {
      return {
        editor: null,
      }
    },
    props: {
      content: {
        type: String,
        default: ""
      }
    },
    watch: {
      content: {
        handler: function (newValue, oldValue) {
          this.editor.txt.html(newValue);
        }
      }
    },
    methods: {
      wangEditor(){
        let _this = this;
        let editor = new Editor(this.$refs.editor);
        editor.customConfig.uploadFileName = 'file';
        editor.customConfig.zIndex = '0';
        editor.customConfig.uploadImgServer = ajaxUrl + '/article/upload';
        editor.customConfig.onchange = (html) => {
          _this.$emit('highlight');
          _this.$emit('input', html);
        };
        editor.create();
        this.editor = editor;
      },
    },
    mounted() {
      this.wangEditor();
    }
  }
</script>

<style lang="less">
  .w-e-toolbar {
    background-color: rgb(255, 255, 255) !important;
    border: none !important;
    display: block;
    height: 31px;
  }

  .w-e-menu {
    float: left;
  }

  .w-e-text-container {
    border: none !important;
  }
</style>
