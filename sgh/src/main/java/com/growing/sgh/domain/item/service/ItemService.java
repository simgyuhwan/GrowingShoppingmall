package com.growing.sgh.domain.item.service;

import com.growing.sgh.domain.item.dto.ItemDto;
import com.growing.sgh.domain.item.dto.ItemImgDto;
import com.growing.sgh.domain.item.entity.Item;
import com.growing.sgh.domain.item.entity.ItemImg;
import com.growing.sgh.domain.item.repository.ItemImgRepository;
import com.growing.sgh.domain.item.repository.ItemRepository;
import com.growing.sgh.exception.ItemNotFoundException;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.web.multipart.MultipartFile;

import java.io.IOException;
import java.util.List;
import java.util.stream.Collectors;

@Service
@RequiredArgsConstructor
@Transactional
public class ItemService {

    private final ItemRepository itemRepository;
    private final ItemImgRepository itemImgRepository;
    private final ItemImgService itemImgService;

    public void itemRegister(ItemDto registerDto, List<MultipartFile> itemImgList) throws IOException {
        Item item = ItemDto.toEntity(registerDto);
        itemRepository.save(item);
        itemImgService.itemImgRegister(item, itemImgList);
    }

    public void itemDelete(Long itemId){
        Item item = itemRepository.findById(itemId).orElseThrow(ItemNotFoundException::new);
        itemRepository.delete(item);
    }

    public ItemDto itemUpdate(Long itemId,ItemDto itemDto, List<MultipartFile> itemImgList) throws IOException {
        Item item = itemRepository.findById(itemId).orElseThrow(ItemNotFoundException::new);
        item.updateItem(itemDto);
        return ItemDto.of(item,itemImgService.itemImgUpdate(item, itemImgList).stream().map(itemImg -> ItemImgDto.of(itemImg)).collect(Collectors.toList()));
    }

//    private void validateItem(Long itemId) {
//        if(!itemRepository.existsById(itemId))
//            throw new ItemNotFoundException(itemId.toString());
//    }


}