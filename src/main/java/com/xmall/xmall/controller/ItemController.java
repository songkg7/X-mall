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
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;

import javax.validation.Valid;
import java.util.Optional;

@Controller
@RequiredArgsConstructor
public class ItemController {

    private final ItemService itemService;
    private final ItemRepository itemRepository;

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

    @GetMapping("/items/{id}")
    public String itemInfo(@PathVariable Long id, Model model) {

        // null check 필요, Optional을 사용하는게 좋을수도 있다.
        Item item = itemRepository.findById(id).get();

        if (item == null) {
            return "error";
        }

        model.addAttribute("item", item);

        return "items/item-info";
    }
}
