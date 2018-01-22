package com.quickbase.service.changeset

import com.quickbase.api.change.ChangeColumn
import com.quickbase.api.change.CreateTable
import liquibase.change.ColumnConfig
import liquibase.change.core.CreateTableChange

class CreateTableChangeSetFactory : ChangeSetFactory<CreateTable> {

    override fun generateChange(change: CreateTable): CreateTableChange {
        val createTableChange = CreateTableChange()
        createTableChange.schemaName = change.schemaName
        createTableChange.tableName = change.tableName
        createTableChange.columns = change.columns.map(this::getColumnConfig)
        return createTableChange
    }

    private fun getColumnConfig(column: ChangeColumn): ColumnConfig {
        val columnConfig = ColumnConfig()
        columnConfig.name = column.name
        columnConfig.type = column.type
        return columnConfig
    }
}