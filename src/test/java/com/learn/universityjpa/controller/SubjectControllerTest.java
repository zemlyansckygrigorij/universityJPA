package com.learn.universityjpa.controller;

import com.learn.universityjpa.annotations.SqlTest;
import com.learn.universityjpa.entity.Subject;
import com.learn.universityjpa.repo.SubjectComponent;
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
import static org.hamcrest.Matchers.hasSize;
import static org.hamcrest.Matchers.is;
import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.get;
import static org.springframework.test.web.servlet.result.MockMvcResultHandlers.print;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.jsonPath;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;

@SpringBootTest(webEnvironment = SpringBootTest.WebEnvironment.RANDOM_PORT)
@AutoConfigureMockMvc
class SubjectControllerTest {
    private final String subjectBody = "{\n" +
            "        \"name\":\"getName\",\n" +
            "        \"description\":\"getDescription\"\n" +
            "    \n" +
            "}}";
    @Autowired
    private MockMvc mockMvc;
    @Autowired
    private SubjectController controller;
    @LocalServerPort
    private int port;
    @Autowired
    private TestRestTemplate restTemplate;
    @Autowired
    private SubjectComponent subjectComponent;

    @DisplayName("1. Загрузка контекста.")
    @Test
    public void contextLoads() {
        assertThat(controller).isNotNull();
        assertThat(subjectComponent).isNotNull();
        assertThat(restTemplate).isNotNull();
    }

