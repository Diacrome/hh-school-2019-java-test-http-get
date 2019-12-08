package ru.hh.school.apiGetText;

import static io.restassured.RestAssured.*;

import io.restassured.*;
import io.restassured.http.Method;
import io.restassured.response.Response;
import io.restassured.specification.RequestSpecification;
import org.testng.Assert;
import org.testng.annotations.Test;

public class HhApiGetTest {

    @Test
    void ResponseGetVacancies() {
        baseURI = "https://api.hh.ru/vacancies";
        RequestSpecification httpRequest = RestAssured.given();

        //normal text request
        Response response = httpRequest.request(Method.GET, "?text=java");
        Assert.assertEquals(response.statusCode(), 200);
        Assert.assertEquals(response.getStatusLine(), "HTTP/1.1 200 OK");
        Assert.assertNotNull(response.jsonPath().get("items").getClass());

        //random text request
        response = httpRequest.request(Method.GET, "?text=fsdsfewe");
        Assert.assertEquals(response.statusCode(), 400);
        Assert.assertEquals(response.getStatusLine(), "HTTP/1.1 400 Bad Request");

        //empty text request
        response = httpRequest.request(Method.GET, "?text=");
        Assert.assertEquals(response.statusCode(), 400);
        Assert.assertEquals(response.getStatusLine(), "HTTP/1.1 400 Bad Request");

        //long text request
        response = httpRequest.request(Method.GET, "?text=fgdfdrrrrrrrrrrrrrrrrrrrrrrrrrrrrrrrrrrrrrrrrrrreeeeeeeeeeeeeeeeeeeeeeeeeeeeeeeeeeeeeeeeeeeeeeeeeeeeeeeeeeeeeeeeeeeeeeeeeerrrrrrrrrrrrrrrrrrrrrrrrrrrrrrrrrrrrrrrrrrrrrrrrrrrrrrrrrrrrrrrrrrrrrrrrrrrrrrrrrrrrrrrrrrrrrrrrrrrrrrrrrrrrrrrrrrrrrrrrrrrrrrrrrrrrrrrrrrrrrrrrrrrrrrrrrrrrrrrrrrrrrrrrrrrrrrrrrrrrrrrrrrrrrrrrrrrrrrrrrrrrrrrrrrrrr");
        Assert.assertEquals(response.statusCode(), 400);
        Assert.assertEquals(response.getStatusLine(), "HTTP/1.1 400 Bad Request");

        //random symbols text request
        response = httpRequest.request(Method.GET, "?text=%$#*$");
        Assert.assertEquals(response.statusCode(), 400);
        Assert.assertEquals(response.getStatusLine(), "HTTP/1.1 400 Bad Request");

        //SQL request
        response = httpRequest.request(Method.GET, "?id=10 AND 1=1");
        Assert.assertEquals(response.statusCode(), 400);
        Assert.assertEquals(response.getStatusLine(), "HTTP/1.1 400 Bad Request");

    }
}


