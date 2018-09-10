package com.slobodenyuk.vetclinic.controller;

import javax.servlet.ServletConfig;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;

/**
 * Created by Dmytro_Slobodenyuk on 9/10/2018.
 */

public class ServletExample extends HttpServlet {

    @Override
    public void init(ServletConfig config) throws ServletException {
        // configuration
    }

    @Override
    protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        // to handle request
    }
}
