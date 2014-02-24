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
	public void save(Post post) {
		postRepository.save(post);
	}
	
	@Transactional(readOnly = true)
	public Post findPostById(Long id) {
		return postRepository.findOne(id);
	}
	
	@Transactional
	public void deletePostById(Long id){
		postRepository.delete(id);
	}
	
}
