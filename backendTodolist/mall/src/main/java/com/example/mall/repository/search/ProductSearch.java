package com.example.mall.repository.search;

import com.example.mall.dto.PageRequestDTO;
import com.example.mall.dto.PageResponseDTO;
import com.example.mall.dto.ProductDTO;

public interface ProductSearch {

    PageResponseDTO<ProductDTO> searchList (PageRequestDTO pageRequestDTO);
}
