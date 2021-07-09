package com.torquato.spock.delivery.people.facade.internal;

import com.torquato.spock.application.PeopleSearchService;
import com.torquato.spock.delivery.people.facade.PeopleFacade;
import com.torquato.spock.delivery.people.facade.dto.PeopleDTO;
import com.torquato.spock.delivery.people.facade.dto.SearchParametersDTO;
import com.torquato.spock.delivery.people.facade.internal.mapper.PeopleMapper;
import com.torquato.spock.delivery.shared.ResultPageDTO;
import com.torquato.spock.domain.model.people.People;
import com.torquato.spock.domain.shared.ResultPage;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

@Service
@RequiredArgsConstructor
public class PeopleFacadeImpl implements PeopleFacade {

    private final PeopleMapper mapper;
    private final PeopleSearchService peopleSearchService;

    @Override
    public ResultPageDTO<PeopleDTO> search(SearchParametersDTO searchParameters) {
        ResultPage<People> result = peopleSearchService.search(mapper.fromDTO(searchParameters));
        return mapper.toDTO(result);
    }

}
