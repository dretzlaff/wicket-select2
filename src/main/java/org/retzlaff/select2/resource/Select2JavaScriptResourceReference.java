package org.retzlaff.select2.resource;

import org.apache.wicket.resource.JQueryPluginResourceReference;

public final class Select2JavaScriptResourceReference extends JQueryPluginResourceReference {

	private static final long serialVersionUID = -8922802197577810066L;
	
	private static final Select2JavaScriptResourceReference INSTANCE = new Select2JavaScriptResourceReference();

	private Select2JavaScriptResourceReference() {
		super(Select2JavaScriptResourceReference.class, "select2.js");
	}

	public static Select2JavaScriptResourceReference get() {
		return INSTANCE;
	}

}
