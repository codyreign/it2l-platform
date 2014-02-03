package com.italk2learn.controller;

import java.util.List;

import javax.servlet.http.HttpServletRequest;
import javax.validation.Valid;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.security.ldap.userdetails.LdapUserDetailsImpl;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.servlet.ModelAndView;

import com.italk2learn.bo.inter.IExerciseSequenceBO;
import com.italk2learn.bo.inter.ILoginUserService;
import com.italk2learn.vo.ExerciseSequenceRequestVO;
import com.italk2learn.vo.ExerciseSequenceResponseVO;
import com.italk2learn.vo.ExerciseVO;
import com.italk2learn.vo.HeaderVO;

/**
 * Handles requests for the application exercise sequence.
 */
@Controller
@RequestMapping("/monitorWOZ")
public class MonitorWOZController {
	
	
	private LdapUserDetailsImpl user;
	
	private static final Logger logger = LoggerFactory
			.getLogger(MonitorWOZController.class);

	/*Services*/
	private IExerciseSequenceBO exerciseSequenceService;
	private ILoginUserService loginUserService;

    @Autowired
    public MonitorWOZController(IExerciseSequenceBO exerciseSequence, ILoginUserService loginUserService) {
    	this.exerciseSequenceService = exerciseSequence;
    	this.loginUserService=loginUserService;
    }
    
    @ModelAttribute("allExercises")
    public List<ExerciseVO> populateExercises() {
    	logger.info("JLF --- MonitorWOZ.populateExercises");
    	user = (LdapUserDetailsImpl)SecurityContextHolder.getContext().getAuthentication().getPrincipal();
    	ExerciseSequenceRequestVO request= new ExerciseSequenceRequestVO();
    	request.setHeaderVO(new HeaderVO());
		request.getHeaderVO().setLoginUser(user.getUsername());
        return this.exerciseSequenceService.findAllExercises(request).getResponse();
    }
	
	/**
	 * JLF: Get the main view
	 */
	@RequestMapping(value = "/",method = RequestMethod.GET)
	public String monitorWOZInit(Model model) {
		logger.info("JLF --- MonitorWOZ.init");
		user = (LdapUserDetailsImpl)SecurityContextHolder.getContext().getAuthentication().getPrincipal();
		model.addAttribute("messageInfo", new ExerciseVO());
		return "monitorWOZ";
	}

	/**
	 * JLF: Insert next exercise in a sequence of exercise by a given user
	 */
	@RequestMapping(value = "/insertNextID", method = RequestMethod.POST)
    public @ResponseBody String insertNextExercise(@RequestBody ExerciseVO messageForm, HttpServletRequest req){
		logger.info("JLF --- insertNextExercise()");
		ExerciseSequenceRequestVO request= new ExerciseSequenceRequestVO();
		ModelAndView modelAndView=new ModelAndView();
		modelAndView.setViewName("monitorWOZ");
		try{
			request.setHeaderVO(new HeaderVO());
			request.getHeaderVO().setLoginUser(user.getUsername());
			request.setIdUser(getLoginUserService().getIdUser(messageForm.getUser()));
			request.setIdExercise(getLoginUserService().getSimpleIdExersiceUser(messageForm.getUser()));
			request.setIdNextexercise(messageForm.getIdNextexercise());
			request.setFeedback(messageForm.getFeedback());
			ExerciseSequenceResponseVO response=getExerciseSequenceService().insertNextIDExercise(request);
			return "success";
		}
		catch (Exception e){
			return "error";
		}
		
	}

	public IExerciseSequenceBO getExerciseSequenceService() {
		return exerciseSequenceService;
	}

	public void setExerciseSequenceService(IExerciseSequenceBO exerciseSequenceService) {
		this.exerciseSequenceService = exerciseSequenceService;
	}

	public ILoginUserService getLoginUserService() {
		return loginUserService;
	}

	public void setLoginUserService(ILoginUserService loginUserService) {
		this.loginUserService = loginUserService;
	}

}
