import Vue from 'vue'
import Vuex from 'vuex'
import { login } from '@/api/login'
import { constantRouterMap } from '../router/routes'

Vue.use(Vuex)

const state = {
    token: null,
    user: {},
    role: [],
    // 第一次加载菜单时用到
    loadMenus: false,
    menus: null,
    routers: constantRouterMap,
    addRouters: [],
    sidebarRouters: [],
    isGenRouters: false
}

const mutations = {
  SET_LOAD_MENUS: (state, loadMenus) => {
    state.loadMenus = loadMenus
  },
    SET_ROUTERS: (state, routers) => {
        state.addRouters = routers
        state.routers = constantRouterMap.concat(routers)
      },
    SET_SIDEBAR_ROUTERS: (state, routers) => {
        state.sidebarRouters = routers
    },
    SET_USER: (state, user) => {
        state.user = user
      },
    SET_ROLE: (state, role) => {
        state.role = role
      },
    SET_TOKEN: (state, token) => {
        state.token = token
      },
    SET_MEMUS: (state, menus) => {
        state.menus = menus
      },
}
const actions = {
  updateLoadMenus({ commit }) {
    return new Promise((resolve, reject) => {
      commit('SET_LOAD_MENUS', false)
    })
  },
     // 登录
     Login({ commit }, loginQuery) {
        return new Promise((resolve, reject) => {
          login(loginQuery).then(res => {
            commit('SET_USER', res.data.user.user)
            commit('SET_ROLE', res.data.user.userRole)
            commit('SET_TOKEN', res.data.token)
            commit('SET_MEMUS', res.data.user.menus)
            // 设置 menu
            resolve()
          }).catch(error => {
            reject(error)
          })
        })
      },
      GenerateRoutes({ commit }, asyncRouter) {
    commit('SET_ROUTERS', asyncRouter)
    },
      SetSidebarRouters({ commit }, sidebarRouter) {
    commit('SET_SIDEBAR_ROUTERS', sidebarRouter)
    }
  
}

export default new Vuex.Store({
    state,
    mutations,
    actions
})