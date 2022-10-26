package com.cp.newspaper.service;

import java.util.HashMap;

import com.cp.newspaper.entity.Particular;
import com.cp.newspaper.exception.CPException;

public interface ParticularService {
	boolean checkPart(String partName);

	int createPart(Particular part) throws CPException;

	HashMap<String, Particular> getPartHashMap() throws CPException;

	Particular getParticularById(int part_id);
}
