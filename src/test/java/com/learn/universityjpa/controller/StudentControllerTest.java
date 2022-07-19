package com.learn.universityjpa.controller;

import com.learn.universityjpa.annotations.SqlTest;
import com.learn.universityjpa.entity.Gender;
import com.learn.universityjpa.entity.Student;
import com.learn.universityjpa.exceptions.PersonNotFoundException;
import com.learn.universityjpa.repo.StudentComponent;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.AutoConfigureMockMvc;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.boot.test.web.client.TestRestTemplate;
import org.springframework.boot.web.server.LocalServerPort;
import org.springframework.http.MediaType;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.test.web.servlet.MvcResult;
import org.springframework.test.web.servlet.request.MockMvcRequestBuilders;
import org.springframework.test.web.servlet.result.MockMvcResultMatchers;

import java.util.Optional;

import static org.assertj.core.api.Assertions.assertThat;
import static org.hamcrest.Matchers.containsString;
import static org.hamcrest.Matchers.hasSize;
import static org.hamcrest.Matchers.is;
import static org.junit.Assert.assertNotNull;
import static org.junit.Assert.assertTrue;
import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertThrows;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.get;
import static org.springframework.test.web.servlet.result.MockMvcResultHandlers.print;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.content;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.jsonPath;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;


@SpringBootTest(webEnvironment = SpringBootTest.WebEnvironment.RANDOM_PORT)
@AutoConfigureMockMvc
class StudentControllerTest {

    private String studentBody = "{\n" +
            "        \"firstName\":\"FirstName\",\n" +
            "        \"secondName\":\"SecondName\",\n" +
            "        \"lastName\": \"LastName\",\n" +
            "        \"groupId\":1,\n" +
            "        \"dateBirth\":\"1999-02-14\",\n" +
            "        \"gender\":\"MALE\"\n" +
            "}";
    @Autowired
    private MockMvc mockMvc;
    @Autowired
    private GroupController controller;
    @LocalServerPort
    private int port;
    @Autowired
    private TestRestTemplate restTemplate;
    @Autowired
    private StudentComponent studentComponent;

    @DisplayName("1. Загрузка контекста.")
    @Test
    public void contextLoads() throws Exception {
        assertThat(controller).isNotNull();
    }

    @DisplayName("2. Проверка поиска всех студентов.")
    @SqlTest
    void getAllStudents() throws Exception {
        assertThat(this.restTemplate.getForObject("http://localhost:" + port + "/students",
                String.class)).contains("Bradley");
        assertThat(this.restTemplate.getForObject("http://localhost:" + port + "/students",
                String.class)).contains("Martin");
        mockMvc.perform(get("http://localhost:" + port + "/students"))
                .andExpect(jsonPath("$", hasSize(72)));
    }

