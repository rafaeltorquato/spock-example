package com.torquato.spock.domain.shared;

import lombok.Getter;
import lombok.Setter;

import javax.validation.constraints.Max;
import javax.validation.constraints.Min;
import javax.validation.constraints.NotNull;

@Getter
@Setter
public abstract class PageParameters {

    @NotNull
    @Min(1)
    private Integer page;
    @NotNull
    @Min(5)
    @Max(500)
    private Integer pageSize;

}
