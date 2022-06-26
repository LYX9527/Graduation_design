<script lang="ts" setup>
import {onMounted, reactive, ref} from '@vue/runtime-dom';
import Cookies from "js-cookie";
import {useRouter} from 'vue-router';
import {ElMessage} from 'element-plus'
import {ICtrlUserInfo, setUserInfo} from "@/hooks/index";
import {UserInfo} from '@/typings';
import api from '@/api'

const {getCode, login} = api
const $router = useRouter()
const imgUrl = ref("");
const loginForm = reactive({
  user: '',
  password: '',
  code: '',
  uuid: ""
})
const {setuserinfo}: ICtrlUserInfo = setUserInfo();
const getCheckCode = () => {
  getCode().then((data: { data: { image: string; uuid: any; }; }) => {
    imgUrl.value = data.data.image
    loginForm.uuid = data.data.uuid
  })
}
onMounted(() => {
  getCheckCode()
})
const ck = async () => {
  if (loginForm.user != "" && loginForm.password != "" && loginForm.code != "") {
    await login(loginForm).then((data: { code: number; data: { token: string, info: UserInfo } }): void => {
      if (data.code == 200) {
        ElMessage.success("登陆成功，正在跳转......")
        Cookies.set("User-Token", data.data.token, {
          expires: new Date(new Date().getTime() + 15 * 60 * 1000)
        })
        setuserinfo(data.data.info);
        setTimeout(() => {
          $router.push({
            path: '/about'
          })
        }, 1000);
      } else {
        getCheckCode()
        loginForm.user = ''
        loginForm.password = ''
        loginForm.code = ''
      }
    });
    getCheckCode()
  } else {
    ElMessage.error("请输入账号密码与验证码再继续！！")
  }
}
const wn = () => {
  ElMessage.warning('暂不支持密码找回功能！')
}
</script>

<template>
  <div
      class="h-2/3 overflow-auto flex items-center justify-center py-12 px-4 sm:px-6 lg:px-8 mt-50 bg-slate-100 w-96 rounded-3xl absolute right-0 left-0 top-0 bottom-0 m-auto mbox"
  >
    <div class="max-w-md w-full space-y-8 py-12">
      <h2 class="mt-6 text-center text-3xl font-extrabold text-gray-900 h-20">毕业生面试题库系统</h2>
      <div class="h-36">
        <img
            class="mx-auto h-12 w-auto"
            src="https://tailwindui.com/img/logos/workflow-mark-indigo-600.svg"
            alt="Workflow"
        />
        <h2 class="mt-6 text-center text-2xl font-extrabold text-gray-900">请登陆您的账户</h2>
      </div>
      <div class="mt-8 space-y-6">
        <input type="hidden" name="remember" value="true"/>
        <div class="rounded-md shadow-sm -space-y-px">
          <div>
            <label for="user" class="sr-only">用户名</label>
            <input
                v-model="loginForm.user"
                id="user"
                name="user"
                type="text"
                autocomplete="email"
                required
                class="appearance-none rounded-none relative block w-full px-3 py-2 border border-gray-300 placeholder-gray-500 text-gray-900 rounded-t-md focus:outline-none focus:ring-indigo-500 focus:border-indigo-500 focus:z-10 sm:text-sm"
                placeholder="用户名"
            />
          </div>
          <div>
            <label for="password" class="sr-only">密码</label>
            <input
                v-model="loginForm.password"
                id="password"
                name="password"
                type="password"
                autocomplete="current-password"
                required
                class="appearance-none rounded-none relative block w-full px-3 py-2 border border-gray-300 placeholder-gray-500 text-gray-900 focus:outline-none focus:ring-indigo-500 focus:border-indigo-500 focus:z-10 sm:text-sm"
                placeholder="密码"
            />
          </div>
          <div>
            <label for="password" class="sr-only">验证码</label>
            <input
                @keyup.enter="ck"
                v-model="loginForm.code"
                type="text"
                autocomplete="current-password"
                required
                class="appearance-none rounded-none relative block w-full px-3 py-2 border border-gray-300 placeholder-gray-500 text-gray-900 rounded-b-md focus:outline-none focus:ring-indigo-500 focus:border-indigo-500 focus:z-10 sm:text-sm"
                placeholder="验证码(不区分大小写)"
            />
          </div>
        </div>
        <div class="flex items-center justify-between mt-0">
          <img :src="imgUrl"/>
          <p
              @click="getCheckCode"
              class="text-1xl cursor-pointer underline text-indigo-300"
          >看不清,换一张</p>
        </div>
        <div class="flex items-center justify-between">
          <div class="text-sm">
            <a
                href="/register"
                class="font-medium text-indigo-600 hover:text-indigo-500"
            >还没有账号,去注册?</a>
          </div>

          <div class="text-sm">
            <a
                @click="wn"
                class="font-medium text-indigo-600 hover:text-indigo-500"
            >忘记密码?</a>
          </div>
        </div>

        <div>
          <button
              @click="ck"
              class="group relative w-full flex justify-center py-2 px-4 border border-transparent text-sm font-medium rounded-md text-white bg-indigo-600 hover:bg-indigo-700 focus:outline-none focus:ring-2 focus:ring-offset-2 focus:ring-indigo-500"
          >
                        <span class="absolute left-0 inset-y-0 flex items-center pl-3">
                            <svg
                                class="h-5 w-5 text-indigo-500 group-hover:text-indigo-400"
                                xmlns="http://www.w3.org/2000/svg"
                                viewBox="0 0 20 20"
                                fill="currentColor"
                                aria-hidden="true"
                            >
                                <path
                                    fill-rule="evenodd"
                                    d="M5 9V7a5 5 0 0110 0v2a2 2 0 012 2v5a2 2 0 01-2 2H5a2 2 0 01-2-2v-5a2 2 0 012-2zm8-2v2H7V7a3 3 0 016 0z"
                                    clip-rule="evenodd"
                                />
                            </svg>
                        </span>
            登陆
          </button>
        </div>
      </div>
    </div>
  </div>
</template>


<style lang="less" scoped>
</style>