package com.example.demo.services;

import com.example.demo.models.Segment;
import com.example.demo.repositories.SegmentRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.Collections;
import java.util.List;

@Service
public class SegmentServiceImpl implements SegmentService{

    @Autowired
    SegmentRepository segmentRepository;

    @Override
    public List<Segment> getAllSegment() {
        return segmentRepository.findAll();
    }

    @Override
    public Segment getSegmentById(Integer id) {
        return segmentRepository.findById(id)
                .orElseThrow(()-> new IllegalArgumentException("Segment Not Found"));
    }

    @Override
    public Boolean saveSegment(Segment segment) {
        segmentRepository.save(segment);
        return segmentRepository.findById(segment.getId()).isPresent();
    }

    @Override
    public Boolean deleteSegment(Integer id) {
        segmentRepository.deleteById(id);
        return segmentRepository.findById(id).isEmpty();
    }

    @Override
    public List<Integer> getSegmentIdRangeByCourseId(Integer courseId) {
        return segmentRepository.getSegmentIdRangeByCourseId(courseId);
    }

    @Override
    public List<Integer> filterSegmentIdRange(Integer courseId) {
        List<Integer> listofSegmentId = segmentRepository.getSegmentIdRangeByCourseId(courseId);
        List<Integer> list = new ArrayList<>();

        list.add(Collections.min(listofSegmentId));
        list.add(Collections.max(listofSegmentId));

        return list;
    }
}
