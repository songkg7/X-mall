package com.xmall.xmall.controller;

import com.xmall.xmall.domain.Item;
import com.xmall.xmall.form.ItemForm;
import com.xmall.xmall.repository.ItemRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;

import java.util.Optional;

@Controller
@RequiredArgsConstructor
public class ItemController {

    private final ItemRepository itemRepository;

    @GetMapping("/item/1")
    public String main_banner1(Model model) {
        Item item = itemRepository.findById(1L).get();
        ItemForm itemForm = new ItemForm();

        itemForm.setName(item.getName());

    }
}
