package com.quickbase.api

enum class ChangeType constructor(val desc: String) {

    INSERT("Insert Record"),
    DROP_TABLE("Drop Table"),
    CREATE_TABLE("Create Table"),
    UPDATE("Update");

    companion object {
        fun asCodeWithLabels(): Collection<CodeWithLabel> {
            return ChangeType.values().map { changeType -> CodeWithLabel(changeType.name, changeType.desc) }.toCollection(ArrayList())
        }
    }

}
