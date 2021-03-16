package com.xmall.xmall.service;

import com.xmall.xmall.domain.Item;
import com.xmall.xmall.form.ItemForm;
import com.xmall.xmall.repository.ItemRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.time.LocalDateTime;

@Service
@Transactional
@RequiredArgsConstructor
public class ItemService {
    private final ItemRepository itemRepository;

    public Long create(ItemForm itemForm) {
        Item item = new Item();
        item.setName(itemForm.getName());
        item.setSubTitle(itemForm.getSubTitle());
        item.setPrice(itemForm.getPrice());
        item.setStockQuantity(itemForm.getStockQuantity());
        item.setDescription(itemForm.getDescription());

        item.setCreateAt(LocalDateTime.now());

        Item newItem = itemRepository.save(item);

        return newItem.getId();

    }
}
