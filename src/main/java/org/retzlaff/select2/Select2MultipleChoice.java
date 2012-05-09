package org.retzlaff.select2;

import java.util.ArrayList;
import java.util.Collection;
import java.util.Collections;
import java.util.List;

import org.apache.wicket.markup.html.form.FormComponent;
import org.apache.wicket.markup.html.form.ListMultipleChoice;
import org.apache.wicket.model.IModel;

/**
 * A multiple-valued Select2 component populated with AJAX
 * 
 * @param <T> model type
 * @param <T> model type
 * @author dan
 */
public class Select2MultipleChoice<T> extends AbstractSelect2Choice<Collection<T>, T> {
	private static final String SELECT2_VALUE_SEPARATOR = ",";
	
	public Select2MultipleChoice(String id, ISelect2AjaxAdapter<T> adapter) {
		this(id, null, adapter);
	}
	
	public Select2MultipleChoice(String id, IModel<Collection<T>> model, ISelect2AjaxAdapter<T> adapter) {
		super(id, model, adapter, true);
	}
	
	@Override
	public void updateModel() {
		FormComponent.updateCollectionModel(this);
	}
	
	@Override
	protected Collection<T> getModelObjects() {
		Collection<T> collection = getModelObject();
		if (collection == null) {
			return Collections.emptyList();
		}
		return collection;
	}
	
	/**
	 * @see ListMultipleChoice#getModelValue()
	 */
	@Override
	protected String getModelValue() {
		Collection<T> collection = getModelObject();
		if (collection == null || collection.isEmpty()) {
			return "";
		}
		StringBuilder sb = new StringBuilder();
		for (T object : collection) {
			sb.append(getAdapter().getChoiceId(object)).append(SELECT2_VALUE_SEPARATOR);
		}
		sb.setLength(sb.length() - 1); // trailing comma
		return sb.toString();
	}
	
	/**
	 * @see ListMultipleChoice#convertValue().
	 */
	@Override
	protected List<T> convertValue(final String[] value) {
		if (value == null || value.length == 0 || value[0] == null) {
			return Collections.emptyList();
		}

		List<T> list = new ArrayList<T>();
		String[] ids = value[0].split(SELECT2_VALUE_SEPARATOR);
		for (String id : ids) {
			list.add(getAdapter().getChoice(id));
		}
		return list;
	}
}
