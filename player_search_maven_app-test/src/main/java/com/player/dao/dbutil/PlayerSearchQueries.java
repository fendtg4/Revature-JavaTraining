package com.player.dao.dbutil;

public class PlayerSearchQueries {

	public static final String GETPLAYERBYID = "select  name, age, gender, teamname, contact from roc_revature.player where id=?";
	public static final String GETALLPLAYERS = "select id,name,age,gender,teamname,contact from roc_revature.player";
	public static final String GETPLAYERSBYGENDER = "select id,name,age,teamname,contact from roc_revature.player where gender=?";
	public static final String GETPLAYERSBYNAME = "select id,name,age,gender,teamname,contact from roc_revature.player where name=?";
	public static final String GETPLAYERSBYAGE = "select id,name,gender,teamname,contact from roc_revature.player where age=?";
	public static final String GETPLAYERSBYCONTACT = "select id,name,age,gender,teamname from roc_revature.player where contact = ?";
	public static final String GETPLAYERSBYTEAMNAME = "select id,name,age,gender,contact from roc_revature.player where teamname = ?";
	
}