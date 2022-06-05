package com.afterdrawing.backendapi.resource;

import lombok.Data;

import javax.persistence.Column;
import javax.validation.constraints.Size;


@Data
public class SaveElementResource {
    @Size(max = 15)
    @Column(name = "positionX", nullable = false)
    private String positionX;

    @Size(max = 15)
    @Column(name = "positionY", nullable = false)
    private String positionY;

    @Column(name = "element_type", nullable = false)
    @Size(max = 25)
    private String elementType;

}
