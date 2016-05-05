package io.boot.swagger.rest;

import org.springframework.hateoas.Resource;

import io.boot.swagger.core.Hotel;

public class HotelResource extends Resource<Hotel> {

	public HotelResource(Hotel hotel) {
		super(hotel);
	}

}
