<template>

  <v-card class="changeSet">
    <h1> change set</h1>
    <draggable v-model="actions" :options="{group:'other'}" @start="drag=true" @end="drag=false">
      <table-action v-for="action in actions" v-bind:action="action" ></table-action>
    </draggable>
    debug:{{actions}}
  </v-card>
</template>

<script>
  import draggable from 'vuedraggable';
  import TableAction from "./tableAction.vue";

  export default {
    components: {
      TableAction,
      draggable
    },
    name: 'changeSet',
    props: ['actions'],
    data() {
    return {}
    }
  }
</script>

<!-- Add "scoped" attribute to limit CSS to this component only -->
<style scoped>
.changeSet{
  width:100%;
  margin-top:10px;
}
</style>
