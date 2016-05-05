package io.boot.swagger.core;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;

import org.hibernate.annotations.GenericGenerator;
import org.springframework.hateoas.Identifiable;

import lombok.Data;

@Entity
@Data
public class Hotel implements Identifiable<String>{
	
	@Id
	@GeneratedValue(generator = "system-uuid")
	@GenericGenerator(name = "system-uuid", strategy = "uuid")
	private String id;
	
	private String name;
	
	private String description;
	
}
