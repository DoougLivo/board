package com.choi.mvcboard.boardDomain;

import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.time.LocalDateTime;

@Entity
@AllArgsConstructor
@NoArgsConstructor
@Data
@Table(name = "board_tb")
public class BoardEntity {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;
    private String title;
    @Column(nullable = false)
    private String writer;
    private String contents;
    @Column(nullable = false, updatable = false)
    private LocalDateTime regdate = LocalDateTime.now();
    private LocalDateTime updateDate;
    private int hit;

    public static BoardEntity dtoToEntity(BoardDto dto) {
        BoardEntity entity = new BoardEntity();
        entity.setId(dto.getId());
        entity.setTitle(dto.getTitle());
        entity.setWriter(dto.getWriter());
        entity.setContents(dto.getContents());
        entity.setRegdate(dto.getRegdate());
//        System.out.println("asd : " + dto.getUpdateDate());
        entity.setUpdateDate(dto.getUpdateDate());
//        System.out.println("qwe : " + entity.getUpdateDate());
        entity.setHit(dto.getHit());
        return entity;
    }
}
