package com.quickbase.service.changeset

import com.quickbase.api.change.*
import liquibase.changelog.ChangeSet
import liquibase.changelog.DatabaseChangeLog
import liquibase.serializer.core.xml.XMLChangeLogSerializer
import java.io.ByteArrayOutputStream

class ChangeSetService {

    fun generateXml(request: ChangeLogRequest): String {
        val change = when (request.change) {
            is DropTable -> DropTableChangeSetFactory().generateChange(request.change)
            is Insert -> InsertChangeSetFactory().generateChange(request.change)
            is Update -> UpdateChangeSetFactory().generateChange(request.change)
            is CreateTable -> CreateTableChangeSetFactory().generateChange(request.change)
            else -> throw RuntimeException()
        }
        val changeLog = DatabaseChangeLog()
        val contexts = request.contexts.joinToString()
        val changeSet = ChangeSet(request.changeSetId, request.author, false, false, "", contexts, null, true, null, changeLog)
        changeSet.addChange(change)
        changeLog.addChangeSet(changeSet)
        val outputStream = ByteArrayOutputStream()
        XMLChangeLogSerializer().write(changeLog.changeSets, outputStream)
        return outputStream.toString()
    }

}