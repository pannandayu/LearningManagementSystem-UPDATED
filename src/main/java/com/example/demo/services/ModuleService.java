package com.example.demo.services;

import com.example.demo.models.Module;

import java.util.List;

public interface ModuleService {

    public List<Module> getAllModule();
    public List<Module> getModuleBySegmentId(Integer id);
    public Module getModuleById(Integer id);
    public Boolean saveModule(Module module);
    public Boolean deleteModule(Integer id);
    public List<Integer> getModuleIdRangeByCourseId(Integer segmentId);
    public List<Integer> filterModuleIdRange(Integer courseId);
}
