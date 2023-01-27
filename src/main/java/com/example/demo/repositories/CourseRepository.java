package com.example.demo.repositories;

import com.example.demo.models.Course;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface CourseRepository extends JpaRepository<Course, Integer> {

    @Query(value = """
            select tts.name, count(*) as module_count
            from tb_tr_course ttc
            join tb_tr_segment tts
            on ttc.id = tts.course_id
            join tb_tr_module tmm
            on tts.id = tmm.segment_id
            where ttc.id = ?1
            group by 1
            """, nativeQuery = true)
    List<String> getModuleCountPerSegment(Integer id);

    @Query(value = """
            with cte as
            (
                select tts.name, count(*) as module_count
                from tb_tr_course ttc
                join tb_tr_segment tts
                on ttc.id = tts.course_id
                join tb_tr_module tmm
                on tts.id = tmm.segment_id
                where ttc.id = ?1
                group by 1
            )
            select sum(module_count) as total_module
            from cte
            """, nativeQuery = true)
    Integer getModuleRemainingPerCourse(Integer id);

    @Query(value = """
            select id
            from tb_tr_course ttc
            where name = ?1
            """, nativeQuery = true)
    Integer getCourseIdByName(String courseName);
}
