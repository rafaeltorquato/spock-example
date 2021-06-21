package com.torquato.spock.delivery.people.facade;

import com.torquato.spock.delivery.people.facade.dto.PeopleDTO;
import com.torquato.spock.delivery.people.facade.dto.SearchParametersDTO;
import com.torquato.spock.delivery.shared.ResultPageDTO;

public interface PeopleFacade {

    ResultPageDTO<PeopleDTO> search(SearchParametersDTO searchParameters);

}
