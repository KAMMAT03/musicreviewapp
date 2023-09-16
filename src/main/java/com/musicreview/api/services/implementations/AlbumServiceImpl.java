package com.musicreview.api.services.implementations;

import com.musicreview.api.dto.AlbumDTO;
import com.musicreview.api.responses.AlbumResponse;
import com.musicreview.api.services.AlbumService;
import com.musicreview.api.spotify.api.SpotifyApiHandler;
import org.springframework.stereotype.Service;
import java.util.List;

@Service
public class AlbumServiceImpl implements AlbumService {
    @Override
    public AlbumResponse searchAlbum(String searchPhrase, int pageNo, int pageSize) {
        String searchContent = "search?q=" + searchPhrase + "&type=album&market=PL&limit=50";
        String response = SpotifyApiHandler.spotifyApiGetResponse(searchContent);

        return mapToResponse(SpotifyApiHandler.extractAlbums(response), pageNo, pageSize);
    }

    @Override
    public AlbumDTO getAlbumById(String id) {
        String searchContent = "albums/" + id + "?market=PL";
        String response = SpotifyApiHandler.spotifyApiGetResponse(searchContent);

        return SpotifyApiHandler.extractAlbumDetailed(response);
    }

    private static AlbumResponse mapToResponse(List<AlbumDTO> albumDTOList, int pageNo, int pageSize){
        int originalSize = albumDTOList.size();

        int totalPages = (originalSize - (originalSize % pageSize)) / pageSize;
        if ((originalSize % pageSize) > 0) totalPages++;

        if (pageNo < 1) pageNo = 1;
        pageNo = Math.min(pageNo, totalPages);

        int startIndex = (pageNo - 1) * pageSize;
        int endIndex = startIndex + pageSize;


        AlbumResponse albumResponse = new AlbumResponse();

        albumResponse.setContent(albumDTOList.subList(startIndex, endIndex));
        albumResponse.setLast(pageNo >= totalPages);
        albumResponse.setTotalPages(totalPages);
        albumResponse.setPageNo(pageNo);
        albumResponse.setTotalElements(originalSize);
        albumResponse.setPageSize(pageSize);

        return albumResponse;
    }
}
