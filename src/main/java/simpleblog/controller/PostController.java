package simpleblog.controller;

import javax.validation.Valid;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.orm.ObjectOptimisticLockingFailureException;
import org.springframework.stereotype.Controller;
import org.springframework.ui.ModelMap;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.servlet.ModelAndView;
import org.springframework.web.servlet.mvc.support.RedirectAttributes;

import simpleblog.domain.BlogService;
import simpleblog.domain.EntryDoesNotExistException;
import simpleblog.domain.Post;

@Controller
public class PostController {
	
	@Autowired
	BlogService blogService;

	@ModelAttribute("posts")
	public Iterable<Post> posts() {
	    return blogService.getItems();
	}
	
	@RequestMapping("/")
	public String newPost(ModelMap model) {
		model.put("post", new Post());
		return "posts";
	}
	
	@RequestMapping("/{id}/edit")
	public String editPost(@PathVariable Long id, ModelMap model) {
		model.put("post", blogService.findPostById(id));
		return "posts";
	}
	
	@RequestMapping("/{id}/delete")
	public String deletePost(@PathVariable Long id) {
		blogService.deletePostById(id);
		return "redirect:/";
	}
	
	@RequestMapping(value = "/", method = RequestMethod.POST)
	public String save(@Valid Post post, BindingResult result, RedirectAttributes redirect) {
		if(result.hasErrors()) {
			return "posts";
		}
		blogService.save(post);
		redirect.addFlashAttribute("msg", "Successfull save post entry!");
		return "redirect:/";
	}
	
	@RequestMapping(value = "/posts/error", method = RequestMethod.GET)
	public String error(@RequestParam String msg, ModelMap model, RedirectAttributes redirect) {
		redirect.addFlashAttribute("error", msg);
		return "redirect:/";
	}
	
	@ExceptionHandler(ObjectOptimisticLockingFailureException.class)
	public ModelAndView optimisticLockingFailure() {
		ModelMap model = new ModelMap();
		model.put("msg", "Optimistic Locking Failure!");
		return new ModelAndView("redirect:/posts/error", model);
	}
	
	@ExceptionHandler(EntryDoesNotExistException.class)
	public ModelAndView postDoesNotExist(EntryDoesNotExistException exp) {
		ModelMap model = new ModelMap();
		model.put("msg", String.format("Post entry with id %s no longer exists!", exp.getEntryId()));
		return new ModelAndView("redirect:/posts/error", model);
	}
	
	@RequestMapping("/ping")
	public @ResponseBody String ping() {
		return "OK";
	}
	
}
