package com.choi.mvcboard.boardService;

import com.choi.mvcboard.boardDomain.BoardDto;
import com.choi.mvcboard.boardDomain.BoardEntity;
import com.choi.mvcboard.boardRepository.BoardRepository;
import org.springframework.stereotype.Service;

import java.time.LocalDateTime;
import java.util.List;
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
        List<BoardEntity> entityList = boardDao.findAllByOrderByRegdateDesc();
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
        dto.setUpdateDate(entity.getUpdateDate());
        dto.setHit(entity.getHit());
        // 나머지 필드도 필요에 따라 설정

        return dto;
    }

    public BoardDto getView(Long id) {
        BoardEntity entity = boardDao.findById(id).get();
        System.out.println("test : " + entity);
        return convertEntityToDto(entity);
    }

    public void updateHit(Long id) {
        boardDao.updateHitCount(id);
    }

    public void delete(Long id) {
        System.out.println("service id : " + id);
        boardDao.deleteById(id);
    }

    public void update(BoardDto dto) {
        System.out.println("service dto : " + dto);
        BoardEntity entity = new BoardEntity();
        dto.setUpdateDate(LocalDateTime.now());
//        System.out.println("디티오 : " + dto.getUpdateDate());
        BoardEntity entity2 = entity.dtoToEntity(dto);
        boardDao.save(entity2);
//        System.out.println("저장후 : " + entity2.getUpdateDate());
    }
}
