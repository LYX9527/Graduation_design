import request from '@/service'

export default {
    // 获取验证码
    getCode() {
        return request({
            url: '/checkcode',
            method: 'get',
        })
    },
    // 登录
    login(params: { user: string, password: string, code: string, uuid: string }) {
        return request({
            url: '/login',
            method: 'post',
            data: params
        })
    },
    //注册
    register(data: { name: string, user: string, password: string, code: string, email: string, phone: string }) {
        return request({
            url: '/register',
            method: 'post',
            data,
        })
    },
    //获取所有题目
    getAllQuestions(params: { pageSize?: number, pageNum?: number }) {
        return request({
            url: '/allsubject',
            method: 'get',
            params,
        })
    },
    //根据题目类型获取题目
    getQuestionByType(params: { type: number,pageSize?: number, pageNum?: number }) {
        return request({
            url: '/subjectbyType',
            method: 'get',
            params,
        })
    },
    //根据技术栈查询题目
    getQuestionByClassType(params: { classtype: number, pageSize?: number, pageNum?: number }) {
        return request({
            url: '/subjectByClassType',
            method: 'get',
            params,
        })
    },
    //根据sid获取题目选项
    getOptionsBySid(params: { sid: number }) {
        return request({
            url: '/selectionBySid',
            method: 'get',
            params,
        })
    },
    //根据题目ID获取答案
    getAnswerBySid(params: { topicId: number }) {
        return request({
            url: '/getRightSelection',
            method: 'get',
            params,
        })
    },
    //搜索题目
    searchQuestion(params: { kw: any, pageSize?: number, pageNum?: number }) {
        return request({
            url: '/searchSubject',
            method: 'get',
            params,
        })
    },
    //添加题目
    addQuestion(data: { topic:string,type:number,classType:number}) {
        return request({
            url: '/addSubject',
            method: 'post',
            data,
        })
    },
    //添加题目选项
    addOptions(data:{selectionList:Array<{ sid: number, options: string,isRight:number }>}) {
        return request({
            url: '/addSelection',
            method: 'post',
            data,
        })
    },
}