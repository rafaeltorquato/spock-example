package com.torquato.spock.delivery.people.facade.dto;

import lombok.*;

@Getter
@Setter
@ToString
@EqualsAndHashCode
@AllArgsConstructor
@NoArgsConstructor
public class PeopleDTO {

    private String id;
    private String name;
    private String surname;

}
