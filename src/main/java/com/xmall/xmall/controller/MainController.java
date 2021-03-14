package com.xmall.xmall.controller;

import com.xmall.xmall.domain.Item;
import com.xmall.xmall.repository.ItemRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;

import java.util.List;

@Controller
@RequiredArgsConstructor
public class MainController {

    private final ItemRepository itemRepository;

    @GetMapping("/")
    public String home(Model model) {
        List<Item> itemLists = itemRepository.findAll();
        model.addAttribute("itemLists", itemLists);

        return "main";
    }

}
