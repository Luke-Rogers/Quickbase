package com.quickbase.api.change

import com.fasterxml.jackson.annotation.JsonSubTypes
import com.fasterxml.jackson.annotation.JsonTypeInfo
import com.quickbase.api.ChangeType
import org.hibernate.validator.constraints.NotBlank

@JsonTypeInfo(
        use = JsonTypeInfo.Id.NAME,
        include = JsonTypeInfo.As.PROPERTY,
        property = "changeType")
@JsonSubTypes(
        JsonSubTypes.Type(value = CreateTable::class, name = "CREATE_TABLE"),
        JsonSubTypes.Type(value = DropTable::class, name = "DROP_TABLE"),
        JsonSubTypes.Type(value = Update::class, name = "UPDATE"),
        JsonSubTypes.Type(value = Insert::class, name = "INSERT"))
abstract class Change(val changeType: ChangeType, val schemaName: String?, @NotBlank val tableName: String)