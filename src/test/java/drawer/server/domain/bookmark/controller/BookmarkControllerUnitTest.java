package drawer.server.domain.bookmark.controller;

import static org.assertj.core.api.Assertions.*;
import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.BDDMockito.given;
import static org.mockito.Mockito.*;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.*;
import static org.springframework.test.web.servlet.result.MockMvcResultHandlers.*;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.*;

import com.google.gson.Gson;
import drawer.server.domain.bookmark.dto.bookmark.CreateBookmarkRequest;
import drawer.server.domain.bookmark.dto.bookmark.CreateBookmarkResponse;
import drawer.server.domain.bookmark.service.BookmarkService;
import java.util.UUID;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.mockito.Mockito;
import org.springframework.http.MediaType;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.test.web.servlet.setup.MockMvcBuilders;

class BookmarkControllerUnitTest {

    private static BookmarkService bookmarkService;

    private static MockMvc mockMvc;

    @BeforeEach
    void setup() {
        this.bookmarkService = Mockito.mock(BookmarkService.class);
        this.mockMvc = MockMvcBuilders.standaloneSetup(new BookmarkController(bookmarkService)).build();
    }

    @Test
    public void createBookmarkWithNoCollection() throws Exception {
        // given
        String uuid = "example-uuid";
        String bookmarkId = UUID.randomUUID().toString();
        String url = "https://example.com";
        String title = "example";

        CreateBookmarkRequest request = new CreateBookmarkRequest(null, url, title);
        CreateBookmarkResponse response = new CreateBookmarkResponse(bookmarkId, null, url, title);

        given(bookmarkService.createBookmark(uuid, request)).willReturn(response);

        Gson gson = new Gson();
        String content = gson.toJson(request);

        // when
        // then
        mockMvc
                .perform(
                        post("/api/v1/users/{userId}/bookmarks", uuid)
                                .content(content)
                                .contentType(MediaType.APPLICATION_JSON)
                                .accept(MediaType.APPLICATION_JSON_VALUE))
                .andExpect(status().isCreated())
                .andExpect(jsonPath("$.bookmarkId").exists())
                .andExpect(jsonPath("$.collectionId").doesNotExist())
                .andExpect(jsonPath("$.url").exists())
                .andExpect(jsonPath("$.title").exists())
                .andDo(print());

        verify(bookmarkService).createBookmark(uuid, request);
    }

    @Test
    public void createBookmarkWithCollection() throws Exception {
        // given
        String uuid = "example-uuid";
        String bookmarkId = UUID.randomUUID().toString();
        String collectionId = UUID.randomUUID().toString();
        String url = "https://example.com";
        String title = "example";

        CreateBookmarkRequest request = new CreateBookmarkRequest(collectionId, url, title);
        CreateBookmarkResponse response =
                new CreateBookmarkResponse(bookmarkId, collectionId, url, title);

        given(bookmarkService.createBookmark(uuid, request)).willReturn(response);

        Gson gson = new Gson();
        String content = gson.toJson(request);

        // when
        // then
        mockMvc
                .perform(
                        post("/api/v1/users/{userId}/bookmarks", uuid)
                                .content(content)
                                .contentType(MediaType.APPLICATION_JSON)
                                .accept(MediaType.APPLICATION_JSON_VALUE))
                .andExpect(status().isCreated())
                .andExpect(jsonPath("$.bookmarkId").exists())
                .andExpect(jsonPath("$.collectionId").exists())
                .andExpect(jsonPath("$.url").exists())
                .andExpect(jsonPath("$.title").exists())
                .andDo(print());

        verify(bookmarkService).createBookmark(uuid, request);
    }
}
