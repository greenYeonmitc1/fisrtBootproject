package com.boot.fisrt;

import static org.junit.jupiter.api.Assertions.*;

import com.boot.fisrt.config.SpringConfig;
import com.boot.fisrt.domain.Member;
import com.boot.fisrt.domain.Role;
import com.boot.fisrt.repository.MemberRepository;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.context.annotation.Import;
import org.springframework.test.annotation.Rollback;
import org.springframework.test.context.junit.jupiter.SpringExtension;
import org.springframework.transaction.TransactionManager;
import org.springframework.transaction.annotation.Transactional;

import javax.persistence.EntityManager;
import javax.sql.DataSource;
@ExtendWith(SpringExtension.class)
@Transactional
@SpringBootTest
@Rollback(value = false)
public class DBconfigTest {
//    @Autowired
//    DataSource dataSource;  // db 객체
//    @Autowired
//    TransactionManager transactionManager; // db연결 객체
    @Autowired
    EntityManager entityManager;

    @Autowired
    MemberRepository memberRepository;
    @Test
    void db연동(){
        assertNotNull(entityManager);
    }


    @Test
    void 회원가입(){
        //setData();
        Member member1 = Member.builder()
                .loginId("test8").
                password("8888").
                name("테스트8").
                role(Role.STUDENT)
                .build();

        memberRepository.save(member1);

    }
}
