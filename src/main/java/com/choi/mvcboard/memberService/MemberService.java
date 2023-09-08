package com.choi.mvcboard.memberService;

import com.choi.mvcboard.memberDomain.MemberDto;
import com.choi.mvcboard.memberDomain.MemberEntity;
import com.choi.mvcboard.memberRepository.MemberRepository;
import org.springframework.stereotype.Service;

import java.util.Optional;

@Service
public class MemberService {
    private final MemberRepository memberDao;

    public MemberService(MemberRepository memberDao) {
        this.memberDao = memberDao;
    }

    public void joinMember(MemberDto dto) {
        MemberEntity entity = new MemberEntity();
        memberDao.save(entity.dtoToEntity(dto));
    }

    public Optional<MemberEntity> loginMember(String userId, String passwd) {
        if (memberDao.findById(userId) == null)
            return null;
        else {
            Optional<MemberEntity> entity = memberDao.findById(userId);
            if (entity.get().getPasswd().equals(passwd)) {
                return entity;
            }
            return null;
        }
    }

    public MemberEntity findId(String userName, String email) {
        if (memberDao.findById(email) == null)
            return null;
        else if (memberDao.findById(email) != null) {
            MemberEntity entity = memberDao.findByEmail(email);
//            System.out.println("service memberEntity : " + entity);
            if (entity.getUserName().equals(userName)) {
                return entity;
            }
            return null;
        }
        return null;
    }

    public Optional<MemberEntity> findPw(String userId, String email) {
        if (memberDao.findById(userId) == null)
            return null;
        else {
            Optional<MemberEntity> entity = memberDao.findById(userId);
            if (entity.get().getEmail().equals(email)) {
                return entity;
            }
            return null;
        }
    }

}
