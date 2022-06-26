<script lang="ts" setup>
import dayjs from 'dayjs';
import {UserInfo} from '@/typings';
import {computed, onMounted, reactive, ref} from '@vue/runtime-dom';
import Cookies from "js-cookie";
import {useRouter} from 'vue-router';
import {useStore} from 'vuex'
import {Message, Menu as IconMenu, Setting} from '@element-plus/icons-vue'
import {Search} from '@element-plus/icons-vue'
import api from "@/api"
import {ICtrlUserInfo, setUserInfo} from '@/hooks';
import {ElMessage, ElMessageBox} from 'element-plus';
import * as constants from "constants";

const {
  getAllQuestions,
  getQuestionByType,
  getQuestionByClassType,
  getOptionsBySid,
  getAnswerBySid,
  searchQuestion,
  addQuestion,
  addOptions
} = api
const {setuserinfo}: ICtrlUserInfo = setUserInfo();
const tableData = ref([])
const store = useStore()
const info: UserInfo = reactive({id: 0, name: "", user: '', password: "", email: "", phone: ""});
const $router = useRouter();
const searchvalue = ref("")
const circleUrl = 'http://5b0988e595225.cdn.sohucs.com/images/20190122/01ba24e2acde4fe187d4553e332d390f.jpeg'
const getAllQuestionsData = async () => {
  await eraly()
  getAllQuestions({pageNum: 1}).then((data: { data: { data: any } }) => {
    tableData.value = data.data.data
  })
}
const getQuestionByTypeData = async (type: number) => {
  await eraly()
  getQuestionByType({pageNum: 1, type}).then((data: { data: { data: any } }) => {
    tableData.value = data.data.data
  })
}
const getQuestionByClassTypeData = async (classtype: number) => {
  await eraly()
  getQuestionByClassType({pageNum: 1, classtype}).then((data: { data: { data: any } }) => {
    tableData.value = data.data.data
  })
}
// 定义一个答案列表
const answerList = ref([])
// 定义一个正确答案列表
const rightAnswerList = ref([])
// 定义题目对象
const question = ref({} as any)
// 定义正确答案对象
const rightAnswer = ref({} as any)
//显示争取答案状态
const stateCorrectAnswer = ref(false)
const getOptionsBySidData = async (sid: number) => {
  await eraly()
  getOptionsBySid({sid}).then((data: { data: any }) => {
    // tableData.value = data.data.data
    answerList.value = data.data
    console.log(data)
  })
}
// 根据题目ID获取答案
const getAnswerBySidData = async () => {
  await eraly()
  getAnswerBySid({topicId: question.value.id}).then((data: { data: any }) => {
    rightAnswer.value = data.data
    stateCorrectAnswer.value = true
  })
}
// 关闭弹框
const close = () => {
  stateCorrectAnswer.value = false
  dialogFormVisible.value = false
}
const dialogFormVisible = ref(false);
const rowClickHandle = async (row: any) => {
  question.value = row
  await getOptionsBySidData(row.id)
  dialogFormVisible.value = true
}
const searchQuestionData = () => {
  searchQuestion({kw: searchvalue.value}).then((data: { data: any }) => {
    tableData.value = data.data.data
  })
}
const addQuestionStatus = ref(false)
const openAddQuestion = () => {
  addQuestionStatus.value = true
}
const questionForm = ref({
  content: '',
  classtype: "",
  type: "",
  options: [],
} as any)
const addAOptions = () => {
  if(questionForm.value.options.length < 4){
    questionForm.value.options.push({
      options: '',
      isRight: 0
    })
  }else{
    ElMessage.error('最多只能添加四个选项')
  }
}
const delAOptions = (index: number) => {
  questionForm.value.options.splice(index, 1)
}
const confirmToAdd = ()=>{
  const {content, classtype, type, options} = questionForm.value
  let id = -1;
  addQuestion({topic:content,type,classType:classtype}).then((data: { data: any }) => {
    id = data.data
  }).then(()=>{
    options.map((item:{sid: number, options: string,isRight:number})=>{
      item.sid = id
    })
    addOptions({selectionList:options}).then((data: { data: any }) => {
    })
    ElMessage.success('添加成功')
    addQuestionStatus.value = false
  })
}
const setRight=(index:number)=>{
  questionForm.value.options.forEach((item:any,i:number)=>{
    if(i === index){
      item.isRight = 1
    }else{
      item.isRight = 0
    }
  })
}
let nowtime = reactive(dayjs(new Date()))
let timer = 0;
const nowtimestr = ref("")
const eraly = async () => {
  if (Cookies.get("User-Token") == null) {
    setuserinfo({id: 0, name: '', email: '', phone: '', user: '', password: ''})
    ElMessageBox.confirm(
        '您的登陆已经失效，请重新登陆?',
        '错误',
        {
          confirmButtonText: '确认',
          cancelButtonText: '退出',
          type: 'error',
        }
    )
        .then(() => {
          setTimeout(() => {
            $router.push({
              path: "/login"
            })
          })
        })
        .catch(() => {
          setTimeout(() => {
            $router.push({
              path: "/login"
            })
          })
        })
    return false
  } else {
    return true
  }
}
onMounted(async () => {
  if (await eraly()) {
    const {id, name, user, password, email, phone} = computed(() => store.state.userInfo).value
    info.id = id
    info.user = user
    info.name = name
    info.password = password
    info.email = email
    info.phone = phone
    if (timer == 0) {
      timer = window.setInterval(() => {
        nowtime = dayjs(new Date())
        nowtimestr.value = nowtime.year() + '年' + nowtime.month() + "月" + nowtime.day() + "日" + "  " + nowtime.hour() + ":" + ("00" + nowtime.minute()).slice(-2) + ":" + ("00" + nowtime.second()).slice(-2)
      }, 1000)
    }
    await getAllQuestionsData()
  }
})
</script>
<template>
  <div class="h-full">
    <el-container class="layout-container-demo h-full">
      <el-aside style="background-color: rgb(238, 241, 246)" class="w-72">
        <h1 class="block  text-center bg-indigo-200  text-xl h-[60px] leading-10">毕业生面试题库系统</h1>
        <el-scrollbar class="h-auto">
          <el-menu>
            <el-sub-menu index="1">
              <template #title>
                <el-icon>
                  <message/>
                </el-icon>
                题目类型
              </template>
              <el-menu-item index="1-1" @click="getQuestionByTypeData(0)">选择题</el-menu-item>
              <el-menu-item index="1-2" @click="getQuestionByTypeData(1)">填空题</el-menu-item>
              <el-menu-item index="1-3" @click="getQuestionByTypeData(2)">简答题</el-menu-item>
              <el-menu-item index="1-4" @click="getQuestionByTypeData(3)">其他</el-menu-item>
            </el-sub-menu>
            <el-sub-menu index="2">
              <template #title>
                <el-icon>
                  <icon-menu/>
                </el-icon>
                技术栈类型
              </template>
              <el-menu-item index="2-1" @click="getQuestionByClassTypeData(0)">Java</el-menu-item>
              <el-menu-item index="2-2" @click="getQuestionByClassTypeData(1)">JavaScript</el-menu-item>
              <el-menu-item index="2-3" @click="getQuestionByClassTypeData(2)">C</el-menu-item>
              <el-menu-item index="2-4" @click="getQuestionByClassTypeData(3)">其他</el-menu-item>
            </el-sub-menu>
          </el-menu>
          <el-dialog v-model="addQuestionStatus" title="新增题目">
            <div>
              <span class="mb-4 font-bold block">题目内容: </span>
              <el-input type="textarea" v-model="questionForm.content" placeholder="请输入题目内容"
                        style="width: 100%"></el-input>
              <span class="my-4 font-bold block">答案内容: <el-button @click="addAOptions">新增一个答案</el-button></span>
              <div class="h-auto">
                <div v-for="(v,i) in questionForm.options" class="flex items-center" :key="i">
                  <span class="font-bold text-2xl mx-4">{{i+1+". "}}</span>
                  <el-input type="textarea" v-model="v.options" placeholder="请输入内容"
                            style="width: 100%;margin-bottom: 10px"></el-input>
                  <el-button class="ml-1.5 bg-red-500 text-white hover:bg-white hover:text-red-500" @click="delAOptions(i)">删除选项</el-button>
                  <el-button @click="setRight(i)">{{v.isRight==1?"已是正确选项":'设置为正确选项'}}</el-button>
                </div>
              </div>
              <span class="my-4 font-bold block">题目类型: </span>
              <el-select v-model="questionForm.type" class="m-2" placeholder="题目类型" size="small">
                <el-option label="选择题" :value="0"/>
                <el-option label="填空题" :value="1"/>
                <el-option label="简答题" :value="2"/>
                <el-option label="其他" :value="3"/>
                <el-option label="暂未开放其他类型" value="-1" disabled/>
              </el-select>
              <span class="my-4 font-bold block">题目技术栈类型: </span>
              <el-select v-model="questionForm.classtype" class="m-2" placeholder="技术栈" size="small">
                <el-option label="Java" :value="0"/>
                <el-option label="JavaScript" :value="1"/>
                <el-option label="C" :value="2"/>
                <el-option label="其他" :value="3"/>
                <el-option label="暂未开放其他技术栈" value="-1" disabled/>
              </el-select>
            </div>
            <template #footer>
                <span class="dialog-footer">
                  <el-button @click="addQuestionStatus = false">取消</el-button>
                  <el-button @click="confirmToAdd">确定</el-button>
                </span>
            </template>
          </el-dialog>
          <el-dialog v-model="dialogFormVisible" title="题目详情" @close="close">
            <div class="text-2xl mb-3"><span class="font-bold">题目</span>：{{ question.topic }}</div>
            <el-checkbox-group v-model="rightAnswerList">
              <el-checkbox :label="v.options" v-for="v in answerList"/>
            </el-checkbox-group>
            <el-button class="my-10" @click="getAnswerBySidData">获取正确答案</el-button>
            <div v-if="stateCorrectAnswer">正确答案：{{ rightAnswer.options }}</div>
            <template #footer>
                <span class="dialog-footer">
                  <el-button @click="close">取消</el-button>
                  <el-button @click="close">确定</el-button>
                </span>
            </template>
          </el-dialog>
        </el-scrollbar>
      </el-aside>

      <el-container>
        <el-header style="text-align: right; font-size: 12px" class="text- relative">
          <div class="searchbar">
            <el-input
                placeholder="请输入你感兴趣的面试题类别或者关键字"
                class="input-with-select"
                v-model="searchvalue"
            >
              <template #append>
                <el-button :icon="Search"></el-button>
              </template>
            </el-input>
            <el-button @click="searchQuestionData">搜索题目</el-button>
            <el-button @click="getAllQuestionsData">刷新数据</el-button>
            <el-button @click="openAddQuestion">新增题目</el-button>
          </div>
          <div class="toolbar">
            <span>当前时间为：{{ nowtimestr }}</span>
            <span>欢迎您： {{ info.name }}</span>
            <el-avatar :size="30" :src="circleUrl"></el-avatar>
            <el-dropdown>
              <el-icon style="margin-right: 8px; margin-top: 1px">
                <setting/>
              </el-icon>
              <template #dropdown>
                <el-dropdown-menu>
                  <el-dropdown-item>切换账号</el-dropdown-item>
                  <el-dropdown-item>注销</el-dropdown-item>
                </el-dropdown-menu>
              </template>
            </el-dropdown>
          </div>
        </el-header>

        <el-main>
          <el-scrollbar>
            <el-table :data="tableData" @row-click="rowClickHandle">
              <el-table-column prop="id" label="题目ID"></el-table-column>
              <el-table-column prop="topic" label="题目内容"></el-table-column>
              <el-table-column prop="type" label="题目类型">
                <template #default="scope">
                  <el-tag type="success" disable-transitions v-if="scope.row.type==0">选择题</el-tag>
                  <el-tag type="info" disable-transitions v-else-if="scope.row.type==1">填空题</el-tag>
                  <el-tag type="warning" disable-transitions v-else-if="scope.row.type==2">简答题</el-tag>
                  <el-tag type="danger" disable-transitions v-else>其他</el-tag>
                </template>
              </el-table-column>
              <el-table-column prop="classType" label="技术栈类型">
                <template #default="scope">
                  <el-tag type="success" disable-transitions v-if="scope.row.classType==0">Java</el-tag>
                  <el-tag disable-transitions v-else-if="scope.row.classType==1">JavaScript</el-tag>
                  <el-tag type="warning" disable-transitions v-else-if="scope.row.classType==2">C</el-tag>
                  <el-tag type="danger" disable-transitions v-else>其他</el-tag>
                </template>
              </el-table-column>
            </el-table>
          </el-scrollbar>
        </el-main>
      </el-container>
    </el-container>
  </div>
</template>

<style lang="less">
header {
  height: 48px;
  position: relative;
  font-weight: bold;

  > a {
    position: absolute;

    &:nth-child(1) {
      right: 80px;
    }

    &:nth-child(2) {
      right: 40px;
    }
  }
}

.layout-container-demo {
  .el-header {
    background-color: #b3c0d1;
    color: var(--el-text-color-primary);
  }

  .el-aside {
    color: var(--el-text-color-primary);
    background: #fff !important;
    border-right: solid 1px #e6e6e6;
    box-sizing: border-box;
  }

  .el-menu {
    border-right: none;
  }

  .el-main {
    padding: 0;
  }

  .toolbar {
    position: absolute;
    display: inline-flex;
    align-items: center;
    top: 50%;
    right: 20px;
    transform: translateY(-50%);

    span {
      margin: 0 1rem;
    }
  }

  .searchbar {
    position: absolute;
    display: inline-flex;
    align-items: center;
    top: 50%;
    left: 20px;
    transform: translateY(-50%);

    .el-input {
      width: 30rem;
    }
  }
}
</style>