    @DisplayName("2. Проверка поиска всех предметов.")
    @SqlTest
    void getAllSubjects() throws Exception {
        assertThat(this.restTemplate.getForObject("http://localhost:" + port + "/subjects",
                String.class)).contains(
                        "[{\"id\":1,\"name\":\"Introduction to Computational Science and Engineering\","
                      + "\"description\":\"Follows the same pedagogy as 6.0002 (Introduction "
                      + "to Computational Thinking and Data Science), including Python, "
                      + "but is set in the context of computational science and engineering "
                      + "and uses applications from across science and engineering including dynamics, "
                      + "mechanics, robotics, heat transfer, climate science, chemistry, biology, "
                      + "aerospace and others.\\n\\nThis course provides an introduction "
                      + "to computational algorithms for understanding of scientific phenomena and designing "
                      + "of engineering systems. Topics include: computational algorithms "
                      + "to simulate time-dependent phenomena; optimize and control applications "
                      + "from science and engineering; and quantify uncertainty in problems involving randomness, "
                      + "including an introduction to probability and statistics.\\n\\nCredit cannot also be received "
                      + "for 6.0002. Can be combined with 6.0001 (Introduction to Computer Science Programming "
                      + "in Python) for REST credit.\","
                      + "\"groups\":[1],"
                      + "\"teachers\":[1,3]},"
                      + "{\"id\":2,\"name\":\"Linear Algebra and Optimization\","
                      + "\"description\":\"Covers same fundamental concepts as 18.06 with a view toward modeling, "
                      + "computation, and applications. Integrates geometric, algebraic, and computational "
                      + "viewpoints.\\n\\nUnified introduction to linear algebra and optimization, "
                      + "their interconnections, and applications throughout science and engineering. "
                      + "Topics include: vectors, matrices, eigenvalues, singular values, least-squares, "
                      + "convex optimization, linear/quadratic programming, gradient descent, NewtonвЂ™s method. "
                      + "Viewpoint will emphasize conceptual, geometric, and computational aspects. "
                      + "Applications from many domains, including networks, signal processing, "
                      + "and machine learning.\\n\\nApproved substitution for 18.06 for EECS and Math students.\","
                      + "\"groups\":[2],"
                      + "\"teachers\":[2,4,7,9]},"
                      + "{\"id\":3,\"name\":\"Modeling with Machine Learning\","
                      + "\"description\":\"Teaches students from a range of majors to translate a problem "
                      + "into a machine learning (ML) formulation and find appropriate tools for solving it. "
                      + "Students enroll in two 6-unit modules, run in tandem over the course of "
                      + "a full termвЂ”the common core, which covers ML fundamentals, "
                      + "and one of four discipline-specific modules that build on the core material.\","
                      + "\"groups\":[1],"
                      + "\"teachers\":[1,3,6,8]},"
                      + "{\"id\":4,\"name\":\"Core Module\","
                      + "\"description\":\"Focuses on modeling with machine learning methods with an eye "
                      + "towards applications in engineering and sciences. Students will be introduced "
                      + "to modern machine learning methods, from supervised to unsupervised models, with an emphasis "
                      + "on newer neural approaches. Develops understanding of how and why the methods work from "
                      + "the point of view of modeling, and when they are applicable. Using concrete examples, "
                      + "covers formulation of machine learning tasks, "
                      + "adapting and extending methods to given problems, "
                      + "and how the methods can and should be evaluated. Students taking graduate version complete "
                      + "additional assignments. Enrollment limited.\","
                      + "\"groups\":[2],"
                      + "\"teachers\":[2,5,7,9]},"
                      + "{\"id\":5,\"name\":\"Programming Skills and Computational Thinking in-Context\","
                      + "\"description\":\"Teaches experimental section of 6.0001 (Introduction to Computer Science "
                      + "and Programming in Python) in tandem with the Physics I GIR. In the fall term, one section "
                      + "of ES.801 (ESG version of 8.01, 12 units) is paired with one section of 6.0001 (6 units). "
                      + "Students in these sections will enroll in both subjects and receive separate course grades "
                      + "for each subject. This pilot will generate a library of problems that connect and combine "
                      + "basic computation in Python with basic concepts in the physics GIRs.\","
                      + "\"groups\":[1],"
                      + "\"teachers\":[1,3,6,8]},"
                      + "{\"id\":6,\"name\":\"Nanoelectronics and Computing Systems\","
                      + "\"description\":\"Studies interaction between materials, semiconductor physics, "
                      + "electronic devices, and computing systems. Develops intuition of how transistors operate. "
                      + "Topics range from introductory semiconductor physics to modern state-of-the-art "
                      + "nano-scale devices. Considers how innovations in devices have driven historical progress "
                      + "in computing, and explores ideas for further improvements in devices and computing. "
                      + "Students apply material to understand how building improved computing systems "
                      + "requires knowledge of devices, and how making the correct device requires knowledge "
                      + "of computing systems. Includes a design project for practical application of concepts, "
                      + "and labs for experience building silicon transistors and devices.\","
                      + "\"groups\":[2],"
                      + "\"teachers\":[2,6,8,9]},"
                      + "{\"id\":7,\"name\":\"Fundamentals of Programming\","
                      + "\"description\":\"Introduces fundamental concepts of programming. Designed to develop skills "
                      + "in applying basic methods from programming languages to abstract problems. "
                      + "Topics include programming and Python basics, computational concepts, software engineering, "
                      + "algorithmic techniques, data types, and recursion.  "
                      + "Lab component consists of software design, "
                      + "construction, and implementation of design. Enrollment may be limited.\","
                      + "\"groups\":[1],"
                      + "\"teachers\":[1,4,6,9]}]");

        mockMvc.perform(get("http://localhost:" + port + "/subjects"))
                .andExpect(jsonPath("$", hasSize(7)));
    }

    @DisplayName("3. Проверка поиска всех предметов по имени.")
    @SqlTest
    void findSubjectsByName() throws Exception {
        mockMvc.perform(get("http://localhost:" + port + "/subjects/name/{name}", "Computational"))
                .andExpect(jsonPath("$", hasSize(2)));
    }

