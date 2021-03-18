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

    public Long create(ItemForm itemForm) {
        Item item = new Item();
        item.setName(itemForm.getName());
        item.setSubTitle(itemForm.getSubTitle());
        item.setPrice(itemForm.getPrice());
        item.setStockQuantity(itemForm.getStockQuantity());
        item.setDescription(itemForm.getDescription());
        item.setItemImage(itemForm.getItemImage());

        item.setCreateAt(LocalDateTime.now());

        Item newItem = itemRepository.save(item);

        return newItem.getId();

    }

    public void delete(Long id) {
        Optional<Item> byId = itemRepository.findById(id);
        byId.ifPresent(itemRepository::delete);
    }

    public void update(Long id, ItemForm itemForm) {
        Item item = itemRepository.findById(id).get();

        item.setSubTitle(itemForm.getSubTitle());
        item.setName(itemForm.getName());
        item.setPrice(itemForm.getPrice());
        item.setStockQuantity(itemForm.getStockQuantity());
        item.setDescription(itemForm.getDescription());

    }
}
