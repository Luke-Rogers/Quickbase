<template>
  <div>
    <draggable v-model="scriptComponents" :options="{group:'people'}" @start="drag=true" @end="drag=false">
      <change-set v-for="(scriptComponent,index) in scriptComponents"  v-bind:actions="scriptComponent.actions">
      </change-set>
    </draggable>
  debug:{{scriptComponents}}
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
    name: 'ChangeSetList',
    data() {
      return {
        scriptComponents: [{actions: [{test: "test1"}, {test: "test2"}]},
          {actions: [{test: "test3"}, {test: "test4"}]}],
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
