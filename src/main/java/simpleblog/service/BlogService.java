package simpleblog.service;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import simpleblog.model.Post;
import simpleblog.model.PostRepository;

@Service
public class BlogService {

	@Autowired
	PostRepository postRepository;
	
	@Transactional(readOnly = true)
	public Iterable<Post> getItems() {
		return postRepository.findAll();
	}
	
	@Transactional
	public void add(Post post) {
		postRepository.save(post);
	}
	
}
