package com.quickbase.controller

import com.quickbase.api.ChangeType
import com.quickbase.api.QuickbaseScreenData
import com.quickbase.api.Table
import com.quickbase.api.change.ChangeRequest
import com.quickbase.service.TableService
import com.quickbase.service.changeset.ChangeService
import com.quickbase.service.validation.ChangeRequestValidator
import org.springframework.web.bind.WebDataBinder
import org.springframework.web.bind.annotation.*
import java.io.IOException

@RestController
@RequestMapping("quickbase")
class QuickbaseController(val tableService: TableService, val changeRequestValidator: ChangeRequestValidator) {

    @InitBinder
    fun initBinder(binder: WebDataBinder) {
        binder.addValidators(changeRequestValidator)
    }

    @GetMapping(value = [""])
    fun getQuickbase() = QuickbaseScreenData(ChangeType.asCodeWithLabels(), tableService.getTableOptions())

    @GetMapping(value = ["definition/{tableName}"])
    fun getTableDefinition(@PathVariable tableName: String): Table = tableService.getDefinition(tableName)

    @PostMapping(value = ["generate"], produces = ["application/xml"])
    @Throws(IOException::class)
    fun generateDatabaseChangeSet(@RequestBody request: ChangeRequest): String = ChangeService().generateXml(request)

}
