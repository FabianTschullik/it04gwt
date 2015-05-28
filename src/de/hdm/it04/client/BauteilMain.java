package de.hdm.it04.client;



import com.google.gwt.user.client.ui.Composite;
import com.google.gwt.user.client.ui.Label;
import com.google.gwt.user.client.ui.VerticalPanel;

public class BauteilMain extends Composite {
	
	private VerticalPanel vPanel = new VerticalPanel();
	//private MainViewEditor main;
	public BauteilMain() {
		
		initWidget(this.vPanel);
		//this.main = main;
		
	
		
		
		Label label1 =new Label("Hallo BauteilMain");
		this.vPanel.add(label1);
		
		
		
			

}


	
}
