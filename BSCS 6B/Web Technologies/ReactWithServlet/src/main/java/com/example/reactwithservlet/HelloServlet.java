package com.example.reactwithservlet;

import java.io.*;

import jakarta.servlet.http.*;
import jakarta.servlet.annotation.*;

//@WebServlet(name = "helloServlet", value = "/hello-servlet")
//http://localhost:8080/ReactWithServlet_war_exploded/
@WebServlet("/api/hello")
public class HelloServlet extends HttpServlet {
    @Override
    protected void doGet(HttpServletRequest req, HttpServletResponse resp)
            throws IOException {

        resp.setContentType("application/json");
        resp.setCharacterEncoding("UTF-8");
        resp.setHeader("Access-Control-Allow-Origin", "*");

        resp.getWriter().write("{\"message\":\"Hello from Combined App!\"}");
    }


}