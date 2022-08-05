package com.definex.myweb;

import com.definex.myweb.entity.Department;
import com.definex.myweb.service.DepartmentService;
import com.fasterxml.jackson.databind.ObjectMapper;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.WebMvcTest;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.http.MediaType;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.test.web.servlet.ResultActions;

import java.util.ArrayList;
import java.util.List;

import static org.hamcrest.CoreMatchers.is;
import static org.hamcrest.Matchers.hasSize;
import static org.mockito.ArgumentMatchers.any;
import static org.mockito.BDDMockito.given;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.get;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.post;
import static org.springframework.test.web.servlet.result.MockMvcResultHandlers.print;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.jsonPath;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;

@WebMvcTest
public class DepartmentControllerTests {

    @MockBean
    private DepartmentService departmentService;
    @Autowired
    private MockMvc mockMvc;

    @Autowired
    private ObjectMapper objectMapper;

    @Test
    public void givenDepartmentsObject_whenCreateDepartment_thenReturnSavedDepartment() throws Exception{


        Department department = Department.builder()
                .departmentName("ETC")
                .departmentCode("EC-02")
                .departmentAddress("A-Block")
                .build();
        given(departmentService.saveDepartment(any(Department.class)))
                .willAnswer((invocation)-> invocation.getArgument(0));


        ResultActions response = mockMvc.perform(post("/departments")
                .contentType(MediaType.APPLICATION_JSON)
                .content(objectMapper.writeValueAsString(department)));


        response.andDo(print()).
                andExpect(status().isOk())
                .andExpect(jsonPath("$.departmentName",
                        is(department.getDepartmentName())))
                .andExpect(jsonPath("$.departmentCode",
                        is(department.getDepartmentCode())))
                .andExpect(jsonPath("$.departmentAddress",
                        is(department.getDepartmentAddress())));

    }

    @Test
    public void givenListOfDepartments_whenGetAllDepartments_thenReturnDepartmentsList() throws Exception{
        // given - precondition or setup
        List<Department> listOfDepartments = new ArrayList<>();
        listOfDepartments.add(Department.builder().departmentName("ETC").departmentCode("EC-02").departmentAddress("A-Block").build());
        given(departmentService.getAllDepartment()).willReturn(listOfDepartments);
        //when(departmentService.findAllDepartment()).thenReturn(listOfDepartments);

        // when -  action or the behaviour that we are going test
        ResultActions response = mockMvc.perform(get("/getEmployees"));
        System.out.println("departmentResponse" + response);


                 response.andExpect(status().isOk())
                .andDo(print())
                .andExpect(jsonPath("$", hasSize(1)));
                 // is(listOfDepartments.size());


    }
}
