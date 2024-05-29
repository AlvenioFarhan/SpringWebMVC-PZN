package pzn.springwebmvc.controller;

import org.hamcrest.Matchers;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.AutoConfigureMockMvc;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.http.MediaType;
import org.springframework.test.web.servlet.MockMvc;

import static org.junit.jupiter.api.Assertions.*;
import static org.springframework.test.web.servlet.MockMvcBuilder.*;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.*;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.*;
import static org.springframework.test.web.servlet.result.MockMvcResultHandlers.*;

@SpringBootTest
@AutoConfigureMockMvc
class PersonControllerTest {

    @Autowired
    private MockMvc mockMvc;

    @Test
    void createPerson() throws Exception {
        mockMvc.perform(
                post("/person")
                        .contentType(MediaType.APPLICATION_FORM_URLENCODED)
                        .param("firstName", "Alvenio")
                        .param("middleName", "Farhan")
                        .param("lastName", "Prayogo")
                        .param("email", "alveniofarhan@gmail.com")
                        .param("phone", "085156066856")
                        .param("address.street", "Jalan Dinar Mas")
                        .param("address.city", "Semarang")
                        .param("address.country", "Indonesia")
                        .param("address.postalCode", "50271")
                        .param("hobbies[0]", "Coding")
                        .param("hobbies[1]", "Reading")
                        .param("hobbies[2]", "Jogging")
                        .param("socialMedia[0].name", "Facebook")
                        .param("socialMedia[0].location", "facebook.com/alveniofarhan")
                        .param("socialMedia[1].name", "Instagram")
                        .param("socialMedia[1].location", "instagram.com/alveniofarhan")
        ).andExpectAll(
                status().isOk(),
                content().string(Matchers.containsString("Success create person Alvenio Farhan Prayogo " +
                        "with email alveniofarhan@gmail.com and phone 085156066856 " +
                        "with address Jalan Dinar Mas, Semarang, Indonesia, 50271"))
        );
    }

    @Test
    void createPersonValidationError() throws Exception {
        mockMvc.perform(
                post("/person")
                        .contentType(MediaType.APPLICATION_FORM_URLENCODED)
//                        .param("firstName", "Alvenio")
                        .param("middleName", "Farhan")
                        .param("lastName", "Prayogo")
                        .param("email", "alveniofarhan@gmail.com")
                        .param("phone", "085156066856")
                        .param("address.street", "Jalan Dinar Mas")
                        .param("address.city", "Semarang")
                        .param("address.country", "Indonesia")
                        .param("address.postalCode", "50271")
                        .param("hobbies[0]", "Coding")
                        .param("hobbies[1]", "Reading")
                        .param("hobbies[2]", "Jogging")
                        .param("socialMedia[0].name", "Facebook")
                        .param("socialMedia[0].location", "facebook.com/alveniofarhan")
                        .param("socialMedia[1].name", "Instagram")
                        .param("socialMedia[1].location", "instagram.com/alveniofarhan")
        ).andExpectAll(
                status().isBadRequest(),
                content().string(Matchers.containsString("You send invalid data"))
        );
    }
}