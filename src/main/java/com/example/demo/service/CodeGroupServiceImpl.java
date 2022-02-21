package com.example.demo.service;

import com.example.demo.domain.CodeGroup;
import com.example.demo.repository.CodeGroupRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.data.domain.Sort;
import org.springframework.stereotype.Service;

import java.util.List;

@RequiredArgsConstructor
@Service
public class CodeGroupServiceImpl implements CodeGroupService {

    private final CodeGroupRepository repository;

    @Override
    public void register(CodeGroup codeClass) throws Exception {
        repository.save(codeClass);
    }

    @Override
    public CodeGroup read(String classCode) throws Exception {
        return repository.getOne(classCode);
    }

    @Override
    public void modify(CodeGroup codeClass) throws Exception {
        CodeGroup codeGroupEntity = repository.getOne(codeClass.getGroupCode());

        codeGroupEntity.setGroupName(codeGroupEntity.getGroupName());

        repository.save(codeGroupEntity);
    }

    @Override
    public void remove(String classCode) throws Exception {
        repository.deleteById(classCode);
    }

    @Override
    public List<CodeGroup> list(CodeGroup codeClass) throws Exception {
        return repository.findAll(Sort.by(Sort.Direction.DESC, "groupCode"));
    }
}