    @DisplayName("3. Проверка поиска студента по идентификатору.")
    @SqlTest
    void getStudentById() throws Exception {
        assertThat(this.restTemplate.getForObject("http://localhost:" + port + "/students/1",
                String.class)).contains("Bradley");

        this.mockMvc.perform(get("/students/1"))
                .andDo(print())
                .andExpect(status().isOk())
                .andExpect(jsonPath("$.id", is(1)))
                .andExpect(jsonPath("$.firstName", is("Bradley")))
                .andExpect(jsonPath("$.secondName", is("Alexander")))
                .andExpect(jsonPath("$.lastName", is("Abbe")))
                .andExpect(jsonPath("$.dateBirth", is("1999-03-02")))
                .andExpect(jsonPath("$.gender", is("MALE")))
                .andExpect(jsonPath("$.group", is("Computer Science LEVEL first")));

        assertThat(this.restTemplate.getForObject("http://localhost:" + port + "/students/2",
                String.class)).contains("Martin");

        this.mockMvc.perform(get("/students/2"))
                .andDo(print())
                .andExpect(status().isOk())
                .andExpect(jsonPath("$.id", is(2)))
                .andExpect(jsonPath("$.firstName", is("Martin")))
                .andExpect(jsonPath("$.secondName", is("Quinn")))
                .andExpect(jsonPath("$.lastName", is("Abbett")))
                .andExpect(jsonPath("$.dateBirth", is("2000-04-01")))
                .andExpect(jsonPath("$.gender", is("MALE")))
                .andExpect(jsonPath("$.group", is("Computer Science LEVEL second")));

        assertThat(this.restTemplate.getForObject("http://localhost:" + port + "/students/3",
                String.class)).contains("Max");

        this.mockMvc.perform(get("/students/3"))
                .andDo(print())
                .andExpect(status().isOk())
                .andExpect(jsonPath("$.id", is(3)))
                .andExpect(jsonPath("$.firstName", is("Max")))
                .andExpect(jsonPath("$.secondName", is("Mendoza")))
                .andExpect(jsonPath("$.lastName", is("Alan")))
                .andExpect(jsonPath("$.dateBirth", is("2001-05-02")))
                .andExpect(jsonPath("$.gender", is("MALE")))
                .andExpect(jsonPath("$.group", is("Computer Science LEVEL first")));

        assertThat(this.restTemplate.getForObject("http://localhost:" + port + "/students/4",
                String.class)).contains("Stephen");

        this.mockMvc.perform(get("/students/4"))
                .andDo(print())
                .andExpect(status().isOk())
                .andExpect(jsonPath("$.id", is(4)))
                .andExpect(jsonPath("$.firstName", is("Stephen")))
                .andExpect(jsonPath("$.secondName", is("Allen")))
                .andExpect(jsonPath("$.lastName", is("Alden")))
                .andExpect(jsonPath("$.dateBirth", is("2000-05-12")))
                .andExpect(jsonPath("$.gender", is("MALE")))
                .andExpect(jsonPath("$.group", is("Computer Science LEVEL second")));

        assertThat(this.restTemplate.getForObject("http://localhost:" + port + "/students/5",
                String.class)).contains("Dominic");

        this.mockMvc.perform(get("/students/5"))
                .andDo(print())
                .andExpect(status().isOk())
                .andExpect(jsonPath("$.id", is(5)))
                .andExpect(jsonPath("$.firstName", is("Dominic")))
                .andExpect(jsonPath("$.secondName", is("Clarke")))
                .andExpect(jsonPath("$.lastName", is("Beaner")))
                .andExpect(jsonPath("$.dateBirth", is("2002-06-03")))
                .andExpect(jsonPath("$.gender", is("MALE")))
                .andExpect(jsonPath("$.group", is("Computer Science LEVEL first")));

        assertThat(this.restTemplate.getForObject("http://localhost:" + port + "/students/6",
                String.class)).contains("Theobold");

        this.mockMvc.perform(get("/students/6"))
                .andDo(print())
                .andExpect(status().isOk())
                .andExpect(jsonPath("$.id", is(6)))
                .andExpect(jsonPath("$.firstName", is("Theobold")))
                .andExpect(jsonPath("$.secondName", is("Elliott")))
                .andExpect(jsonPath("$.lastName", is("Bear")))
                .andExpect(jsonPath("$.dateBirth", is("1998-07-04")))
                .andExpect(jsonPath("$.gender", is("MALE")))
                .andExpect(jsonPath("$.group", is("Computer Science LEVEL second")));
    }

    @DisplayName("4. Проверка поиска всех студентов данной группы.")
    @SqlTest
    void getStudentByGroupId() throws Exception {
        assertThat(this.restTemplate.getForObject("http://localhost:" + port + "/students/group/1",
                String.class)).contains("Bradley");

        assertThat(this.restTemplate.getForObject("http://localhost:" + port + "/students/group/1",
                String.class)).contains("Max");

        assertThat(this.restTemplate.getForObject("http://localhost:" + port + "/students/group/1",
                String.class)).contains("Dominic");

        this.mockMvc.perform(get("/students/group/1")).andDo(print()).andExpect(status().isOk())
                .andExpect(content().string(containsString("Robin")));

        this.mockMvc.perform(get("/students/group/1"))
                .andDo(print())
                .andExpect(status().isOk())
                .andExpect(jsonPath("$.*", hasSize(36)));
        this.mockMvc.perform(get("/students/group/2"))
                .andDo(print())
                .andExpect(status().isOk())
                .andExpect(jsonPath("$.*", hasSize(36)));

    }

    @DisplayName("5. Проверка поиска группы студента по его идентификатору.")
    @SqlTest
    void findGroup() throws Exception {
        this.mockMvc.perform(get("/students/1/group"))
                .andDo(print())
                .andExpect(status().isOk())
                .andExpect(jsonPath("$.id", is(1)))
                .andExpect(jsonPath("$.name", is("Computer Science LEVEL first")))
                .andExpect(jsonPath("$.subjects", hasSize(4)));
    }

    @DisplayName("6. Проверка поиска всех предметов студента по Id.")
    @SqlTest
    void findAllSubjects() throws Exception {
        this.mockMvc.perform(get("/students/1/subjects"))
                .andDo(print())
                .andExpect(status().isOk())
                .andExpect(jsonPath("$.*", hasSize(4)));
    }

    @DisplayName("7. Проверка поиска всех студентов по имени.")
    @SqlTest
    void findStudentsByName() throws Exception {
        this.mockMvc.perform(get("/students/name/Dav"))
                .andDo(print())
                .andExpect(status().isOk())
                .andExpect(jsonPath("$.*", hasSize(2)));
    }

