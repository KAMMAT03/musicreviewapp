package com.musicreview.api.responses;

import com.musicreview.api.dto.ReviewDTO;
import lombok.Data;

import java.util.List;

@Data
public class ReviewResponse {
    private int pageNo;
    private int pageSize;
    private long totalElements;
    private int totalPages;
    private boolean last;
    private List<ReviewDTO> content;
}
