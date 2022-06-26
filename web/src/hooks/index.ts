import {  SET_USER_INFO } from "@/store/types";
import { Store, useStore } from "vuex";
import { UserInfo } from "@/typings";
export interface ICtrlUserInfo {
    setuserinfo: (userinfo: UserInfo) => void;
}

function setUserInfo(): ICtrlUserInfo {
    const store: Store<any> = useStore();
    function setuserinfo(userinfo:UserInfo): void {
        store.dispatch(SET_USER_INFO,userinfo)
    }
    return {
        setuserinfo
    }
}

export {
    setUserInfo,
}
