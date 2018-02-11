<template>
  <div class="tableAction">
        <div>
          <h3 color="primary">{{action.test}}   <v-icon class="action--delete"  v-on:click="removeAction()">delete</v-icon></h3>
        </div>
      </div>
</template>

<script>
import draggable from 'vuedraggable'

export default {
  components: {
    draggable
  },
  name: 'tableAction',
  props: ['action'],
  data () {
    return {}
  },
  methods: {
    removeAction: function () {
      this.$emit('deleteAction')
    }
  }
}
</script>

<!-- Add "scoped" attribute to limit CSS to this component only -->
<style scoped>
  .action--delete{
    color:grey;

    cursor: pointer;
  }
  .action--delete:hover {
    color:maroon;
  }

</style>
