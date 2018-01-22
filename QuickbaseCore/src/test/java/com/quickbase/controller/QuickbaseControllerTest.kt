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
        val dropTable = DropTable(ChangeType.DROP_TABLE, null, "TEST_TABLE")
        mockMvc.perform(MockMvcRequestBuilders.post("/quickbase/generate")
                .accept(MediaType.APPLICATION_XML)
                .contentType(MediaType.APPLICATION_JSON)
                .content(asJson(ChangeLogRequest(dropTable, "author", listOf("test_context"), "changeSetId"))))
                .andExpect(status().isOk)
                .andExpect(content().xml(xmlFileAsString("drop-table-change-log.xml")))
    }

    @Test
    @Throws(Exception::class)
    fun should_generate_insert_change() {
        val columns = listOf(ChangeColumn("TEST_COLUMN", "NVARCHAR", "TEST"))
        val insert = Insert(ChangeType.INSERT, null, "TEST_TABLE", columns)
        mockMvc.perform(MockMvcRequestBuilders.post("/quickbase/generate")
                .accept(MediaType.APPLICATION_XML)
                .contentType(MediaType.APPLICATION_JSON)
                .content(asJson(ChangeLogRequest(insert, "author", listOf("test_context"), "changeSetId"))))
                .andExpect(status().isOk)
                .andExpect(content().xml(xmlFileAsString("insert-change-log.xml")))
    }

    @Test
    @Throws(Exception::class)
    fun should_generate_update_change() {
        val columns = listOf(ChangeColumn("TEST_COLUMN", "NVARCHAR", "TEST"))
        val update = Update(ChangeType.UPDATE, null, "TEST_TABLE", columns, "TEST_COLUMN = 'ABC'")
        mockMvc.perform(MockMvcRequestBuilders.post("/quickbase/generate")
                .accept(MediaType.APPLICATION_XML)
                .contentType(MediaType.APPLICATION_JSON)
                .content(asJson(ChangeLogRequest(update, "author", listOf("test_context"), "changeSetId"))))
                .andExpect(status().isOk)
                .andExpect(content().xml(xmlFileAsString("update-change-log.xml")))
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
