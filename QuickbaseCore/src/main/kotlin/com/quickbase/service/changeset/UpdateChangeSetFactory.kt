package com.quickbase.service.changeset

import com.quickbase.api.change.ChangeColumn
import com.quickbase.api.change.Update
import liquibase.change.ColumnConfig
import liquibase.change.core.UpdateDataChange

class UpdateChangeSetFactory : ChangeSetFactory<Update> {

    override fun generateChange(change: Update): UpdateDataChange {
        val updateDataChange = UpdateDataChange()
        updateDataChange.schemaName = change.schemaName
        updateDataChange.tableName = change.tableName
        updateDataChange.columns = change.columns.map(this::getColumnConfig)
        updateDataChange.where = change.condition
        return updateDataChange
    }

    private fun getColumnConfig(column: ChangeColumn): ColumnConfig {
        val columnConfig = ColumnConfig()
        columnConfig.name = column.name
        columnConfig.value = column.value
        return columnConfig
    }

}
