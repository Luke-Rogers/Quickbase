package com.quickbase.repository

import com.quickbase.domain.Quickbase
import org.springframework.data.jpa.repository.JpaRepository
import org.springframework.data.jpa.repository.Query
import org.springframework.data.repository.query.Param
import org.springframework.stereotype.Repository

@Repository
interface QuickbaseRepository : JpaRepository<Quickbase, Quickbase.QuickbaseKey> {

    @Query("SELECT DISTINCT key.tableName FROM Quickbase")
    fun findTableNames(): List<String>

    @Query("FROM Quickbase WHERE key.tableName = :tableName")
    fun getTable(@Param("tableName") tableName: String): List<Quickbase>

}