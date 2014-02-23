package simpleblog.controller;

import javax.validation.Valid;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.ModelMap;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.ResponseBody;

import simpleblog.model.Post;
import simpleblog.service.BlogService;

@Controller
public class PostController {
	
	@Autowired
	BlogService blogService;

	@ModelAttribute("posts")
	public Iterable<Post> posts() {
	    return blogService.getItems();
	}
	
	@RequestMapping("/")
	public String posts(@ModelAttribute Post post) {
		return "posts";
	}
	
	@RequestMapping(value = "/", method = RequestMethod.POST)
	public String add(@Valid Post post, BindingResult result, ModelMap model) {
		if(result.hasErrors()) {
			return "posts";
		}
		blogService.add(post);
		model.clear();
		return "redirect:/";
	}
	
	@RequestMapping("/ping")
	public @ResponseBody String ping() {
		return "OK";
	}
	
}
