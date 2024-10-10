package com.example.mall.dto;


import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;
import org.springframework.web.multipart.MultipartFile;

import java.util.ArrayList;
import java.util.List;

@Builder
@Data
@AllArgsConstructor
@NoArgsConstructor
public class ProductDTO {

    private Long pno;
    private String pname;
    private int price;
    private String pdesc;
    private boolean delFlag; //실제 상품이 삭제되지 않음! 그저 표기만 해줌. (외래키로 2개가 걸릴거라)

    //Todo 얘가 진짜 파일임
    @Builder.Default
    private List<MultipartFile> files = new ArrayList<>();


    //TOdo 이미 업로드된 파일 이름들
    @Builder.Default
    private List<String> uploadFileNames = new ArrayList<>();
}
