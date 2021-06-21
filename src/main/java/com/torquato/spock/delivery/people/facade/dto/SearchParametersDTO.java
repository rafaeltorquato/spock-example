package com.torquato.spock.delivery.people.facade.dto;

import com.torquato.spock.delivery.shared.PageParametersDTO;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import lombok.experimental.SuperBuilder;

@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
@SuperBuilder
public class SearchParametersDTO extends PageParametersDTO {

    private String name;
    private String surname;

}
