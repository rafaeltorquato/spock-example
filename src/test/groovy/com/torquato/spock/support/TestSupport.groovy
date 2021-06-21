package com.torquato.spock.support


import spock.lang.Specification

import javax.validation.Validation
import javax.validation.Validator
import javax.validation.ValidatorFactory

class TestSupport extends Specification {

    Validator validator

    def setup() {
        ValidatorFactory factory = Validation.buildDefaultValidatorFactory();
        validator = factory.getValidator();
    }
}
