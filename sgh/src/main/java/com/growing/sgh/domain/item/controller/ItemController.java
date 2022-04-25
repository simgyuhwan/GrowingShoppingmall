package com.growing.sgh.domain.item.controller;

import com.growing.sgh.common.response.Response;
import com.growing.sgh.domain.item.dto.ItemRegisterDto;
import com.growing.sgh.domain.item.service.ItemService;
import com.growing.sgh.exception.RegisterImgNotExistsException;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.multipart.MultipartFile;

import java.io.IOException;
import java.util.List;

import static com.growing.sgh.common.response.Response.*;

@RestController
@RequiredArgsConstructor
@Transactional
@RequestMapping("/items")
public class ItemController {

    private final ItemService itemService;

    @PostMapping("/new")
    @ResponseStatus(HttpStatus.CREATED)
    public Response register(@Validated @RequestBody ItemRegisterDto registerDto,
                             @RequestPart("itemImgFile")List<MultipartFile> itemImgList) throws IOException {
        validateImgFile(itemImgList);

        itemService.registerItem(registerDto, itemImgList);
        return success();
    }

    @DeleteMapping("/delete/{itemId}")
    @ResponseStatus(HttpStatus.OK)
    public Response delete(@PathVariable Long itemId){
        itemService.delete(itemId);
        return success();
    }

    private void validateImgFile(List<MultipartFile> itemImgList) {
        if(itemImgList.get(0).isEmpty())throw new RegisterImgNotExistsException();
    }


}
