package com.example.demo.controller;

import com.example.demo.domain.CodeDetail;
import com.example.demo.dto.CodeLabelValue;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.aspectj.apache.bcel.classfile.Code;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@Slf4j
@RequiredArgsConstructor
@RestController
@RequestMapping("/codedetails")
public class CodeDetailController {

    private final CodeDetailService codeDetailService;

    /**
     * 상세 조회
     * @param groupCode
     * @param codeValue
     * @return
     */
    @GetMapping("/{groupCode}/{codeValue}")
    public ResponseEntity<CodeDetail> read(@PathVariable("groupCode") String groupCode, @PathVariable("codeValue") String codeValue) throws Exception {
        CodeDetail codeDetail = new CodeDetail();
        codeDetail.setGroupCode(groupCode);
        codeDetail.setCodeValue(codeValue);

        return new ResponseEntity<>(codeDetailService.read(codeDetail), HttpStatus.OK);
    }

    /**
     * 목록 조회
     */
    @GetMapping("/list")
    public ResponseEntity<List<CodeDetail>> list() throws Exception {
        return new ResponseEntity<>(codeDetailService.list(), HttpStatus.OK);
    }

    /**
     * 등록
     */
    @PostMapping("/register")
    public ResponseEntity<CodeDetail> register(@Validated @RequestBody CodeDetail codeDetail) throws Exception {
        codeDetailService.register(codeDetail);

        return new ResponseEntity<>(codeDetail, HttpStatus.OK);
    }

    /**
     *  삭제
     */
    @DeleteMapping("/{groupCode}/{codeValue}")
    public ResponseEntity<Void> remove(@PathVariable("groupCode") String groupCode, @PathVariable("codeValue") String codeValue) throws Exception {
        CodeDetail codeDetail = new CodeDetail();
        codeDetail.setGroupCode(groupCode);
        codeDetail.setCodeValue(codeValue);

        codeDetailService.remove(codeDetail);

        return new ResponseEntity<>(HttpStatus.NO_CONTENT);
    }

    /**
     * 수정
     */
    @PutMapping("/{groupCode}/{codeValue}")
    public ResponseEntity<CodeDetail> modify(@PathVariable("groupCode") String groupCode,
                                             @PathVariable("codeValue") String codeValue,
                                             @Validated @RequestBody CodeDetail codeDetail) throws Exception {
        codeDetail.setGroupCode(groupCode);
        codeDetail.setCodeValue(codeValue);

        codeDetailService.modify(codeDetail);

        return new ResponseEntity<>(codeDetail, HttpStatus.OK);
    }

    @GetMapping("/codeGroup")
    public ResponseEntity<List<CodeLabelValue>> codeGroupList() throws Exception {
        return new ResponseEntity<>(codeDetailService.getCodeGroupList(), HttpStatus.OK);
    }


}
