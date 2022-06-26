import { IState, UserInfo } from "@/typings";
import {  SET_USER_INFO } from "./types";

export default {
    [SET_USER_INFO](state: IState, userInfo: UserInfo) {
        state.userInfo = userInfo;
    },
}