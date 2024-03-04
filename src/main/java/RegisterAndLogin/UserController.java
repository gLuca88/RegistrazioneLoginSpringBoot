package RegisterAndLogin;

import java.security.Principal;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.servlet.mvc.support.RedirectAttributes;

import RegisterAndLogin.dto.UserDto;
import RegisterAndLogin.model.User;
import RegisterAndLogin.service.UserService;

@Controller
public class UserController {
	
	@Autowired
	private UserDetailsService userDetailsservice;

	private UserService userService;

	public UserController(UserService userService) {
		
		this.userService = userService;
	}

	@GetMapping("/home")
	public String home(Model model,Principal principal) {
		UserDetails userDetails=userDetailsservice.loadUserByUsername(principal.getName());
		model.addAttribute("userdetail",userDetails);
		return "home";
	}

	@GetMapping("/login")
	public String login(Model model, UserDto userDto) {
		model.addAttribute("user", userDto);
		return "login";
	}

	@GetMapping("/register")
	public String register(Model model, UserDto userDto) {
		model.addAttribute("user", userDto);
		return "register";
	}
    /*
	@PostMapping("/register")
	public String registerSave(@ModelAttribute("user") UserDto userdto) {
		userService.save(userdto);
		return "register";
	}
	*/
	
	@PostMapping("/register")
	public String registerSave(@ModelAttribute("user") UserDto userdto, RedirectAttributes redirectAttributes) {
	    // Save the user to the database
	    User savedUser = userService.save(userdto);

	    if (savedUser != null) {
	        // Registration successful
	        redirectAttributes.addFlashAttribute("registrationMessage", "Registration successful!");
	    } else {
	        // Registration failed
	        redirectAttributes.addFlashAttribute("registrationMessage", "Registration failed. Please try again.to control the field");
	       
	    }

	    return "redirect:/login"; // Redirect to login page
	}
	
	
}
