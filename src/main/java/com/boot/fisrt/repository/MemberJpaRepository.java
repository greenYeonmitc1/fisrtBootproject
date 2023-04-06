package com.boot.fisrt.repository;

import com.boot.fisrt.domain.Member;
import lombok.extern.slf4j.Slf4j;

import javax.persistence.EntityManager;
import java.util.List;
@Slf4j
public class MemberJpaRepository implements MemberRepository{
    private final EntityManager em;
    public MemberJpaRepository(EntityManager em){
        this.em = em;
    }

    @Override
    public Member save(Member member) {
        em.persist(member);
        return member;
    }

    @Override
    public List<Member> findAll() {
        //List<Member> query = ;
        return em.createQuery("select m from Member m" , Member.class).getResultList();
    }

    @Override
    public Member findOne(Long id) {

        return em.find(Member.class, id);
    }

    @Override
    public Member findByLoginId(String loginId) {
        List<Member> members = em.createQuery("select m from Member m where m.loginId =:loginId" , Member.class)
                .setParameter("loginId" , loginId)
                .getResultList();
        // members 리스트 중에 첫번째 값 가져오고 값이 없으면 null 리턴
        return members.stream().findAny().orElse(null);
    }

    @Override
    public void delete(Long id) {
        // delete한 row 갯수를 리턴
        int deleteCnt = em.createQuery("delete from Member m where m.id=:id").setParameter("id", id).executeUpdate();
        if(deleteCnt > 0) log.info("member id={} 삭제 성공 " , id);
    }
}
