package pl.medical.service.files.controllers;

import org.springframework.web.bind.annotation.RestController;

import javax.servlet.ServletContext;

@RestController
public class FilesController {

    private ServletContext servletContext;

    FilesController(ServletContext servletContext) {
        this.servletContext = servletContext;
    }

}
