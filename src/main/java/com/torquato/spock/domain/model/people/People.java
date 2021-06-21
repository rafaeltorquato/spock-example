package com.torquato.spock.domain.model.people;

import lombok.Getter;
import lombok.Setter;
import org.bson.types.ObjectId;
import org.hibernate.validator.constraints.Length;
import org.springframework.data.annotation.Id;
import org.springframework.data.mongodb.core.mapping.Document;

import javax.validation.constraints.NotNull;

@Getter
@Setter
@Document(collection = "peoples")
public class People {

    @Id
    private ObjectId id;
    @NotNull
    @Length(min = 3, max = 20)
    private String name;
    @NotNull
    @Length(min = 3, max = 20)
    private String surname;

}
