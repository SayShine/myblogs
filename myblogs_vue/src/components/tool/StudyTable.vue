<template>
  <div>
    <div class="header">

    </div>

    <div class="container">
      <!--操作栏-->
      <el-input
        v-model="input"
        placeholder="搜索"
        @keyup.enter.native="searchByKey(input)"
        style="width: 280px; margin-bottom: 10px;"
      />

      <div class="handle-box">
        <el-button
          icon="el-icon-plus"
          class="handle-box mr10"
          @click="addNewTable">
          新增</el-button>

        <el-button
          type="primary"
          icon="el-icon-delete"
          class="handle-box mr10"
          @click="delAllSelection">
          批量删除</el-button>

      </div>
      <el-table
        ref="multipleTable"
        highlight-current-row
        :data="StudyList.slice((currentPage-1)*pagesize,currentPage*pagesize)"
        stripe
        border
        @current-change="handleCurrentChange"
        @selection-change="handleSelectionChange">
        <el-table-column type="selection" width="55"></el-table-column>
        <el-table-column v-if="isShow" property="id" label="博客id"
                         width="228">
        </el-table-column>
        <el-table-column property="url" label="网址"
                         width="550">
        </el-table-column>

        <el-table-column property="comment" label="备注"
                         width="500">
        </el-table-column>



        <el-table-column label="操作" width="180" align="center">
          <template slot-scope="scope">

            <el-button
              type="text"
              icon="el-icon-edit"
              @click="handleEdit(scope.$index, scope.row)"
            >编辑</el-button>
            <el-button
              type="text"
              icon="el-icon-delete"
              class="red"
              @click="handleDelete(scope.$index, scope.row)"
            >删除</el-button>
          </template>
        </el-table-column>
      </el-table>

      <div class="paginationClass" style="margin: 40px 20px">
        <el-pagination
          background
          @size-change="handleSizeChange"
          @current-change="handlePageChange"
          :current-page="currentPage"
          :page-sizes="[5,10,20,40]"
          :page-size="pagesize"
          layout="total,sizes,prev,pager,next,jumper"
          :total="StudyList.length"
        >
        </el-pagination>
      </div>

    </div>

    <!-- 编辑弹出框 -->
    <el-dialog title="编辑" :visible.sync="editVisible" width="30%">
      <el-form :rules="formRules" ref="form" :model="form" label-width="70px">
        <el-form-item label="网址" >
          <el-input v-model="form.url"></el-input>
        </el-form-item>
        <el-form-item label="备注" prop="readnum">
          <el-input v-model="form.comment"></el-input>
        </el-form-item>
      </el-form>
      <span slot="footer" class="dialog-footer">
                <el-button @click="editVisible = false">取 消</el-button>
                <el-button type="primary" @click="saveEdit('form')">保存</el-button>
            </span>
    </el-dialog>


  </div>

</template>

