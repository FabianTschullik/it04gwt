package de.hdm.it04.client.gui;

import com.google.gwt.user.client.ui.RootPanel;
import com.google.gwt.user.client.ui.Widget;

public class ContentContainer {
	
	 private static ContentContainer singleton = new ContentContainer( );
	 
	 
	 private ContentContainer(){ }
	 
	 /* Static 'instance' method */
	   public static ContentContainer getInstance( ) {
	      return singleton;
	   }
	   
	   
	   
	   
	 public void setContent (Widget widget){
		 
		 RootPanel.get("content").clear();
		 RootPanel.get("content").add(widget);
		 
		 
	 }
	 

}
