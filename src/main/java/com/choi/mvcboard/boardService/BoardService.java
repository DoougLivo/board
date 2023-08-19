package com.choi.mvcboard.boardService;

import com.choi.mvcboard.boardDomain.BoardDto;
import com.choi.mvcboard.boardDomain.BoardEntity;
import com.choi.mvcboard.boardRepository.BoardRepository;
import org.springframework.stereotype.Service;

import javax.swing.text.html.Option;
import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;

@Service
public class BoardService {

    private final BoardRepository boardDao;

    public BoardService(BoardRepository boardDao) {
        this.boardDao = boardDao;
    }

    public void insert(BoardDto dto) {
        BoardEntity entity = new BoardEntity();
        boardDao.save(entity.dtoToEntity(dto));
    }

    public List<BoardDto> getList() {
        List<BoardEntity> entityList = boardDao.findAll();

        // BoardEntity를 BoardDto로 변환하고 새로운 리스트에 추가
        List<BoardDto> dtoList = entityList.stream()
                .map(this::convertEntityToDto)
                .collect(Collectors.toList());

        return dtoList;
    }

    private BoardDto convertEntityToDto(BoardEntity entity) {
        BoardDto dto = new BoardDto();
        dto.setId(entity.getId());
        dto.setTitle(entity.getTitle());
        dto.setWriter(entity.getWriter());
        dto.setContents(entity.getContents());
        dto.setRegdate(entity.getRegdate());
        dto.setHit(entity.getHit());
        // 나머지 필드도 필요에 따라 설정

        return dto;
    }

    public BoardDto getView(Long id) {
//        System.out.println("test : " + boardDao.findById(id));
        Optional<BoardEntity> entity = boardDao.findById(id);
        Optional<BoardDto> dto = Optional.of(new BoardDto());
        return dto.entityToDto(entity);
    }
}
