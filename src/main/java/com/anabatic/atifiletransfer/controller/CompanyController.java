package com.anabatic.atifiletransfer.controller;

import java.util.Locale;
import javax.validation.Valid;

import org.apache.log4j.Logger;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.MessageSource;
import org.springframework.security.core.Authentication;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.servlet.mvc.support.RedirectAttributes;

import com.anabatic.atifiletransfer.component.CompanyValidator;
import com.anabatic.atifiletransfer.entities.Company;
import com.anabatic.atifiletransfer.entities.CompanyParameter;
import com.anabatic.atifiletransfer.entities.Company.StatusCompany;
import com.anabatic.atifiletransfer.form.FormCompanyParameter;
import com.anabatic.atifiletransfer.service.CompanyParameterService;
import com.anabatic.atifiletransfer.service.CompanyService;
import com.anabatic.atifiletransfer.service.CoreSystemService;
import com.anabatic.atifiletransfer.service.UserService;

@Controller
public class CompanyController {

	private static final Logger logger = Logger.getLogger(CompanyController.class);

	@Autowired
	MessageSource messageSource;

	@Autowired
	CompanyValidator companyValidator;

	@Autowired
	CoreSystemService coreSysSvc;

	@Autowired
	UserService userSvc;

	@Autowired
	CompanyService companySvc;

	@Autowired
	CompanyParameterService companyParameterSvc;

	@RequestMapping(path = "/company/list", method = RequestMethod.GET)
	public String listCompany(Model model) {
		try {
			model.addAttribute("core", coreSysSvc.findOneCoreSystem("SYSTEM"));
			model.addAttribute("company", companySvc.findAllCompanyOrderById());
		} catch (Exception ex) {
			logger.error("load data company", ex);
			System.out.println(ex);
		}
		return "company/listcompany";
	}

	@RequestMapping(path = "/company/parameter/list", method = RequestMethod.GET)
	public String listCompanyParameter(Model model) {
		try {
			if (coreSysSvc.findOneCoreSystem("SYSTEM") == null) {
				return "redirect:../list";
			}
			model.addAttribute("parameter", companyParameterSvc.findAllCompanyParameter());
		} catch (Exception ex) {
			logger.error("load data company parameter", ex);
			System.out.println(ex);
		}
		return "company/companyparameter/listparameter";
	}

	@RequestMapping(path = "/company/list/{companyStatus}", method = RequestMethod.GET)
	public String listCompanyLive(Model model, @PathVariable StatusCompany companyStatus) {
		try {
			model.addAttribute("core", coreSysSvc.findOneCoreSystem("SYSTEM"));
			model.addAttribute("company", companySvc.findAllCompanyByStatus(companyStatus));
		} catch (Exception ex) {
			logger.error("load data company", ex);
			System.out.println(ex);
		}
		return "company/listcompany";
	}

	@RequestMapping(path = "/company/form", method = RequestMethod.GET)
	public String formCompany(Company company, Model model, Authentication authentication) {
		try {
			if (coreSysSvc.findOneCoreSystem("SYSTEM") == null) {
				return "redirect:list";
			}
			model.addAttribute("coreSystem", coreSysSvc.findOneCoreSystem("SYSTEM"));
			model.addAttribute("user", userSvc.findOneUserByUsername(authentication.getName()));
		} catch (Exception ex) {
			logger.error("load form company", ex);
			System.out.println(ex);
		}
		return "company/formcompany";
	}

	@RequestMapping(path = "/company/add", method = RequestMethod.POST)
	public String addCompany(Company company, BindingResult bindingResult, RedirectAttributes redirectAttributes,
			Locale locale) {
		try {
			companyValidator.validate(company, bindingResult);
			if (bindingResult.hasErrors()) {
				return "company/formcompany";
			} else {
				companySvc.saveCompany(company);
				redirectAttributes.addFlashAttribute("msgsuccess",
						messageSource.getMessage("message.success.company.add", null, locale));
			}
		} catch (Exception ex) {
			redirectAttributes.addFlashAttribute("msgerror",
					messageSource.getMessage("message.error.company.add", null, locale));
			logger.error("save data company", ex);
			System.out.println(ex);
		}
		return "redirect:list";
	}

