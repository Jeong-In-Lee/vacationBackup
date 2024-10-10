package com.example.mall.dto;

import lombok.Builder;
import lombok.Data;

import java.util.List;
import java.util.stream.Collectors;
import java.util.stream.IntStream;


@Data
public class PageResponseDTO<E> {

    private List<E> dtoList;

    private List<Integer> pageNumList;

    private PageRequestDTO pageRequestDTO;

    //  그냥 페이지 한움큼씩 넘기는거
    private boolean prev, next;

    // totalCount = 총 페이지 수 prevPage =11~20이전 화살표 눌러서 넘어가지는 이전 페이지(10) nextPage =1~10이후 화살표 눌러서 넘어가지는 다음 페이지(11같이) totalPage =? current = 현재 페이지 넘버
    private int totalCount, prevPage, nextPage, totalPage, current;


    @Builder(builderMethodName = "withAll")
    public PageResponseDTO(List<E> dtoList, PageRequestDTO pageRequestDTO, long total){

        this.dtoList = dtoList;
        this.pageRequestDTO = pageRequestDTO;
        this.totalCount = (int) total;

        //끝페이지부터 계산함 (1~10 페이지 넘버 있고 화살표 누르면 11~20으로 넘어갈 때 3페이지에 대해서 end는 10
        int end = (int) (Math.ceil(pageRequestDTO.getPage() / 10.0)) * 10;
        int start = end - 9;

        //진짜 마지막
        int last = (int)(Math.ceil(totalCount/(double)pageRequestDTO.getSize()));

        end = end>last ? last:end;

        this.prev = start>1;
        this.next = totalCount > end * pageRequestDTO.getSize();

        this.pageNumList = IntStream.rangeClosed(start , end).boxed().collect(Collectors.toList());

        this.prevPage = prev ? start-1 : 0;
        this.nextPage = next ? end + 1 : 0;

        this.current = pageRequestDTO.getPage();
        this.totalPage = this.pageNumList.size();
    }

}
