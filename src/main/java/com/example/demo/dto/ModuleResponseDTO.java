package com.example.demo.dto;

import lombok.Getter;
import lombok.Setter;

import java.util.List;

@Getter
@Setter
public class ModuleResponseDTO {

    private Integer finishedModuleCount;
    private Integer moduleRemainingCount;
    private Integer totalModuleCount;

}
