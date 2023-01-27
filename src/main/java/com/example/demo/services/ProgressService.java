package com.example.demo.services;

import com.example.demo.models.Employee;
import com.example.demo.models.Module;
import com.example.demo.models.Progress;
import org.springframework.data.repository.query.Param;

import java.util.List;

public interface ProgressService {
    public List<Progress> getAllProgress();
    public Progress getProgressById(Integer id);
    public Boolean saveProgress(Progress progress);
    public Boolean deleteProgress(Integer id);
    public void finishedModule(@Param("finished") Boolean finished,
                               @Param("id") Integer id,
                               @Param("empid") Integer empid);
    public Boolean changeIsfinished(Integer moduleId, Integer empId);
    public Progress checkFinishedModule(Integer empId, Integer moduleId);
}
