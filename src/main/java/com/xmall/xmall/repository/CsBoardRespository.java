package com.xmall.xmall.repository;


import com.xmall.xmall.board.Cs_Board;
import com.xmall.xmall.domain.Account;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;


@Transactional(readOnly = true)
public interface CsBoardRespository extends JpaRepository<Cs_Board, Long> {
}
