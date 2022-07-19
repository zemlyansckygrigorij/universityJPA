package com.learn.universityjpa.controller;

import com.learn.universityjpa.annotations.SqlTest;
import com.learn.universityjpa.entity.Gender;
import com.learn.universityjpa.entity.Teacher;
import com.learn.universityjpa.exceptions.PersonNotFoundException;
import com.learn.universityjpa.repo.TeacherComponent;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.DisplayName;
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
import org.springframework.transaction.annotation.Transactional;
import org.springframework.web.util.NestedServletException;
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
class TeacherControllerTest {

    private String teacherBody = "{\n" +
            "        \"firstName\":\"FirstName\",\n" +
            "        \"secondName\":\"SecondName\",\n" +
            "        \"lastName\": \"LastName\",\n" +
            "        \"category\":\"testCategory\",\n" +
            "        \"dateBirth\":\"1999-02-14\",\n" +
            "        \"gender\":\"MALE\",\n" +
            "        \"subjects\":[]\n" +
            "}";

    @Autowired
    private MockMvc mockMvc;
    @LocalServerPort
    private int port;
    @Autowired
    private TestRestTemplate restTemplate;
    @Autowired
    private TeacherComponent teacherComponent;


    @DisplayName("1. Проверка поиска всех преподавателей.")
    @SqlTest
    void getAllTeachers() throws Exception {
        assertThat(this.restTemplate.getForObject("http://localhost:" + port + "/teachers",
                String.class)).contains("Oliver");
        assertThat(this.restTemplate.getForObject("http://localhost:" + port + "/teachers",
                String.class)).contains("Harry");
        mockMvc.perform(get("http://localhost:" + port + "/teachers"))
                .andExpect(jsonPath("$", hasSize(9)));
    }

    @DisplayName("2. Проверка поиска преподавателя по идентификатору.")
    @SqlTest
    void getTeacherById() throws Exception {
       assertThat(this.restTemplate.getForObject("http://localhost:" + port + "/teachers/1",
                String.class)).contains("Oliver");

        this.mockMvc.perform(get("/teachers/1"))
                .andDo(print())
                .andExpect(status().isOk())
                .andExpect(jsonPath("$.id", is(1)))
                .andExpect(jsonPath("$.firstName", is("Oliver")))
                .andExpect(jsonPath("$.secondName", is("Alexander")))
                .andExpect(jsonPath("$.lastName", is("Williams")))
                .andExpect(jsonPath("$.dateBirth", is("1999-03-02")))
                .andExpect(jsonPath("$.gender", is("MALE")))
                .andExpect(jsonPath("$.category", is("first")));

        assertThat(this.restTemplate.getForObject("http://localhost:" + port + "/teachers/2",
                String.class)).contains("Harry");

        this.mockMvc.perform(get("/teachers/2"))
                .andDo(print())
                .andExpect(status().isOk())
                .andExpect(jsonPath("$.id", is(2)))
                .andExpect(jsonPath("$.firstName", is("Harry")))
                .andExpect(jsonPath("$.secondName", is("Quinn")))
                .andExpect(jsonPath("$.lastName", is("Peters")))
                .andExpect(jsonPath("$.dateBirth", is("2000-04-01")))
                .andExpect(jsonPath("$.gender", is("MALE")))
                .andExpect(jsonPath("$.category", is("Second")));

        assertThat(this.restTemplate.getForObject("http://localhost:" + port + "/teachers/3",
                String.class)).contains("Jack");

        this.mockMvc.perform(get("/teachers/3"))
                .andDo(print())
                .andExpect(status().isOk())
                .andExpect(jsonPath("$.id", is(3)))
                .andExpect(jsonPath("$.firstName", is("Jack")))
                .andExpect(jsonPath("$.secondName", is("Mendoza")))
                .andExpect(jsonPath("$.lastName", is("Gibson")))
                .andExpect(jsonPath("$.dateBirth", is("2001-05-02")))
                .andExpect(jsonPath("$.gender", is("MALE")))
                .andExpect(jsonPath("$.category", is("Third")));

        assertThat(this.restTemplate.getForObject("http://localhost:" + port + "/teachers/4",
                String.class)).contains("Jacob");

        this.mockMvc.perform(get("/teachers/4"))
                .andDo(print())
                .andExpect(status().isOk())
                .andExpect(jsonPath("$.id", is(4)))
                .andExpect(jsonPath("$.firstName", is("Jacob")))
                .andExpect(jsonPath("$.secondName", is("Allen")))
                .andExpect(jsonPath("$.lastName", is("Martin")))
                .andExpect(jsonPath("$.dateBirth", is("2000-05-12")))
                .andExpect(jsonPath("$.gender", is("MALE")))
                .andExpect(jsonPath("$.category", is("first")));

        assertThat(this.restTemplate.getForObject("http://localhost:" + port + "/teachers/5",
                String.class)).contains("Charley");

        this.mockMvc.perform(get("/teachers/5"))
                .andDo(print())
                .andExpect(status().isOk())
                .andExpect(jsonPath("$.id", is(5)))
                .andExpect(jsonPath("$.firstName", is("Charley")))
                .andExpect(jsonPath("$.secondName", is("Clarke")))
                .andExpect(jsonPath("$.lastName", is("Jordan")))
                .andExpect(jsonPath("$.dateBirth", is("2002-06-03")))
                .andExpect(jsonPath("$.gender", is("MALE")))
                .andExpect(jsonPath("$.category", is("Second")));

        assertThat(this.restTemplate.getForObject("http://localhost:" + port + "/teachers/6",
                String.class)).contains("Thomas");

        this.mockMvc.perform(get("/teachers/6"))
                .andDo(print())
                .andExpect(status().isOk())
                .andExpect(jsonPath("$.id", is(6)))
                .andExpect(jsonPath("$.firstName", is("Thomas")))
                .andExpect(jsonPath("$.secondName", is("Elliott")))
                .andExpect(jsonPath("$.lastName", is("Jackson")))
                .andExpect(jsonPath("$.dateBirth", is("1998-07-04")))
                .andExpect(jsonPath("$.gender", is("MALE")))
                .andExpect(jsonPath("$.category", is("Third")));
    }

