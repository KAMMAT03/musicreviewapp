package com.musicreview.api.dto;

import lombok.Data;

import java.util.List;

@Data
public class ReviewResponse {
    private List<ReviewDTO> content;
    private int pageNo;
    private int pageSize;
    private long totalElements;
    private int totalPages;
    private boolean last;
}
