package com.player.service.impl;

import java.util.List;

import com.player.dao.PlayerSearchDAO;
import com.player.dao.impl.PlayerSearchDAOImpl;
import com.player.exception.BusinessException;
import com.player.model.Player;
import com.player.service.PlayerSearchService;

public class PlayerSearchServiceImpl implements PlayerSearchService {

	private PlayerSearchDAO searchDAO = new PlayerSearchDAOImpl();

	@Override
	public Player getPlayerById(int id) throws BusinessException {
		Player player = null;
		if (id > 99 && id < 1000) {
			// code here for DAO
			player = searchDAO.getPlayerById(id);
		} else {
			throw new BusinessException("Entered Player ID " + id + " is INVALID!!!.. Please ReEnter");
		}
		return player;
	}

	@Override
	public List<Player> getAllPlayers() throws BusinessException {
		List<Player> playersList = null;
		playersList = searchDAO.getAllPlayers();
		return playersList;
	}

	@Override
	public List<Player> getPlayersByName(String name) throws BusinessException {
		List<Player> playerList = null;
		if (name!=null && !(name.isEmpty())) {
			playerList = searchDAO.getPlayersByName(name);
		}
		else {
			throw new BusinessException("Please enter a player name! Please ReEnter");
		}
		return playerList;
	}

	@Override
	public List<Player> getPlayersByAge(int age) throws BusinessException {
		List<Player> playerList = null;
		if(age>=0) {
			playerList = searchDAO.getPlayersByAge(age);
		} else {
			throw new BusinessException("Please enter a positive age! Please ReEnter");
		}
		return playerList;
	}

	@Override
	public Player getPlayerByContactNumber(long contact) throws BusinessException {
		Player player = null;
	
		if (contact>=0) {
			player = searchDAO.getPlayerByContactNumber(contact);
		}
		else {
			throw new BusinessException("Please enter a positive number for contact number! Please ReEnter");
			}
		
		return player;
	}

	@Override
	public List<Player> getPlayersByTeamName(String teamName) throws BusinessException {
		 List<Player> playerList = null;
		 System.out.println(teamName);
		 if(teamName!=null && !(teamName.isEmpty())) {
			 playerList = searchDAO.getPlayersByTeamName(teamName);
		 }
		 else {
			 throw new BusinessException("Please enter a team name! Please ReEnter");
		 }
		return playerList;
	}

	@Override
	public List<Player> getPlayersByGender(String gender) throws BusinessException {
		List<Player> playerListGender=null;
		if(gender!=null && gender.matches("[mMFfoO]{1}")) {
			playerListGender=searchDAO.getPlayersByGender(gender);
		}else {
			throw new BusinessException("Entered Gender "+gender+" is INVALID...");
		}
		return playerListGender;
	}

}
