package io.khasang.jrepetitor.controller;

import io.khasang.jrepetitor.entity.News;
import io.khasang.jrepetitor.entity.User;
import io.khasang.jrepetitor.service.NewsService;
import io.khasang.jrepetitor.service.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;

import java.util.List;

@Controller
@RequestMapping(value = "/adm")
public class AdmController {
    @Autowired
    private UserService userService;
    @Autowired
    private NewsService newsService;

    @RequestMapping()
    public String createMain() {
        return "adm";
    }

    @RequestMapping(value = "/add", method = RequestMethod.POST, produces = "application/json;charset=utf-8")
    @ResponseBody
    public User addUser(@RequestBody User user) {
        return userService.addUser(user);
    }

    @RequestMapping(value = "/get/{id}", method = RequestMethod.GET, produces = "application/json;charset=utf-8")
    @ResponseBody
    public User getUserById(@PathVariable(value = "id") String id) {
        return userService.getUserById(Long.parseLong(id));
    }

    @RequestMapping(value = "/get/name/{name}", method = RequestMethod.GET, produces = "application/json;charset=utf-8")
    @ResponseBody
    public List<User> getUserByName(@PathVariable(value = "name") String name) {
        return userService.getUserByName(name);
    }

    @RequestMapping(value = "/all", method = RequestMethod.GET, produces = "application/json;charset=utf-8")
    @ResponseBody
    public List<User> getListUsers() {
        return userService.getAllUsers();
    }

    @RequestMapping(value = "/delete", method = RequestMethod.DELETE, produces = "application/json;charset=utf-8")
    @ResponseBody
    public User deleteUser(@RequestParam(value = "id") String id) {
        return userService.deleteUser(Long.parseLong(id));
    }

    @RequestMapping(value = "add/news", method = RequestMethod.POST, produces = "application/json;charset=utf-8")
    @ResponseBody
    public News addNews(@RequestBody News news) {
        return newsService.addNews(news);
    }

    @RequestMapping(value = "/get/news/{id}", method = RequestMethod.GET, produces = "application/json;charset=utf-8")
    @ResponseBody
    public News getNewsById(@PathVariable(value = "id") String id) {
        return newsService.getNewsById(Long.parseLong(id));
    }

    @RequestMapping(value = "/get/news/title/{title}",
            method = RequestMethod.GET, produces = "application/json;charset=utf-8")
    @ResponseBody
    public List<News> getNewsByTitle(@PathVariable(value = "title") String title) {
        return newsService.getNewsByTitle(title);
    }

    @RequestMapping(value = "/delete/news", method = RequestMethod.DELETE, produces = "application/json;charset=utf-8")
    @ResponseBody
    public News deleteNews(@RequestParam(value = "id") String id) {
        return newsService.deleteNews(Long.parseLong(id));
    }
}
