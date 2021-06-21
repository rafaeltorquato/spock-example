package com.torquato.spock.delivery.shared;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import java.io.Serializable;
import java.util.List;

@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
public class ResultPageDTO<T> implements Serializable {

    private Long totalElements;
    private List<T> elements;

}
