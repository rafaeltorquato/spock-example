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
        ResultPageDTO<PeopleDTO> result = get(PeoplesRest.PATH + queryParameters, ResultPageDTO.class, HttpStatus.OK) as ResultPageDTO<PeopleDTO>

        then: 'Result list should have 5 peoples with name equals Rafael'
        result.elements.size() == 5
        result.elements*.name.count(searchParameter.name) == 5
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
        ResultPageDTO<PeopleDTO> result = get(PeoplesRest.PATH + queryParameters, ResultPageDTO.class, HttpStatus.OK) as ResultPageDTO<PeopleDTO>

        then: 'Result list should have 3 peoples with name Rafael and surname Torquato'
        result.elements.size() == 3
        result.elements*.name.count(searchParameter.name) == 3
        result.elements*.surname.count(searchParameter.surname) == 3
    }

    def setup() {
        def people = new People()
        people.setName('Rafael');
        people.setSurname('Torquato')
        mongoTemplate.save(people)
        people = new People()
        people.setName('Rafael');
        people.setSurname('Torquato')
        mongoTemplate.save(people)
        people = new People()
        people.setName('Rafael');
        people.setSurname('Torquato')
        mongoTemplate.save(people)

        people = new People()
        people.setName('Rafael');
        people.setSurname('Nascimento')
        mongoTemplate.save(people)

        people = new People()
        people.setName('Rafael');
        people.setSurname('Silva')
        mongoTemplate.save(people)
    }

    def cleanup() {
        mongoTemplate.dropCollection(People.class)
    }

    static def toQueryString(SearchParametersDTO parameters) {
        "?page=$parameters.page&pageSize=$parameters.pageSize&name=$parameters.name&surname=$parameters.surname"
    }

}
