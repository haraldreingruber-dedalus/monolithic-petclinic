package org.springframework.samples.petclinic.vets;

import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.get;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;

import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.AutoConfigureMockMvc;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.jsonPath;

@AutoConfigureMockMvc 
@SpringBootTest
public class VetsApiTest {

    @Autowired
    MockMvc mockMvc;

    @Test
    void receive_vets() throws Exception {
        mockMvc.perform(get("/vets/"))
            .andExpect(status().isOk())
            .andExpect(jsonPath("$[0].firstName").value("James"));
    }
}

@RestController
class HelloWorldController {

    /**
     * responds with the json:
     *
     * { "message": "hello world" }
     */
    @GetMapping("/api")
    public ResponseData world() {
        return new ResponseData("hello world");
    }

    private static class ResponseData {
        private final String message;

        public ResponseData(String message) {
            this.message = message;
        }

        public String getMessage() {
            return message;
        }
    }
}