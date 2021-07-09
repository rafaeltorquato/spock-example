package com.torquato.spock.delivery.people.rest;

import com.torquato.spock.delivery.people.facade.PeopleFacade;
import com.torquato.spock.delivery.people.facade.dto.PeopleDTO;
import com.torquato.spock.delivery.people.facade.dto.SearchParametersDTO;
import com.torquato.spock.delivery.shared.ResultPageDTO;
import lombok.RequiredArgsConstructor;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping(PeoplesRest.PATH)
@RequiredArgsConstructor
public class PeoplesRest {

    public static final String PATH = "/peoples";

    private final PeopleFacade peopleFacade;

    @GetMapping
    public ResultPageDTO<PeopleDTO> search(SearchParametersDTO parameters) {
        return peopleFacade.search(parameters);
    }

}
