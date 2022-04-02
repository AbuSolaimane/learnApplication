package com.fadili.learn.controllers;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.hateoas.CollectionModel;
import org.springframework.hateoas.EntityModel;
import org.springframework.hateoas.Link;
import org.springframework.http.MediaType;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import com.fadili.learn.dto.AddressDto;
import com.fadili.learn.dto.UserAppDto;
import com.fadili.learn.exceptions.RequiredFieldException;
import com.fadili.learn.services.AddressService;
import com.fadili.learn.services.UserService;

import static org.springframework.hateoas.server.mvc.WebMvcLinkBuilder.linkTo;
import static org.springframework.hateoas.server.mvc.WebMvcLinkBuilder.methodOn;

@RestController
@RequestMapping("/users")
public class UserController {

	@Autowired
	private UserService userService;

	@Autowired
	private AddressService addressService;

	@GetMapping(produces = { MediaType.APPLICATION_XML_VALUE, MediaType.APPLICATION_JSON_VALUE })
	public List<UserAppDto> getAllUsers(@RequestParam(defaultValue = "1") int page,
			@RequestParam(defaultValue = "25") int limit) {

		List<UserAppDto> list = this.userService.getUsers(page, limit);

		return list;
	}

	@GetMapping(path = "/{userID}", produces = { MediaType.APPLICATION_XML_VALUE, MediaType.APPLICATION_JSON_VALUE })
	public UserAppDto getUser(@PathVariable String userID) {

		return this.userService.getUserByUserId(userID);

	}

	@PostMapping(consumes = { MediaType.APPLICATION_XML_VALUE, MediaType.APPLICATION_JSON_VALUE }, produces = {
			MediaType.APPLICATION_XML_VALUE, MediaType.APPLICATION_JSON_VALUE })
	public UserAppDto createUser(@RequestBody UserAppDto userDto) throws Exception {

		if (userDto.getFirstName() == null || userDto.getFirstName().isEmpty())
			throw new RequiredFieldException("FirstName");

		return userService.createUser(userDto);
	}

	@PutMapping(path = "/{userID}", consumes = { MediaType.APPLICATION_XML_VALUE,
			MediaType.APPLICATION_JSON_VALUE }, produces = { MediaType.APPLICATION_XML_VALUE,
					MediaType.APPLICATION_JSON_VALUE })
	public UserAppDto updateUser(@PathVariable String userID, @RequestBody UserAppDto userDto) {

		return this.userService.updateUser(userID, userDto);

	}

	@DeleteMapping("/{userID}")
	public void deleteUser(@PathVariable String userID) {

		this.userService.deleteUser(userID);
	}

	@GetMapping(path = "{userID}/addresses", produces = { MediaType.APPLICATION_XML_VALUE,
			MediaType.APPLICATION_JSON_VALUE, "application/hal+json" })
	public CollectionModel<AddressDto> getAddresses(@PathVariable String userID) {

		List<AddressDto> addresses = this.userService.getUserByUserId(userID).getAddresses();

		Link addressessLink = linkTo(methodOn(UserController.class).getAddresses(userID)).withRel("addresses");
		Link userLink = linkTo(UserController.class).slash(userID).withRel("user");

		addresses.forEach(address -> {

			address.add(addressessLink);
			address.add(userLink);

			Link addresseLink = linkTo(methodOn(UserController.class).getAddress(userID, address.getAddressId()))
					.withSelfRel();
			address.add(addresseLink);

		});

		return CollectionModel.of(addresses);
	}

	@GetMapping(path = "{userID}/addresses/{addressID}", produces = { MediaType.APPLICATION_XML_VALUE,
			MediaType.APPLICATION_JSON_VALUE, "application/hal+json" })
	public EntityModel<AddressDto> getAddress(@PathVariable String userID, @PathVariable String addressID) {

		// Link addressLink =
		// linkTo(UserController.class).slash(userID).slash("addresses").slash(addressID).withSelfRel();
		Link addressLink = linkTo(methodOn(UserController.class).getAddress(userID, addressID)).withSelfRel();
		Link userLink = linkTo(UserController.class).slash(userID).withRel("user");
		Link addressesLink = linkTo(UserController.class).slash(userID).slash("addresses").withRel("addresses");

		AddressDto addressDto = this.addressService.getAddressesByIdAndByUser(userID, addressID);

		addressDto.add(addressLink);
		addressDto.add(userLink);
		addressDto.add(addressesLink);

		return EntityModel.of(addressDto);
	}

}
