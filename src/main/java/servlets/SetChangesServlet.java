package servlets;

import managers.TaskManager;
import models.TaskStatusEnum;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Date;

@WebServlet(urlPatterns = "/userHome")
public class SetChangesServlet extends HttpServlet {

    private TaskManager taskManager = new TaskManager();
    private SimpleDateFormat sdf = new SimpleDateFormat("yyyy-MM-dd");
    @Override
    protected void doPost(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {

        String [] status = req.getParameterValues("status");
        int id = Integer.parseInt(req.getParameter("id"));
        String title = req.getParameter("title");
        String desc = req.getParameter("desc");
        try {
            Date deadline = sdf.parse(req.getParameter("deadline")) ;
            String user = req.getParameter("user");
            for (String s : status) {
                if (s.equals(TaskStatusEnum.TODO.name())) {
                    taskManager.updateTaskStatus(id, title, desc, TaskStatusEnum.TODO.name(), deadline, user);
                //    req.getRequestDispatcher("/UserHome.jsp").forward(req, resp);
                    resp.sendRedirect("/UserHome.jsp");
                }
                if (s.equals(TaskStatusEnum.ACTIVE.name())) {
                    taskManager.updateTaskStatus(id, title, desc, TaskStatusEnum.ACTIVE.name(), deadline, user);
                //    req.getRequestDispatcher("/UserHome.jsp").forward(req, resp);
                    resp.sendRedirect("/UserHome.jsp");
                }
                if (s.equals(TaskStatusEnum.FINISHED.name())) {
                    taskManager.updateTaskStatus(id, title, desc, TaskStatusEnum.FINISHED.name(), deadline, user);
                //    req.getRequestDispatcher("/UserHome.jsp").forward(req, resp);
                    resp.sendRedirect("/UserHome.jsp");
                }
            }
        } catch (ParseException e) {
            e.printStackTrace();
        }
    }
}

