package com.choi.mvcboard.boardService;

import com.choi.mvcboard.boardDomain.BoardDto;
import com.choi.mvcboard.boardDomain.BoardEntity;
import com.choi.mvcboard.boardRepository.BoardRepository;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;

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
        List<BoardDto> list = new ArrayList<>();
        list.add((BoardDto) boardDao.findAll());
        return list;
    }
}
