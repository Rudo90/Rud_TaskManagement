package servlets;

import managers.TaskManager;
import models.Task;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.util.List;

@WebServlet("/allTasks")
public class SeeAllTasksServlet extends HttpServlet {

    private TaskManager taskManager = new TaskManager();

    @Override
    protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        List<Task> list = taskManager.getAllTasks();
        req.setAttribute("allTasksList", list);
        req.getRequestDispatcher("allTasks.jsp").forward(req, resp);
    }
}
