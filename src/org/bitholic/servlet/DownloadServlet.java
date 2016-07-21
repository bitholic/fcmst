package org.bitholic.servlet;

import org.bitholic.dao.CarAccess;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.FileInputStream;
import java.io.IOException;
import java.io.InputStream;
import java.io.OutputStream;

/**
 * Created by bitholic on 16/7/8.
 */
public class DownloadServlet extends HttpServlet{
    @Override
    protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        request.setCharacterEncoding("UTF-8");
        String path = getServletContext().getRealPath("/Resources/tt.xls");
        //CarAccess.exportCars(path);

        /*
        InputStream in = new FileInputStream(path);
        OutputStream out = response.getOutputStream();
        int b;
        while((b=in.read())!= -1){
            out.write(b);
        }

        in.close();
        out.close();
        */
    }
}
