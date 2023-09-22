package com.choi.mvcboard.memberController;

import com.choi.mvcboard.memberDomain.MemberDto;
import com.choi.mvcboard.memberDomain.MemberEntity;
import com.choi.mvcboard.memberService.MemberService;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpSession;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.ResponseBody;

import java.util.HashMap;
import java.util.Map;
import java.util.Optional;

@Controller
public class MemberController {

    private final MemberService memberService;

    public MemberController(MemberService memberService) {
        this.memberService = memberService;
    }

    @GetMapping("/join")
    public String goJoin() {
        return "member/join";
    }

    @ResponseBody
    @PostMapping("/joinMember")
    public Map<String, String> joinMember(MemberDto dto) {
        Map<String, String> map = new HashMap<>();
        try {
            memberService.joinMember(dto);
            map.put("ok", "회원가입 완료");
            return map;
        } catch (Exception e) {
            map.put("fail", "실패");
            e.printStackTrace();
            return map;
        }
    }

    @GetMapping("/login")
    public String goLogin() {
        return "member/login";
    }

    @ResponseBody
    @PostMapping("/loginMember")
    public Map<String, String> loginMember(String userId, String passwd, HttpServletRequest request) {
        Map<String, String> map = new HashMap<>();
        try {
            Optional<MemberEntity> entity = memberService.loginMember(userId, passwd);
            System.out.println("로그인 테스트 : " + entity);
            if (entity == null)
                map.put("not", "아이디나 비밀번호가 틀립니다");
            else if (entity != null) {
                HttpSession sesstion = request.getSession(true);
                int sTime = 60*30;
                sesstion.setMaxInactiveInterval(sTime);
                sesstion.setAttribute("member", entity);
                sesstion.setAttribute("userName", entity.get().getUserName());
                map.put("ok", "로그인이 되었습니다");
            }
            return map;
        } catch (Exception e) {
            map.put("fail", "실패");
            e.printStackTrace();
            return map;
        }
    }

    @GetMapping("/logout")
    public String logout(HttpSession session) {
        session.invalidate();
        System.out.println("로그아웃 되었습니다");
        return "redirect:/boardList";
    }

    @GetMapping("/findId")
    public String goFindId() {
        return "member/findId";
    }

    @ResponseBody
    @PostMapping("/findId_proc")
    public Map<String, String> findId_proc(String userName, String email) {
        Map<String, String> map = new HashMap<>();
        try {
            MemberEntity entity = memberService.findId(userName, email);
            System.out.println("controller getUserName : " + entity.getUserName());
            if (entity == null)
                map.put("not", "아이디가 없습니다");
            else if (entity != null) {
                System.out.println("find getUserId : " + entity.getUserId());
                map.put("ok", entity.getUserId());
            }
            return map;
        } catch (Exception e) {
            map.put("fail", "아이디나 이메일이 틀립니다");
            e.printStackTrace();
            return map;
        }
    }

    @GetMapping("/findPw")
    public String goFindPw() {
        return "member/findPw";
    }

    @ResponseBody
    @PostMapping("/findPw_proc")
    public Map<String, String> findPw_proc(String userId, String email) {
        Map<String, String> map = new HashMap<>();
        try {
            Optional<MemberEntity> entity = memberService.findPw(userId, email);
            System.out.println("controller getPasswd : " + entity.get().getPasswd());
            if (entity == null)
                map.put("not", "아이디나 이메일이 틀립니다");
            else if (entity != null) {
                map.put("ok", entity.get().getPasswd());
            }
            return map;
        } catch (Exception e) {
            map.put("fail", "실패");
            e.printStackTrace();
            return map;
        }
    }

    @ResponseBody
    @PostMapping("/idCheck")
    public Map<String, String> idCheck(String userId) {
        Map<String, String> map = new HashMap<>();
        try {
            int result = memberService.idCheck(userId);
            System.out.println("idcheck controller : " + result);
            if (result == 1)
                map.put("ok", "사용 가능한 아이디입니다");
            else if (result == -1) {
                map.put("not", "이미 사용중인 아이디입니다");
            }
            return map;
        } catch (Exception e) {
            map.put("fail", "실패");
            e.printStackTrace();
            return map;
        }
    }
}
