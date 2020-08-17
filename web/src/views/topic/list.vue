<template>
  <mu-container>
    <mu-button color="secondary" @click="openAddModal">添加</mu-button>
    <mu-data-table stripe :columns="columns" @sort-change="" :data="topicList" style="margin-top: 16px;">
      <template slot-scope="scope">
        <td>{{formatData(scope.row.name)}}</td>
        <td>{{formatData(scope.row.description)}}</td>
        <td>{{formatData(scope.row.creator)}}</td>
        <td>{{formatData(scope.row.createTime)}}</td>
        <td>{{formatData(scope.row.updator)}}</td>
        <td>{{formatData(scope.row.updatorTime)}}</td>
        <td>
          <mu-button icon small color="primary" @click="openEditModal(scope.row)">
            <mu-icon value="edit"></mu-icon>
          </mu-button>
          <mu-button icon small color="red" @click="openDeleteModal(scope.row.id)">
            <mu-icon value="delete"></mu-icon>
          </mu-button>
        </td>
      </template>
    </mu-data-table>

    <mu-flex justify-content="end" style="margin-top: 16px; padding-bottom: 16px;" v-if="topicList.length > 0">
      <mu-pagination raised circle :page-size="pageSize" :total="total" :current.sync="page"
                     @change="getTopicPageList"></mu-pagination>
    </mu-flex>

    <mu-dialog width="600" :title="dialogTitle" transition="scale" :fullscreen="!$store.state.app.isDesktop"
               :overlay-close="false" :open.sync="addModal">
      <mu-form :model="topic" ref="topicForm"  label-position="top" label-width="100">
        <mu-form-item prop="name" label="名称" :rules="nameRules">
          <mu-text-field v-model="topic.name" placeholder="请输入名称"></mu-text-field>
        </mu-form-item>

        <mu-form-item prop="description" label="描述" :rules="descriptionRules">
          <mu-text-field v-model="topic.description" placeholder="请输入描述"></mu-text-field>
        </mu-form-item>
      </mu-form>
      <mu-button slot="actions" flat color="primary" @click="addModal = false">取消</mu-button>
      <mu-button slot="actions" flat color="primary" @click="saveTopic">保存</mu-button>
    </mu-dialog>

    <mu-dialog title="提示" width="400" max-width="80%" :esc-press-close="false" :overlay-close="false"
               :open.sync="deleteModal">
      是否确认删除所选主题？
      <mu-button slot="actions" flat color="primary" @click="deleteModal = false">取消</mu-button>
      <mu-button slot="actions" flat color="primary" @click="deleteTopic">确认</mu-button>
    </mu-dialog>

    <mu-snackbar position="top" :open.sync="snackbar.open">
      {{snackbar.message}}
      <mu-button flat slot="action" color="secondary" @click="snackbar.open = false">关闭</mu-button>
    </mu-snackbar>
  </mu-container>
</template>

<script>
  export default {
    name: "topic",
    data() {
      return {
        topicList: [],
        page: 1,
        total: 1,
        pageSize: 10,
        topic: {
          id: 0,
          name: "",
          description: ""
        },
        columns: [
          {
            title: "名称",
            name: "name"
          },
          {
            title: "描述",
            name: "description"
          },
          {
            title: "创建者",
            name: "creator"
          },
          {
            title: "创建时间",
            name: "createTime",
            width: 180,
          },
          {
            title: "更新者",
            name: "updator"
          },
          {
            title: "更新时间",
            name: "updatorTime",
            width: 180,
          },
          {
            title: "操作",
            name: "operate"
          },
        ],
        nameRules: [
          {validate: (val) => !!val, message: '必须填写名称'},
        ],
        descriptionRules: [
          {validate: (val) => !!val, message: '必须填写描述'},
        ],
        // 添加模态框
        addModal: false,
        dialogTitle: "添加主题",
        // 删除确认框
        deleteModal: false,
        snackbar: {
          message: "",
          open: false,
        },
      }
    },
    methods: {
      // 获取主题
      getTopicPageList() {
        this.$requests.get("/topic/getTopicPageList", {
          page: this.page,
          rows: this.pageSize
        }).then((res) => {
          if (res.data.code === 0) {
            this.topicList = res.data.data.list;
            this.total = res.data.data.total;
          }
        })
      },
      // 打开添加模态框
      openAddModal() {
        this.topic = {
          id: 0,
          name: "",
          description: ""
        };
        this.addModal = true;
        this.dialogTitle = "添加主题";
      },
      // 打开编辑模态框
      openEditModal(topic) {
        this.topic = topic;
        this.addModal = true;
        this.dialogTitle = "编辑主题";
      },
      // 保存主题
      saveTopic() {
        this.$refs.topicForm.validate().then((validate) => {
          if (validate) {
            let form = new FormData;
            if (this.topic.id) {
              form.append("id", this.topic.id);
            }
            form.append("name", this.topic.name);
            form.append("description", this.topic.description);
            this.$requests.post("/topic/save", form).then((res) => {
              if (res.data.code === 0) {
                this.addModal = false;
                this.openSnackbar("保存成功");
                this.getTopicPageList();
              }
            })
          }
        })
      },
      openDeleteModal(id) {
        this.topic.id = id;
        this.deleteModal = true;
      },
      deleteTopic() {
        let form = new FormData;
        form.append("id", this.topic.id);
        this.$requests.post("/topic/delete", form).then((res) => {
          if (res.data.code === 0) {
            this.deleteModal = false;
            this.openSnackbar("删除成功");
            this.getTopicPageList();
          }
        })
      },
      // 打开通知
      openSnackbar(msg) {
        this.snackbar.message = msg;
        this.snackbar.open = true;
        setTimeout(() => {
          if (this.snackbar.open) {
            this.snackbar.open = false;
          }
        }, 3000)
      },
      formatData(data) {
        return data ? data : "无";
      }
    },
    created() {
      this.getTopicPageList();
    },
  }
</script>

<style scoped>

</style>
