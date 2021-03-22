package com.xmall.xmall.controller;

import com.xmall.xmall.domain.Item;
import com.xmall.xmall.form.ItemForm;
import com.xmall.xmall.repository.ItemRepository;
import com.xmall.xmall.service.ItemService;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.core.io.ClassPathResource;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.Errors;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.multipart.MultipartFile;

import javax.validation.Valid;
import java.util.List;
import java.util.Optional;

@Controller
@RequiredArgsConstructor
@Slf4j
public class ItemController {

    private final ItemService itemService;
    private final ItemRepository itemRepository;

    /**
     * 상품 등록
     */
    @GetMapping("/items/create-item")
    public String createItemForm(Model model) {
        long totalCount = itemRepository.count();
        model.addAttribute(new ItemForm());
        model.addAttribute("totalCount", totalCount);
        return "items/create-item";
    }

    @PostMapping(value="/items/create-item")
    public String createItem(@Valid ItemForm itemForm, Errors errors, @RequestParam MultipartFile itemImage2) throws Exception {
        if (errors.hasErrors()) {
            return "items/create-item";
        }

//        ClassPathResource resource = new ClassPathResource();

        itemService.create(itemForm ,itemImage2);

        return "redirect:/";
    }

    /**
     * 상품 수정
     */
    @GetMapping("/items/{id}/edit")
    public String updateItem(@PathVariable Long id, Model model) {
        Item item = itemRepository.findById(id).get();
        model.addAttribute(item);
        return "items/update-item";
    }

    @PostMapping(value="/items/{id}/edit")
    public String updateItemProcess(@PathVariable Long id, @ModelAttribute("item") ItemForm itemForm, @RequestParam MultipartFile itemImage2) throws Exception {
        itemService.update(id, itemForm, itemImage2);

        return "redirect:/";
    }

    /**
     * 상품 삭제
     */
    @GetMapping(value="/items/{id}/delete")
    public String deleteItem(@PathVariable Long id) {
        itemService.delete(id);
        return "redirect:/";
    }


    @GetMapping("/items")
    public String items(Model model) {
        List<Item> itemLists = itemRepository.findAll();
        model.addAttribute("itemLists", itemLists);
        return "items/item-list";
    }

    /**
     * Item 상세 정보 페이지
     */
    @GetMapping("/items/{id}")
    public String itemInfo(@PathVariable Long id, Model model) {
        // null check 필요, Optional 을 사용하는게 좋을수도 있다.
        Item item = itemRepository.findById(id).get();
        if (item == null) {
            return "error";
        }
        model.addAttribute("item", item);
        return "items/item-info";
    }
}
