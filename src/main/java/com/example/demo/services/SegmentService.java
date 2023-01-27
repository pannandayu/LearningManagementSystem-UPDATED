package com.example.demo.services;


import com.example.demo.models.Segment;

import java.util.ArrayList;
import java.util.List;

public interface SegmentService {
    public List<Segment> getAllSegment();
    public Segment getSegmentById(Integer id);
    public Boolean saveSegment(Segment segment);
    public Boolean deleteSegment(Integer id);
    public List<Integer> getSegmentIdRangeByCourseId(Integer courseId);
    public List<Integer> filterSegmentIdRange(Integer courseId);
}
