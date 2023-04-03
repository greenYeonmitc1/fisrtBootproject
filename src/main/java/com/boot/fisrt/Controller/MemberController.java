package com.boot.fisrt.Controller;

import com.boot.fisrt.Form.MemberForm;
import com.boot.fisrt.domain.Member;
import com.boot.fisrt.domain.Role;
import com.boot.fisrt.repository.MemberListRepository;
import com.boot.fisrt.service.MemberService;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@Slf4j
@Controller
@RequestMapping("/member")  // member 라고 하는 url 요청은 모두 memberController
public class MemberController {

    //private final MemberService service = new MemberService();
    private final MemberService service;
    @Autowired
    MemberController(MemberService memberService){
        this.service = memberService;
    }
    @GetMapping
    public String createForm(){
        return "members/joinForm";
    }
    @PostMapping
    public String create(@ModelAttribute MemberForm form , Model model){
        Member member = Member.builder()
                .loginId(form.getId()).
                password(form.getPw()).
                name(form.getName()).
                role(Role.STUDENT)
                .build();
        //service.join(member);
        try {
            service.join(member);
        }catch(IllegalStateException e){
            model.addAttribute("msg" , e.getMessage());
            return "members/joinForm";
        }
        log.info(" join member={}" , member);
        return "redirect:/member/members";
    }

    @GetMapping("/members")  // member/members
    public String getMemberList(Model model){
        List<Member> list = null;
        try{
            list = service.findAllMembers();
        }catch(IllegalStateException e){
           // return "redirect:/member";  // createForm();
            return "members/joinForm";
        }

        model.addAttribute("list",list);

        return "members/list";
    }
// controller -> service -> memberListRepository(-> memberRepository)
    @GetMapping("{keyId}")
    public String deleteMember(@PathVariable Long keyId){
        log.info("delete member id ={}" ,keyId );
        service.removeMember(keyId);
        return "redirect:/member/members";
    }

    @ResponseBody
    @DeleteMapping("{keyId}")
    public String deleteMemberAjax(@PathVariable Long keyId){
        log.info("delete member id ={}" ,keyId );
        service.removeMember(keyId);
        return "ok";
    }

}
