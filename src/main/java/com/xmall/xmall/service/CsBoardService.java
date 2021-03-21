package com.xmall.xmall.board;

import com.xmall.xmall.domain.Account;
import com.xmall.xmall.repository.CsBoardRespository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

@Service
@Transactional(readOnly = true)
@RequiredArgsConstructor
public class CsBoardService {

    private final CsBoardRespository csBoardRespository;
    // 게시물 작성
    @Transactional
    public void create(Account account, String subject, String mainText) {
        Cs_Board cs_board = Cs_Board.createBoard(account, subject, mainText);

        csBoardRespository.save(cs_board);

    }

}