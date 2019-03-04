package com.anabatic.atifiletransfer.service.impl;

import java.util.List;

import com.anabatic.atifiletransfer.entities.CoreSystem;

public interface ICoreSystem  {
	
	public List<CoreSystem> findAllCoreSystem();
	
	public CoreSystem findOneCoreSystem(String coreSystemId);
	
	public void saveCoreSystem(CoreSystem CoreSystem);
	
	public void editCoreSystem(CoreSystem CoreSystem);
	
}
