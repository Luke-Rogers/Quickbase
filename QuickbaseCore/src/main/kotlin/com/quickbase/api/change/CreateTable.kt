package com.quickbase.api.change

import com.quickbase.api.ChangeType

class CreateTable(changeType: ChangeType, schemaName: String? = null, tableName: String, val columns: Collection<ChangeColumn>) : Change(changeType, schemaName, tableName)
