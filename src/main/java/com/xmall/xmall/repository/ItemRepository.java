package com.xmall.xmall.repository;

import com.xmall.xmall.domain.Item;
import org.springframework.data.domain.Sort;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;

@Transactional(readOnly = true)
public interface ItemRepository extends JpaRepository<Item, Long> {
    Item findByName(String name);

    List<Item> findByCategoryType(String categoryType);

    List<Item> findByGenderType(String gender);

    List<Item> findByGenderTypeAndCategoryType(String gender, String categoryType);

    List<Item> findByOrderByPriceAsc();

    List<Item> findByOrderByPriceDesc();

//    List<Item> findByGenderTypeAndCategoryType1ORCategoryType2(String gender, String categoryType1, String categoryType2);

}
