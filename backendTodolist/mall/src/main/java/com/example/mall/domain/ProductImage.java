package com.example.mall.domain;

import jakarta.persistence.Embeddable;
import lombok.*;

@Embeddable
@Getter
@ToString
@Builder
@AllArgsConstructor
@NoArgsConstructor
public class ProductImage {

    private String fileName;

    //순번 (ord가 0번인 애가 목록에서 표시되는 사진이게 하기 위해)
    private int ord;

    public void setOrd(int ord){
        this.ord = ord;
    }
}
