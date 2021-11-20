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
    public void shouldRedirectToLoginPageWhenListPage() throws Exception {
        mockMvc.perform(MockMvcRequestBuilders.get("/book/list"))
            .andExpect(MockMvcResultMatchers.status().is3xxRedirection())
            .andExpect(MockMvcResultMatchers.header().exists("Location"));
    }

    @Test
    public void shouldRedirectToLoginPageWhenSearchPageWithoutUser() throws Exception {
        mockMvc.perform(MockMvcRequestBuilders.get("/book/search/b1"))
            .andExpect(MockMvcResultMatchers.status().is3xxRedirection())
            .andExpect(MockMvcResultMatchers.header().exists("Location"));
    }

    @Test
    public void shouldRedirectToLoginPageWhenCreatePageWithoutUser() throws Exception {
        mockMvc.perform(MockMvcRequestBuilders.get("/book/create"))
            .andExpect(MockMvcResultMatchers.status().is3xxRedirection())
            .andExpect(MockMvcResultMatchers.header().exists("Location"));
    }

    @WithMockUser(
        username = "user",
        authorities = {"viewer"}
    )
    @Test
    public void shouldReturnOkWhenSearchPageWithUser() throws Exception {
        when(bookRepository.findByTitle("b1")).thenReturn(Optional.of(new Book()));

        mockMvc.perform(MockMvcRequestBuilders.get("/book/search/b1"))
            .andExpect(MockMvcResultMatchers.status().isOk());
    }

    @WithMockUser(
        username = "test",
        authorities = {"viewer"}
    )
    @Test
    public void shouldReturnOkWhenSavePageWithUser() throws Exception {
        NewBookDto request = new NewBookDto();

        mockMvc.perform(MockMvcRequestBuilders.post("/book/create")
                .content(objectMapper.writeValueAsString(request)))
            .andExpect(MockMvcResultMatchers.status().is3xxRedirection())
            .andExpect(MockMvcResultMatchers.redirectedUrl("/"));
    }
}