package com.afterdrawing.backendapi.resource;

import com.afterdrawing.backendapi.core.entity.Wireframe;
import lombok.Data;

@Data
public class InterfaceResource {
    private Long id;
    private String interfaceName;
    private Wireframe wireframe;
}
