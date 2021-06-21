package com.torquato.spock.domain.model.people

import com.torquato.spock.support.TestSupport
import spock.lang.Unroll

class SearchParametersTest extends TestSupport {

    @Unroll
    def "Should create a valid SearchParameters with page equals #page and page size equals #pageSize"() {
        when: 'A search parameter is created'
        def parameters = new SearchParameters()
        parameters.page = page
        parameters.pageSize = pageSize
        def violations = validator.validate(parameters)

        then: 'There is no violations'
        violations.isEmpty()

        where: 'Page must be greater than 0 and pageSize must be greater than 4'
        page | pageSize
        1    | 5
        10   | 10
        2    | 50
    }

    @Unroll
    def "Should fail when create an invalid SearchParameters with page equals #page and page size equals #pageSize"() {
        when: 'A search parameter is created'
        def parameters = new SearchParameters()
        parameters.page = page
        parameters.pageSize = pageSize
        def violations = validator.validate(parameters)

        then: 'There is violations'
        !violations.isEmpty()

        where: 'Page must be less than 1 or pageSize must be less than 5'
        page | pageSize
        0    | 5
        -1   | 5
        1    | 4
        1    | 3
        1    | 2
        1    | 1
        1    | 0
        1    | -1
    }

}
