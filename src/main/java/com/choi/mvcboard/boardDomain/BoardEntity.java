package com.choi.mvcboard.boardDomain;

import lombok.*;

import javax.persistence.*;
import java.util.Date;

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
    private Date regdate;
    private int hit;

    public static BoardEntity dtoToEntity(BoardDto dto) {
        BoardEntity entity = new BoardEntity();
        entity.setId(dto.getId());
        entity.setTitle(dto.getTitle());
        entity.setWriter(dto.getWriter());
        entity.setContents(dto.getContents());
        entity.setRegdate(dto.getRegdate());
        entity.setHit(dto.getHit());
        return entity;
    }
}
