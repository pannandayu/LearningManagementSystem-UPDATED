package com.example.demo.repositories;

import com.example.demo.models.Segment;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface SegmentRepository extends JpaRepository<Segment, Integer> {

    @Query(value = "SELECT s.* FROM tb_tr_segment s JOIN tb_tr_course c on s.course_id = c.id WHERE s.course_id = ?1", nativeQuery=true)
    public List<Segment> findSegmentByCourseId(Integer id);

    @Query(value = """
           select id
           from tb_tr_segment tts
           where course_id = ?1
            """, nativeQuery = true)
    public List<Integer> getSegmentIdRangeByCourseId(Integer courseId);
}
