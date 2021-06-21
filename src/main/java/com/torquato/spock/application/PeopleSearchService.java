package com.torquato.spock.application;

import com.torquato.spock.domain.model.people.People;
import com.torquato.spock.domain.model.people.SearchParameters;
import com.torquato.spock.domain.shared.ResultPage;

import javax.validation.Valid;
import javax.validation.constraints.NotNull;

public interface PeopleSearchService {

    @NotNull ResultPage<People> search(@Valid SearchParameters searchParameters);

}
