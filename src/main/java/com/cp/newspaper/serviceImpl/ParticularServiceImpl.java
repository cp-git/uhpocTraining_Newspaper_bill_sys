package com.cp.newspaper.serviceImpl;

import java.util.HashMap;
import java.util.List;

import com.cp.newspaper.entity.Particular;
import com.cp.newspaper.exception.CPException;
import com.cp.newspaper.repository.PartRepo;
import com.cp.newspaper.service.ParticularService;

public class ParticularServiceImpl implements ParticularService {

	// private Connection con = null;
	private HashMap<String, Particular> partHashMap;;
	private List<Particular> partList;
	PartRepo partRepo;

	public ParticularServiceImpl() {
		partHashMap = null;
		partRepo = null;
		partList = null;
	}

	public boolean checkPart(String partName) {
		boolean isPartExist = false;

		if (partHashMap.isEmpty()) {
			isPartExist = false;
		} else {
			if (partHashMap.containsKey(partName)) {
				isPartExist = true;
			}
		}
		return isPartExist;
	}

	@Override
	public int createPart(Particular part) throws CPException {
		// TODO Auto-generated method stub
		int partId = 0;
		this.partRepo = new PartRepo();
		try {
			partRepo.insertParticular(part);
		} catch (Exception e) {
			// TODO: handle exception
			e.printStackTrace();
		}
		return partId;

	}

	@Override
	public HashMap<String, Particular> getPartHashMap() throws CPException {
		// TODO Auto-generated method stub
		this.partRepo = new PartRepo();
		this.partList = this.partRepo.getParticularDetails();
		this.partHashMap = new HashMap<>();
		for (Particular part : partList) {
			partHashMap.put(part.getPart_name(), part);
		}

		return this.partHashMap;
	}

	@Override
	public Particular getParticularByName(String partName) {
		// TODO Auto-generated method stub
		this.partRepo = new PartRepo();
		return partRepo.getParticularByName(partName);

	}
}
