package simpleblog.domain

import java.io.Serializable;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;
import javax.persistence.Version;

import org.hibernate.validator.constraints.NotEmpty;

@Entity
class Post implements Serializable {

	@Id
	@GeneratedValue
	Long id;

	@Column(nullable = false)
	@NotEmpty
	@Title
	String title;

	@Column(nullable = false)
	@NotEmpty
	String description;

	@Version
	Long version;

}

