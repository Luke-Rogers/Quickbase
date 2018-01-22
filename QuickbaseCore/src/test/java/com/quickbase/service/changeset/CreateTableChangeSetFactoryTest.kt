package com.quickbase.service.changeset

import com.quickbase.api.ChangeType
import com.quickbase.api.change.ChangeColumn
import com.quickbase.api.change.CreateTable
import kotlin.test.Test
import kotlin.test.asserter

class CreateTableChangeSetFactoryTest {

    @Test
    fun should_generate_change_set() {
        val columns = listOf(ChangeColumn(name = "COLUMN_NAME", type = "TYPE"))
        val changeSet = CreateTableChangeSetFactory().generateChange(CreateTable(ChangeType.CREATE_TABLE, "SCHEMA_NAME", "TABLE_NAME", columns))
        asserter.assertEquals("Schema name should be populated", "SCHEMA_NAME", changeSet.schemaName)
        asserter.assertEquals("Table name should be populated", "TABLE_NAME", changeSet.tableName)
        asserter.assertEquals("Should have one column", 1, changeSet.columns.size)
        val column = changeSet.columns.first()
        asserter.assertEquals("Column should have name", "COLUMN_NAME", column.name)
        asserter.assertEquals("Column should have type", "TYPE", column.type)
    }

}