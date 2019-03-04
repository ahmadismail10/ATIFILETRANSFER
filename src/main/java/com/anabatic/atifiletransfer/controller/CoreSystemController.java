package com.anabatic.atifiletransfer.controller;

import java.util.Locale;

import javax.validation.Valid;

import org.apache.log4j.Logger;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.MessageSource;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.servlet.mvc.support.RedirectAttributes;

import com.anabatic.atifiletransfer.entities.CoreSystem;
import com.anabatic.atifiletransfer.service.CoreSystemService;

@Controller
public class CoreSystemController {
	
	private static final Logger logger = Logger.getLogger(CoreSystemController.class);
	
	@Autowired
	MessageSource messageSource;
	
	@Autowired
	CoreSystemService coreSysSvc;
	
	@RequestMapping(path = "coresystem", method=RequestMethod.GET)
	public String coreSystemForm(CoreSystem coreSystem, Model model) {		
		try {
			model.addAttribute("coresystem", coreSysSvc.findOneCoreSystem("SYSTEM"));
		} catch (Exception ex) {
			logger.error("load form core system", ex);
			System.out.println(ex);
		}
		return "coresystem/formcoresystem";
	}
	
	@RequestMapping(path = "coresystem/save", method=RequestMethod.POST)
	public String coreSystemSave(@Valid CoreSystem coreSystem, BindingResult bindingResult, RedirectAttributes redirectAttributes, Locale locale) {		
		try {
			if (bindingResult.hasErrors()) {
	            return "coresystem/formcoresystem";
	        } else {
	        	if(coreSysSvc.findOneCoreSystem(coreSystem.getCoreSystemId()) == null) {
					coreSysSvc.saveCoreSystem(coreSystem);
				} else {
					coreSysSvc.editCoreSystem(coreSystem);
				}
	        	redirectAttributes.addFlashAttribute("msgsuccess", messageSource.getMessage("message.success.coresystem.update", null, locale));
	        }
		} catch (Exception ex) {
			redirectAttributes.addFlashAttribute("msgerror", messageSource.getMessage("message.error.coresystem.update", null, locale));
			logger.error("save core system", ex);
			System.out.println(ex);
		}
		return "redirect:../coresystem";
	}

}
