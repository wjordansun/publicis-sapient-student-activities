package com.example.controller;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.example.exceptions.ProfileNotFoundException;
import com.example.model.beans.Friend;
import com.example.model.beans.Profile;
import com.example.model.service.FriendService;
import com.example.model.service.ProfileService;

@RestController
@RequestMapping("/profile")
public class ProfileController {

	@Autowired
	private ProfileService profileService;
	
	@Autowired
	private FriendService friendService;
	
	@PostMapping
	public ResponseEntity<Object> store(@RequestBody Profile profile) { // profileId, name, dob, phone
		Profile storedProfile = profileService.storeProfile(profile);
		return ResponseEntity.status(HttpStatus.CREATED).body(storedProfile);
	}
	@GetMapping
	public ResponseEntity<Object> getProfiles() {
		List<Profile> list = profileService.fetchProfiles();
		return ResponseEntity.status(HttpStatus.OK).body(list);
	}
	@GetMapping("/{id}")
	public ResponseEntity<Object> getProfile(@PathVariable("id") int id) {
		try {
			Profile profile = profileService.fetchProfile(id);
			return ResponseEntity.status(HttpStatus.OK).body(profile);
		} catch (ProfileNotFoundException e) {
			// Exception has getMessage() that returns the message initialized when exception occurs
			Map<String, String> map = new HashMap<String, String>();
			map.put("error", e.getMessage()); // e.getMessage(): Profile with an id not found
			map.put("status", "404");
			return ResponseEntity.status(HttpStatus.NOT_FOUND).body(map);
		}	
	}
	@PostMapping("/{profileId}")
	public ResponseEntity<Object> addFriend(@RequestBody Friend friend, 
			@PathVariable("profileId") int id) {
		Friend createdFriend = friendService.addFriend(id, friend);
		Map<String, String> map = new HashMap<String, String>();
		map.put("message", "Added "+createdFriend.getName());
		map.put("description", "Added to profile id: "+createdFriend.getProfileIdRef());
		return ResponseEntity.status(HttpStatus.OK).body(map);
	}
	@PutMapping("/{proId}/{number}")
	public ResponseEntity<Object> changeNumber(@PathVariable("number") long number, 
			@PathVariable("proId") int id) {
		try {
			Profile profile = profileService.changeProfileNumber(id, number);
			return ResponseEntity.status(HttpStatus.OK).body(profile);
		} catch (ProfileNotFoundException e) {
			// Exception has getMessage() that returns the message initialized when exception occurs
			Map<String, String> map = new HashMap<String, String>();
			map.put("error", e.getMessage()); // e.getMessage(): Profile with an id not found
			map.put("status", "404");
			return ResponseEntity.status(HttpStatus.NOT_FOUND).body(map);
		}
	}
	@DeleteMapping("/{proId}/{friendId}")
	public ResponseEntity<Object> deleteFriend(@PathVariable("friendId") int fid, 
			@PathVariable("proId") int id) {
		Friend friend = friendService.deleteFriendById(id, fid);
		Map<String, String> map = new HashMap<String, String>();
		map.put("message", "deleted "+friend.getName());
		map.put("description", "Added to profile id: "+friend.getProfileIdRef());
		return ResponseEntity.status(HttpStatus.OK).body(map);
		
	}
	
}