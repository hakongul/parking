package no.hakgul.parking.api;

import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.AutoConfigureMockMvc;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.http.MediaType;
import org.springframework.test.context.junit4.SpringRunner;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.test.web.servlet.request.MockMvcRequestBuilders;

import java.time.DayOfWeek;
import java.time.LocalDateTime;

import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.content;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;

@RunWith(SpringRunner.class)
@SpringBootTest
@AutoConfigureMockMvc
public class ParkingControllerTest {

    @Autowired
    private MockMvc mvc;

    @Test
    public void getTakstFor35MinutesInM1() throws Exception {
        mvc.perform(MockMvcRequestBuilders.get("/takst?sone=M1&minutter=35").accept(MediaType.APPLICATION_JSON))
                .andExpect(status().isOk())
                .andExpect(content().json("{\"pris\":35,\"sone\":\"M1\"}"));
    }

    @Test
    public void getTakstFor99MinutesInM1() throws Exception {
        mvc.perform(MockMvcRequestBuilders.get("/takst?sone=M1&minutter=99").accept(MediaType.APPLICATION_JSON))
                .andExpect(status().isOk())
                .andExpect(content().json("{\"pris\":99,\"sone\":\"M1\"}"));
    }

    @Test
    public void getTakstFor63MinutesInM2() throws Exception {
        LocalDateTime now = LocalDateTime.now();
        if(now.getDayOfWeek() == DayOfWeek.SATURDAY || now.getDayOfWeek() == DayOfWeek.SUNDAY){
            mvc.perform(MockMvcRequestBuilders.get("/takst?sone=M2&minutter=63").accept(MediaType.APPLICATION_JSON))
                    .andExpect(status().isOk())
                    .andExpect(content().json("{\"pris\":400,\"sone\":\"M2\"}"));
        } else {
            mvc.perform(MockMvcRequestBuilders.get("/takst?sone=M2&minutter=63").accept(MediaType.APPLICATION_JSON))
                    .andExpect(status().isOk())
                    .andExpect(content().json("{\"pris\":200,\"sone\":\"M2\"}"));
        }
    }

    @Test
    public void getTakstFor447MinutesInM2() throws Exception {
        LocalDateTime now = LocalDateTime.now();
        if(now.getDayOfWeek() == DayOfWeek.SATURDAY || now.getDayOfWeek() == DayOfWeek.SUNDAY){
            mvc.perform(MockMvcRequestBuilders.get("/takst?sone=M2&minutter=447").accept(MediaType.APPLICATION_JSON))
                    .andExpect(status().isOk())
                    .andExpect(content().json("{\"pris\":1600,\"sone\":\"M2\"}"));
        } else {
            mvc.perform(MockMvcRequestBuilders.get("/takst?sone=M2&minutter=447").accept(MediaType.APPLICATION_JSON))
                    .andExpect(status().isOk())
                    .andExpect(content().json("{\"pris\":800,\"sone\":\"M2\"}"));
        }
    }

    @Test
    public void getTakstFor45Minutter() throws Exception {
        LocalDateTime now = LocalDateTime.now();

        if(now.getDayOfWeek() == DayOfWeek.SUNDAY) {
            mvc.perform(MockMvcRequestBuilders.get("/takst?sone=M3&minutter=45").accept(MediaType.APPLICATION_JSON))
                    .andExpect(status().isOk())
                    .andExpect(content().json("{\"pris\":0,\"sone\":\"M3\"}"));
        } else if(now.getHour() < 8 && now.getHour() > 16) {
            mvc.perform(MockMvcRequestBuilders.get("/takst?sone=M3&minutter=45").accept(MediaType.APPLICATION_JSON))
                    .andExpect(status().isOk())
                    .andExpect(content().json("{\"pris\":0,\"sone\":\"M3\"}"));
        } else {
            mvc.perform(MockMvcRequestBuilders.get("/takst?sone=M3&minutter=45").accept(MediaType.APPLICATION_JSON))
                    .andExpect(status().isOk())
                    .andExpect(content().json("{\"pris\":135,\"sone\":\"M3\"}"));
        }
    }

    @Test
    public void getTakstFor162Minutter() throws Exception {
        LocalDateTime now = LocalDateTime.now();

        if(now.getDayOfWeek() == DayOfWeek.SUNDAY) {
            mvc.perform(MockMvcRequestBuilders.get("/takst?sone=M3&minutter=162").accept(MediaType.APPLICATION_JSON))
                    .andExpect(status().isOk())
                    .andExpect(content().json("{\"pris\":0,\"sone\":\"M3\"}"));
        } else if(now.getHour() < 8 && now.getHour() > 16) {
            mvc.perform(MockMvcRequestBuilders.get("/takst?sone=M3&minutter=162").accept(MediaType.APPLICATION_JSON))
                    .andExpect(status().isOk())
                    .andExpect(content().json("{\"pris\":204,\"sone\":\"M3\"}"));
        } else {
            mvc.perform(MockMvcRequestBuilders.get("/takst?sone=M3&minutter=162").accept(MediaType.APPLICATION_JSON))
                    .andExpect(status().isOk())
                    .andExpect(content().json("{\"pris\":486,\"sone\":\"M3\"}"));
        }
    }
}
