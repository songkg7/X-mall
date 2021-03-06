package com.xmall.xmall.board;

import com.xmall.xmall.domain.Account;
import com.xmall.xmall.repository.CommentRepository;
import com.xmall.xmall.repository.CsBoardRespository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

@Service
@Transactional(readOnly = true)
@RequiredArgsConstructor
public class CsBoardService {

    private final CsBoardRespository csBoardRespository;
    private final CommentRepository commentRepository;
    // 게시물 작성
    @Transactional
    public void create(Account account, String subject, String mainText) {
        Cs_Board cs_board = Cs_Board.createBoard(account, subject, mainText);

        csBoardRespository.save(cs_board);

    }

    @Transactional
    public void create(String commentText){
        Comment comment = Comment.createComment(commentText);

        commentRepository.save(comment);
    }

    @Transactional
    public void updateViewCount(Long board_id) {

        Cs_Board cs_board = csBoardRespository.findById(board_id).get();

        cs_board.setViewCount(cs_board.getViewCount() + 1);
    }

    // 게시물 수정
    @Transactional
    public void update(Long boardId, String subject, String mainText) {
        Cs_Board cs_board = csBoardRespository.findById(boardId).get();
        cs_board.setSubject(subject);
        cs_board.setMainText(mainText);

        // save 명령은 불필요하다
//        boardRepository.save(board);

    }

    // 게시물 삭제
    @Transactional
    public void delete(Long boardId) {
        Cs_Board cs_board = csBoardRespository.findById(boardId).get();

        csBoardRespository.delete(cs_board);
    }

}
