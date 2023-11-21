package org.project.servlets;

import com.fasterxml.jackson.databind.ObjectMapper;

import jakarta.servlet.ServletException;
import jakarta.servlet.annotation.WebServlet;
import jakarta.servlet.http.HttpServlet;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import org.project.dao.implementation.TaskDaoImpl;

import org.project.entity.Task;

import java.io.IOException;
import java.util.List;

@WebServlet("/tasks")
public class TaskServlet extends HttpServlet {
    private TaskDaoImpl taskDao;
    private ObjectMapper objectMapper;

    @Override
    public void init() throws ServletException {
        super.init();
        taskDao = new TaskDaoImpl();
        objectMapper = new ObjectMapper();
    }

    @Override
    protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        List<Task> tasks = taskDao.findAll();

        String json = objectMapper.writeValueAsString(tasks);

        resp.setContentType("application/json");
        resp.getWriter().write(json);
    }

    @Override
    protected void doPost(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        Long taskId = Long.valueOf(req.getParameter("id"));
        String taskDescription = req.getParameter("description");

        taskDao.save(new Task(taskId, taskDescription));

        resp.setStatus(HttpServletResponse.SC_CREATED);
    }

    @Override
    protected void doPut(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        Long taskId = Long.valueOf(req.getParameter("id"));
        String taskDescription = req.getParameter("description");

        taskDao.update(new Task(taskId, taskDescription));

        resp.setStatus(HttpServletResponse.SC_OK);
    }

    @Override
    protected void doDelete(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        Long taskId = Long.valueOf(req.getParameter("id"));
        String taskDescription = req.getParameter("description");

        taskDao.delete(new Task(taskId, taskDescription));

        resp.setStatus(HttpServletResponse.SC_OK);
    }
}
