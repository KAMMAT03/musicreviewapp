package com.musicreview.api.responses;

import com.musicreview.api.dto.AlbumDTO;
import lombok.Data;

import java.util.List;

@Data
public class AlbumResponse {
    private int pageNo;
    private int pageSize;
    private long totalElements;
    private int totalPages;
    private boolean last;
    private List<AlbumDTO> content;
}
