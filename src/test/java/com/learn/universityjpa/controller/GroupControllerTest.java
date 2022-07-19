package com.learn.universityjpa.controller;

import com.fasterxml.jackson.databind.ObjectMapper;
import com.learn.universityjpa.annotations.SqlTest;
import com.learn.universityjpa.controller.model.request.GroupRequest;
import com.learn.universityjpa.entity.Group;
import com.learn.universityjpa.exceptions.GroupHasStudentsException;
import com.learn.universityjpa.repo.GroupComponent;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.AutoConfigureMockMvc;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.boot.test.web.client.TestRestTemplate;
import org.springframework.boot.web.server.LocalServerPort;
import org.springframework.http.MediaType;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.test.web.servlet.request.MockMvcRequestBuilders;
import org.springframework.web.util.NestedServletException;

import java.util.Optional;

import static org.assertj.core.api.Assertions.assertThat;

import static org.hamcrest.Matchers.aMapWithSize;
import static org.hamcrest.Matchers.containsString;
import static org.hamcrest.Matchers.hasSize;
import static org.hamcrest.Matchers.is;

import static org.junit.Assert.assertNotNull;
import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertThrows;
import static org.junit.jupiter.api.Assertions.assertTrue;

import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.get;
import static org.springframework.test.web.servlet.result.MockMvcResultHandlers.print;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.content;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.jsonPath;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;


@SpringBootTest(webEnvironment = SpringBootTest.WebEnvironment.RANDOM_PORT)
@AutoConfigureMockMvc
class GroupControllerTest {
    @Autowired
    private MockMvc mockMvc;
    @Autowired
    private GroupController controller;
    @LocalServerPort
    private int port;
    @Autowired
    private TestRestTemplate restTemplate;
    @Autowired
    private GroupComponent groupComponent;
    @Test
    public void contextLoads() throws Exception {
        assertThat(controller).isNotNull();
    }

    @DisplayName("1. Проверка поиска всех групп.")
    @SqlTest
    void getAllGroups() throws Exception {
        assertThat(this.restTemplate.getForObject("http://localhost:" + port + "/groups",
                String.class)).contains("Computer Science LEVEL first", "specification");
        assertThat(this.restTemplate.getForObject("http://localhost:" + port + "/groups",
                String.class)).contains("Computer Science LEVEL second");
        mockMvc.perform(get("http://localhost:" + port + "/groups"))
                .andExpect(jsonPath("$", hasSize(2)));
    }

    @DisplayName("2. Проверка поиска группы по Id.")
    @SqlTest
    void getGroupById() throws Exception {
        this.mockMvc.perform(get("/groups/1"))
                .andDo(print())
                .andExpect(status().isOk())
                .andExpect(content().string(containsString("Computer Science LEVEL first")))
                .andExpect(jsonPath("$.name").value("Computer Science LEVEL first"))
                .andExpect(jsonPath("$", aMapWithSize(5)))
                .andExpect(jsonPath("$.id", is(1)))
                .andExpect(jsonPath("$.name", is("Computer Science LEVEL first")))
                .andExpect(jsonPath("$.students", hasSize(36)))
                .andExpect(jsonPath("$.subjects", hasSize(4)));

        this.mockMvc.perform(get("/groups/2"))
                .andDo(print())
                .andExpect(status().isOk())
                .andExpect(content().string(containsString("Computer Science LEVEL second")))
                .andExpect(jsonPath("$.name").value("Computer Science LEVEL second"))
                .andExpect(jsonPath("$", aMapWithSize(5)))
                .andExpect(jsonPath("$.id", is(2)))
                .andExpect(jsonPath("$.name", is("Computer Science LEVEL second")))
                .andExpect(jsonPath("$.students", hasSize(36)))
                .andExpect(jsonPath("$.subjects", hasSize(3)));

    }

    @DisplayName("3. Проверка поиска всех предметов группы по Id.")
    @SqlTest
    void getSubjectsByGroupId() throws Exception {
        this.mockMvc
                .perform(get("/groups/1/subjects"))
                .andDo(print())
                .andExpect(status().isOk())
                .andExpect(content().string(containsString("Introduction to Computational Science and Engineering")))
                .andExpect(content().string(containsString("Modeling with Machine Learning")))
                .andExpect(content().string(containsString("Programming Skills and Computational Thinking in-Context")))
                .andExpect(content().string(containsString("Fundamentals of Programming")))
                .andExpect(jsonPath("$", hasSize(4)));
    }

    @DisplayName("4. Проверка наличия предмета в группе по Id.")
    @SqlTest
    void checkSubject() throws Exception {
        this.mockMvc.perform(get("/groups/1/checksubject")
                        .content("Introduction to Computational Science and Engineering"))
                .andDo(print())
                .andExpect(status().isOk())
                .andExpect(content().string(containsString("true")));
        this.mockMvc.perform(get("/groups/1/checksubject")
                        .content("Linear Algebra and Optimization"))
                .andDo(print())
                .andExpect(status().isOk())
                .andExpect(content().string(containsString("false")));
    }

    @DisplayName("5. Проверка поиска всех студентов группы по Id.")
    @SqlTest
    void getStudentsByGroupId() throws Exception {
        this.mockMvc.perform(get("/groups/1/students"))
                .andDo(print())
                .andExpect(status().isOk())
                .andExpect(jsonPath("$", hasSize(36)));
    }

