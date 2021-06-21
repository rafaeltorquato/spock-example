package com.torquato.spock.domain.model.people;

import com.torquato.spock.domain.shared.ResultPage;

public interface PeopleRepository {

    ResultPage<People> search(SearchParameters searchParameters);

}
