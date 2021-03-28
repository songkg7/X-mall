package com.xmall.xmall.controller;

import com.xmall.xmall.account.CurrentAccount;
import com.xmall.xmall.domain.Account;
import com.xmall.xmall.domain.Item;
import com.xmall.xmall.domain.Order;
import com.xmall.xmall.repository.AccountRepository;
import com.xmall.xmall.repository.ItemRepository;
import com.xmall.xmall.repository.OrderRepository;
import com.xmall.xmall.service.AdminService;
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
    private final AdminService adminService;
    private final AccountRepository accountRepository;
    private final OrderRepository orderRepository;

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

    /**
     * 검색
     * - 아이템의 name, 및 subtitle 에 keyword 가 포함되어 있는지를 기준으로 데이터를 검색한다.
     */
    @GetMapping("/search/items")
    public String searchItem(String keyword, Model model,
                             @PageableDefault(size = 6, sort = "createdAt", direction = Sort.Direction.ASC) Pageable pageable) {
        Page<Item> itemLists = itemRepository.findByKeyword(keyword, pageable);
        model.addAttribute("itemLists", itemLists);

        // TODO: search page 를 따로 만드는것이 좋을 것 같다.
        model.addAttribute("keyword", keyword);
        model.addAttribute("sortProperty", pageable.getSort().toString().contains("createdAt") ? "createdAt" : "price");

        return "items/item-list";
    }

    @GetMapping("/admin")
    public String adminPage(@CurrentAccount Account account, Model model) {

        // 전체 회원 조회
        List<Account> accountList = adminService.findAllAccount();
        model.addAttribute("accountCount", accountRepository.count());
        model.addAttribute("accountList", accountList);

        // 현재 주문의 수
        List<Order> orderList = adminService.findAllOrder();
        model.addAttribute("orderCount", orderRepository.count());
        model.addAttribute("orderList", orderList);

        // 총매출
        model.addAttribute("totalSales",adminService.getTotalSales());

        // 가장 많이 팔린 상품


        model.addAttribute(account);
        return "admin";
    }
}
