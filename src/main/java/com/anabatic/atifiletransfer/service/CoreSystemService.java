package com.anabatic.atifiletransfer.service;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.jpa.repository.Modifying;
import org.springframework.stereotype.Service;

import com.anabatic.atifiletransfer.entities.CoreSystem;
import com.anabatic.atifiletransfer.repository.CoreSystemDao;
import com.anabatic.atifiletransfer.service.impl.ICoreSystem;

@Service
public class CoreSystemService implements ICoreSystem {

	@Autowired
	CoreSystemDao coreSysDao;
	
	@Override
	public List<CoreSystem> findAllCoreSystem() {
		return coreSysDao.findAll();
	}

	@Override
	public CoreSystem findOneCoreSystem(String idCoreSystem) {
		return coreSysDao.findOne(idCoreSystem);
	}

	@Override
	@Modifying
	public void saveCoreSystem(CoreSystem coreSystem) {
		coreSysDao.saveAndFlush(coreSystem);
	}

	@Override
	@Modifying
	public void editCoreSystem(CoreSystem coreSystem) {
		CoreSystem coreSystemTemp = coreSysDao.findOne(coreSystem.getCoreSystemId());
		coreSystemTemp.setCoreSystemLocalDirectory(coreSystem.getCoreSystemLocalDirectory());
		coreSystemTemp.setCoreSystemTimer(coreSystem.getCoreSystemTimer());
		coreSysDao.saveAndFlush(coreSystemTemp);
	}

}
