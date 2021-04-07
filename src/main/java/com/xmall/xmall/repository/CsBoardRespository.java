package com.xmall.xmall.repository;


import com.xmall.xmall.board.Cs_Board;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.transaction.annotation.Transactional;


@Transactional(readOnly = true)
public interface CsBoardRespository extends JpaRepository<Cs_Board, Long> {
}
