<script lang="ts" setup>
import {onMounted, reactive, ref} from '@vue/runtime-dom';

import api from '@/api'
const {getCode, register} = api
import {ElMessage} from 'element-plus'
import {useRouter} from 'vue-router';

const $router = useRouter()
const imgUrl = ref("")
onMounted(() => {
  getCheckCode()
})
const getCheckCode = () => {
  getCode().then((data: { data: { image: string, uuid: string } }) => {
    imgUrl.value = data.data.image
    form.uuid = data.data.uuid
  })
}
const form = reactive({
  name: '',
  user: '',
  password: '',
  password2: '',
  code: '',
  email: '',
  phone: '',
  uuid: '',
});
const rs = () => {
  if (form.user != "" && form.password != "" && form.code != "" && form.email != "" && form.phone != "" && form.name != "") {
    if (form.password == form.password2) {
      const {name, user, password, code, email, phone} = form
      register({name, user, password, code, email, phone}).then((data: { code: number; }) => {
        if (data.code == 200) {
          ElMessage.success("注册成功，正在跳转到登陆页......")
          setTimeout(() => {
            $router.push({
              path: '/login'
            })
          }, 1000);
        } else {
          ElMessage.error("注册失败请稍后重试！")
          getCheckCode()
        }

      })
    } else {
      ElMessage.error("两次输入密码不一致！")
      getCheckCode()
    }
  } else {
    ElMessage.error("请输入账号密码与验证码再继续！！")
    getCheckCode()
  }
  form.name = "";
  form.user = "";
  form.password = "";
  form.password2 = ""
  form.code = "";
  form.email = "";
  form.phone = "";
}
</script>

<template>
  <div
      class="h-3/4 overflow-auto flex items-center justify-center py-12 px-4 sm:px-6 lg:px-8 mt-50 bg-slate-100 w-96 rounded-md absolute right-0 left-0 top-0 bottom-0 m-auto mbox"
  >
    <div class="max-w-md w-full space-y-8 py-12">
      <h2 class="mt-6 text-center text-3xl font-extrabold text-gray-900 h-20">毕业生面试题库系统</h2>
      <div class="h-36">
        <img
            class="mx-auto h-12 w-auto"
            src="https://tailwindui.com/img/logos/workflow-mark-indigo-600.svg"
            alt="Workflow"
        />
        <h2 class="mt-6 text-center text-2xl font-extrabold text-gray-900">开始注册您的账户</h2>
      </div>
      <div class="mt-8 space-y-6">
        <input type="hidden" name="remember" value="true"/>
        <div class="rounded-md shadow-sm -space-y-px">
          <div>
            <label class="sr-only">用户名</label>
            <input
                v-model="form.name"
                type="text"
                required
                class="appearance-none rounded-none relative block w-full px-3 py-2 border border-gray-300 placeholder-gray-500 text-gray-900 rounded-t-md focus:outline-none focus:ring-indigo-500 focus:border-indigo-500 focus:z-10 sm:text-sm"
                placeholder="用户名"
            />
          </div>
          <div>
            <label class="sr-only">账号</label>
            <input
                v-model="form.user"
                type="text"
                autocomplete="current-password"
                required
                class="appearance-none rounded-none relative block w-full px-3 py-2 border border-gray-300 placeholder-gray-500 text-gray-900 focus:outline-none focus:ring-indigo-500 focus:border-indigo-500 focus:z-10 sm:text-sm"
                placeholder="账号"
            />
          </div>
          <div>
            <label class="sr-only">密码</label>
            <input
                v-model="form.password"
                type="password"
                autocomplete="current-password"
                required
                class="appearance-none rounded-none relative block w-full px-3 py-2 border border-gray-300 placeholder-gray-500 text-gray-900 focus:outline-none focus:ring-indigo-500 focus:border-indigo-500 focus:z-10 sm:text-sm"
                placeholder="密码"
            />
          </div>
          <div>
            <label class="sr-only">确认密码</label>
            <input
                v-model="form.password2"
                type="password"
                autocomplete="current-password"
                required
                class="appearance-none rounded-none relative block w-full px-3 py-2 border border-gray-300 placeholder-gray-500 text-gray-900 focus:outline-none focus:ring-indigo-500 focus:border-indigo-500 focus:z-10 sm:text-sm"
                placeholder="确认密码"
            />
          </div>
          <div>
            <div>
              <label class="sr-only">邮箱</label>
              <input
                  v-model="form.email"
                  type="email"
                  autocomplete="email"
                  required
                  class="appearance-none rounded-none relative block w-full px-3 py-2 border border-gray-300 placeholder-gray-500 text-gray-900 focus:outline-none focus:ring-indigo-500 focus:border-indigo-500 focus:z-10 sm:text-sm"
                  placeholder="邮箱"
              />
            </div>
          </div>
          <div>
            <label class="sr-only">手机号</label>
            <input
                v-model="form.phone"
                type="text"
                autocomplete="current-password"
                required
                class="appearance-none rounded-none relative block w-full px-3 py-2 border border-gray-300 placeholder-gray-500 text-gray-900 focus:outline-none focus:ring-indigo-500 focus:border-indigo-500 focus:z-10 sm:text-sm"
                placeholder="手机号"
            />
          </div>
          <div>
            <label class="sr-only">验证码</label>
            <input
                v-model="form.code"
                type="text"
                autocomplete="current-password"
                required
                class="appearance-none rounded-none relative block w-full px-3 py-2 border border-gray-300 placeholder-gray-500 text-gray-900 rounded-b-md focus:outline-none focus:ring-indigo-500 focus:border-indigo-500 focus:z-10 sm:text-sm"
                placeholder="验证码(不区分大小写)"
            />
          </div>
        </div>
        <div class="flex items-center justify-between mt-0">
          <img :src="imgUrl" alt/>
          <p @click="getCheckCode" class="text-1xl cursor-pointer">看不清,换一张</p>
        </div>
        <div class="flex items-center justify-between">
          <div class="text-sm"></div>

          <div class="text-sm">
            <a
                href="/login"
                class="font-medium text-indigo-600 hover:text-indigo-500"
            >已有账号,去登陆?</a>
          </div>
        </div>

        <div>
          <button
              @click="rs"
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
            注册
          </button>
        </div>
      </div>
    </div>
  </div>
</template>


<style lang="less" scoped>
</style>