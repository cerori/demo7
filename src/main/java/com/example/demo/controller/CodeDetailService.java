package com.example.demo.controller;

import com.example.demo.domain.CodeDetail;
import com.example.demo.dto.CodeLabelValue;

import java.util.List;

public interface CodeDetailService {

    CodeDetail read(CodeDetail codeDetail) throws Exception;

    List<CodeDetail> list() throws Exception;

    void register(CodeDetail codeDetail) throws Exception;

    void remove(CodeDetail codeDetail) throws Exception;

    void modify(CodeDetail codeDetail) throws Exception;

    List<CodeLabelValue> getCodeGroupList() throws Exception;
}
