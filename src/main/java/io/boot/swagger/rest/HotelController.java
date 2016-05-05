package io.boot.swagger.rest;

import javax.servlet.http.HttpServletRequest;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.data.domain.Sort;
import org.springframework.data.domain.Sort.Direction;
import org.springframework.data.web.PagedResourcesAssembler;
import org.springframework.hateoas.ExposesResourceFor;
import org.springframework.hateoas.PagedResources;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseStatus;
import org.springframework.web.bind.annotation.RestController;

import io.boot.swagger.core.Hotel;
import io.boot.swagger.core.HotelRepository;
import io.jsonwebtoken.Claims;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import springfox.documentation.annotations.ApiIgnore;

@Api(tags= "Hotel", description="API to manage hotels.")
@RestController
@RequestMapping(path = "/hotel", produces = "application/vnd.company.app-v1+json")
@ExposesResourceFor(Hotel.class)
public class HotelController {
	
	@Autowired
	HotelRepository hotelRepository;
	
	@Autowired
	HotelResourceAssembler hotelResourceAssembler;
	
	@ApiOperation(value = "GET list of hotels")
	@RequestMapping(method = RequestMethod.GET)
	PagedResources<HotelResource> query(
			@RequestParam(defaultValue = "0") int page, 
			@RequestParam(defaultValue = "10") int size,
			@RequestParam(defaultValue = "ASC", required = false) String sortDirection,
			@RequestParam(defaultValue = "id", required = false) String[] sortProperties,
			@ApiIgnore PagedResourcesAssembler<Hotel> assembler) {
		Direction direction = Direction.fromString(sortDirection);
		Pageable pageable = new PageRequest(page, size, new Sort(direction, sortProperties));
		Page<Hotel> hotels = hotelRepository.findAll(pageable);
		PagedResources<HotelResource> resources = assembler.toResource(hotels, hotelResourceAssembler);
		return resources;
	}

	@ApiOperation(value = "Creates a new hotel", produces = "application/vnd.company.app-v1+json")
	@RequestMapping(method = RequestMethod.PUT)
	@ResponseStatus(value = HttpStatus.CREATED)
	HotelResource create(@RequestBody Hotel hotel) {
		Hotel savedHotel = hotelRepository.save(hotel);
		HotelResource resource = hotelResourceAssembler.toResource(savedHotel);
		return resource;
	}
	
	@ApiOperation(value = "Get a hotel by Id.")
	@RequestMapping(method = RequestMethod.GET, path="/{id}")
	HotelResource get(@PathVariable String id) {
		Hotel hotel = hotelRepository.findOne(id);
		HotelResource resource = hotelResourceAssembler.toResource(hotel);
		return resource;
	}
	
	@ApiOperation(value = "Update a new hotel")
	@RequestMapping(method = RequestMethod.POST, path="/{id}")
	HttpStatus update(@PathVariable String id, @RequestBody Hotel hotel) {
		hotel.setId(id);
		hotelRepository.save(hotel);
		return HttpStatus.OK;
	}
	
	@ApiOperation(value = "Delete a hotel.")
	@RequestMapping(method = RequestMethod.DELETE, path="/{id}")
	HttpStatus delete(@PathVariable String id) {
		hotelRepository.delete(id);
		return HttpStatus.OK;
	}
	
}
