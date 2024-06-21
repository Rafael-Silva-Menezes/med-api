package med.vol.api.appointment.presentation.controller;

import med.vol.api.appointment.application.ScheduleAppointmentsService;
import med.vol.api.appointment.presentation.dto.CreateAppointmentDto;
import med.vol.api.appointment.presentation.dto.DetailsAppointmentDto;
import med.vol.api.doctor.domain.entity.Specialization;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.json.AutoConfigureJsonTesters;
import org.springframework.boot.test.autoconfigure.web.servlet.AutoConfigureMockMvc;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.boot.test.json.JacksonTester;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.mock.web.MockHttpServletResponse;
import org.springframework.security.test.context.support.WithMockUser;
import org.springframework.test.context.ActiveProfiles;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.test.web.servlet.request.MockMvcRequestBuilders;

import java.time.LocalDateTime;

import static org.assertj.core.api.Assertions.assertThat;
import static org.mockito.Mockito.when;


@SpringBootTest
@AutoConfigureMockMvc
@AutoConfigureJsonTesters
@ActiveProfiles("test")
class AppointmentControllerTest {

    @Autowired
    private JacksonTester<DetailsAppointmentDto> detailsAppointmentDtoJson;
    @Autowired
    private JacksonTester<CreateAppointmentDto> createAppointmentDtoJson;
    @Autowired
    private MockMvc mockMvc;

    @MockBean
    private ScheduleAppointmentsService scheduleAppointmentsService;

    @Test
    @DisplayName("Should be return 400 if the request body is not valid")
    @WithMockUser
    void shouldBeReturn400IfRequestBodyIsNotValid() throws Exception {
        MockHttpServletResponse response = mockMvc.perform(MockMvcRequestBuilders.post("/schedule/create"))
                .andReturn().getResponse();

        assertThat(response.getStatus()).isEqualTo(HttpStatus.BAD_REQUEST.value());
    }

    @Test
    @DisplayName("Should be return 200 if the request body is valid")
    @WithMockUser
    void shouldBeReturn200IfRequestBodyIsValid() throws Exception {
        CreateAppointmentDto createAppointmentDto =
                new CreateAppointmentDto(2L , 1L , LocalDateTime.now() , Specialization.CARDIOLOGY);
        DetailsAppointmentDto detailsAppointmentDto =
                new DetailsAppointmentDto(null , 2L , 1L , LocalDateTime.now());

        String requestJson = createAppointmentDtoJson.write(createAppointmentDto).getJson();
        String responseJson = detailsAppointmentDtoJson.write(detailsAppointmentDto).getJson();

        when(scheduleAppointmentsService.schedule(createAppointmentDto)).thenReturn(detailsAppointmentDto);

        MockHttpServletResponse response = mockMvc.perform(MockMvcRequestBuilders.post("/schedule/create")
                .contentType(MediaType.APPLICATION_JSON)
                .content(requestJson)).andReturn().getResponse();

        assertThat(response.getStatus()).isEqualTo(HttpStatus.OK.value());
        assertThat(response.getContentAsString()).isEqualTo(responseJson);
    }
}