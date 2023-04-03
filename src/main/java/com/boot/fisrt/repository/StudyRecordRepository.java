package com.boot.fisrt.repository;

import com.boot.fisrt.domain.StudyRecord;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface StudyRecordRepository extends JpaRepository<StudyRecord,Long> {

    @Query(value="select * from Member m , study_record r where m.member_id = r.member_id", nativeQuery = true)
    List<StudyRecord> selectAll();

}
