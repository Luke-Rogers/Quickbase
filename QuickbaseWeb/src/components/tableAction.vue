<template>
  <div class="tableAction">
        {{action}}
        <div>
          <v-btn color="primary">Primary</v-btn>
        </div>

      </div>

</template>

<script>
  import draggable from 'vuedraggable';

  export default {
    components: {
      draggable
    },
    name: 'tableAction',
    props: ['action'],
    data() {
      return {}
    }
  }
</script>

<!-- Add "scoped" attribute to limit CSS to this component only -->
<style scoped>

</style>
