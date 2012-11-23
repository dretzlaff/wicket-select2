package org.retzlaff.select2;

import java.util.List;

import org.apache.wicket.util.io.IClusterable;
import org.apache.wicket.markup.html.form.IChoiceRenderer;

/**
 * Provides choices to a Select2, implements choice/ID mapping, and renders choices.
 * 
 * @author dan
 * @param <T>
 */
public interface ISelect2AjaxAdapter<T> extends IClusterable {
	
	/**
	 * Returns an internal representation of the object. IDs cannot contain commas.
	 */
	String getChoiceId(T object);
	
	/**
	 * Returns the object corresponding to the given ID
	 * <p>
	 * @see IChoiceRenderer#getIdValue(Object, int)
	 */
	T getChoice(String id);
	
	/**
	 * Returns a page of results
	 */
	List<T> getChoices(int start, int count, String filter);
	
	/**
	 * Returns a user-facing display value.
	 */
	Object getDisplayValue(T object);
}
