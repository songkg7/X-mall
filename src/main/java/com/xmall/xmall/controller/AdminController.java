package com.xmall.xmall.controller;

import com.xmall.xmall.account.CurrentAccount;
import com.xmall.xmall.domain.Account;
import com.xmall.xmall.domain.Item;
import com.xmall.xmall.domain.Order;
import com.xmall.xmall.repository.AccountRepository;
import com.xmall.xmall.repository.OrderRepository;
import com.xmall.xmall.service.AdminService;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;

import java.util.List;

@Controller
@RequiredArgsConstructor
public class AdminController {

    private final AdminService adminService;
    private final AccountRepository accountRepository;
    private final OrderRepository orderRepository;

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
        model.addAttribute("totalSales", adminService.getTotalSales());

        // 가장 많이 팔린 상품
        Item bestItem = adminService.getBestItem();
        model.addAttribute("bestItem", bestItem);

        // 재고

        model.addAttribute(account);
        return "admin";
    }

    @GetMapping("/admin/customers")
    public String adminCustomers(Model model) {
        List<Account> accountList = adminService.findAllAccount();
        List<Long> graphData = adminService.getSignUpAccountPerDay();

        for (int i = 0; i < graphData.size(); i++) {
            model.addAttribute("graphData" + i, graphData.get(i));
        }


        model.addAttribute("accountList", accountList);
        model.addAttribute("totalAccount", accountRepository.count());
        return "admin-customers";
    }

    @GetMapping("/admin/integrations")
    public String adminIntegration(Model model) {
        return "admin-integrations";
    }

    @GetMapping("/admin/orders")
    public String adminOrders(Model model) {
        return "admin-orders";
    }

    @GetMapping("/admin/products")
    public String adminProducts(Model model) {
        return "admin-products";
    }

    @GetMapping("/admin/reports")
    public String adminReport(Model model) {
        return "admin-reports";
    }
}
