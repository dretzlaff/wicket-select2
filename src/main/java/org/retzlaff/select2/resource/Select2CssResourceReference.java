package org.retzlaff.select2.resource;

import org.apache.wicket.request.resource.CssResourceReference;

public class Select2CssResourceReference extends CssResourceReference {
	private static final long serialVersionUID = -4192693085575758769L;
	
	private static final Select2CssResourceReference INSTANCE = new Select2CssResourceReference();
	
	private Select2CssResourceReference() {
		super(Select2CssResourceReference.class, "select2.css");
	}
	
	public static Select2CssResourceReference get() {
		return INSTANCE;
	}
}
