package de.hdm.it04.server;



import com.google.gwt.user.server.rpc.RemoteServiceServlet;

import de.hdm.it04.client.service.It04gwtService;
import de.hdm.it04.server.db.BauteilMapper;
import de.hdm.it04.shared.Bauteil;
import java.util.Vector;



public class It04gwtServiceImpl extends RemoteServiceServlet implements It04gwtService {

	
	private static final long serialVersionUID = 1L;

	public Vector<Bauteil> getBauteil(int id){
		
		return BauteilMapper.bauteilMapper().findByKey(id);
	}
	
	public Bauteil updateBauteil(Bauteil bt){
		
		bt = BauteilMapper.bauteilMapper().update(bt);
		
		
		return bt;
	}
	
	public Vector<Bauteil> findByName(String name){
		
		Bauteil bt = new Bauteil();
	
	
		return BauteilMapper.bauteilMapper().findByName(name);
	}
	
	public Bauteil create(Bauteil bt){
	
	bt = BauteilMapper.bauteilMapper().insert(bt);
	
	return bt;
	}
	
	
	public Vector<Bauteil> getAll() {
			
	return BauteilMapper.bauteilMapper().findAll();
	}


	
	
	
	


	
	
	

}
		
	


	
	
	



