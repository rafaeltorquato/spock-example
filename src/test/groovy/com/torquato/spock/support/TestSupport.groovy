package com.torquato.spock.support

import spock.lang.Shared
import spock.lang.Specification

import javax.validation.Validation
import javax.validation.Validator
import javax.validation.ValidatorFactory

class TestSupport extends Specification {

    @Shared
    Validator validator

    def setupSpec() {
        ValidatorFactory factory = Validation.buildDefaultValidatorFactory();
        validator = factory.getValidator();
    }
}
