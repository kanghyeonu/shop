package com.project.shop.service;

import com.project.shop.domain.Item;
import com.project.shop.repository.ItemRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
public class ItemService {

    final private ItemRepository itemRepository;

    @Autowired
    public ItemService(ItemRepository itemRepository){
        this.itemRepository = itemRepository;
    }

    public void saveItem(Item item){
        validateItem(item);
        itemRepository.save(item);
    }

    private void validateItem(Item item) {
        String title = item.getTitle();
        Integer price = item.getPrice();

        if (title.length() > 15){
            throw new IllegalArgumentException("상품명은 15글자 이내여야 합니다");
        }
        if (price <= 0){
            throw new IllegalArgumentException("상품의 가격은 0보다 커야합니다");
        }
    }

    public void updateItem(Long id, String title, String price){
        Optional<Item> item = getItem(id);
        if (item.isPresent()){
            Item exsitingItem = item.get();
            exsitingItem.setTitle(title);
            exsitingItem.setPrice(price);
            saveItem(exsitingItem);
        } else{
            throw new IllegalArgumentException("잘못된 상품 정보");
        }
    }
    public Optional<Item> getItem(Long id){
        return itemRepository.findById(id);
    }

    public List<Item> getAllItem(){
        return itemRepository.findAll();
    }
}
