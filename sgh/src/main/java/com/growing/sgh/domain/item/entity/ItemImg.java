package com.growing.sgh.domain.item.entity;

import com.growing.sgh.domain.member.entity.BaseEntity;
import lombok.*;
import org.springframework.web.multipart.MultipartFile;

import javax.persistence.*;
import java.util.List;

@Entity
@Getter
@Table(name = "item_img")
@NoArgsConstructor(access = AccessLevel.PROTECTED)
@AllArgsConstructor
public class ItemImg extends BaseEntity {

    @Id @Column(name = "item_img_id")
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    private String imgName;

    private String oriImgName;

    private String imgUrl;

    private String repImgYn;

    @ManyToOne(fetch = FetchType.LAZY )
    @JoinColumn(name = "item_id")
    private Item item;

    public ItemImg(Item item){
        this.item = item;
        this.repImgYn = "N";
    }

    @Builder
    public ItemImg(String imgName, String oriImgName, String imgUrl, String repImgYn){
        this.imgName = imgName;
        this.oriImgName = oriImgName;
        this.repImgYn = repImgYn;
        this.imgUrl = imgUrl;
    }

    public ItemImg(String imgName, String oriImgName, String imgUrl, String repImgYn, Item item) {
        this.imgName = imgName;
        this.oriImgName = oriImgName;
        this.imgUrl = imgUrl;
        this.repImgYn = repImgYn;
        this.item = item;
    }

    public void RepImg(){
        this.repImgYn = "Y";
    }

    public void SubImg(){
        this.repImgYn = "N";
    }

    public void updateItemImg(String oriImgName, String imgName, String imgUrl) {
        this.oriImgName = oriImgName;
        this.imgName = imgName;
        this.imgUrl = imgUrl;
    }
}
