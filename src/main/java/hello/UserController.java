package hello;

import java.util.List;
import java.util.Locale;

import model.User;
import model.UserService;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;

@Controller
public class UserController {

	@Autowired
	UserService userService;
	
	@RequestMapping(value = "/login2", method = RequestMethod.POST)
	public String login(Locale locale, Model model) {
		return "login";
	}
	
	@RequestMapping(value = "/join", method = RequestMethod.GET)
	public String join() {
		return "join";
	}
	
	@RequestMapping(value = "/join", method = RequestMethod.POST)
	public String join(@RequestParam String name, @RequestParam String email, @RequestParam String password) {
		User user = new User();
		user.setEmail(email);
		user.setPw(password);
		userService.add(user);
		System.out.println(email + "를 사용자 추가했습니다.");
		return "home";
	}
}
