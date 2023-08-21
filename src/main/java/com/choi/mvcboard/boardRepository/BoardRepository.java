package com.choi.mvcboard.boardRepository;

import com.choi.mvcboard.boardDomain.BoardEntity;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Modifying;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;

public interface BoardRepository extends JpaRepository<BoardEntity, Long> {
    @Transactional
    @Modifying
    @Query("update BoardEntity e set e.hit = e.hit + 1 where e.id = :id")
    void updateHitCount(@Param("id") Long id);

    @Transactional
    @Modifying
    List<BoardEntity> findAllByOrderByRegdateDesc();
}
