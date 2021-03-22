package com.xmall.xmall.service;

import com.xmall.xmall.domain.Item;
import com.xmall.xmall.form.ItemForm;
import com.xmall.xmall.repository.ItemRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.time.LocalDateTime;
import java.util.Optional;

@Service
@Transactional
@RequiredArgsConstructor
public class ItemService {

    private final ItemRepository itemRepository;


    public void create(ItemForm itemForm) {
        Item item = Item.builder()
                .name(itemForm.getName())
                .price(itemForm.getPrice())
                .description(itemForm.getDescription())
                .CreatedAt(LocalDateTime.now())
                .genderType(itemForm.getGenderType())
                .itemImage(itemForm.getItemImage())
                .stockQuantity(itemForm.getStockQuantity())
                .categoryType(itemForm.getCategoryType())
                .subTitle((itemForm.getSubTitle()))
                .build();

        itemRepository.save(item);
    }

    public void delete(Long id) {
        Optional<Item> byId = itemRepository.findById(id);
        byId.ifPresent(itemRepository::delete);
    }
}
