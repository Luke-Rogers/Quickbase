package com.quickbase.repository

import org.hamcrest.CoreMatchers.equalTo
import org.hamcrest.CoreMatchers.hasItem
import org.junit.Assert.assertThat
import org.junit.runner.RunWith
import org.springframework.beans.factory.annotation.Autowired
import org.springframework.boot.test.autoconfigure.orm.jpa.DataJpaTest
import org.springframework.test.context.junit4.SpringRunner
import kotlin.test.Test
import kotlin.test.asserter

@RunWith(value = SpringRunner::class)
@DataJpaTest
class QuickbaseRepositoryTest {

    @Autowired
    private lateinit var quickbaseRepository: QuickbaseRepository

    @Test
    fun should_find_tables() {
        val tables = quickbaseRepository.findTableNames();
        asserter.assertEquals("Should pull back 2 table names", 2, tables.size)
        assertThat(tables, hasItem("TEST_TABLE_1"))
        assertThat(tables, hasItem("TEST_TABLE_2"))
    }

    @Test
    fun should_find_rows_by_table_name() {
        val rows = quickbaseRepository.getTable("TEST_TABLE_1")
        assertThat(rows.size, equalTo(1))
    }

}