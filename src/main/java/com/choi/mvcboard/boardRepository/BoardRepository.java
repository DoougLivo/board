package com.choi.mvcboard.boardRepository;

import com.choi.mvcboard.boardDomain.BoardEntity;
import org.springframework.data.jpa.repository.JpaRepository;

public interface BoardRepository extends JpaRepository<BoardEntity, Long> {

}
