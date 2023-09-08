package com.choi.mvcboard.memberRepository;

import com.choi.mvcboard.memberDomain.MemberEntity;
import org.springframework.data.jpa.repository.JpaRepository;

public interface MemberRepository extends JpaRepository<MemberEntity, String> {
    MemberEntity findByEmail(String email);
}

