package com.quickbase.api.change

import com.quickbase.api.ChangeType

class Insert(changeType: ChangeType, schemaName: String? = null, tableName: String, val columns: Collection<ChangeColumn>) : Change(changeType, schemaName, tableName)