package ru.otus.lesson.controller;

import static org.mockito.Mockito.when;

import com.fasterxml.jackson.databind.ObjectMapper;
import java.util.Optional;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.WebMvcTest;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.security.test.context.support.WithMockUser;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.test.web.servlet.request.MockMvcRequestBuilders;
import org.springframework.test.web.servlet.result.MockMvcResultMatchers;
import ru.otus.lesson.domain.Book;
import ru.otus.lesson.dto.NewBookDto;
import ru.otus.lesson.repository.AuthorRepository;
import ru.otus.lesson.repository.BookRepository;
import ru.otus.lesson.repository.CommentRepository;
import ru.otus.lesson.repository.GenreRepository;

@WebMvcTest(BookController.class)
class BookMvcControllerTest {

    private final ObjectMapper objectMapper = new ObjectMapper();

    @Autowired
    private MockMvc mockMvc;
    @MockBean
    private BookRepository bookRepository;
    @MockBean
    private AuthorRepository authorRepository;
    @MockBean
    private GenreRepository genreRepository;
    @MockBean
    private CommentRepository commentRepository;

    @Test
    public void shouldReturnOkWhenStartPage() throws Exception {
        mockMvc.perform(MockMvcRequestBuilders.get("/"))
            .andExpect(MockMvcResultMatchers.status().isOk());
    }

    @Test
    public void shouldReturnOkWhenListPage() throws Exception {
        mockMvc.perform(MockMvcRequestBuilders.get("/book/list"))
            .andExpect(MockMvcResultMatchers.status().isOk());
    }

    @Test
    public void shouldReturnForbiddenWhenEndpointNotExist() throws Exception {
        mockMvc.perform(MockMvcRequestBuilders.get("/endpoint-not-exist"))
            .andExpect(MockMvcResultMatchers.status().is(302));
    }

    @Test
    public void shouldRedirectToLoginPageWhenSearchPageWithoutUser() throws Exception {
        mockMvc.perform(MockMvcRequestBuilders.get("/book/search/b1"))
            .andExpect(MockMvcResultMatchers.status().is(302))
            .andExpect(MockMvcResultMatchers.header().exists("Location"));
    }

    @Test
    @WithMockUser(
        username = "test",
        authorities = {"VIEWER"}
    )
    public void shouldReturnForbiddenWhenCreatePageWithoutAuthorities() throws Exception {
        mockMvc.perform(MockMvcRequestBuilders.get("/book/create"))
            .andExpect(MockMvcResultMatchers.status().is(403));
    }

    @WithMockUser(
        username = "test",
        authorities = {"VIEWER"}
    )
    @Test
    public void shouldReturnForbiddenWhenSearchPageWithoutAuthorities() throws Exception {
        when(bookRepository.findByTitle("b1")).thenReturn(Optional.of(new Book()));

        mockMvc.perform(MockMvcRequestBuilders.get("/book/search/b1"))
            .andExpect(MockMvcResultMatchers.status().is(403));
    }

    @WithMockUser(
        username = "test",
        authorities = {"ADMIN"}
    )
    @Test
    public void shouldReturnOkWhenSavePageWithUser() throws Exception {
        NewBookDto request = new NewBookDto();

        mockMvc.perform(MockMvcRequestBuilders.post("/book/create")
                .content(objectMapper.writeValueAsString(request)))
            .andExpect(MockMvcResultMatchers.status().is(302))
            .andExpect(MockMvcResultMatchers.redirectedUrl("/"));
    }
}