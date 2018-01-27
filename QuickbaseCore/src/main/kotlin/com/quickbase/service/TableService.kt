package com.quickbase.service

import com.quickbase.api.Column
import com.quickbase.api.Table
import com.quickbase.repository.QuickbaseRepository
import org.springframework.stereotype.Service

@Service
class TableService(val quickbaseRepository: QuickbaseRepository) {

    fun getTableOptions(): Collection<String> = quickbaseRepository.findTableNames()

    fun getDefinition(tableName: String): Table {
        val columns = quickbaseRepository.getTable(tableName)
                .map { quickbase ->
                    Column(
                            name = quickbase.key.columnName,
                            type = quickbase.columnType,
                            nullable = quickbase.nullable,
                            description = quickbase.description,
                            characterLength = quickbase.characterLength,
                            numericPrecision = quickbase.numericPrecision,
                            numericScale = quickbase.numericScale)
                }
        return Table(tableName, columns)
    }
}