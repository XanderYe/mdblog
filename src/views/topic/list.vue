<template>
  <mu-container>
    <mu-data-table stripe :columns="columns" @sort-change="" :data="topicList">
      <template slot-scope="scope">
        <td>{{scope.row.name}}</td>
        <td>{{scope.row.description}}</td>
        <td>{{scope.row.creator}}</td>
        <td>{{scope.row.createTime}}</td>
        <td>{{scope.row.updator}}</td>
        <td>{{scope.row.updatorTime}}</td>
        <td>
          <mu-button icon small color="primary">
            <mu-icon value="edit"></mu-icon>
          </mu-button>
          <mu-button icon small color="primary">
            <mu-icon value="delete"></mu-icon>
          </mu-button>
        </td>
      </template>
    </mu-data-table>

    <mu-flex justify-content="end" style="margin-top: 16px; padding-bottom: 16px;" v-if="topicList.length > 0">
      <mu-pagination raised circle :page-size="pageSize" :total="total" :current.sync="page"
                     @change="getTopicPageList"></mu-pagination>
    </mu-flex>
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
            name: "createTime"
          },
          {
            title: "更新者",
            name: "updator"
          },
          {
            title: "更新时间",
            name: "updatorTime"
          },
          {
            title: "操作",
            name: "operate"
          },
        ]
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
    },
    created() {
      this.getTopicPageList();
    },
  }
</script>

<style scoped>

</style>
