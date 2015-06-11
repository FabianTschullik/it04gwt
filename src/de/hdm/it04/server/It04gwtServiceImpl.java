package de.hdm.it04.server;



import com.google.gwt.user.server.rpc.RemoteServiceServlet;

import de.hdm.it04.client.service.It04gwtService;
import de.hdm.it04.server.db.BauteilMapper;
import de.hdm.it04.shared.Bauteil;
import java.util.Vector;



public class It04gwtServiceImpl extends RemoteServiceServlet implements It04gwtService {

	
	private static final long serialVersionUID = 1L;

	public Bauteil getBauteil(int id){
		
		Bauteil bt = new Bauteil();
		bt = BauteilMapper.bauteilMapper().findByKey(id);
	
		return bt;
	}
	
	public Bauteil create(Bauteil bt){
	
	bt = BauteilMapper.bauteilMapper().insert(bt);
	
	return bt;
	}
	
	
	public Vector<Bauteil> getAll() {
			
	return BauteilMapper.bauteilMapper().findAll();
	}


	
	
	
	


	
	
	

}
		
	


	
	
	



