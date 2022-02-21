package com.example.demo.service;

import com.example.demo.controller.CodeDetailService;
import com.example.demo.domain.CodeDetail;
import com.example.demo.domain.CodeDetailId;
import com.example.demo.domain.CodeGroup;
import com.example.demo.dto.CodeLabelValue;
import com.example.demo.repository.CodeDetailRepository;
import com.example.demo.repository.CodeGroupRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.data.domain.Sort;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;

@RequiredArgsConstructor
@Service
public class CodeDetailServiceImpl implements CodeDetailService {

    private final CodeGroupRepository codeGroupRepository;
    private final CodeDetailRepository codeDetailRepository;

    /**
     * 상세조회
     * @param codeDetail
     * @return
     * @throws Exception
     */
    @Override
    public CodeDetail read(CodeDetail codeDetail) throws Exception {
        CodeDetailId codeDetailId = new CodeDetailId();
        codeDetailId.setGroupCode(codeDetailId.getGroupCode());
        codeDetailId.setCodeValue(codeDetail.getCodeValue());

        return codeDetailRepository.getById(codeDetailId);
    }

    @Override
    public List<CodeDetail> list() throws Exception {
        return codeDetailRepository.findAll(Sort.by(Sort.Direction.ASC, "groupCode", "codeValue"));
    }

    @Override
    public void register(CodeDetail codeDetail) throws Exception {
        String groupCode = codeDetail.getGroupCode();
        List<Object[]> rsList = codeDetailRepository.getMaxSortSeq(groupCode);

        Integer maxSortSeq = 0;
        if (rsList.size() > 0) {
            Object[] maxValues = rsList.get(0);
            System.out.println(maxValues);
            if (maxValues != null && maxValues.length > 0) {
                maxSortSeq = (Integer) maxValues[0];
            }
        }

        codeDetail.setSortSeq(maxSortSeq + 1);

        codeDetailRepository.save(codeDetail);
    }

    @Override
    public void remove(CodeDetail codeDetail) throws Exception {
        CodeDetailId codeDetailId = new CodeDetailId();
        codeDetailId.setGroupCode(codeDetail.getGroupCode());
        codeDetailId.setCodeValue(codeDetail.getCodeValue());

        codeDetailRepository.deleteById(codeDetailId);
    }

    @Override
    public void modify(CodeDetail codeDetail) throws Exception {
        CodeDetailId codeDetailId = new CodeDetailId();
        codeDetailId.setGroupCode(codeDetail.getGroupCode());
        codeDetailId.setCodeValue(codeDetail.getCodeValue());

        CodeDetail codeDetailEntity = codeDetailRepository.getById(codeDetailId);

        codeDetailEntity.setCodeValue(codeDetail.getCodeValue());
        codeDetailEntity.setCodeName(codeDetail.getCodeName());

        codeDetailRepository.save(codeDetailEntity);
    }

    @Override
    public List<CodeLabelValue> getCodeGroupList() throws Exception {
        List<CodeGroup> codeGroups = codeGroupRepository.findAll(Sort.by(Sort.Direction.ASC, "groupCode"));

        List<CodeLabelValue> codeGroupList = new ArrayList<CodeLabelValue>();

        for (CodeGroup codeGroup : codeGroups) {
            codeGroupList.add(new CodeLabelValue(codeGroup.getGroupCode(), codeGroup.getGroupName()));
        }

        return codeGroupList;
    }
}
