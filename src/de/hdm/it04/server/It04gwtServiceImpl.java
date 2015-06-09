package de.hdm.it04.server;



import com.google.gwt.user.server.rpc.RemoteServiceServlet;

import de.hdm.it04.client.service.It04gwtService;
import de.hdm.it04.server.db.BauteilMapper;
import de.hdm.it04.shared.Bauteil;



public class It04gwtServiceImpl extends RemoteServiceServlet implements It04gwtService {

	
	
	
	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;

	public Bauteil getBauteil(int id){
		
		Bauteil bt = new Bauteil();
		bt = BauteilMapper.bauteilMapper().findByKey(id);
	
		
		
		return bt;
		
		
	}
	
	public Bauteil create(String name){
	
	Bauteil bt = new Bauteil();
	
	bt.setName(name);
	bt = BauteilMapper.bauteilMapper().insert(bt);
	
	return bt;
	
	}
	
	


	
	
	

}
		
	


	
	
	



