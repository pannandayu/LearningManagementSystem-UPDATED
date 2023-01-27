package com.example.demo.repositories;

import com.example.demo.models.Module;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface ModuleRepository extends JpaRepository<Module, Integer> {

    @Query(value = "SELECT distinct m.* FROM tb_tr_module m \n"+
            "JOIN tb_tr_segment s \n" +
            "where m.segment_id = ?1 order by id", nativeQuery=true)
    public List<Module> findModuleBySegmentId(Integer id);

    @Query(value = """
           select id
           from tb_tr_module ttm
           where ttm.segment_id = ?1
            """, nativeQuery = true)
    public List<Integer> getModuleIdRangeByCourseId(Integer segmentId);
}
