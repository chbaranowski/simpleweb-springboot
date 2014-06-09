package simpleblog.controller

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
class PostController {

	@Autowired
	BlogService blogService

	@ModelAttribute("posts")
	Iterable<Post> posts() {
		blogService.getItems()
	}

	@RequestMapping("/")
	String newPost(ModelMap model) {
		model["post"] = new Post()
		"posts"
	}

	@RequestMapping("/{id}/edit")
	String editPost(@PathVariable Long id, ModelMap model) {
		model["post"] = blogService.findPostById(id)
		"posts"
	}

	@RequestMapping("/{id}/delete")
	String deletePost(@PathVariable Long id) {
		blogService.deletePostById(id)
		"redirect:/"
	}

	@RequestMapping(value = "/", method = RequestMethod.POST)
	String save(@Valid Post post, BindingResult result, RedirectAttributes redirect) {
		if(result.hasErrors()) {
			"posts"
		} else {
			blogService.save(post)
			redirect.addFlashAttribute("msg", "Successfull save post entry!")
			"redirect:/"
		}
	}

	@RequestMapping(value = "/posts/error", method = RequestMethod.GET)
	String error(@RequestParam String msg, ModelMap model, RedirectAttributes redirect) {
		redirect.addFlashAttribute("error", msg)
		"redirect:/"
	}

	@ExceptionHandler(ObjectOptimisticLockingFailureException.class)
	ModelAndView optimisticLockingFailure() {
		ModelMap model = new ModelMap()
		model["msg"] = "Optimistic Locking Failure!"
		new ModelAndView("redirect:/posts/error", model)
	}

	@ExceptionHandler(EntryDoesNotExistException.class)
	ModelAndView postDoesNotExist(EntryDoesNotExistException exp) {
		ModelMap model = new ModelMap()
		model["msg"] = "Post entry with id ${exp.entryId} no longer exists!"
		new ModelAndView("redirect:/posts/error", model)
	}
}

