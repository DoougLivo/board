package com.choi.mvcboard.memberDomain;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.time.LocalDateTime;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class MemberDto {
    private String userId;
    private String passwd;
    private String userName;
    private String email;
    private String phone;
    private String birth;
    private String gender;
    private LocalDateTime userRegdate = LocalDateTime.now();
    private LocalDateTime userUpdateDate;


    public static MemberDto entityToDto(MemberEntity entity) {
        MemberDto dto = new MemberDto();
        dto.setUserId(entity.getUserId());
        dto.setPasswd(entity.getPasswd());
        dto.setUserName(entity.getUserName());
        dto.setEmail(entity.getEmail());
        dto.setPhone(entity.getPhone());
        dto.setBirth(entity.getBirth());
        dto.setGender(entity.getGender());
        dto.setUserRegdate(entity.getUserRegdate());
        dto.setUserUpdateDate(entity.getUserUpdateDate());
        return dto;
    }

}
