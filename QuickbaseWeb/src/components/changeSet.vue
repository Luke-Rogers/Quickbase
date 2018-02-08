<template>
  <div class="changeSet">
    <h1> change set {{index}}</h1>
    <draggable v-model="actions" :options="{group:'other'}" @start="drag=true" @end="drag=false">
      <div v-for="action in actions" :key="action">{{action}}
        <div>
          <v-btn color="primary">Primary</v-btn>
        </div>

      </div>

    </draggable>
  </div>
</template>

<script>
  import draggable from 'vuedraggable';

  export default {
    components: {
      draggable
    },
    name: 'changeSet',
    props: ['actions'],
    data() {

    }
  }
</script>

<!-- Add "scoped" attribute to limit CSS to this component only -->
<style scoped>

</style>
