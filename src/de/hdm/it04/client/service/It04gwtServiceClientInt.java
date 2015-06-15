package de.hdm.it04.client.service;


import de.hdm.it04.shared.Bauteil;

public interface It04gwtServiceClientInt {
	
	void getBauteil(int id);
	void create(Bauteil bt);
	void getAll();
	void findByName(String name);
	void updateBauteil(Bauteil bt);
	void delete(int id);



}
