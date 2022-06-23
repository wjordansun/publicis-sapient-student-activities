package com.example.model.dao;

import org.springframework.data.jpa.repository.JpaRepository;

import com.example.model.beans.Profile;

/*
 * Since ProfileRepository must perform operation on Profile entity & primary key type is Integer
 * We are using JpaRepository<Profile, Integer>
 * ProfileRepository gets all the methods from JpaRepository & will be implemented by spring data jpa
 * The methods like save(), deleteById(), delete(), findAll(), findById()
 */
public interface ProfileRepository extends JpaRepository<Profile, Integer>{

}