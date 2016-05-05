package io.boot.swagger.rest;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.hateoas.EntityLinks;
import org.springframework.hateoas.mvc.ResourceAssemblerSupport;
import org.springframework.stereotype.Component;

import io.boot.swagger.core.Hotel;

@Component
public class HotelResourceAssembler extends ResourceAssemblerSupport<Hotel, HotelResource> {

	@Autowired
	EntityLinks entityLinks;
	
	public HotelResourceAssembler() {
		super(HotelController.class, HotelResource.class);
	}
	
	@Override
	public HotelResource toResource(Hotel entity) {
		HotelResource resource = new HotelResource(entity);
		resource.add(entityLinks.linkToSingleResource(entity));
		return resource;
	}

}
