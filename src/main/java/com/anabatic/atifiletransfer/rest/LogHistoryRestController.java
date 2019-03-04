package com.anabatic.atifiletransfer.rest;

import java.util.ArrayList;
import java.util.List;

import org.modelmapper.ModelMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.bind.annotation.RestController;

import com.anabatic.atifiletransfer.dto.CompanyDTO;
import com.anabatic.atifiletransfer.dto.CompanyParameterDTO;
import com.anabatic.atifiletransfer.dto.LogHistoryDTO;
import com.anabatic.atifiletransfer.entities.LogHistory;
import com.anabatic.atifiletransfer.service.LogHistoryService;

@RestController
@RequestMapping("/sftp/api")
public class LogHistoryRestController {
	
	@Autowired
	ModelMapper modelMapper;
	
	@Autowired
	LogHistoryService logHistorySvc;
	
	@RequestMapping(value="/histories/list", method=RequestMethod.GET)
	@ResponseBody
	public List<LogHistoryDTO> searchAllLogHistoriesOrderByDateTime() {
		List<LogHistoryDTO> logHistoriesDTOTemp = new ArrayList<LogHistoryDTO>();
		for(LogHistory logHistoryTemp : logHistorySvc.findAllLogHistoryOrderByDateAndTimeJoinCompanyParameterAndCompany()) {
			CompanyParameterDTO companyParameterDTO = new CompanyParameterDTO();
			CompanyDTO companyDTO = new CompanyDTO();
			companyDTO.setCompanyName(logHistoryTemp.getCompanyParameter().getCompany().getCompanyName());
			companyParameterDTO.setCompanyDTO(companyDTO);
			companyParameterDTO.setCompanyParameterType(logHistoryTemp.getCompanyParameter().getCompanyParameterType());
			companyParameterDTO.setCompanyParameterId(logHistoryTemp.getCompanyParameter().getCompanyParameterId());
			LogHistoryDTO logHistoryDTOTemp = modelMapper.map(logHistoryTemp, LogHistoryDTO.class);
			logHistoryDTOTemp.setCompanyParameterDTO(companyParameterDTO);
			logHistoriesDTOTemp.add(logHistoryDTOTemp);
		}
		return logHistoriesDTOTemp;
	}
}