	@RequestMapping(path = "/company/edit", method = RequestMethod.POST)
	public String editCompany(Company company, BindingResult bindingResult, RedirectAttributes redirectAttributes,
			Locale locale) {
		try {
			companyValidator.validate(company, bindingResult);
			if (bindingResult.hasErrors()) {
				return "company/formcompanyedit";
			} else {
				companySvc.editCompany(company);
				redirectAttributes.addFlashAttribute("msgsuccess",
						messageSource.getMessage("message.success.company.edit", null, locale));
			}
		} catch (Exception ex) {
			redirectAttributes.addFlashAttribute("msgerror",
					messageSource.getMessage("message.error.company.edit", null, locale));
			logger.error("edit data company", ex);
			System.out.println(ex);
		}
		return "redirect:list";
	}

	@RequestMapping(path = "/company/form/edit/{companyId}", method = RequestMethod.GET)
	public String formEditCompany(@PathVariable Long companyId, Company company, Model model) {
		try {
			model.addAttribute("companyData", companySvc.findOneCompany(companyId));
		} catch (Exception ex) {
			logger.error("load form edit company", ex);
			System.out.println(ex);
		}
		return "company/formcompanyedit";
	}

	@RequestMapping(path = "/company/delete/{companyId}", method = RequestMethod.GET)
	public String formDeleteCompany(@PathVariable Long companyId, Company company, Model model) {
		try {
			model.addAttribute("companyData", companySvc.findOneCompany(companyId));
			model.addAttribute("formType", "delete");
		} catch (Exception ex) {
			logger.error("load form delete company", ex);
			System.out.println(ex);
		}
		return "company/formcompanyedit";
	}

	@RequestMapping(path = "/company/delete", method = RequestMethod.POST)
	public String deleteCompany(Company company, RedirectAttributes redirectAttributes, Locale locale) {
		try {
			companySvc.deleteCompanyById(company.getCompanyId());
			redirectAttributes.addFlashAttribute("msgsuccess",
					messageSource.getMessage("message.success.company.delete", null, locale));
		} catch (Exception ex) {
			redirectAttributes.addFlashAttribute("msgerror",
					messageSource.getMessage("message.error.company.delete", null, locale));
			logger.error("delete company", ex);
			System.out.println(ex);
		}
		return "redirect:list";
	}

	@RequestMapping(path = "/company/setting/parameter/{companyId}", method = RequestMethod.GET)
	public String settingCompanyParameter(FormCompanyParameter formCompanyParameter, @PathVariable Long companyId,
			Model model) {
		try {
			model.addAttribute("putparam",
					companyParameterSvc.findCompanyParameterByTypeAndCompanyId("Put", companyId));
			model.addAttribute("getparam",
					companyParameterSvc.findCompanyParameterByTypeAndCompanyId("Get", companyId));
			model.addAttribute("companyId", companyId);
		} catch (Exception ex) {
			logger.error("load form company parameter", ex);
			System.out.println(ex);
		}
		return "company/companyparameter/formparameter";
	}

	@RequestMapping(path = "/company/setting/parameter/save", method = RequestMethod.POST)
	public String saveCompanyParameter(@Valid FormCompanyParameter formCompanyParameter, BindingResult bindingResult,
			Model model, RedirectAttributes redirectAttributes, Locale locale) {
		try {
			if (bindingResult.hasErrors()) {
				for (CompanyParameter companyParameter : formCompanyParameter.getCompaniesParameter()) {
					if (companyParameter.getCompanyParameterType().equalsIgnoreCase("Get")) {
						model.addAttribute("getparam", companyParameter);
						model.addAttribute("companyId", companyParameter.getCompany().getCompanyId());
					} else {
						model.addAttribute("putparam", companyParameter);
					}
				}
				return "company/companyparameter/formparameter";
			} else {
				for (CompanyParameter companyParameter : formCompanyParameter.getCompaniesParameter()) {
					if (companyParameter.getCompanyParameterId() != null && companyParameterSvc
							.findOneCompanyParameter(companyParameter.getCompanyParameterId()) != null) {
						companyParameterSvc.editCompanyParameter(companyParameter);
					} else {
						companyParameterSvc.saveCompanyParameter(companyParameter);
					}
				}
				redirectAttributes.addFlashAttribute("msgsuccess",
						messageSource.getMessage("message.success.companyparameter.edit", null, locale));
			}
		} catch (Exception ex) {
			redirectAttributes.addFlashAttribute("msgerror",
					messageSource.getMessage("message.error.companyparameter.edit", null, locale));
			logger.error("save company parameter", ex);
			System.out.println(ex);
		}
		return "redirect:../../list";
	}

}