    @DisplayName("3. Проверка поиска преподавателей по имени.")
    @SqlTest
    void findTeachersByName() throws Exception {
        this.mockMvc.perform(get("/teachers/name/Dav"))
                .andDo(print())
                .andExpect(status().isOk())
                .andExpect(jsonPath("$.*", hasSize(1)));
    }

    @DisplayName("4. Проверка создания преподавателя.")
    @SqlTest
    void createTeacher() throws Exception {
        MvcResult result = mockMvc.perform(MockMvcRequestBuilders
                        .post("/teachers")
                        .content(teacherBody)
                        .contentType(MediaType.APPLICATION_JSON)
                        .accept(MediaType.APPLICATION_JSON))
                .andExpect(MockMvcResultMatchers.jsonPath("$.id").exists()).andReturn();
        int idResult = Integer.parseInt(result
                .getResponse()
                .getContentAsString()
                .split(",")[0].split(":")[1]);
        this.mockMvc.perform(get("/teachers/" + idResult))
                .andDo(print())
                .andExpect(status().isOk())
                .andExpect(jsonPath("$.id", is(idResult)))
                .andExpect(jsonPath("$.firstName", is("FirstName")))
                .andExpect(jsonPath("$.secondName", is("SecondName")))
                .andExpect(jsonPath("$.lastName", is("LastName")))
                .andExpect(jsonPath("$.dateBirth", is("1999-02-14")))
                .andExpect(jsonPath("$.gender", is("MALE")))
                .andExpect(jsonPath("$.category", is("testCategory")));

        Optional<Teacher> teacherOpt =  teacherComponent.findById(Long.valueOf(idResult));
        assertTrue(teacherOpt.isPresent());
        Teacher teacherNew = teacherOpt.get();
        assertNotNull(teacherNew);
        assertEquals("FirstName", teacherNew.getFirstName());
        assertEquals("SecondName", teacherNew.getSecondName());
        assertEquals("LastName", teacherNew.getLastName());
        assertEquals(Gender.MALE, teacherNew.getGender());
        assertEquals("testCategory", teacherNew.getCategory());
        assertEquals("1999-02-14", teacherNew.getDateBirth().toString());
    }

