import Vue from 'vue'
import Router from 'vue-router'
import ChangeSetList from '@/components/changeSetList'

Vue.use(Router)

export default new Router({
  routes: [
    {
      path: '/',
      name: 'HelloWorld',
      component: ChangeSetList
    }
  ]
})
