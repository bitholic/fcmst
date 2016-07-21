package org.bitholic.servlet;

import org.bitholic.dao.CarAccess;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.io.InterruptedIOException;

/**
 * Created by bitholic on 16/7/8.
 */
public class SearchServlet extends HttpServlet {
    @Override
    protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        request.setCharacterEncoding("UTF-8");
        response.setCharacterEncoding("UTF-8");
        response.setHeader("content-type","text/html;charset=UTF-8");

        String type = request.getParameter("type");
        String option = request.getParameter("option");
        String content = request.getParameter("content");
        Integer offset = Integer.parseInt(request.getParameter("offset"));
        Integer limit = Integer.parseInt(request.getParameter("limit"));
        if(type.equals("car")) {
            if (option != null && content != null) {
                response.getWriter().print(CarAccess.searchCar(option,content,offset,limit));
            }
        }else if(type.equals("employee")){

        }else if(type.equals("driver")){

        }
    }
}
