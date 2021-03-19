package com.xmall.xmall.controller;

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
import org.springframework.web.bind.annotation.PostMapping;

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
    public String cs_createForm(BoardCreateForm form, Principal principal){

        String connectUser = principal.getName();
        Optional<Account> account = Optional.ofNullable(accountRepository.findByEmail(connectUser));

        account.ifPresent(value -> csBoardService.create(value, form.getSubject(), form.getMainText()));
        return "redirect:/cs/cs_board";
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
