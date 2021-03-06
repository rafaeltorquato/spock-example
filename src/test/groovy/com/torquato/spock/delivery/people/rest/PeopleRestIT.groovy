package com.torquato.spock.delivery.people.rest

import com.torquato.spock.delivery.people.facade.dto.PeopleDTO
import com.torquato.spock.delivery.people.facade.dto.SearchParametersDTO
import com.torquato.spock.delivery.shared.ResultPageDTO
import com.torquato.spock.domain.model.people.People
import com.torquato.spock.support.ITSupport
import org.springframework.http.HttpStatus

class PeopleRestIT extends ITSupport {

    def 'A search by name will return a list with 5 peoples with given name'() {
        given: 'A name parameter with value Rafael'
        def searchParameter = SearchParametersDTO.builder()
                .name('Rafael')
                .page(1)
                .pageSize(5)
                .build()

        when: 'Search is performed with given parameters'
        def queryParameters = toQueryString(searchParameter)
        ResultPageDTO<PeopleDTO> result = get(
                PeoplesRest.PATH + queryParameters,
                ResultPageDTO.class,
                HttpStatus.OK
        ) as ResultPageDTO<PeopleDTO>

        then: 'The result list should have 5 peoples'
        result.elements.size() == 5
        and: 'everyone must have name equals Rafael'
        result.elements*.name.count('Rafael') == 5
    }

    def 'A search by name and surname will return a list with 3 peoples with given name and surname'() {
        given: 'A search parameters with name equals Rafael and surname equals Torquato'
        def searchParameter = SearchParametersDTO.builder()
                .name('Rafael')
                .surname('Torquato')
                .page(1)
                .pageSize(5)
                .build()

        when: 'Search is performed with given parameters'
        def queryParameters = toQueryString(searchParameter)
        ResultPageDTO<PeopleDTO> result = get(
                PeoplesRest.PATH + queryParameters,
                ResultPageDTO.class,
                HttpStatus.OK
        ) as ResultPageDTO<PeopleDTO>

        then: 'The result list should have 3 peoples'
        result.elements.size() == 3
        and: 'everyone must have name equals Rafael'
        result.elements*.name.count('Rafael') == 3
        and: 'everyone must have surname equals Torquato'
        result.elements*.surname.count('Torquato') == 3
    }

    @SuppressWarnings("unused")
    def setup() {
        def people = new People()
        people.setName('Rafael')
        people.setSurname('Torquato')
        mongoTemplate.save(people)
        people = new People()
        people.setName('Rafael')
        people.setSurname('Torquato')
        mongoTemplate.save(people)
        people = new People()
        people.setName('Rafael')
        people.setSurname('Torquato')
        mongoTemplate.save(people)

        people = new People()
        people.setName('Rafael')
        people.setSurname('Nascimento')
        mongoTemplate.save(people)

        people = new People()
        people.setName('Rafael')
        people.setSurname('Silva')
        mongoTemplate.save(people)
    }

    @SuppressWarnings("unused")
    def cleanup() {
        mongoTemplate.dropCollection(People.class)
    }

    static def toQueryString(SearchParametersDTO parameters) {
        "?page=$parameters.page&pageSize=$parameters.pageSize&name=$parameters.name&surname=$parameters.surname"
    }

}
