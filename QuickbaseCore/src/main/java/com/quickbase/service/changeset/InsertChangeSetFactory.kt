package com.quickbase.service.changeset

import com.quickbase.api.change.ChangeColumn
import com.quickbase.api.change.Insert
import liquibase.change.ColumnConfig
import liquibase.change.core.InsertDataChange

class InsertChangeSetFactory : ChangeSetFactory<Insert> {

    override fun generateChange(change: Insert): InsertDataChange {
        val insertDataChange = InsertDataChange()
        insertDataChange.schemaName = change.schemaName
        insertDataChange.tableName = change.tableName
        insertDataChange.columns = change.columns.map(this::getColumnConfig)
        return insertDataChange
    }

    private fun getColumnConfig(column: ChangeColumn): ColumnConfig {
        val columnConfig = ColumnConfig()
        columnConfig.name = column.name
        columnConfig.value = column.value
        return columnConfig
    }
}
