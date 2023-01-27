package com.example.demo.services;

import com.example.demo.models.Employee;
import com.example.demo.models.Module;
import com.example.demo.models.Progress;
import com.example.demo.repositories.ProgressRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class ProgressServiceImpl implements ProgressService{

    @Autowired
    ProgressRepository progressRepository;

    @Override
    public List<Progress> getAllProgress() {
        return progressRepository.findAll();
    }

    @Override
    public Progress getProgressById(Integer id) {
        return progressRepository.findById(id)
                .orElseThrow(() -> new IllegalArgumentException("Progress Not Found"));
    }

    @Override
    public Boolean saveProgress(Progress progress) {
        progressRepository.save(progress);
        return progressRepository.findById(progress.getId()).isPresent();
    }

    @Override
    public Boolean deleteProgress(Integer id) {
        progressRepository.deleteById(id);
        return progressRepository.findById(id).isEmpty();
    }

    @Override
    public void finishedModule(Boolean finished, Integer id, Integer empid) {
    }

    @Override
    public Boolean changeIsfinished(Integer moduleId, Integer empId) {
        progressRepository.finishedModule(true, moduleId, empId);;
        return true;
    }

    @Override
    public Progress checkFinishedModule(Integer empId, Integer moduleId) {
        return progressRepository.checkFinishedModule(empId, moduleId);
    }
}
