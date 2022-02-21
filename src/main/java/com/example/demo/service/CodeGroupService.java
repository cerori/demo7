package com.example.demo.service;

import com.example.demo.domain.CodeGroup;
import org.springframework.stereotype.Service;

import java.util.List;

public interface CodeGroupService {

    public void register(CodeGroup codeClass) throws Exception;

    public CodeGroup read(String classCode) throws Exception;

    public void modify(CodeGroup codeClass) throws Exception;

    public void remove(String classCode) throws Exception;

    public List<CodeGroup> list(CodeGroup codeClass) throws Exception;




}
