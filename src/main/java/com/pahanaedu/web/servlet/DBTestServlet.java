package com.pahanaedu.web.servlet;

import com.pahanaedu.util.DBUtil;
import jakarta.servlet.ServletException;
import jakarta.servlet.http.*;

import java.io.IOException;
import java.io.PrintWriter;
import java.sql.Connection;

public class DBTestServlet extends HttpServlet {
    protected void doGet(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
        try (PrintWriter out = response.getWriter()) {
            Connection conn = DBUtil.getConnection();
            if (conn != null) {
                out.println("Database connected successfully!");
            } else {
                out.println("Failed to connect to the database.");
            }
        } catch (Exception e) {
            System.out.println(e.getMessage());
        }
    }
}
