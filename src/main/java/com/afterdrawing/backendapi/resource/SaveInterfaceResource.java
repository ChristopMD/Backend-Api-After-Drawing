package com.afterdrawing.backendapi.resource;

import lombok.Data;

import javax.persistence.Column;
import javax.validation.constraints.Size;

@Data
public class SaveInterfaceResource {
    @Size(max = 50)
    @Column(name = "interfaceName", nullable = false)
    private String interfaceName;
}
