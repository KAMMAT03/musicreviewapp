package com.musicreview.api.dto;

import lombok.Data;

import java.util.List;

@Data
public class AlbumResponse {
    private List<AlbumDTO> content;
    private int pageNo;
    private int pageSize;
    private long totalElements;
    private int totalPages;
    private boolean last;
}
