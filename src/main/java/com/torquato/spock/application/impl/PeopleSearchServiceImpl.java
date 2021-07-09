package com.torquato.spock.application.impl;

import com.torquato.spock.application.PeopleSearchService;
import com.torquato.spock.domain.model.people.People;
import com.torquato.spock.domain.model.people.PeopleRepository;
import com.torquato.spock.domain.model.people.SearchParameters;
import com.torquato.spock.domain.shared.ResultPage;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import org.springframework.validation.annotation.Validated;

@Validated
@Service
@RequiredArgsConstructor
public class PeopleSearchServiceImpl implements PeopleSearchService {

    private final PeopleRepository peopleRepository;

    @Override
    public ResultPage<People> search(SearchParameters searchParameters) {
        return peopleRepository.search(searchParameters);
    }

}
