package com.cp.newspaper.service;


import java.util.HashMap;


import com.cp.newspaper.entity.Particular;
import com.cp.newspaper.exception.CPException;

public interface ParticularService {
	boolean checkProd(String prodName);

	int createPart(Particular part) throws CPException;

	HashMap<String, Particular> getPartHashMap() throws CPException;

	public Particular getParticularByName(String partName);
}
