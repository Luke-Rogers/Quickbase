package com.quickbase.api.change

import com.quickbase.api.ChangeType

class DropTable(changeType: ChangeType, schemaName: String? = null, tableName: String) : Change(changeType, schemaName, tableName)