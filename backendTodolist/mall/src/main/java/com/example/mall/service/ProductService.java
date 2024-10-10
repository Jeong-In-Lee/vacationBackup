package com.example.mall.service;

import com.example.mall.domain.Product;
import com.example.mall.domain.ProductImage;
import com.example.mall.dto.PageRequestDTO;
import com.example.mall.dto.PageResponseDTO;
import com.example.mall.dto.ProductDTO;
import jakarta.transaction.Transactional;

import java.util.ArrayList;
import java.util.List;

@Transactional
public interface ProductService {

    ProductDTO get(Long pno);
    Long register(ProductDTO productDTO);
    void modify(ProductDTO productDTO);
    void remove(Long pno);

    PageResponseDTO<ProductDTO> getList(PageRequestDTO pageRequestDTO);

    default ProductDTO entityToDTO(Product product) {
        ProductDTO productDTO = ProductDTO.builder()
                .pname(product.getPname())
                .pdesc(product.getPdesc())
                .price(product.getPrice())
                .delFlag(product.isDelFlag())
                .pno(product.getPno())
                .build();

        List<String> uploadFileNames = new ArrayList<>();

        product.getImageList().forEach(image -> {
            uploadFileNames.add(image.getFileName());
        });

        productDTO.setUploadFileNames(uploadFileNames);

        //교재 코드
//        List<ProductImage> imageList = product.getImageList();
//
//        if(imageList == null || imageList.size() == 0 ){
//            return productDTO;
//        }
//
//        List<String> fileNameList = imageList.stream().map(productImage ->
//                productImage.getFileName()).toList();
//
//        productDTO.setUploadFileNames(fileNameList);


        return productDTO;

    }

    default Product DTOToEntity(ProductDTO productDTO) {
        Product product = Product.builder()
                .pno(productDTO.getPno())
                .pname(productDTO.getPname())
                .pdesc(productDTO.getPdesc())
                .price(productDTO.getPrice())
                .build();

        //그대로 넣으면 안됨 - 갈아끼우는거는 문제가 생길 수 있음! <<주의
        List<String> uploadFileNames = productDTO.getUploadFileNames();

        if (uploadFileNames == null || uploadFileNames.size() ==0){
            return product;
        }

        uploadFileNames.forEach(fileName -> {
            product.addImageString(fileName);
        });

        return product;
    }
}
