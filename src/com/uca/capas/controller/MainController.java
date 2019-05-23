package com.uca.capas.controller;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
 import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.servlet.ModelAndView;

import com.uca.capas.daos.StudentDAO;
import com.uca.capas.domain.Student;

@Controller
public class MainController {
	
	@Autowired
	private StudentDAO studentDao;
	
	@RequestMapping("/")
	public ModelAndView initMain() {
		
		ModelAndView mav = new ModelAndView();
		List<Student> students = null;
		try {
			students = studentDao.findAll();
		}catch(Exception e){
			e.printStackTrace();
		}

		mav.addObject("students", students);
		mav.setViewName("main");
		
		return mav;
	}
	
	@RequestMapping("/buscar")
	public ModelAndView buscar(String code) {
		ModelAndView mav = new ModelAndView();
		
		Integer Code = Integer.parseInt(code);
		
		Student st = new Student();
		try {
			st = studentDao.findOne(Code);
		}catch(Exception e) {
			e.printStackTrace();
		}
		
		mav.addObject("student", st);
		mav.setViewName("result");
		
		return mav;
	}
	
	@RequestMapping(value="/save", method=RequestMethod.POST)
	public ModelAndView save() {
		ModelAndView mav = new ModelAndView();
		mav.addObject("student", new Student());
		mav.setViewName("form");
		
		return mav;
	}
	
	@RequestMapping(value="/formData", method=RequestMethod.POST)
	public ModelAndView formData(@ModelAttribute Student s) {
		ModelAndView mav = new ModelAndView();
		List<Student> students = null;
		
		int cond;
		if(s.getcStudent() != null) cond = 0;
		else cond = 1;

		try {
			studentDao.save(s, cond);
		}catch(Exception w) {
			w.printStackTrace();
		}
		students = studentDao.findAll();
		mav.addObject("students", students);
		mav.setViewName("main");
		
		return mav;
	}
	
	@RequestMapping(value="/editar", method=RequestMethod.GET)
	public ModelAndView formData2(@RequestParam("cStudent") String Code) {
		ModelAndView mav = new ModelAndView();
		Student s = null;
		Integer code = Integer.parseInt(Code);
		try {
			s = studentDao.findOne(code);
		}catch(Exception e) {
			e.printStackTrace();
		}
		
		mav.addObject("student", s);
		mav.setViewName("form2");
		return mav;
	}
	
	@RequestMapping(value="/eliminar", method=RequestMethod.GET)
	public ModelAndView eliminar(@RequestParam("cStudent") String Code) {
		ModelAndView mav = new ModelAndView();
		Integer code = Integer.parseInt(Code);
		try {
			studentDao.delete(code);
		}catch(Exception e) {
			e.printStackTrace();
		}
		
		mav.setViewName("main");
		return mav;
	}
}
