package simpleblog.domain

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

@Service
public class BlogService {

	@Autowired
	PostRepository postRepository

	@Transactional(readOnly = true)
	Iterable<Post> getItems() {
		postRepository.findAll()
	}

	@Transactional
	void save(Post post) {
		postRepository.save(post)
	}

	@Transactional(readOnly = true)
	Post findPostById(Long id) {
		Post post = postRepository.findOne(id)
		if (!post) {
			throw new EntryDoesNotExistException(id)
		}
		post
	}

	@Transactional
	void deletePostById(Long id) {
		Post post = postRepository.findOne(id)
		if (!post) {
			throw new EntryDoesNotExistException(id)
		}
		postRepository.delete(id)
	}

}

