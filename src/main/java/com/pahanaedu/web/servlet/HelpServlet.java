package com.pahanaedu.web.servlet;

import com.pahanaedu.model.HelpTopic;
import jakarta.servlet.ServletException;
import jakarta.servlet.annotation.WebServlet;
import jakarta.servlet.http.HttpServlet;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;

import java.io.IOException;
import java.util.ArrayList;
import java.util.List;

@WebServlet("/help")
public class HelpServlet extends HttpServlet {
    private List<HelpTopic> getHelpTopics() {
        List<HelpTopic> topics = new ArrayList<>();
        topics.add(new HelpTopic("Login", "Enter your username and password to log in. Contact admin if you forget your credentials."));
        topics.add(new HelpTopic("Create Bill", "Go to 'Dashboard' > 'Add New Bill', select customer using phone number, add items, and click Save Bill."));
        topics.add(new HelpTopic("Search Customer", "Use the search bar in the Customers section to find by ID, name, or phone."));
        topics.add(new HelpTopic("Generate Reports", "Go to 'Reports', select a date range, and click 'Generate'."));
        return topics;
    }

    @Override
    protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        List<HelpTopic> topics = getHelpTopics();
        request.setAttribute("helpTopics", topics);
        request.getRequestDispatcher("/help.jsp").forward(request, response);
    }
}
