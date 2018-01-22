package com.quickbase.service.changeset

import com.quickbase.api.ChangeType
import com.quickbase.api.change.DropTable
import kotlin.test.Test
import kotlin.test.asserter


class DropTableChangeSetFactoryTest {

    @Test
    fun should_generate_change_set() {
        val changeSet = DropTableChangeSetFactory().generateChange(DropTable(ChangeType.DROP_TABLE, "SCHEMA_NAME", "TABLE_NAME"))
        asserter.assertEquals("Schema name should be populated", changeSet.schemaName, "SCHEMA_NAME")
        asserter.assertEquals("Table name should be populated", changeSet.tableName, "TABLE_NAME")
    }

}