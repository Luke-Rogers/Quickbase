<template>
  <div>
    <change-set></change-set>
    <draggable v-model="scriptComponents" :options="{group:'people'}" @start="drag=true" @end="drag=false">
      <div  class="changeSet" v-for="(scriptComponent,index) in scriptComponents" :key="scriptComponent">
        <h1> change set {{index}}</h1>
        <draggable v-model="scriptComponent.actions" :options="{group:'other'}" @start="drag=true" @end="drag=false">
          <div v-for="action in scriptComponent.actions" :key="action">{{action}}
            <div>
              <v-btn color="primary">Primary</v-btn>
            </div>

          </div>

        </draggable>

      </div>
    </draggable>

  </div>
</template>

<script>
  import draggable from 'vuedraggable';
  import ChangeSet from "./changeSet.vue";

  export default {
    components: {
      ChangeSet,
      draggable
    },
    name: 'HelloWorld',
    data() {
      return {
        msg: 'Welcome to Your Vue.js App',
        scriptComponents: [{actions: [{test: "test1"}, {test: "test2"}]},
          {actions: [{test: "test3"}, {test: "test4"}]}],
        exampleList: [
          'Item 1',
          'Item 2',
          'Item 3',
          'Item 4',
          'Item 5'
        ]
      }
    }
  }
</script>

<!-- Add "scoped" attribute to limit CSS to this component only -->
<style scoped>
.changeSet{
  width:50%;
  background-color: white;
  margin-top: 2rem;
  min-height: 200px;
}

</style>
