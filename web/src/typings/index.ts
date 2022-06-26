interface UserInfo{
    id:number,
    name:string,
    user:string,
    password:string,
    email:string,
    phone:string,
}
interface IState{
    userInfo:UserInfo
}

interface MyService{

}

export{
    UserInfo,
    IState,
    MyService
}