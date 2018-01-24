package com.quickbase.service.changeset

import com.quickbase.api.change.*
import liquibase.changelog.ChangeSet
import liquibase.changelog.DatabaseChangeLog
import liquibase.serializer.core.xml.XMLChangeLogSerializer
import java.io.ByteArrayOutputStream

class ChangeService {

    fun generateXml(request: ChangeRequest): String {
        val changeLog = DatabaseChangeLog()
        request.changeLogRequests.forEach { changeLogRequest -> generateChangeSet(changeLog, changeLogRequest) }
        val outputStream = ByteArrayOutputStream()
        XMLChangeLogSerializer().write(changeLog.changeSets, outputStream)
        return outputStream.toString()
    }

    private fun generateChangeSet(changeLog: DatabaseChangeLog, changeLogRequest: ChangeLogRequest) {
        val change = when (changeLogRequest.change) {
            is DropTable -> DropTableChangeSetFactory().generateChange(changeLogRequest.change)
            is Insert -> InsertChangeSetFactory().generateChange(changeLogRequest.change)
            is Update -> UpdateChangeSetFactory().generateChange(changeLogRequest.change)
            is CreateTable -> CreateTableChangeSetFactory().generateChange(changeLogRequest.change)
            else -> throw RuntimeException()
        }
        val contexts = changeLogRequest.contexts.joinToString()
        val changeSet = ChangeSet(changeLogRequest.changeSetId, changeLogRequest.author, false, false, "", contexts, null, true, null, changeLog)
        changeSet.addChange(change)
        changeLog.addChangeSet(changeSet)
    }

}