package com.xmall.xmall.controller;

import com.xmall.xmall.account.CurrentAccount;
import com.xmall.xmall.board.BoardCreateForm;
import com.xmall.xmall.board.CsBoardService;
import com.xmall.xmall.board.Cs_Board;
import com.xmall.xmall.domain.Account;
import com.xmall.xmall.repository.AccountRepository;
import com.xmall.xmall.repository.CsBoardRespository;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;

import javax.validation.Valid;
import java.security.Principal;
import java.util.List;
import java.util.Optional;

@Slf4j
@Controller
@RequiredArgsConstructor
public class CsController {

    private final CsBoardService csBoardService;
    private final CsBoardRespository csBoardRespository;
    private final AccountRepository accountRepository;

    @GetMapping("/cs/cs_board")
    public String boardList(Model model)  {

        List<Cs_Board> boardLists = csBoardRespository.findAll();
        model.addAttribute("boardLists",boardLists);
        // <tr th:each="boardList : ${boardLists}"> 오른쪽의 value 값 boardLists 가 ${boardLists}에 들어간다.
        return "cs/cs_board";}

    @GetMapping("/cs/cs_createForm")
    public String cs_createProc(Model model)  {
        model.addAttribute("form" , new BoardCreateForm());
        return "cs/cs_createForm";}

    @PostMapping("/cs/cs_createForm")
    public String cs_createForm(@Valid BoardCreateForm form, @CurrentAccount Account account){
//        account.ifPresent(account -> csBoardService.create(account, form.getSubject(), form.getMainText()));
        csBoardService.create(account, form.getSubject(), form.getMainText());
        return "redirect:/cs/cs_board";
    }

    @GetMapping("/cs/{boardId}")
    public String cs_selectBoard(@PathVariable Long boardId, Model model) {
       Cs_Board cs_board = csBoardRespository.findById(boardId).get();

        // 조회수 증가
        csBoardService.updateViewCount(boardId);

        model.addAttribute("cs_board", cs_board);

        return "cs/cs_selectForm";
    }

    @GetMapping("/cs/{boardId}/edit")
    public String updateBoardForm(@PathVariable Long boardId, Model model, @CurrentAccount Account account) {
        // 수정할 게시글 정보 가져오기
        Cs_Board cs_board = csBoardRespository.findById(boardId).get();

        // FIXME: 조금 더 세련된 코딩방법 고민해보기
        // if 문의 연속사용은 좋지 않고, 검색기능을 만들기 전까지 user 는 반드시 존재할 것이므로 옵셔널로 체크하기 전에 걸러내는 방법을 찾자
        // BoardService 에서 로직을 처리하는 것이 더 나을 것 같다.

        if (account!=null) {
            // 로그인한 사용자가 작성자인지 확인
            if (account.getId().equals(cs_board.getId())) {
                // 수정 버튼을 클릭할 경우 표시해줄 value 세팅
                model.addAttribute("cs_board", cs_board);
                return "cs/cs_updelForm";
            } else {
                return "redirect:/cs_board";
            }
        }

        return "redirect:/";

    }

    @PostMapping("/cs/{boardId}/edit")
    public String updelForm(@PathVariable Long boardId, @ModelAttribute("cs_board") BoardCreateForm form) {

        csBoardService.update(boardId, form.getSubject(), form.getMainText());
        return "redirect:/cs_board";
    }





    @GetMapping("/cs/asview")
    public String asview() {
        return "cs/asview";
    }

    @GetMapping("/cs/cancel_refund")
    public String cancel_refund() {
        return "cs/cancel_refund";
    }

    @GetMapping("/cs/delivery")
    public String delivery() {
        return "cs/delivery";
    }

    @GetMapping("/cs/member_info")
    public String member_info() {
        return "cs/member_info";
    }

    @GetMapping("/cs/member_service")
    public String member_service() {
        return "cs/member_service";
    }

    @GetMapping("/cs/notice")
    public String notice() {
        return "cs/notice";
    }

    @GetMapping("/cs/order_file")
    public String order_file() {
        return "cs/order_file";
    }

    @GetMapping("/cs/refund_service")
    public String refund_service() {
        return "cs/refund_service";
    }

    @GetMapping("/cs/main")
    public String main() {
        return "cs/cus_center_main";
    }
}
