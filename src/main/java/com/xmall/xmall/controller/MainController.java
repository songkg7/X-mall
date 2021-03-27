package com.xmall.xmall.controller;

import com.xmall.xmall.account.CurrentAccount;
import com.xmall.xmall.domain.Account;
import com.xmall.xmall.domain.Item;
import com.xmall.xmall.repository.ItemRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;

import java.util.List;

/**
 * Login 관련 컨트롤러
 */
@Controller
@RequiredArgsConstructor
public class MainController {

    private final ItemRepository itemRepository;

    @GetMapping("/")
    public String home(@CurrentAccount Account account, Model model) {
        // DB 에 저장된 상품들 가져와서 화면에 뿌려주기
        List<Item> itemLists = itemRepository.findAll();
        model.addAttribute("itemLists", itemLists);
        model.addAttribute("account", account);

        return "main";
    }

    // TODO: login 실패 시 에러메세지 표시
    @GetMapping("/login")
    public String login() {
        return "account/login";
    }

    @GetMapping("/search/items")
    public String searchItem(String keyword, Model model) {
        List<Item> itemLists = itemRepository.findByKeyword(keyword);
        model.addAttribute("itemLists", itemLists);
//        model.addAttribute("keyword", keyword);

        return "items/item-list";
    }
}
