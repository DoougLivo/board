package com.choi.mvcboard.memberDomain;

import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.Id;
import jakarta.persistence.Table;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.time.LocalDateTime;

@Entity
@AllArgsConstructor
@NoArgsConstructor
@Data
@Table(name = "member_tb")
public class MemberEntity {
    @Id
    @Column(length = 30, unique = true)
    private String userId;
    @Column(length = 30)
    private String passwd;
    @Column(length = 30)
    private String userName;
    @Column(nullable = false, unique = true, length = 30)
    private String email;
    @Column(length = 13)
    private String phone;
    @Column(length = 10)
    private String birth;
    @Column(length = 1)
    private String gender;
    @Column(nullable = false, updatable = false)
    private LocalDateTime userRegdate = LocalDateTime.now();
    private LocalDateTime userUpdateDate;

    public static MemberEntity dtoToEntity(MemberDto dto) {
        MemberEntity entity = new MemberEntity();
        entity.setUserId(dto.getUserId());
        entity.setPasswd(dto.getPasswd());
        entity.setUserName(dto.getUserName());
        entity.setEmail(dto.getEmail());
        entity.setPhone(dto.getPhone());
        entity.setBirth(dto.getBirth());
        entity.setGender(dto.getGender());
        entity.setUserRegdate(dto.getUserRegdate());
        entity.setUserUpdateDate(dto.getUserUpdateDate());
        return entity;
    }
}
