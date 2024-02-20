package com.anikulin.we;

import com.anikulin.we.domain.Employee;
import com.anikulin.we.dto.WorkTimeResponse;
import com.anikulin.we.service.EmployeeService;
import io.restassured.RestAssured;
import io.restassured.http.ContentType;
import org.junit.jupiter.api.BeforeAll;
import org.junit.jupiter.api.Test;
import org.mockito.Mock;
import org.springframework.boot.test.context.SpringBootTest;

import java.time.LocalDateTime;
import java.time.LocalTime;
import java.util.List;

import static org.hamcrest.Matchers.equalTo;
import static org.mockito.Mockito.when;

@SpringBootTest(webEnvironment = SpringBootTest.WebEnvironment.DEFINED_PORT)
public class ControllerTest {

    @Mock
    private EmployeeService employeeService;

    @BeforeAll
    static void beforeAll(){
        RestAssured.baseURI = "http://localhost";
        RestAssured.port = 8080;
    }

    @Test
    public void testGetMaxExperienceEmployee() {

        //Given
        Employee employee1 = Employee.builder()
                .id(1L)
                .dateStart(LocalDateTime.of(2024, 12, 6, 12, 33))
                .dateEnd(LocalDateTime.of(2024, 12, 6, 12, 35))
                .build();

        Employee employee2 = Employee.builder()
                .id(2L)
                .dateStart(LocalDateTime.of(2024, 12, 6, 12, 33))
                .dateEnd(LocalDateTime.of(2024, 12, 6, 12, 36))
                .build();
        List<Employee> employees = List.of(employee1, employee2);

        WorkTimeResponse expectedResponse = WorkTimeResponse.builder()
                .id(2L)
                .time(LocalTime.of(0, 3))
                .build();

        when(employeeService.getMaxExperienceEmployee(employees)).thenReturn(expectedResponse);

        String response =
                "{\n" +
                "    \"employees\": [\n" +
                "        {\n" +
                "            \"id\": 1,\n" +
                "            \"dateStart\": \"2024-12-06T12:33:00\",\n" +
                "            \"dateEnd\": \"2024-12-06T12:35:00\"\n" +
                "        },\n" +
                "        {\n" +
                "            \"id\": 2,\n" +
                "            \"dateStart\": \"2024-12-06T12:33:00\",\n" +
                "            \"dateEnd\": \"2024-12-06T12:36:00\"\n" +
                "        }\n" +
                "    ]\n" +
                "}";

        //Then

        RestAssured.given()
                .contentType(ContentType.JSON)
                .body(response)
                .post("/")
                .then()
                .statusCode(200)
                .body("id", equalTo(2))
                .body("time", equalTo("00:03:00"));
    }
}