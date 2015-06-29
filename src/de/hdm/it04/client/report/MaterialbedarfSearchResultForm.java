package de.hdm.it04.client.report;

import de.hdm.it04.client.ShowCase;

public class MaterialbedarfSearchResultForm extends ShowCase {
	
	private String headlineText;
	private String headlineTextStyle;
	
	public MaterialbedarfSearchResultForm() {
		this.headlineText = "Bitte Daten eintragen.";
		this.headlineTextStyle = "formTitle";
	}
	

	protected String getHeadlineText() {
		return this.headlineText;
	}

	
	protected String getHeadlineTextStyle() {
		return this.headlineTextStyle;
	}
	
	protected void run() {

	}

}
