package com.example.demo.dto;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
public class SegmentResponseDTO {

    private Integer segmentId;

    private String segmentName;

    private String segmentDescription;
    private Integer courseId;

}