    @DisplayName("6. Проверка создания группы.")
    @SqlTest
    void createGroup() throws Exception {
        Group group = new Group();
        group.setName("testName");
        group.setSpecification("testSpecification");
        group.setId(21L);

        mockMvc.perform(MockMvcRequestBuilders
                        .post("/groups")
                        .content(asJsonString(group))
                        .contentType(MediaType.APPLICATION_JSON)
                        .accept(MediaType.APPLICATION_JSON))
                .andExpect(jsonPath("$.id").exists()).andReturn();

        this.mockMvc.perform(get("/groups")).andDo(print()).andExpect(status().isOk())
                .andExpect(content().string(containsString("testName")))
                .andExpect(content().string(containsString("testSpecification")))
                .andExpect(jsonPath("$", hasSize(3)));
        Optional<Group> groupOpt = groupComponent.findByName("testName").stream().findFirst();
        assertNotNull(groupOpt.get());
    }

    @DisplayName("7. Проверка удаления группы по Id.")
    @SqlTest
    void deleteById() throws Exception {
        Group group = new Group();
        group.setName("testName");
        group.setSpecification("testSpecification");
        group.setId(21L);

        mockMvc.perform(MockMvcRequestBuilders
                        .post("/groups")
                        .content(asJsonString(group))
                        .contentType(MediaType.APPLICATION_JSON)
                        .accept(MediaType.APPLICATION_JSON))
                .andExpect(jsonPath("$.id").exists()).andReturn();

        mockMvc.perform(get("http://localhost:" + port + "/groups"))
                .andExpect(jsonPath("$", hasSize(3)));

        Optional<Group> groupOpt = groupComponent.findByName("testName").stream().findFirst();
        assertTrue(groupOpt.isPresent());
        Group groupNew = groupOpt.get();
        this.mockMvc.perform(MockMvcRequestBuilders
                        .delete("/groups/{id}", groupNew.getId())
                        .contentType(MediaType.APPLICATION_JSON)
                        .accept(MediaType.APPLICATION_JSON))
                .andExpect(status().isOk());
        mockMvc.perform(get("http://localhost:" + port + "/groups"))
                .andExpect(jsonPath("$", hasSize(2)));
        assertEquals(2, groupComponent.findAll().size());
        Optional<Group> groupOptNew = groupComponent.findByName("Name").stream().findFirst();
        assertTrue(groupOptNew.isEmpty());
    }

    @DisplayName("8. Проверка удаления группы, содержащей студентов по Id.")
    @SqlTest
    void deleteByIdGroupWithStudents() throws Exception {

        assertThrows(NestedServletException.class, ()-> {
            this.mockMvc.perform(MockMvcRequestBuilders
                            .delete("/groups/{id}", "1")
                            .contentType(MediaType.APPLICATION_JSON)
                            .accept(MediaType.APPLICATION_JSON))
                    .andExpect(status().isInternalServerError())
                    .andExpect(result -> assertTrue(result.getResolvedException() instanceof GroupHasStudentsException))
                    .andExpect(
                            result ->
                                    assertEquals(
                                            "Удаление данной группы невозможно. Она содержит студентов.",
                                            result.getResolvedException().getMessage()
                                    )
                    );
        });
    }

    @DisplayName("9. Проверка изменения группы по Id.")
    @SqlTest
    public void changeGroup() throws Exception {
        Group group1 = new Group();
        group1.setName("testName3");
        group1.setSpecification("testSpecification3");
        group1.setId(1L);
        GroupRequest groupRequest = new GroupRequest(group1);
        mockMvc.perform(MockMvcRequestBuilders
                        .put("/groups/1")
                        .content(asJsonString(groupRequest))
                        .contentType(MediaType.APPLICATION_JSON)
                        .accept(MediaType.APPLICATION_JSON));
        this.mockMvc.perform(get("/groups/1")).andDo(print()).andExpect(status().isOk())
                .andExpect(content().string(containsString("testName3")))
                .andExpect(content().string(containsString("testSpecification3")))
                .andExpect(jsonPath("$.name", is("testName3")))
                .andExpect(jsonPath("$.specification", is("testSpecification3")));
    }

    @DisplayName("10. Проверка добавления предмета в группу по Id.")
    @SqlTest
    void addSubject() throws Exception {
        this.mockMvc.perform(get("/groups/1/subjects"))
                .andDo(print())
                .andExpect(status().isOk())
                .andExpect(jsonPath("$", hasSize(4)));

        mockMvc.perform(MockMvcRequestBuilders
                .put("/groups/1/addSubject/2"));

        this.mockMvc.perform(get("/groups/1/subjects"))
                .andDo(print())
                .andExpect(status().isOk())
                .andExpect(jsonPath("$", hasSize(5)))
                .andExpect(content().string(containsString("Linear Algebra and Optimization")));
    }

    @DisplayName("11. Проверка удаления предмета из группы по Id.")
    @SqlTest
    void deleteSubject() throws Exception {
        this.mockMvc.perform(get("/groups/1/subjects"))
                .andDo(print())
                .andExpect(status().isOk())
                .andExpect(jsonPath("$", hasSize(4)));

        mockMvc.perform(MockMvcRequestBuilders
                .put("/groups/1/deleteSubject/1"));

        this.mockMvc.perform(get("/groups/1/subjects"))
                .andDo(print())
                .andExpect(status().isOk())
                .andExpect(jsonPath("$", hasSize(3)));
    }

    public static String asJsonString(final Object obj) {
        try {
            return new ObjectMapper().writeValueAsString(obj);
        } catch (Exception e) {
            throw new RuntimeException(e);
        }
    }
}
