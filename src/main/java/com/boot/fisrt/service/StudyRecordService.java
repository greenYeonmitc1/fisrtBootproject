package com.boot.fisrt.service;

import com.boot.fisrt.Form.StudyForm;
import com.boot.fisrt.domain.Member;
import com.boot.fisrt.domain.StudyRecord;
import com.boot.fisrt.repository.StudyRecordRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
public class StudyRecordService {
    @Autowired
    StudyRecordRepository repository;
    public List<StudyRecord> getAllRecordList(){
       return repository.selectAll();
    }
    public StudyRecord getOneRecord(Long id){
        Optional<StudyRecord> list = repository.findById(id);
        if(list.isEmpty()){
            throw new IllegalStateException("기록데이터가 없습니다");
        }
        return list.get();
    }

    public void saveRecord(StudyForm form, Member member){
        StudyRecord record = StudyRecord.createRecord(form, member);
        repository.save(record);
        //StudyRecord rd = new StudyRecord();
    }
    public void updateRecord(StudyRecord record ,StudyForm form){
        StudyRecord newRecord = StudyRecord.modyfiyRecord(record, form);
        repository.save(newRecord);
    }
    public void deleteRecord(Long id){
        repository.deleteById(id);
    }
}
