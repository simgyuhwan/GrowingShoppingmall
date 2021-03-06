package com.growing.sgh.domain.item.dto;

import com.fasterxml.jackson.annotation.JsonProperty;
import com.growing.sgh.domain.category.entity.Category;
import com.growing.sgh.domain.item.entity.Item;
import com.growing.sgh.domain.item.entity.ItemSellStatus;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

import javax.validation.constraints.NotBlank;
import javax.validation.constraints.NotEmpty;
import javax.validation.constraints.NotNull;
import java.util.ArrayList;
import java.util.List;

@Data
@NoArgsConstructor
public class ItemDto {

    private Long id;

    @NotBlank(message = "상품명은 필수 입력 값입니다.")
    private String itemNm;

    @NotNull(message = "상품 가격은 필수 입력 값입니다.")
    private Integer price;

    @NotNull(message = "재고는 필수 입력 값입니다.")
    private Integer stockNumber;

    @NotBlank(message = "상품 상세 정보는 필수 입력 값입니다.")
    private String description;

    @NotNull(message = "카테고리는 반드시 선택해주세요.")
    private Long categoryId;

    @JsonProperty("itemSellStatus")
    private ItemSellStatus itemSellStatus;

    private List<ItemImgDto> itemImgDtoList = new ArrayList<>();

    @Builder
    public ItemDto(Long id, String itemNm, Integer price, Integer stockNumber,
                   String description, Long categoryId, ItemSellStatus itemSellStatus, List<ItemImgDto> itemImgDtoList) {
        this.id = id;
        this.itemNm = itemNm;
        this.price = price;
        this.categoryId = categoryId;
        this.stockNumber = stockNumber;
        this.description = description;
        this.itemSellStatus = itemSellStatus;
        this.itemImgDtoList = itemImgDtoList;
    }

    public static Item toEntity(ItemDto registerDto){
        return Item.builder()
                .id(registerDto.getId())
                .itemNm(registerDto.getItemNm())
                .price(registerDto.getPrice())
                .stockNumber(registerDto.getStockNumber())
                .description(registerDto.getDescription())
                .itemSellStatus(registerDto.getItemSellStatus())
                .category(new Category())
                .build();
    }

    public static ItemDto of(Item item, List<ItemImgDto> itemImgDtos) {
        return ItemDto.builder()
                .id(item.getId())
                .itemNm(item.getItemNm())
                .description(item.getDescription())
                .price(item.getPrice())
                .stockNumber(item.getStockNumber())
                .itemSellStatus(item.getItemSellStatus())
                .itemImgDtoList(itemImgDtos)
                .build();
    }
}
