package simpleblog.domain;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

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
		Post post = postRepository.findOne(id);
		if(post == null) {
			throw new EntryDoesNotExistException(id);
		}
		return post;
	}
	
	@Transactional
	public void deletePostById(Long id) {
		Post post = postRepository.findOne(id);
		if(post == null) {
			throw new EntryDoesNotExistException(id);
		}
		postRepository.delete(post);
	}
	
}
