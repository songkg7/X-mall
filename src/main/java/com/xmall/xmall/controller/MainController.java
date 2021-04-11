package com.xmall.xmall.controller;

import com.xmall.xmall.account.CurrentAccount;
import com.xmall.xmall.domain.Account;
import com.xmall.xmall.domain.Item;
import com.xmall.xmall.repository.ItemRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.domain.Sort;
import org.springframework.data.web.PageableDefault;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;

import java.util.List;

@Controller
@RequiredArgsConstructor
public class MainController {

    private final ItemRepository itemRepository;

    @GetMapping("/")
    public String home(@CurrentAccount Account account, Model model) {
        List<Item> itemLists = itemRepository.findAll(Sort.by(Sort.Direction.DESC, "createdAt"));
        model.addAttribute("itemLists", itemLists);
        model.addAttribute("account", account);

        return "main";
    }

    @GetMapping("/login")
    public String login() {
        return "account/login";
    }

    /**
     * 검색
     * - 아이템의 name, 및 subtitle 에 keyword 가 포함되어 있는지를 기준으로 데이터를 검색한다.
     */
    @GetMapping("/search/items")
    public String searchItem(String keyword, Model model,
                             @PageableDefault(size = 6, sort = "createdAt", direction = Sort.Direction.ASC) Pageable pageable) {

        Page<Item> itemLists = itemRepository.findByKeyword(keyword, pageable);
        model.addAttribute("itemLists", itemLists);

        model.addAttribute("keyword", keyword);
        model.addAttribute("sortProperty", pageable.getSort().toString().contains("createdAt") ? "createdAt" : "price");

        return "items/search";
    }

}
