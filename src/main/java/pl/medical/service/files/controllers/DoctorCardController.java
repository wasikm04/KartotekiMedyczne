package pl.medical.service.files.controllers;

import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.RestController;

@CrossOrigin(value = "*", maxAge = 3600, allowCredentials = "true")
@RestController
public class DoctorCardController {
}
