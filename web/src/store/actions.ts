import { IState,UserInfo } from "@/typings";
import { Commit } from "vuex";
import { SET_USER_INFO } from "./types";
interface ICtx {
    commit: Commit,
    state: IState,
}
export default{
    [SET_USER_INFO]({ commit }: ICtx, userInfo: UserInfo): void {
        commit(SET_USER_INFO, userInfo);
    },
}