package com.quickbase.api.change

import com.quickbase.api.ChangeType

class Update(changeType: ChangeType, schemaName: String? = null, tableName: String, val columns: Collection<ChangeColumn>, val condition: String) : Change(changeType, schemaName, tableName)