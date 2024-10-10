package com.example.mall.service;

import com.example.mall.domain.Product;
import com.example.mall.domain.ProductImage;
import com.example.mall.domain.Todo;
import com.example.mall.dto.PageRequestDTO;
import com.example.mall.dto.PageResponseDTO;
import com.example.mall.dto.ProductDTO;
import com.example.mall.repository.ProductRepository;
import lombok.RequiredArgsConstructor;
import lombok.extern.log4j.Log4j2;
import org.apache.coyote.Request;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.data.domain.Sort;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Objects;
import java.util.Optional;
import java.util.stream.Collectors;

@Service
@Log4j2
@RequiredArgsConstructor
public class ProductServiceImpl implements ProductService{
    private final ProductRepository productRepository;

    @Override
    public ProductDTO get(Long pno) {
        Optional<Product> result = productRepository.selectOne(pno);
        Product product = result.orElseThrow();
        return entityToDTO(product);
    }

    @Override
    public Long register(ProductDTO productDTO) {
        Product product = DTOToEntity(productDTO);

        log.info("-------------------");
        log.info(product);
        log.info(product.getImageList());

        Product result = productRepository.save(product);
        return result.getPno();
    }

    @Override
    public void modify(ProductDTO productDTO) {
        Optional<Product> result = productRepository.selectOne(productDTO.getPno());

        Product product = result.orElseThrow();

        product.setPrice(productDTO.getPrice());
        product.setDesc(productDTO.getPdesc());
        product.setName(productDTO.getPname());
        product.changeDel(productDTO.isDelFlag());

        product.clearList();

        productDTO.getUploadFileNames().forEach(image -> {
            product.addImageString(image);
        });

        //교재 코드
//        List<String> uploadFileNames = productDTO.getUploadFileNames();
//
//        if(uploadFileNames != null && uploadFileNames.size() > 0 ){
//            uploadFileNames.stream().forEach(uploadName -> {
//                product.addImageString(uploadName);
//            });
//        }

        productRepository.save(product);
    }

    @Override
    public void remove(Long pno) {
        productRepository.updateToDelete(true, pno);
    }
    
    @Override
    public PageResponseDTO<ProductDTO> getList(PageRequestDTO pageRequestDTO) {
        Pageable pageable = PageRequest.of(pageRequestDTO.getPage()-1, pageRequestDTO.getSize(), Sort.by("pno").descending());

        Page<Object[]> result = productRepository.selectList(pageable);

        List<ProductDTO> dtoList = result.get().map(arr -> {
            ProductDTO productDTO = null;

            Product product = (Product) arr[0];
            ProductImage productImage = (ProductImage) arr[1];

            productDTO = ProductDTO.builder()
                        .pname(product.getPname())
                        .pdesc(product.getPdesc())
                        .price(product.getPrice())
                        .pno(product.getPno())
                        .build();

            String imageStr = productImage.getFileName();
            productDTO.setUploadFileNames(List.of(imageStr));


            return productDTO;
        }).collect(Collectors.toList());

        long totalCount = result.getTotalElements();

        return PageResponseDTO.<ProductDTO>withAll()
                .dtoList(dtoList)
                .total(totalCount)
                .pageRequestDTO(pageRequestDTO)
                .build();
    }
}
