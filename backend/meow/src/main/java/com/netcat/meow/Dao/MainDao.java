package com.netcat.meow.Dao;

public class MainDao {

	private static MainDao instance;

	public static MainDao getInstance() {
		if (instance == null) {
			instance = new MainDao();
		}
		return instance;
	}

}
