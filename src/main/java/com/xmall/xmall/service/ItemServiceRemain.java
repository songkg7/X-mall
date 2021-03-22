package com.xmall.xmall.service;

import com.xmall.xmall.domain.Item;
import com.xmall.xmall.file.FileUpload;
import com.xmall.xmall.form.ItemForm;
import com.xmall.xmall.repository.ItemRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.web.multipart.MultipartFile;

import java.time.LocalDateTime;
import java.util.Optional;

@Service
@Transactional
@RequiredArgsConstructor
public class ItemServiceRemain {
    private final ItemRepository itemRepository;

    private String fileUploadDir = "C:\\김성빈\\kosmo\\프로젝트\\X-mall\\src\\main\\resources\\static\\images\\item_img\\";


    public void create(ItemForm itemForm, MultipartFile itemImage2) throws Exception {
        FileUpload fileUpload = null;

        if (itemImage2 != null && !itemImage2.isEmpty()) {
            fileUpload = new FileUpload(itemImage2, fileUploadDir, null);

            itemForm.setItemImage(fileUpload.getNewFileName());
        }

        Item item = Item.builder()
                .name(itemForm.getName())
                .subTitle(itemForm.getSubTitle())
                .price(itemForm.getPrice())
                .stockQuantity(itemForm.getStockQuantity())
                .itemImage(itemForm.getItemImage())
                .description(itemForm.getDescription())
                .itemImage(itemForm.getItemImage())
                .CreatedAt(LocalDateTime.now())
                .genderType(itemForm.getGenderType())
                .categoryType(itemForm.getCategoryType())
                .build();

        itemRepository.save(item);

        if (itemImage2 != null && !itemImage2.isEmpty()) {
            fileUpload.upLoadFile();
        }
    }

    public void delete(Long id) {
        Optional<Item> byId = itemRepository.findById(id);
        String itemImageName = byId.get().getItemImage();
        byId.ifPresent(itemRepository::delete);

        new FileUpload().delete(fileUploadDir + itemImageName);
    }

    public void update(Long id, ItemForm itemForm) {
//        FileUpload fileUpload = null;
//
//        if (itemImage2 != null && !itemImage2.isEmpty()) {
//            fileUpload = new FileUpload(itemImage2, fileUploadDir, itemForm.getItemImage());
//
//            itemForm.setItemImage(fileUpload.getNewFileName());
//        }

        Item item = itemRepository.findById(id).get();

        item.setSubTitle(itemForm.getSubTitle());
        item.setName(itemForm.getName());
        item.setPrice(itemForm.getPrice());
        item.setStockQuantity(itemForm.getStockQuantity());
        item.setItemImage(itemForm.getItemImage());
        item.setDescription(itemForm.getDescription());
        item.setGenderType(itemForm.getGenderType());
        item.setCategoryType(itemForm.getCategoryType());

//        if (itemImage2 != null && !itemImage2.isEmpty()) {
//            fileUpload.upLoadFile();
//        }
    }
}
