
import com.fasterxml.jackson.databind.ObjectMapper;
import com.fasterxml.jackson.databind.ObjectWriter;
import com.fasterxml.jackson.databind.SerializationFeature;
import org.example.DTOSystem.CatOwnerDTO;
import org.example.Main;
import org.junit.jupiter.api.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.AutoConfigureMockMvc;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.junit4.SpringRunner;
import org.springframework.test.web.servlet.MockMvc;

import java.time.LocalDate;
import java.util.ArrayList;

import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.post;
import static org.springframework.test.web.servlet.result.MockMvcResultHandlers.print;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;

@RunWith(SpringRunner.class)
@SpringBootTest(classes = Main.class)
@AutoConfigureMockMvc
public class integracionalTesting {

    @Autowired
    private MockMvc mockMvc;

    private final ObjectWriter ow;

    public integracionalTesting() {
        ObjectMapper mapper = new ObjectMapper();
        mapper.findAndRegisterModules();
        mapper.configure(SerializationFeature.WRAP_ROOT_VALUE, false);
        this.ow = mapper.writer().withDefaultPrettyPrinter();
    }

    @Test
    public void addCatOwner() throws Exception {

        CatOwnerDTO ownerDTO = new CatOwnerDTO(5L,"ananasTest", LocalDate.parse("2003-05-05"),new ArrayList<>());

        this.mockMvc.perform(
                        post("/CatOwner")
                                .contentType("application/json")
                                .content(ow.writeValueAsString(ownerDTO)))
                .andDo(print())
                .andExpect(status().isOk());
    }
}
