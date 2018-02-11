<template>
  <div>
    <v-container grid-list-md text-xs-center>
      <v-layout row wrap>
        <v-flex xs4 offset-xs4 text-xs-center>
          <h1>Quickbase</h1>
        </v-flex>
        <v-flex xs4 offset-xs4 text-xs-center>

            <draggable v-model="data.scriptComponents" :options="{group:'people'}" @start="drag=true" @end="drag=false">
              <change-set v-for="(scriptComponent,index) in data.scriptComponents"  v-bind:changeSet="scriptComponent">
              </change-set>
            </draggable>
        </v-flex>
      </v-layout>
    </v-container>

  debug:{{data.scriptComponents}}

    <div>
      <v-btn color="primary" v-on:click="addChangeSet()">Add changeset +</v-btn>
    </div>
  </div>
</template>

<script>
  import draggable from 'vuedraggable';
  import ChangeSet from "./changeSet.vue";
  import changeSetStore from '../stores/changeSetData'

  export default {
    components: {
      ChangeSet,
      draggable
    },
    name: 'ChangeSetList',
    data() {
      return {
        data: changeSetStore.data,
      }
    },
    methods:{
      addChangeSet: () =>{
        changeSetStore.addChangeSet();
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
