package simpleblog.domain;

import java.util.ArrayList;
import java.util.List;

import org.elasticsearch.index.query.WildcardQueryBuilder;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

@Service
public class BlogService {

	@Autowired
	PostRepository postRepository;

	@Autowired
	SearchPostRepository searchPostRepository;
	
	@Transactional(readOnly = true)
	public Iterable<Post> getItems() {
		return postRepository.findAll();
	}

	@Transactional
	public void save(Post post) {
		postRepository.save(post);
		SearchPost searchPost = new SearchPost();
		searchPost.id = post.getId();
		searchPost.title = post.getTitle();
		searchPostRepository.save(searchPost);
	}

	@Transactional(readOnly = true)
	public Post findPostById(Long id) {
		Post post = postRepository.findOne(id);
		if (post == null) {
			throw new EntryDoesNotExistException(id);
		}
		return post;
	}

	@Transactional
	public void deletePostById(Long id) {
		Post post = postRepository.findOne(id);
		if (post == null) {
			throw new EntryDoesNotExistException(id);
		}
		postRepository.delete(post);
	}

	public Iterable<Post> search(String query) {
		WildcardQueryBuilder queryBuilder = new WildcardQueryBuilder("title", query.toLowerCase());
		List<Long> ids = new ArrayList<>();
		Iterable<SearchPost> results = searchPostRepository.search(queryBuilder);
		for (SearchPost searchItem : results) {
			ids.add(searchItem.id);
		}
		return postRepository.findAll(ids);
	}

}
