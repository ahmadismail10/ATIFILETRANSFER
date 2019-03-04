package com.anabatic.atifiletransfer.controller;

import java.util.Locale;

import javax.validation.Valid;

import org.apache.log4j.Logger;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.MessageSource;
import org.springframework.security.core.Authentication;
import org.springframework.security.crypto.bcrypt.BCrypt;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.servlet.mvc.support.RedirectAttributes;

import com.anabatic.atifiletransfer.form.FormUserProfile;
import com.anabatic.atifiletransfer.service.UserService;

@Controller
public class UserController {
	
	private static final Logger logger = Logger.getLogger(UserController.class);
	
	@Autowired
	MessageSource messageSource;
	
	@Autowired
	UserService userSvc;
	
	@RequestMapping(path = "/user/profile", method=RequestMethod.GET)
	public String userProfile(FormUserProfile formUserProfile, Model model, Authentication authentication) {	
		try {
			model.addAttribute("userProfile", userSvc.findOneUserByUsername(authentication.getName()));
		} catch (Exception ex) {
			logger.error("load form edit user password", ex);
			System.out.println(ex);
		}
		return "user/formuserprofile";
	}
	
	@RequestMapping(path = "/user/edit", method=RequestMethod.POST)
	public String saveUserProfile(@Valid FormUserProfile formUserProfile, BindingResult bindingResult, Authentication authentication, RedirectAttributes redirectAttributes, Locale locale) {	
		try {
			if (bindingResult.hasErrors()) {
	            return "user/formuserprofile";
	        } else {
	        	formUserProfile.getUser().setPassword(BCrypt.hashpw(formUserProfile.getNewPassword(), BCrypt.gensalt()));
	        	userSvc.editUser(formUserProfile.getUser());
	        	redirectAttributes.addFlashAttribute("msgsuccess", messageSource.getMessage("message.success.user.edit", null, locale));
	        }
		} catch (Exception ex) {
			redirectAttributes.addFlashAttribute("msgerror", messageSource.getMessage("message.error.user.edit", null, locale));
			logger.error("save edit user password", ex);
			System.out.println(ex);		
		}
		return "redirect:profile";
	}

}