    @DisplayName("8. Проверка наличия предмета у студента по Id.")
    @SqlTest
    void checkSubject() throws Exception {
        this.mockMvc.perform(get("/students/1/checksubject")
                        .content("Linear Algebra and Optimization")).andDo(print()).andExpect(status().isOk())
                .andExpect(content().string(containsString("false")));
        this.mockMvc.perform(get("/students/1/checksubject")
                        .content("Introduction to Computational Science and Engineering"))
                .andDo(print())
                .andExpect(status().isOk())
                .andExpect(content().string(containsString("true")));
    }

    @DisplayName("9. Проверка создания студента.")
    @SqlTest
    void createStudent() throws Exception {
        MvcResult result = mockMvc.perform(MockMvcRequestBuilders
                        .post("/students")
                        .content(studentBody)
                        .contentType(MediaType.APPLICATION_JSON)
                        .accept(MediaType.APPLICATION_JSON))
                .andExpect(MockMvcResultMatchers.jsonPath("$.id").exists()).andReturn();
        int idResult = Integer.parseInt(result
                .getResponse()
                .getContentAsString()
                .split(",")[0].split(":")[1]);
        this.mockMvc.perform(get("/students/" + idResult))
                .andDo(print())
                .andExpect(status().isOk())
                .andExpect(jsonPath("$.id", is(idResult)))
                .andExpect(jsonPath("$.firstName", is("FirstName")))
                .andExpect(jsonPath("$.secondName", is("SecondName")))
                .andExpect(jsonPath("$.lastName", is("LastName")))
                .andExpect(jsonPath("$.dateBirth", is("1999-02-14")))
                .andExpect(jsonPath("$.gender", is("MALE")))
                .andExpect(jsonPath("$.group", is("Computer Science LEVEL first")));

        Optional<Student> studentOpt =  studentComponent.getStudentsByName("FirstName").stream().findFirst();
        assertTrue(studentOpt.isPresent());
        Student studentNew = studentOpt.get();
        assertNotNull(studentNew);
        assertEquals("FirstName", studentNew.getFirstName());
        assertEquals("SecondName", studentNew.getSecondName());
        assertEquals("LastName", studentNew.getLastName());
        assertEquals(Gender.MALE, studentNew.getGender());
        assertEquals(1, studentNew.getGroup().getId());
        assertEquals("1999-02-14", studentNew.getDateBirth().toString());
    }

    @DisplayName("10. Проверка удаления студента.")
    @SqlTest
    void deleteById() throws Exception {
        mockMvc.perform(get("http://localhost:" + port + "/students"))
                .andExpect(jsonPath("$", hasSize(72)));
        this.mockMvc.perform(MockMvcRequestBuilders
                        .delete("/students/{id}", 1)
                        .contentType(MediaType.APPLICATION_JSON)
                        .accept(MediaType.APPLICATION_JSON))
                .andExpect(status().isOk());
        mockMvc.perform(get("http://localhost:" + port + "/students"))
                .andExpect(jsonPath("$", hasSize(71)));

        Optional<Student> studentOpt = studentComponent.findById(1L);
        assertTrue(studentOpt.isEmpty());

        assertThrows(AssertionError.class, ()-> {
            this.mockMvc.perform(MockMvcRequestBuilders
                            .delete("/students/{id}", "1")
                            .contentType(MediaType.APPLICATION_JSON)
                            .accept(MediaType.APPLICATION_JSON))
                    .andExpect(status().isInternalServerError())
                    .andExpect(result -> Assertions.assertTrue(
                                    result.getResolvedException() instanceof PersonNotFoundException
                            )
                    )
                    .andExpect(
                            result ->
                                    assertEquals(
                                            "Данный человек не найден. Проверьте параметры поиска.",
                                            result.getResolvedException().getMessage()
                                    )
                    );
        });

    }

    @DisplayName("11. Проверка изменения студента.")
    @SqlTest
    void updateStudent() throws Exception {
         mockMvc.perform(MockMvcRequestBuilders
                        .put("/students/1")
                        .content(studentBody)
                        .contentType(MediaType.APPLICATION_JSON)
                        .accept(MediaType.APPLICATION_JSON));

        this.mockMvc.perform(get("/students/1"))
                .andDo(print())
                .andExpect(status().isOk())
                .andExpect(jsonPath("$.id", is(1)))
                .andExpect(jsonPath("$.firstName", is("FirstName")))
                .andExpect(jsonPath("$.secondName", is("SecondName")))
                .andExpect(jsonPath("$.lastName", is("LastName")))
                .andExpect(jsonPath("$.dateBirth", is("1999-02-14")))
                .andExpect(jsonPath("$.gender", is("MALE")))
                .andExpect(jsonPath("$.group", is("Computer Science LEVEL first")));
    }
}
