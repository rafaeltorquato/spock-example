package com.torquato.spock.delivery.people.facade.internal.mapper;

import com.torquato.spock.delivery.people.facade.dto.PeopleDTO;
import com.torquato.spock.delivery.people.facade.dto.SearchParametersDTO;
import com.torquato.spock.delivery.shared.ResultPageDTO;
import com.torquato.spock.domain.model.people.People;
import com.torquato.spock.domain.model.people.SearchParameters;
import com.torquato.spock.domain.shared.ResultPage;
import org.mapstruct.Mapper;

@Mapper(
        componentModel = "spring",
        uses = {ObjectIdMapper.class}
)
public interface PeopleMapper {

    SearchParameters fromDTO(SearchParametersDTO dto);

    ResultPageDTO<PeopleDTO> toDTO(ResultPage<People> result);

}
