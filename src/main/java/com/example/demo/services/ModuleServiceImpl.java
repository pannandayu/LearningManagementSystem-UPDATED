package com.example.demo.services;

import com.example.demo.models.Module;
import com.example.demo.repositories.ModuleRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.Collections;
import java.util.List;

@Service
public class ModuleServiceImpl implements ModuleService{

    @Autowired
    ModuleRepository moduleRepository;

    @Override
    public List<Module> getAllModule() {
        return moduleRepository.findAll();
    }

    @Override
    public Module getModuleById(Integer id) {
        return moduleRepository.findById(id).orElseThrow(()-> new IllegalArgumentException("Module Not Found"));
    }

    @Override
    public Boolean saveModule(Module module) {
        moduleRepository.save(module);
        return moduleRepository.findById(module.getId()).isPresent();
    }

    @Override
    public Boolean deleteModule(Integer id) {
        moduleRepository.deleteById(id);
        return moduleRepository.findById(id).isEmpty();
    }

    @Override
    public List<Integer> getModuleIdRangeByCourseId(Integer segmentId) {
        return null;
    }

    @Override
    public List<Module> getModuleBySegmentId(Integer id) {
        return moduleRepository.findModuleBySegmentId(id);
    }

    @Override
    public List<Integer> filterModuleIdRange(Integer courseId) {
        List<Integer> listofModuleId = moduleRepository.getModuleIdRangeByCourseId(courseId);
        List<Integer> list = new ArrayList<>();

        list.add(Collections.min(listofModuleId));
        list.add(Collections.max(listofModuleId));

        return list;
    }

}
