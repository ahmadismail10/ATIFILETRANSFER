package com.anabatic.atifiletransfer.controller;

import org.apache.log4j.Logger;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;

import com.anabatic.atifiletransfer.service.LogHistoryService;

@Controller
public class LogHistoryController {
	
	private static final Logger logger = Logger.getLogger(LogHistoryController.class);
	
	@Autowired
	LogHistoryService logHistoryService;

	@RequestMapping(value="/histories/list",method = RequestMethod.GET)
    public String listHistories(Model model){
		try {
			model.addAttribute("loghistories",logHistoryService.findAllLogHistoryOrderByDateAndTime());
		} catch (Exception ex) {
			logger.error("load log history", ex);
			System.out.println(ex);
		}
		
    	return "loghistories/listhistories";
    }
	
	@RequestMapping(value="/histories/list/{logHistoryStatus}",method = RequestMethod.GET)
    public String listHistoriesStatus(Model model, @PathVariable String logHistoryStatus){
		try {
			model.addAttribute("loghistories",logHistoryService.findAllLogHistoryByStatusOrderByDateAndTime(logHistoryStatus));
		} catch (Exception ex) {
			logger.error("load log history", ex);
			System.out.println(ex);
		}
		
    	return "loghistories/listhistories";
    }
	
	@RequestMapping(value="/histories/detail/{logHistoryId}",method = RequestMethod.GET)
    public String detailHistories(@PathVariable Long logHistoryId, Model model){
		try {
			model.addAttribute("detailloghistories",logHistoryService.findOneLogHistory(logHistoryId));
		} catch (Exception ex) {
			logger.error("load detail log history", ex);
			System.out.println(ex);
		}
    	return "loghistories/detailhistories";
    }
	
}
