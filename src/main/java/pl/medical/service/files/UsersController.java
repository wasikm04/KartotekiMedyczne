package pl.medical.service.files;

import org.springframework.http.MediaType;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.bind.annotation.RestController;

@RestController
public class UsersController
{
    @ResponseBody
    @RequestMapping(value ="/users", produces = MediaType.APPLICATION_JSON_VALUE, method= RequestMethod.GET)
    public String index() {
        return "Users Here";
    }
}
