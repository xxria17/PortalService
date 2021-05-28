package kr.ac.jejunu.user;

import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.multipart.MultipartFile;
import org.springframework.web.servlet.ModelAndView;

import javax.servlet.http.HttpServletRequest;
import java.io.*;

@Controller
@RequestMapping
@RequiredArgsConstructor
public class UserController {
    private final UserDao userDao;

    @RequestMapping("/user")
    public User getUser(@RequestParam("id") Integer id) {
        System.out.println("********** user **********");
        return userDao.findById(id);
    }

    //@RequestMapping(value = "/upload", method = RequestMethod.GET)
    @GetMapping("/upload")
    public void upload() {

    } 

    //@RequestMapping(value = "/upload", method = RequestMethod.POST)
    @PostMapping("/upload")
    public ModelAndView upload(@RequestParam("file") MultipartFile file, HttpServletRequest request) throws IOException {
        String path = request.getServletContext().getRealPath("/") + "/WEB-INF/static/" + file.getOriginalFilename();
        File saveFile = new File(path);
        FileOutputStream fileOutputStream = new FileOutputStream(saveFile);
        BufferedOutputStream bufferedOutputStream = new BufferedOutputStream(fileOutputStream);
        bufferedOutputStream.write(file.getBytes());
        fileOutputStream.close();

        ModelAndView modelAndView = new ModelAndView();
        modelAndView.addObject("url", "/images/" + file.getOriginalFilename());
        return modelAndView;
    }

    @ExceptionHandler(Exception.class)
    public ModelAndView error(Exception e) {
        ModelAndView modelAndView = new ModelAndView("error");
        modelAndView.addObject("e", e);
        return modelAndView;
    }
}
