package com.quickbase.controller

import com.fasterxml.jackson.core.JsonProcessingException
import com.fasterxml.jackson.databind.ObjectMapper
import com.quickbase.api.ChangeType
import com.quickbase.api.change.*
import com.quickbase.service.TableService
import org.apache.commons.io.FileUtils
import org.hamcrest.CoreMatchers.equalTo
import org.hamcrest.CoreMatchers.hasItem
import org.junit.Test
import org.junit.runner.RunWith
import org.springframework.beans.factory.annotation.Autowired
import org.springframework.boot.test.autoconfigure.web.servlet.AutoConfigureMockMvc
import org.springframework.boot.test.context.SpringBootTest
import org.springframework.http.MediaType
import org.springframework.test.context.junit4.SpringRunner
import org.springframework.test.web.servlet.MockMvc
import org.springframework.test.web.servlet.request.MockMvcRequestBuilders
import org.springframework.test.web.servlet.result.MockMvcResultMatchers.*
import java.io.IOException

@RunWith(SpringRunner::class)
@SpringBootTest
@AutoConfigureMockMvc
class QuickbaseControllerTest {

    @Autowired
    private lateinit var mockMvc: MockMvc

    @Autowired
    private lateinit var tableService: TableService

    @Test
    @Throws(Exception::class)
    fun should_get_quickbase() {
        mockMvc.perform(MockMvcRequestBuilders.get("/quickbase")
                .accept(MediaType.APPLICATION_JSON))
                .andExpect(status().isOk)
                .andExpect(jsonPath("$.actions[*].code", equalTo(ChangeType.values().map { changeType -> changeType.name })))
                .andExpect(jsonPath("$.actions[*].label", equalTo(ChangeType.values().map { changeType -> changeType.desc })))
                .andExpect(jsonPath("$.tables", equalTo(tableService.getTableOptions())))
                .andExpect(jsonPath("$.tables", hasItem("TEST_TABLE_1")))
    }

    @Test
    @Throws(Exception::class)
    fun should_generate_drop_table_change() {
        val changeRequest = ChangeRequest(listOf(ChangeLogRequest(getDropTableChange(), "author", listOf("test_context"), "changeSetId")))
        performRequest(changeRequest, "drop-table-change-log.xml")
    }

    @Test
    @Throws(Exception::class)
    fun should_generate_insert_change() {

        val changeRequest = ChangeRequest(listOf(ChangeLogRequest(getInsertChange(), "author", listOf("test_context"), "changeSetId")))
        performRequest(changeRequest, "insert-change-log.xml")
    }

    @Test
    @Throws(Exception::class)
    fun should_generate_update_change() {
        val changeRequest = ChangeRequest(listOf(ChangeLogRequest(getUpdateChange(), "author", listOf("test_context"), "changeSetId")))
        performRequest(changeRequest, "update-change-log.xml")
    }

    @Test
    @Throws(Exception::class)
    fun should_generate_mixture_of_changes() {
        val updateChangeLog = ChangeLogRequest(getUpdateChange(), "author", listOf("test_context"), "changeSetId")
        val dropTableChangeLog = ChangeLogRequest(getDropTableChange(), "author", listOf("test_context"), "changeSetId")
        val insertChangeLog = ChangeLogRequest(getInsertChange(), "author", listOf("test_context"), "changeSetId")
        val changeRequest = ChangeRequest(listOf(updateChangeLog, dropTableChangeLog, insertChangeLog))
        performRequest(changeRequest, "mixture-change-log.xml")
    }

    private fun getDropTableChange() : DropTable {
        return DropTable(ChangeType.DROP_TABLE, null, "TEST_TABLE")
    }

    private fun getInsertChange() : Insert {
        val columns = listOf(ChangeColumn("TEST_COLUMN", "NVARCHAR", "TEST"))
        return Insert(ChangeType.INSERT, null, "TEST_TABLE", columns)
    }

    private fun getUpdateChange() : Update {
        val columns = listOf(ChangeColumn("TEST_COLUMN", "NVARCHAR", "TEST"))
        return Update(ChangeType.UPDATE, null, "TEST_TABLE", columns, "TEST_COLUMN = 'ABC'")
    }

    private fun performRequest(changeRequest: ChangeRequest, fileName: String) {
        mockMvc.perform(MockMvcRequestBuilders.post("/quickbase/generate")
                .accept(MediaType.APPLICATION_XML)
                .contentType(MediaType.APPLICATION_JSON)
                .content(asJson(changeRequest)))
                .andExpect(status().isOk)
                .andExpect(content().xml(xmlFileAsString(fileName)))
    }

    @Throws(IOException::class)
    private fun xmlFileAsString(fileName: String): String {
        val file = FileUtils.toFile(this.javaClass.getResource("/" + fileName))
        return FileUtils.readFileToString(file, "UTF-8")
    }

    @Throws(JsonProcessingException::class)
    private fun asJson(`object`: Any): String {
        return ObjectMapper().writeValueAsString(`object`)
    }
}
