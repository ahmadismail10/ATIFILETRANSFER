package com.anabatic.atifiletransfer.controller;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.security.web.authentication.logout.SecurityContextLogoutHandler;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;

import com.anabatic.atifiletransfer.entities.Company.StatusCompany;
import com.anabatic.atifiletransfer.service.CompanyParameterService;
import com.anabatic.atifiletransfer.service.CompanyService;
import com.anabatic.atifiletransfer.service.LogHistoryService;

@Controller
public class MainController {
	
	@Value("${server.contextPath}")
	private String appNames;
	
	@Autowired
	LogHistoryService logHistorySvc;
	
	@Autowired
	CompanyService companySvc;
	
	@Autowired
	CompanyParameterService companyParameterSvc;
	
	@RequestMapping(path = "/", method=RequestMethod.GET)
	public String indexGet(Model model) {
		model.addAttribute("company",companySvc.findSummaryCompanyOrderById(StatusCompany.Active));
		model.addAttribute("companyParameter",companyParameterSvc.findSummaryCompanyParameter());
		model.addAttribute("fileSuccess",logHistorySvc.findSummaryLogHistorySuccess("Success"));
		model.addAttribute("fileFail",logHistorySvc.findSummaryLogHistorySuccess("Fail"));
		return "index";
	}
	
	@RequestMapping(value="/login",method = RequestMethod.GET)
    public String homeLogin(Model model){
		appNames = appNames.replace("/", "");
		model.addAttribute("apps", appNames);
    	return "login";
    }
    
    @RequestMapping(value="/logout", method = RequestMethod.GET)
    public String logoutPage (HttpServletRequest request, HttpServletResponse response) {
        Authentication auth = SecurityContextHolder.getContext().getAuthentication();
        if (auth != null){    
            new SecurityContextLogoutHandler().logout(request, response, auth);
        }
        return "redirect:/login?logout";
    }

}
