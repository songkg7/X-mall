package com.xmall.xmall.controller;

import com.xmall.xmall.account.CurrentAccount;
import com.xmall.xmall.domain.Account;
import com.xmall.xmall.domain.Item;
import com.xmall.xmall.form.ItemForm;
import com.xmall.xmall.form.OrderForm;
import com.xmall.xmall.repository.ItemRepository;
import com.xmall.xmall.repository.MyReviewRepository;
import com.xmall.xmall.service.ItemService;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.Errors;
import org.springframework.web.bind.annotation.*;

import javax.validation.Valid;
import java.util.ArrayList;
import java.util.List;

@Controller
@RequiredArgsConstructor
@Slf4j
public class ItemController {

    private final ItemService itemService;
    private final ItemRepository itemRepository;
    private final MyReviewRepository myReviewRepository;

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


    @PostMapping("/items/create-item")
    public String createItem(@CurrentAccount Account account, @Valid ItemForm itemForm, Errors errors, Model model) {
        if (errors.hasErrors()) {
            return "items/create-item";
        }

        model.addAttribute(account);
        itemService.create(itemForm);

        return "redirect:/";
    }

    /**
     * 상품 수정
     */
    @GetMapping("/items/{id}/edit")
    public String updateItemForm(@CurrentAccount Account account, @PathVariable Long id, Model model) {
        Item item = itemRepository.findById(id).orElseThrow();
        ItemForm itemForm = new ItemForm();
        itemForm.setSubTitle(item.getSubTitle());
        itemForm.setCategoryType(item.getCategoryType());
        itemForm.setDescription(item.getDescription());
        itemForm.setGenderType(item.getGenderType());
        itemForm.setItemImage(item.getItemImage());
        itemForm.setName(item.getName());
        itemForm.setPrice(item.getPrice());
        itemForm.setStockQuantity(item.getStockQuantity());

        model.addAttribute(account);
        model.addAttribute(item);
        model.addAttribute(itemForm);

        return "items/update-item";
    }

    // TODO: PostMapping
    @PostMapping("/items/{id}/edit")
    public String updateItem(@CurrentAccount Account account, @PathVariable Long id, Model model,
                             @Valid ItemForm itemForm, Errors errors) {

        model.addAttribute(account);
        itemService.update(id, itemForm);
        return "redirect:/";
    }


    /**
     * 상품 삭제
     */
    @GetMapping("/items/{id}/delete")
    public String deleteItem(@PathVariable Long id) {
        itemService.delete(id);

        return "redirect:/";
    }

    @GetMapping("/items")
    public String items(@CurrentAccount Account account, Model model) {
        List<Item> itemLists = itemRepository.findAll();
        model.addAttribute("itemLists", itemLists);
        model.addAttribute(account);
        model.addAttribute("genderLeft", null);
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
            return "error-test";
        }

        model.addAttribute("item", item);
        model.addAttribute("orderForm", new OrderForm());

        return "items/item-info";
    }

    @GetMapping("/items/category/{categoryType}")
    public String itemsAllCategory(Model model, @PathVariable String categoryType) {
        List<Item> itemLists = itemRepository.findByCategoryType(categoryType);
        model.addAttribute("itemLists", itemLists);

        return "items/item-list";
    }

    @GetMapping("/items/category/gender/{gender}")
    public String itemsGender(Model model, @PathVariable String gender) {
        List<Item> itemLists = itemRepository.findByGenderType(gender);
        model.addAttribute("itemLists", itemLists);
        model.addAttribute("genderLeft", gender);

        return "items/item-list";
    }

    @GetMapping("/items/category/{gender}/{categoryType}")
    public String itemsGenderCategory(Model model, @PathVariable String gender, @PathVariable String categoryType) {
        List<Item> itemLists = itemRepository.findByGenderTypeAndCategoryType(gender, categoryType);
        model.addAttribute("itemLists", itemLists);
        model.addAttribute("genderLeft", gender);

        return "items/item-list";
    }

    @GetMapping("/items/{gender}/{detail}")
    public String itemsGenderDetail(Model model, @PathVariable String gender, @PathVariable String detail) {
        List<Item> itemLists = itemRepository.findByGenderTypeAndCategoryDetail(gender, detail);
        model.addAttribute("itemLists", itemLists);
        model.addAttribute("genderLeft", gender);

        return "items/item-list";
    }

    // TODO: 정렬
    @GetMapping("/items/category/asc")
    public String itemsPriceAsc(Model model) {
        List<Item> itemLists = itemRepository.findByOrderByPriceAsc();
        model.addAttribute("itemLists", itemLists);
        return "items/item-list";
    }

    @GetMapping("/items/category/gender/men")
    public String itemsMenAll(@CurrentAccount Account account, Model model){
        List<Item> itemLists = itemRepository.findByGenderType("men");

        model.addAttribute(account);
        model.addAttribute("itemLists", itemLists);
        return "items/item-list-men";
    }

    @GetMapping("/items/category/gender/women")
    public String itemsWomenAll(@CurrentAccount Account account, Model model){
        List<Item> itemLists = itemRepository.findByGenderType("women");

        model.addAttribute(account);
        model.addAttribute("itemLists", itemLists);
        return "items/item-list-women";
    }

}

//    @GetMapping("/items/category/{gender}/{categoryType1}/{categoryType2}")
//    public String itemsGenderCategory(Model model, @PathVariable String gender, @PathVariable String categoryType1, @PathVariable String categoryType2) {
//
//        List<Item> itemLists1 = itemRepository.findByGenderTypeAndCategoryType(gender, categoryType1);
//        List<Item> itemLists2 = itemRepository.findByGenderTypeAndCategoryType(gender, categoryType2);
//        List<Item> itemListsResult = new ArrayList<>();
//
//        itemListsResult.addAll(itemLists1);
//        itemListsResult.addAll(itemLists2);
//        model.addAttribute("itemLists", itemListsResult);
//
//        return "items/item-list";
//    }