package com.torquato.spock.delivery.people.rest


import com.torquato.spock.support.ITSupport
import org.springframework.http.HttpStatus

class PeopleRestIT extends ITSupport {

    def 'A search by name will return a list with 5 peoples with given name'() {
        given: 'A name parameter with value Rafael'
        def searchParameter = com.torquato.spock.delivery.people.facade.dto.SearchParametersDTO.builder()
                .name('Rafael')
                .page(1)
                .pageSize(5)
                .build()

        when: 'Search is performed with given parameters'
        def queryParameters = toQueryString(searchParameter)
        com.torquato.spock.delivery.shared.ResultPageDTO<com.torquato.spock.delivery.people.facade.dto.PeopleDTO> result = get(PeoplesRest.PATH + queryParameters, com.torquato.spock.delivery.shared.ResultPageDTO.class, HttpStatus.OK) as com.torquato.spock.delivery.shared.ResultPageDTO<com.torquato.spock.delivery.people.facade.dto.PeopleDTO>

        then: 'Result list should have 5 peoples with name equals Rafael'
        result.elements.size() == 5
        result.elements*.name.count(searchParameter.name) == 5
    }

    def 'A search by name and surname will return a list with 3 peoples with given name and surname'() {
        given: 'A search parameters with name equals Rafael and surname equals Torquato'
        def searchParameter = com.torquato.spock.delivery.people.facade.dto.SearchParametersDTO.builder()
                .name('Rafael')
                .surname('Torquato')
                .page(1)
                .pageSize(5)
                .build()

        when: 'Search is performed with given parameters'
        def queryParameters = toQueryString(searchParameter)
        com.torquato.spock.delivery.shared.ResultPageDTO<com.torquato.spock.delivery.people.facade.dto.PeopleDTO> result = get(PeoplesRest.PATH + queryParameters, com.torquato.spock.delivery.shared.ResultPageDTO.class, HttpStatus.OK) as com.torquato.spock.delivery.shared.ResultPageDTO<com.torquato.spock.delivery.people.facade.dto.PeopleDTO>

        then: 'Result list should have 3 peoples with name Rafael and surname Torquato'
        result.elements.size() == 3
        result.elements*.name.count(searchParameter.name) == 3
        result.elements*.surname.count(searchParameter.surname) == 3
    }

    def setup() {
        def people = new com.torquato.spock.domain.model.people.People()
        people.setName('Rafael');
        people.setSurname('Torquato')
        mongoTemplate.save(people)
        people = new com.torquato.spock.domain.model.people.People()
        people.setName('Rafael');
        people.setSurname('Torquato')
        mongoTemplate.save(people)
        people = new com.torquato.spock.domain.model.people.People()
        people.setName('Rafael');
        people.setSurname('Torquato')
        mongoTemplate.save(people)

        people = new com.torquato.spock.domain.model.people.People()
        people.setName('Rafael');
        people.setSurname('Nascimento')
        mongoTemplate.save(people)

        people = new com.torquato.spock.domain.model.people.People()
        people.setName('Rafael');
        people.setSurname('Silva')
        mongoTemplate.save(people)
    }

    def cleanup() {
        mongoTemplate.dropCollection(com.torquato.spock.domain.model.people.People.class)
    }

    static def toQueryString(com.torquato.spock.delivery.people.facade.dto.SearchParametersDTO parameters) {
        "?page=$parameters.page&pageSize=$parameters.pageSize&name=$parameters.name&surname=$parameters.surname"
    }

}
