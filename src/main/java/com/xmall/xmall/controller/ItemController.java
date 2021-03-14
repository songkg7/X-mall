package com.xmall.xmall.controller;

import com.xmall.xmall.domain.Item;
import com.xmall.xmall.form.ItemForm;
import com.xmall.xmall.repository.ItemRepository;
import com.xmall.xmall.service.ItemService;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.Errors;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;

import javax.validation.Valid;

@Controller
@RequiredArgsConstructor
public class ItemController {

    private final ItemService itemService;
    private final ItemRepository itemRepository;

    @GetMapping("/items/item-info")
    public String itemInfo() {
        return "items/item-info";
    }

    @GetMapping("/items/create-item")
    public String createItemForm(Model model) {
        model.addAttribute(new ItemForm());
        return "items/create-item";
    }

    @PostMapping("/items/create-item")
    public String createItem(@Valid ItemForm itemForm, Errors errors, Model model) {

        if (errors.hasErrors()) {
            return "items/create-item";
        }

        itemService.create(itemForm);

        return "redirect:/";
    }
}
