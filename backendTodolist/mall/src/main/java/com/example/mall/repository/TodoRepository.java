package com.example.mall.repository;

import com.example.mall.domain.Todo;
import com.example.mall.repository.search.TodoSearch;
import org.springframework.data.jpa.repository.JpaRepository;

public interface TodoRepository extends JpaRepository<Todo, Long>, TodoSearch {

}
