package simpleblog.domain;

import org.springframework.data.elasticsearch.repository.ElasticsearchRepository;

public interface SearchPostRepository extends ElasticsearchRepository<SearchPost, Long> {

}
