package kz.mun.webserviceclient;

import kz.mun.webserviceclient.wsdl.GetAllCourseDetailsRequest;
import kz.mun.webserviceclient.wsdl.GetAllCourseDetailsResponse;
import kz.mun.webserviceclient.wsdl.GetCourseDetailsRequest;
import kz.mun.webserviceclient.wsdl.GetCourseDetailsResponse;
import org.springframework.ws.client.core.support.WebServiceGatewaySupport;
import org.springframework.ws.soap.client.core.SoapActionCallback;

public class CourseClient extends WebServiceGatewaySupport {

    private static final String URI = "http://localhost:8080/ws/courses";
    public GetCourseDetailsResponse getCourse(int id) {
        GetCourseDetailsRequest request = new GetCourseDetailsRequest();
        request.setId(id);

        return (GetCourseDetailsResponse) getWebServiceTemplate()
                .marshalSendAndReceive(URI, request, new SoapActionCallback(
                        "http://mun.kz/courses/GetCourseDetailsRequest"
                ));
    }

    public GetAllCourseDetailsResponse getAllCourses() {
        GetAllCourseDetailsRequest request = new GetAllCourseDetailsRequest();

        return (GetAllCourseDetailsResponse) getWebServiceTemplate()
                .marshalSendAndReceive(URI, request, new SoapActionCallback(
                        "http://mun.kz/courses/GetAllCourseDetailsRequest"
                ));
    }
}
