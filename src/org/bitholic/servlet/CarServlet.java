package org.bitholic.servlet;

import org.bitholic.dao.CarAccess;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.BufferedReader;
import java.io.IOException;

/**
 * Created by bitholic on 16/7/6.
 */
public class CarServlet extends HttpServlet{
    @Override
    protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        request.setCharacterEncoding("UTF-8");
        response.setCharacterEncoding("UTF-8");
        response.setHeader("content-type","text/html;charset=UTF-8");
        String query = request.getParameter("query");
        if(query != null && query.equals("pagination")){
            Integer offset = Integer.parseInt(request.getParameter("offset"));
            Integer limit = Integer.parseInt(request.getParameter("limit"));
            if(offset != null && limit!= null){
                response.getWriter().print(CarAccess.getCars(1,offset,limit));
            }
        }else if(query != null && query.equals("fault")){
            Integer offset = Integer.parseInt(request.getParameter("offset"));
            Integer limit = Integer.parseInt(request.getParameter("limit"));
            if(offset != null && limit != null){
                response.getWriter().print(CarAccess.getCars(0,offset,limit));
            }
        }else if(query != null && query.equals("del")){
            String licensePlate = request.getParameter("licensePlate");
            if(licensePlate != null){
                response.getWriter().print(CarAccess.deleteCar(licensePlate));
            }
        }else{
            response.getWriter().print(CarAccess.getCars());
        }
    }

    @Override
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
        String result = CarAccess.addCar(sb.toString());
        response.getWriter().print(result);
    }
}
