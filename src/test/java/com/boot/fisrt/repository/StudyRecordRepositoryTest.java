package com.boot.fisrt.repository;

import com.boot.fisrt.Form.StudyForm;
import com.boot.fisrt.domain.Member;
import com.boot.fisrt.domain.Role;
import com.boot.fisrt.service.StudyRecordService;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.annotation.Rollback;
import org.springframework.test.context.junit.jupiter.SpringExtension;
import org.springframework.transaction.annotation.Transactional;

import javax.persistence.EntityManager;

import java.time.LocalDate;

@ExtendWith(SpringExtension.class)
@Transactional
@SpringBootTest
@Rollback(value = false)
class StudyRecordRepositoryTest {
    @Autowired
    EntityManager em;
    @Autowired
    StudyRecordService service;

    @Test
    public void 기록추가(){
        Member member1 = Member.builder()
                .loginId("test8").
                password("8888").
                name("테스트8").
                role(Role.STUDENT)
                .build();

        em.persist(member1);
        em.flush();
        em.clear();
        Member member = em.find(Member.class, member1.getId());
        StudyForm form = StudyForm.builder().
                memberId(member.getId()).
                studyDay(LocalDate.now().toString()).
                startTime("18:29").
                studyMins(45).
                build();

        service.saveRecord(form,member1);
    }

}