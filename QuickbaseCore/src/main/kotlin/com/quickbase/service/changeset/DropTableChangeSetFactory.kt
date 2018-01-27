package com.quickbase.service.changeset

import com.quickbase.api.change.DropTable
import liquibase.change.core.DropTableChange

class DropTableChangeSetFactory : ChangeSetFactory<DropTable> {
    override fun generateChange(change: DropTable): DropTableChange {
        val dropTableChange = DropTableChange()
        dropTableChange.schemaName = change.schemaName
        dropTableChange.tableName = change.tableName
        return dropTableChange
    }

}
