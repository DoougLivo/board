package com.choi.mvcboard.boardDomain;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.time.LocalDateTime;
import java.util.Optional;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class BoardDto {
    private Long id;
    private String title;
    private String writer;
    private String contents;
    private LocalDateTime regdate = LocalDateTime.now();
    private int hit;

    public static Optional<BoardDto> entityToDto(BoardEntity entity) {
        BoardDto dto = new BoardDto();
        dto.setId(entity.getId());
        dto.setTitle(entity.getTitle());
        dto.setWriter(entity.getWriter());
        dto.setContents(entity.getContents());
        dto.setRegdate(entity.getRegdate());
        dto.setHit(entity.getHit());
        return Optional.of(dto);
    }
}
