package io.github.meritepk.webapp.news;

import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.AutoConfigureMockMvc;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.test.web.servlet.request.MockMvcRequestBuilders;
import org.springframework.test.web.servlet.result.MockMvcResultHandlers;
import org.springframework.test.web.servlet.result.MockMvcResultMatchers;

import com.fasterxml.jackson.databind.ObjectMapper;

@SpringBootTest
@AutoConfigureMockMvc
public class NewsControllerTest {

    @Autowired
    private MockMvc mockMvc;

    @Autowired
    private ObjectMapper objectMapper;

    @Test
    public void testNews() throws Exception {
        mockMvc.perform(MockMvcRequestBuilders.post("/api/v1/news")
                .header("Content-Type", "application/json")
                .content("{\"title\":\"title\",\"details\":\"details\"}"))
                .andDo(MockMvcResultHandlers.print())
                .andExpect(MockMvcResultMatchers.status().is2xxSuccessful());

        mockMvc.perform(MockMvcRequestBuilders.post("/api/v1/news/1")
                .header("Content-Type", "application/json")
                .content("{\"title\":\"title-1\",\"details\":\"details-1\"}"))
                .andDo(MockMvcResultHandlers.print())
                .andExpect(MockMvcResultMatchers.status().is2xxSuccessful());

        mockMvc.perform(MockMvcRequestBuilders.get("/api/v1/news")
                .header("Content-Type", "application/json"))
                .andDo(MockMvcResultHandlers.print())
                .andExpect(MockMvcResultMatchers.status().is2xxSuccessful());

        mockMvc.perform(MockMvcRequestBuilders.delete("/api/v1/news/1"))
                .andDo(MockMvcResultHandlers.print())
                .andExpect(MockMvcResultMatchers.status().is2xxSuccessful());
    }

    public void log(Object value) {
        try {
            System.out.println(objectMapper.writeValueAsString(value));
        } catch (Exception e) {
            System.err.println(e.toString());
        }
    }
}
