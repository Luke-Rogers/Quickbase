package com.quickbase.controller

import com.quickbase.api.ChangeType
import com.quickbase.api.QuickbaseScreenData
import com.quickbase.api.Table
import com.quickbase.api.change.ChangeLogRequest
import com.quickbase.service.TableService
import com.quickbase.service.changeset.ChangeSetService
import com.quickbase.service.validation.ChangeLogRequestValidator
import org.springframework.web.bind.WebDataBinder
import org.springframework.web.bind.annotation.*
import java.io.IOException
import javax.validation.Valid

@RestController
@RequestMapping("quickbase")
class QuickbaseController(val tableService: TableService, val changeLogRequestValidator: ChangeLogRequestValidator) {

    @InitBinder
    fun initBinder(binder: WebDataBinder) {
        binder.addValidators(changeLogRequestValidator)
    }

    @GetMapping(value = [""])
    fun getQuickbase() = QuickbaseScreenData(ChangeType.asCodeWithLabels(), tableService.getTableOptions())

    @GetMapping(value = ["definition/{tableName}"])
    fun getTableDefinition(@PathVariable tableName: String): Table = tableService.getDefinition(tableName)

    @PostMapping(value = ["generate"], produces = ["application/xml"])
    @Throws(IOException::class)
    fun generateDatabaseChangeSet(@RequestBody @Valid request: ChangeLogRequest): String = ChangeSetService().generateXml(request)

}
