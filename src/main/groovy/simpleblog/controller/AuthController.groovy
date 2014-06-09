package simpleblog.controller

import java.util.Map;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;

@Controller
class AuthController {

	@RequestMapping(value = "/login", method = RequestMethod.GET)
	String login(Map<String, Object> model) { 
		"login" 
	}

}
