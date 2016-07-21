package org.bitholic.servlet;

import org.bitholic.dao.ShiftAccess;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.BufferedReader;
import java.io.IOException;

/**
 * Created by bitholic on 16/7/12.
 */
public class ShiftServlet extends HttpServlet {
    @Override
    protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        request.setCharacterEncoding("UTF-8");
        response.setCharacterEncoding("UTF-8");
        response.setHeader("content-type","text/html;charset=UTF-8");
        String operation = request.getParameter("operation");
        if(operation != null && operation.equals("query")) {
            response.getWriter().print(ShiftAccess.getShifts());
        }else if(operation != null && operation.equals("delete")) {
            Integer id = Integer.parseInt(request.getParameter("id"));
            response.getWriter().print(ShiftAccess.deleteShift(id));
        }
    }

    protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        request.setCharacterEncoding("UTF-8");
        response.setCharacterEncoding("UTF-8");
        response.setHeader("content-type","text/html;charset=UTF-8");
        StringBuffer sb = new StringBuffer();
        String data = null;
        try{
            BufferedReader br = request.getReader();
            while((data = br.readLine())!=null){
                sb.append(data);
            }
        }catch(Exception e){}
        String result = ShiftAccess.addShift(sb.toString());
        response.getWriter().print(result);
    }
}