    @DisplayName("4. Проверка поиска предмета по идентификатору.")
    @SqlTest
    void getSubjectById() throws Exception {
        this.mockMvc.perform(get("/subjects/1"))
                .andDo(print())
                .andExpect(status().isOk())
                .andExpect(jsonPath("$.id", is(1)))
                .andExpect(
                        jsonPath("$.name", is("Introduction to Computational Science and Engineering")))
                .andExpect(jsonPath("$.description", is(
                           "Follows the same pedagogy as 6.0002 (Introduction to Computational Thinking and Data "
                           +    "Science), including Python, but is set in the context of computational science and "
                           +    "engineering and uses applications from across science and engineering "
                           +    "including dynamics, mechanics, robotics, heat transfer, climate science, "
                           +    "chemistry, biology, aerospace and others.\n\nThis course provides "
                           +    "an introduction to computational algorithms for understanding of scientific "
                           +    "phenomena and designing of engineering systems. Topics include: "
                           +    "computational algorithms to simulate time-dependent phenomena; "
                           +    "optimize and control applications from science and engineering; "
                           +    "and quantify uncertainty in problems involving randomness, "
                           +    "including an introduction to probability and statistics.\n\n"
                           +    "Credit cannot also be received for 6.0002. Can be combined with 6.0001 "
                           +    "(Introduction to Computer Science Programming in Python) for REST credit.")))
                .andExpect(jsonPath("$.groups", hasSize(1)))
                .andExpect(jsonPath("$.teachers", hasSize(2)));
    }

    @DisplayName("5. Проверка создания предмета.")
    @SqlTest
    void createSubject() throws Exception {
        MvcResult result = mockMvc.perform(MockMvcRequestBuilders
                        .post("/subjects")
                        .content(subjectBody)
                        .contentType(MediaType.APPLICATION_JSON)
                        .accept(MediaType.APPLICATION_JSON))
                .andExpect(MockMvcResultMatchers.jsonPath("$.id").exists())
                .andReturn();
        int idResult = Integer.parseInt(result
                .getResponse()
                .getContentAsString()
                .split(",")[0].split(":")[1]);
        this.mockMvc.perform(get("/subjects/" + idResult))
                .andDo(print())
                .andExpect(status().isOk())
                .andExpect(jsonPath("$.name", is("getName")))
                .andExpect(jsonPath("$.description", is("getDescription")))
                .andExpect(jsonPath("$.groups", hasSize(0)))
                .andExpect(jsonPath("$.teachers", hasSize(0)));

        Optional<Subject> subjectOpt =  subjectComponent.findById((long) idResult);
        Assertions.assertTrue(subjectOpt.isPresent());
        Subject subjectNew = subjectOpt.get();
        Assertions.assertNotNull(subjectNew);
        assertEquals("getName", subjectNew.getName());
        assertEquals("getDescription", subjectNew.getDescription());

    }

    @DisplayName("6. Проверка удаления предмета по идентификатору.")
    @SqlTest
    void deleteById() throws Exception {
        mockMvc.perform(get("http://localhost:" + port + "/subjects"))
                .andExpect(jsonPath("$", hasSize(7)));
        this.mockMvc.perform(MockMvcRequestBuilders
                        .delete("/subjects/{id}", 1)
                        .contentType(MediaType.APPLICATION_JSON)
                        .accept(MediaType.APPLICATION_JSON))
                .andExpect(status().isOk());
        mockMvc.perform(get("http://localhost:" + port + "/subjects"))
                .andExpect(jsonPath("$", hasSize(6)));

        Optional<Subject> subjectOpt =  subjectComponent.findById(1L);
        Assertions.assertTrue(subjectOpt.isEmpty());
    }


    @DisplayName("7. Проверка изменения предмета.")
    @SqlTest
    void updateSubject() throws Exception {
        this.mockMvc.perform(MockMvcRequestBuilders
                .put("/subjects/1")
                .content(subjectBody)
                .contentType(MediaType.APPLICATION_JSON)
                .accept(MediaType.APPLICATION_JSON));

        this.mockMvc.perform(get("/subjects/1"))
                .andDo(print())
                .andExpect(status().isOk())
                .andExpect(jsonPath("$.id", is(1)))
                .andExpect(jsonPath("$.name", is("getName")))
                .andExpect(jsonPath("$.description", is("getDescription")));
    }
}
