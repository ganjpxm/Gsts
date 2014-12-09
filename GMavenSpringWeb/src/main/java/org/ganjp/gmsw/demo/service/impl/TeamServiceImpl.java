package org.ganjp.gmsw.demo.service.impl;

import java.util.List;

import org.ganjp.gmsw.demo.dao.TeamDAO;
import org.ganjp.gmsw.demo.model.Team;
import org.ganjp.gmsw.demo.service.TeamService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

@Service
@Transactional
public class TeamServiceImpl implements TeamService {
	
	@Autowired
	private TeamDAO teamDAO;

	public void addTeam(Team team) {
		teamDAO.addTeam(team);		
	}

	public void updateTeam(Team team) {
		teamDAO.updateTeam(team);
	}

	public Team getTeam(int id) {
		return teamDAO.getTeam(id);
	}

	public void deleteTeam(int id) {
		teamDAO.deleteTeam(id);
	}

	public List<Team> getTeams() {
		return teamDAO.getTeams();
	}

}