package com.torquato.spock.domain.model.people;

import com.torquato.spock.domain.shared.PageParameters;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Getter
@Setter
@NoArgsConstructor
public class SearchParameters extends PageParameters {

    private String name;
    private String surname;

}
