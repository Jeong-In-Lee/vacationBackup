package com.example.mall.service;

import com.example.mall.dto.PageRequestDTO;
import com.example.mall.dto.TodoDTO;
import lombok.extern.log4j.Log4j2;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;

import java.time.LocalDate;

@SpringBootTest
@Log4j2
public class TodoServiceTests {

    @Autowired
    TodoService todoService;

    @Test
    public void testGet(){
        Long tno = 1L;
        log.info(todoService.get(tno));
    }

    @Test
    public void testRegister() {
        TodoDTO todoDTO = TodoDTO.builder()
                .title("TItle")
                .content("contentsss")
                .dueDate(LocalDate.of(2024,8,18)).build();

        log.info(todoService.register(todoDTO));

    }

    @Test
    public void testGetList() {
        PageRequestDTO pageRequestDTO = PageRequestDTO.builder().build();

        log.info(todoService.getList(pageRequestDTO));
    }
}