    @DisplayName("5. Проверка удаления преподавателя.")
    @SqlTest
    void deleteById() throws Exception {
        mockMvc.perform(get("http://localhost:" + port + "/teachers"))
                .andExpect(jsonPath("$", hasSize(9)));
        this.mockMvc.perform(MockMvcRequestBuilders
                        .delete("/teachers/{id}", 1)
                        .contentType(MediaType.APPLICATION_JSON)
                        .accept(MediaType.APPLICATION_JSON))
                .andExpect(status().isOk());
        mockMvc.perform(get("http://localhost:" + port + "/teachers"))
                .andExpect(jsonPath("$", hasSize(8)));

        Optional<Teacher> teacherOpt = teacherComponent.findById(1L);
        assertTrue(teacherOpt.isEmpty());


        assertThrows(NestedServletException.class, ()-> {
            this.mockMvc.perform(MockMvcRequestBuilders
                            .delete("/teachers/{id}", "1")
                            .contentType(MediaType.APPLICATION_JSON)
                            .accept(MediaType.APPLICATION_JSON))
                    .andExpect(status().isInternalServerError())
                    .andExpect(result -> Assertions.assertTrue(
                            result.getResolvedException() instanceof PersonNotFoundException)
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

    @DisplayName("6. Проверка изменения преподавателя.")
    @SqlTest
    void updateTeacher() throws Exception {
        mockMvc.perform(MockMvcRequestBuilders
                .put("/teachers/1")
                .content(teacherBody)
                .contentType(MediaType.APPLICATION_JSON)
                .accept(MediaType.APPLICATION_JSON));

        this.mockMvc.perform(get("/teachers/1"))
                .andDo(print())
                .andExpect(status().isOk())
                .andExpect(jsonPath("$.id", is(1)))
                .andExpect(jsonPath("$.firstName", is("FirstName")))
                .andExpect(jsonPath("$.secondName", is("SecondName")))
                .andExpect(jsonPath("$.lastName", is("LastName")))
                .andExpect(jsonPath("$.dateBirth", is("1999-02-14")))
                .andExpect(jsonPath("$.gender", is("MALE")))
                .andExpect(jsonPath("$.category", is("testCategory")));
    }

    @DisplayName("7.  Проверка поиска всех предметов преподавателя по Id.")
    @SqlTest
    void findAllSubjects() throws Exception {
        this.mockMvc
                .perform(get("/teachers/1/subjects"))
                .andDo(print())
                .andExpect(status().isOk())
                .andExpect(content()
                        .string(containsString("Introduction to Computational Science and Engineering")))
                .andExpect(content()
                        .string(containsString("Modeling with Machine Learning")))
                .andExpect(content()
                        .string(containsString("Programming Skills and Computational Thinking in-Context")))
                .andExpect(content()
                        .string(containsString("Fundamentals of Programming")))
                .andExpect(jsonPath("$", hasSize(4)));
    }

    @DisplayName("8. Проверка наличия предмета у преподавателя по Id.")
    @SqlTest
    void checkSubject() throws Exception {
        this.mockMvc.perform(get("/teachers/1/checksubject")
                        .content("Introduction to Computational Science and Engineering"))
                .andDo(print())
                .andExpect(status().isOk())
                .andExpect(content().string(containsString("true")));
        this.mockMvc.perform(get("/teachers/1/checksubject")
                        .content("Linear Algebra and Optimization")).andDo(print()).andExpect(status().isOk())
                .andExpect(content().string(containsString("false")));
    }

    @DisplayName("9. Проверка добавления предмета  преподавателю по Id.")
    @SqlTest
    @Transactional
    void addSubject() throws Exception {

        this.mockMvc.perform(get("/teachers/1/checksubject")
                        .content("Linear Algebra and Optimization")).andDo(print()).andExpect(status().isOk())
                .andExpect(content().string(containsString("false")));

            mockMvc.perform(MockMvcRequestBuilders
                    .put("/teachers/1/addSubject/2"));

        Teacher teacher1New = teacherComponent.findByIdOrDie(1L);
        assertEquals(5, teacher1New.getSubjects().size());

        this.mockMvc.perform(get("/teachers/1/checksubject")
                        .content("Linear Algebra and Optimization"))
                .andDo(print())
                .andExpect(status().isOk())
                .andExpect(content().string(containsString("true")));
    }

    @DisplayName("10. Проверка удаления предмета у преподавателю по Id.")
    @SqlTest
    @Transactional
    void deleteSubject() throws Exception {

        this.mockMvc.perform(get("/teachers/1/subjects"))
                .andDo(print())
                .andExpect(status().isOk())
                .andExpect(jsonPath("$", hasSize(4)));

        this.mockMvc.perform(get("/groups/1/checksubject")
                        .content("Introduction to Computational Science and Engineering"))
                .andDo(print())
                .andExpect(status().isOk())
                .andExpect(content().string(containsString("true")));

        mockMvc.perform(MockMvcRequestBuilders
                .put("/teachers/1/deleteSubject/1"));

        Teacher teacher1New = teacherComponent.findByIdOrDie(1L);
        assertEquals(3, teacher1New.getSubjects().size());
        this.mockMvc.perform(get("/teachers/1/subjects"))
                .andDo(print())
                .andExpect(status().isOk())
                .andExpect(jsonPath("$", hasSize(3)));
    }
}
