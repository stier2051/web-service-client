package kz.mun.webserviceclient;

import kz.mun.webserviceclient.wsdl.CourseDetails;
import kz.mun.webserviceclient.wsdl.GetAllCourseDetailsResponse;
import kz.mun.webserviceclient.wsdl.GetCourseDetailsResponse;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;

import static org.junit.jupiter.api.Assertions.*;
@SpringBootTest
class CourseClientTest {

    @Autowired
    private CourseClient courseClient;

    @Test
    void shouldGetCourseDetails() {
        CourseDetails courseDetails = new CourseDetails();
        courseDetails.setId(1);
        courseDetails.setName("Spring");
        courseDetails.setDescription("10 Steps");

        GetCourseDetailsResponse response = courseClient.getCourse(1);

        assertEquals(courseDetails.getDescription(), response.getCourseDetails().getDescription());
    }

    @Test
    void shouldGetAllCourses() {
        GetAllCourseDetailsResponse response = courseClient.getAllCourses();
        assertEquals(4, response.getCourseDetails().size());
    }
}