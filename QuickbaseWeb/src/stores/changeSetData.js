let _ = require('lodash')

export default {

  data: {
    scriptComponents: [{id: 1, actions: [{test: 'test1'}, {test: 'test2'}]},
      {id: 2, actions: [{test: 'test3'}, {test: 'test4'}]}]
  },

  // Methods that you need, for e.g fetching data from server etc.

  fetchData () {
    return this.data
  },

  updateAction (changeSet) {
    // find matching action and replace
    let foundSet = _.find(this.data.scriptComponents, {id: changeSet.id})
    if (foundSet) _.merge(foundSet, changeSet)
    console.log('successfully merged')
  },

  addChangeSet () {
    this.data.scriptComponents.push({id: new Date().getTime(), actions: [{test: 'test1'}]})
  },
  removeChangeSet (changeSet) {
    // _.remove isnt being reactive....
    let index = _.findIndex(this.data.scriptComponents, {id: changeSet.id})
    if (index !== -1) {
      this.data.scriptComponents.splice(index, 1)
    }
  }
}
