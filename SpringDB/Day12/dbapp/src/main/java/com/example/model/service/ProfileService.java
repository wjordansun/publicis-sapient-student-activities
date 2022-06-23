package com.example.model.service;

import java.util.List;

import com.example.exceptions.ProfileNotFoundException;
import com.example.model.beans.Profile;

/*
 * This is an interface for business layer, it needs to be implemented by developer
 */
public interface ProfileService {
	// this must store profile & return created profile
	public Profile storeProfile(Profile profile); 
	// this must return all the profiles in the List<Profile>
	public List<Profile> fetchProfiles();
	// this must return Profile or throw ProfileNotFoundException
	public Profile fetchProfile(int id) throws ProfileNotFoundException;
	
	public Profile changeProfileNumber(int id, long num) throws ProfileNotFoundException;
	
}