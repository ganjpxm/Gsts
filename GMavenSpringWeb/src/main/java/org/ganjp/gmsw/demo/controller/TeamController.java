package org.ganjp.gmsw.demo.controller;

import java.util.List;

import org.ganjp.gmsw.demo.model.Team;
import org.ganjp.gmsw.demo.service.TeamService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.servlet.ModelAndView;

@Controller
@RequestMapping(value="/team")
public class TeamController {
	
	@Autowired
	private TeamService teamService;
	
	@RequestMapping(value="/add", method=RequestMethod.GET)
	public ModelAndView addTeamPage() {
		ModelAndView modelAndView = new ModelAndView("demo/team/add-team-form");
		modelAndView.addObject("team", new Team());
		return modelAndView;
	}
	
	@RequestMapping(value="/add", method=RequestMethod.POST)
	public ModelAndView addingTeam(@ModelAttribute Team team) {
		
		ModelAndView modelAndView = new ModelAndView("demo/team/home");
		teamService.addTeam(team);
		
		String message = "Team was successfully added.";
		modelAndView.addObject("message", message);
		
		return modelAndView;
	}
	
	@RequestMapping(value="/list")
	public ModelAndView listOfTeams() {
		ModelAndView modelAndView = new ModelAndView("demo/team/list-of-teams");
		
		List<Team> teams = teamService.getTeams();
		modelAndView.addObject("teams", teams);
		
		return modelAndView;
	}
	
	@RequestMapping(value="/edit/{id}", method=RequestMethod.GET)
	public ModelAndView editTeamPage(@PathVariable Integer id) {
		ModelAndView modelAndView = new ModelAndView("demo/team/edit-team-form");
		Team team = teamService.getTeam(id);
		modelAndView.addObject("team",team);
		return modelAndView;
	}
	
	@RequestMapping(value="/edit/{id}", method=RequestMethod.POST)
	public ModelAndView edditingTeam(@ModelAttribute Team team, @PathVariable Integer id) {
		
		ModelAndView modelAndView = new ModelAndView("demo/team/home");
		
		teamService.updateTeam(team);
		
		String message = "Team was successfully edited.";
		modelAndView.addObject("message", message);
		
		return modelAndView;
	}
	
	@RequestMapping(value="/delete/{id}", method=RequestMethod.GET)
	public ModelAndView deleteTeam(@PathVariable Integer id) {
		ModelAndView modelAndView = new ModelAndView("demo/team/home");
		teamService.deleteTeam(id);
		String message = "Team was successfully deleted.";
		modelAndView.addObject("message", message);
		return modelAndView;
	}

}