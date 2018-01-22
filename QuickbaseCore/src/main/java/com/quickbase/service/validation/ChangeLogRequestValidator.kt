package com.quickbase.service.validation

import com.quickbase.api.change.ChangeLogRequest
import org.springframework.stereotype.Service
import org.springframework.validation.Errors
import org.springframework.validation.Validator

@Service
class ChangeLogRequestValidator : Validator {

    override fun supports(aClass: Class<*>): Boolean {
        return ChangeLogRequest::class.java == aClass
    }

    override fun validate(o: Any, errors: Errors) {

    }

}
