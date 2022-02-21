package com.example.demo.repository;

import com.example.demo.domain.CodeDetail;
import com.example.demo.domain.CodeDetailId;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;

import java.util.List;

public interface CodeDetailRepository extends JpaRepository<CodeDetail, CodeDetailId> {

    @Query("select max(cd.sortSeq) from CodeDetail cd where cd.groupCode = ?1")
    List<Object[]> getMaxSortSeq(String groupCode);
}
