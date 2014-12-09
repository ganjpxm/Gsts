package org.ganjp.gmsw.demo.dao;

import java.util.List;

import org.ganjp.gmsw.demo.model.Team;

public interface TeamDAO {
	
	public void addTeam(Team team);
	public void updateTeam(Team team);
	public Team getTeam(int id);
	public void deleteTeam(int id);
	public List<Team> getTeams();

}