package com.quickbase.controller

import org.junit.Ignore
import org.junit.runner.RunWith
import org.springframework.beans.factory.annotation.Autowired
import org.springframework.boot.test.autoconfigure.web.servlet.AutoConfigureMockMvc
import org.springframework.boot.test.context.SpringBootTest
import org.springframework.test.context.junit4.SpringRunner
import org.springframework.test.web.servlet.MockMvc

@RunWith(SpringRunner::class)
@SpringBootTest
@AutoConfigureMockMvc
@Ignore
class QuickbaseControllerValidationTest {

    @Autowired
    private val mockMvc: MockMvc? = null

    //    @Test
    //    public void should_validate_basic_change_details_missing_change_type() throws Exception {
    //        Table table = new Table("TEST_TABLE", new ArrayList<>(), null);
    //        mockMvc.perform(MockMvcRequestBuilders.post("/quickbase/generate")
    //                .accept(MediaType.APPLICATION_XML)
    //                .contentType(MediaType.APPLICATION_JSON)
    //                .content(asJson(
    //                        ChangeLogRequest.Companion.builder()
    //                                .withChangeSetId("changeSetId")
    //                                .withAuthor("author")
    //                                .withContexts(Collections.singletonList("test_context"))
    //                                .withTable(table)
    //                                .build())))
    //                .andExpect(status().isBadRequest());
    //    }
    //
    //    @Test
    //    public void should_validate_basic_change_details_missing_author() throws Exception {
    //        Table table = new Table("TEST_TABLE", new ArrayList<>(), null);
    //        mockMvc.perform(MockMvcRequestBuilders.post("/quickbase/generate")
    //                .accept(MediaType.APPLICATION_XML)
    //                .contentType(MediaType.APPLICATION_JSON)
    //                .content(asJson(
    //                        ChangeLogRequest.Companion.builder()
    //                                .withType(ChangeType.DROP_TABLE)
    //                                .withChangeSetId("changeSetId")
    //                                .withContexts(Collections.singletonList("test_context"))
    //                                .withTable(table)
    //                                .build())))
    //                .andExpect(status().isBadRequest());
    //    }
    //
    //    @Test
    //    public void should_validate_basic_change_details_missing_contexts() throws Exception {
    //        Table table = new Table("TEST_TABLE", new ArrayList<>(), null);
    //        mockMvc.perform(MockMvcRequestBuilders.post("/quickbase/generate")
    //                .accept(MediaType.APPLICATION_XML)
    //                .contentType(MediaType.APPLICATION_JSON)
    //                .content(asJson(
    //                        ChangeLogRequest.Companion.builder()
    //                                .withType(ChangeType.DROP_TABLE)
    //                                .withChangeSetId("changeSetId")
    //                                .withAuthor("author")
    //                                .withTable(table)
    //                                .build())))
    //                .andExpect(status().isBadRequest());
    //    }
    //
    //    @Test
    //    @Ignore
    //    public void should_validate_valid_table_name() throws Exception {
    //        Table table = new Table("TEST_TABLE", new ArrayList<>(), null);
    //        mockMvc.perform(MockMvcRequestBuilders.post("/quickbase/generate")
    //                .accept(MediaType.APPLICATION_XML)
    //                .contentType(MediaType.APPLICATION_JSON)
    //                .content(asJson(
    //                        ChangeLogRequest.Companion.builder()
    //                                .withType(ChangeType.DROP_TABLE)
    //                                .withChangeSetId("changeSetId")
    //                                .withAuthor("author")
    //                                .withContexts(Collections.singletonList("test_context"))
    //                                .withTable(table)
    //                                .build())))
    //                .andExpect(status().isBadRequest());
    //    }
    //
    //    private String asJson(Object object) throws JsonProcessingException {
    //        return new ObjectMapper().writeValueAsString(object);
    //    }
}
