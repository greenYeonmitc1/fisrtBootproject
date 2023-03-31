package com.boot.fisrt.service;

import com.boot.fisrt.domain.Member;
import com.boot.fisrt.repository.MemberListRepository;
import com.boot.fisrt.repository.MemberRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

public class MemberService {

    //private final MemberRepository dao = new MemberListRepository();
    //@Autowired
    private final MemberRepository dao;
    @Autowired
    public MemberService(MemberRepository dao){
        this.dao = dao;
    }

    // controller에서 사용할꺼니깐 public
    // 회원가입
    public Long join(Member member){
        validateMemberId(member);
        dao.save(member);
        return member.getId();
    }
    // 아이디중복체크
    private void validateMemberId(Member member){
        if (dao.findByLoginId(member.getLoginId()) != null){
            throw new IllegalStateException("이미 존재하는 회원아이디입니다");
        }
    }
    // 전체회원조회
    public List<Member> findAllMembers(){
        List<Member> list = dao.findAll();
        if(list.isEmpty()) throw new IllegalStateException("데이터가 존재하지않습니다");
        return list;
    }
    // 회원한명탈퇴
    public void removeMember(Long id){
        dao.delete(id);
    }
}
