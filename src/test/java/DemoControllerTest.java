import com.example.demo.controller.DemoController;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.AutoConfigureMockMvc;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.http.MediaType;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.test.web.servlet.request.MockMvcRequestBuilders;
import org.springframework.test.web.servlet.setup.MockMvcBuilders;

import static org.hamcrest.Matchers.equalTo;

import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.content;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;

@SpringBootTest
@AutoConfigureMockMvc
public class DemoControllerTest {

    //아래 BeforeEach에서 mockMvc 객체를 초기화 하는 것과 같다.
    @Autowired
    public MockMvc mockMvc;

    @BeforeEach
    public void before(){
        mockMvc =
                MockMvcBuilders
                .standaloneSetup(DemoController.class)
                .alwaysExpect(status().isOk())
                .build();
    }

    @Test
    public void hello() throws Exception{
        mockMvc.perform(
                MockMvcRequestBuilders
                .get("/")
                .accept(MediaType.APPLICATION_JSON)
        )
        .andExpect(status().isOk())
        .andExpect(content().string(equalTo("Hello World!")));
    }
}
