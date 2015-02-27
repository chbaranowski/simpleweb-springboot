package simpleblog.domain;

import org.springframework.data.annotation.Id;
import org.springframework.data.elasticsearch.annotations.Document;

@Document(indexName = "blog", type = "post", shards = 1, replicas = 0, refreshInterval = "-1")
public class SearchPost {

	@Id
	public Long id;
	
	public String title;
	
}
