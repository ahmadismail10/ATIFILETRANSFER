package com.anabatic.atifiletransfer.rest;

import java.util.ArrayList;
import java.util.List;import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.bind.annotation.RestController;

import com.anabatic.atifiletransfer.component.CompanyValidator;
import com.anabatic.atifiletransfer.dto.CompanyDTO;
import com.anabatic.atifiletransfer.entities.Company;
import com.anabatic.atifiletransfer.service.CompanyParameterService;
import com.anabatic.atifiletransfer.service.CompanyService;

@RestController
@RequestMapping("/sftp/api")
public class CompanyRestController {
	
	@Autowired
	CompanyValidator companyValidator;
	
	@Autowired
	CompanyService companySvc;
	
	@Autowired
	CompanyParameterService companyParameterSvc;
	
	@RequestMapping(value="/company/name/type", method=RequestMethod.POST)
	@ResponseBody
	public List<CompanyDTO> searchCompanyByNameOrType(@RequestParam String companyName) {
		List<CompanyDTO> companiesDTO = new ArrayList<CompanyDTO>();
		for(Company companyTemp : companySvc.findAllCompanyByNameOrType(companyName)) {
			CompanyDTO companyDTO = new CompanyDTO(companyTemp.getCompanyId(),companyTemp.getCompanyName(),companyTemp.getCompanyStatus());
			companiesDTO.add(companyDTO);
		}
		return companiesDTO;
	}
	
	@RequestMapping(value="/company/name", method=RequestMethod.POST)
	@ResponseBody
	public List<CompanyDTO> searchCompanyByName(@RequestParam String companyName, @RequestParam Long companyId) {
		List<CompanyDTO> companiesDTO = new ArrayList<CompanyDTO>();
		for(Company companyTemp : companySvc.findAllCompanyByName(companyName)) {
			if(companyTemp.getCompanyId() != companyId) {
				CompanyDTO companyDTO = new CompanyDTO(companyTemp.getCompanyId(),companyTemp.getCompanyName(),companyTemp.getCompanyStatus());
				companiesDTO.add(companyDTO);
			}
		}
		return companiesDTO;
	}
}
