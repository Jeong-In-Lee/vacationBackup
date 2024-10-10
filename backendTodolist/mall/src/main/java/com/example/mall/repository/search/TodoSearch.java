package com.example.mall.repository.search;

import com.example.mall.domain.Todo;
import com.example.mall.dto.PageRequestDTO;
import org.springframework.data.domain.Page;

public interface TodoSearch {

    Page<Todo> search1(PageRequestDTO pageRequestDTO);
}
