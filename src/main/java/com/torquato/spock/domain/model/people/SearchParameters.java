package com.torquato.spock.domain.model.people;

import com.torquato.spock.domain.shared.PageParameters;
import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
public class SearchParameters extends PageParameters {

    private String name;
    private String surname;

}
