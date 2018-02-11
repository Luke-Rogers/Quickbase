<template>
  <v-card class="changeSet">
    <h2> change set {{changeSet.id}}</h2>
    <v-icon class="changeSet--delete"  v-on:click="removeChangeSet()">delete</v-icon>
    <draggable v-model="changeSet.actions" :options="{group:'other'}" @start="drag=true" @end="persistChanges()">
      <table-action v-for="action in changeSet.actions" v-bind:action="action"  v-on:deleteAction="removeAction(action)" />
    </draggable>
    <div>
      <v-btn color="primary" v-on:click="addAction()">Add action +</v-btn>
    </div>
    debug:{{changeSet.actions}}
  </v-card>
</template>

<script>
import draggable from 'vuedraggable'
import TableAction from './tableAction.vue'
import changeSetStore from '../stores/changeSetData'

export default {
  components: {
    TableAction,
    draggable
  },
  name: 'changeSet',
  props: ['changeSet'],
  data () {
    return {}
  },
  methods: {
    persistChanges: function (e) {
      changeSetStore.updateAction(this.changeSet)
    },
    removeChangeSet: function () {
      changeSetStore.removeChangeSet(this.changeSet)
    },
    addAction: function () {
      this.changeSet.actions.push({test: 'new action'})
    },
    removeAction: function (action) {
      let index = this.changeSet.actions.indexOf(action)
      if (index !== -1) {
        this.changeSet.actions.splice(index, 1)
      }
    }
  }
}
</script>

<!-- Add "scoped" attribute to limit CSS to this component only -->
<style scoped>
  .changeSet {
    width: 100%;
    margin-top: 10px;
  }

  .changeSet--delete{
    color:grey;
    position:absolute;
    top:5px;
    right:5px;
    cursor: pointer;
  }
  .changeSet--delete:hover {
    color:maroon;
  }

</style>
