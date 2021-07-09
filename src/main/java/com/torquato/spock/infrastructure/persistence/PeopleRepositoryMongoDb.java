package com.torquato.spock.infrastructure.persistence;

import com.torquato.spock.domain.model.people.People;
import com.torquato.spock.domain.model.people.PeopleRepository;
import com.torquato.spock.domain.model.people.SearchParameters;
import com.torquato.spock.domain.shared.ResultPage;
import lombok.AllArgsConstructor;
import lombok.RequiredArgsConstructor;
import org.springframework.data.domain.Pageable;
import org.springframework.data.mongodb.core.MongoTemplate;
import org.springframework.data.mongodb.core.query.Criteria;
import org.springframework.data.mongodb.core.query.Query;
import org.springframework.stereotype.Repository;

import java.util.List;
import java.util.Optional;
import java.util.function.Predicate;

@Repository
@RequiredArgsConstructor
public class PeopleRepositoryMongoDb implements PeopleRepository {

    private final MongoTemplate mongoTemplate;

    @Override
    public ResultPage<People> search(SearchParameters searchParameters) {
        Pageable pageable = Pageable.ofSize(searchParameters.getPageSize())
                .withPage(searchParameters.getPage() - 1);
        Query query = new Query().with(pageable);

        Optional.ofNullable(searchParameters.getName())
                .filter(Predicate.not(String::isBlank))
                .ifPresent(name -> query.addCriteria(Criteria.where("name").is(name)));
        Optional.ofNullable(searchParameters.getSurname())
                .filter(Predicate.not(String::isBlank))
                .ifPresent(surname -> query.addCriteria(Criteria.where("surname").is(surname)));

        List<People> elements = mongoTemplate.find(query, People.class);
        long totalElements = mongoTemplate.count(query, People.class);

        return new InternalResultPage(totalElements, elements);
    }

    @AllArgsConstructor
    private static class InternalResultPage extends ResultPage<People> {

        private InternalResultPage(Long totalElements, List<People> elements) {
            super(totalElements, elements);
        }

    }

}
