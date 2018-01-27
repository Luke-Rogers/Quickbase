package com.quickbase.service.validation

import com.quickbase.api.change.ChangeRequest
import org.springframework.stereotype.Service
import org.springframework.validation.Errors
import org.springframework.validation.Validator

@Service
class ChangeRequestValidator : Validator {

    override fun supports(aClass: Class<*>): Boolean {
        return ChangeRequest::class.java == aClass
    }

    override fun validate(o: Any, errors: Errors) {

    }

}