<script>
  import {
    Table,
    TableColumn,
    Pagination,
    Button,
    MessageBox,
    Notification,
    Dialog,
    Form,
    FormItem,
    Input,
  } from 'element-ui'
  import ToolApi from '../../api/apiList/tool'
  import qs from 'qs'
  import validate from "../../assets/utils/js/validate";

  export default {
    name: "StudyTable",
    components: {
      [Table.name]:Table,
      [TableColumn.name]:TableColumn,
      [Pagination.name]:Pagination,
      [Button.name]:Button,
      [MessageBox.name]:MessageBox,
      [Notification.name]:Notification,
      [Dialog.name]:Dialog,
      [Form.name]:Form,
      [FormItem.name]:FormItem,
      [Input.name]:Input,
    },
    data() {
      return {
        currentPage:1,   //默认页码为1
        pagesize:10,  //默认一页显示10条
        //
        StudyList: [],
        //多选
        multipleSelection: [],
        form: {
          readnum:'',
          thumbnum:''
        },
        formRules: {
          // readnum: [
          //   //trigger: 'blur' 表示失去焦点时候进行表单验证
          //   {required: true, trigger: 'blur', validator: validate.isInteger}
          // ],
          // thumbnum: [
          //   //trigger: 'blur' 表示失去焦点时候进行表单验证
          //   {required: true, trigger: 'blur', validator: validate.isInteger}
          // ],
        },
        //删除列表
        delList: [],
        idx: -1,
        editVisible: false,
        isShow:false,
        isUpdate:true, //默认是更新
        input: '',
      }
    },
    created() {
      this.getStudyList();
    },
    methods: {
      handleSizeChange: function (size) {   //一页显示多少条
        this.pagesize = size;
      },
      handlePageChange: function (currentPage) {  //页码更改方法
        this.currentPage = currentPage;
      },
      //选取当前行
      handleCurrentChange(val) {
        this.currentRow = val;
      },
      //多选
      handleSelectionChange(val) {
        this.multipleSelection = val;
      },
      handleTimeSortChange(){
        alert(111)
        console.log(this.StudyList[1])
      },
      // 编辑操作
      handleEdit(index, row) {
        //设置为更新状态
        this.isUpdate = true;

        this.idx = index;
        this.form = row;
        this.editVisible = true;
      },
      // 保存编辑  更新/新增
      saveEdit(formName) {
        let param = this.form;
        //表单校验
        this.$refs[formName].validate((valid)=>{
          if (valid) {
            this.editVisible = false;
            if(this.isUpdate === true){
              //更新
              ToolApi.updateStudyList(param).then(res =>{
                if(res.data.code == 0){
                  Notification({
                    type: 'success',
                    message: `修改第 ${this.idx + 1} 行成功`,
                    duration: 2500
                  });
                  this.$set(this.StudyList, this.idx, this.form);
                }else{
                  Notification({
                    type: 'error',
                    message: `修改第 ${this.idx + 1} 行失败`,
                    duration: 2500
                  });
                }
              });
            }else{
              //新增
              ToolApi.insertStudyList(param).then(res =>{
                if(res.data.code == 0){
                  Notification({
                    type: 'success',
                    message: `新增数据成功`,
                    duration: 2500
                  });
                  this.getStudyList();
                }else{
                  Notification({
                    type: 'error',
                    message: `新增数据失败`,
                    duration: 2500
                  });
                }
              });
            }
          }
        })
      },
      handleDelete(index, row) {
        // 二次确认删除
        MessageBox.confirm('确定要删除吗？', '提示', {
          type: 'warning'
        }).then(() => {
          Notification({
            type: 'warning',
            message: `无法删除`,
            duration: 2500
          });
        })
          .catch(() => {});
      },
      getStudyList(){
        ToolApi.getStudyList().then(res => {
          if(res.data.code === "0"){
            this.StudyList = res.data.body;
          }
        });
      },
      searchByKey(param){
        ToolApi.searchStudyList(param).then(res => {
          if(res.data.code === "0"){
            this.StudyList = res.data.body;
          }
        })
      },
      delAllSelection(){
        // 二次确认删除
        MessageBox.confirm('确定要删除吗？', '提示', {
          type: 'warning'
        }).then(() => {
          // const length =this.multipleSelection.length;
          // let ids = [];
          // for (let i = 0; i < length; i++) {
          //   ids.push([i].id)
          // }
          // let param={
          //   "idsString" : ids
          // }
          var ids = [];
          this.multipleSelection.forEach(function (item) {
            ids.push(item.id)
          })
          ToolApi.deleteStudyList(ids).then(res =>{
            if(res.data.code == 0){
              Notification({
                type: 'success',
                message: `批量删除成功`,
                duration: 2500
              });
              this.getStudyList()
              this.multipleSelection = [];
            }else{
              Notification({
                type: 'error',
                message: `批量删除失败`,
                duration: 2500
              });
            }
          })
        })
          .catch(() => {});
      },
      addNewTable(){
        //设置为新增数据状态
        if(this.isUpdate === true){
          this.form = {};
          this.isUpdate = false;
        }

        this.editVisible = true;

      }
    }
  }
</script>

<style>
  .el-table th {
    background-color: #f5f7fa !important;
  }

  .handle-box{
    margin-bottom: 10px;
  }

  .mr10{
    margin-right: 10px;
  }

</style>
