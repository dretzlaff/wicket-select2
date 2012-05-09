package org.retzlaff.select2;

import java.util.Collections;
import java.util.List;

import org.apache.wicket.model.IModel;

/**
 * A single-valued Select2 component populated with AJAX
 * 
 * @param <T> model type
 * @author dan
 */
public class Select2SingleChoice<T> extends AbstractSelect2Choice<T, T> {
	
	public Select2SingleChoice(String id, ISelect2AjaxAdapter<T> adapter) {
		this(id, null, adapter);
	}
	
	public Select2SingleChoice(String id, IModel<T> model, ISelect2AjaxAdapter<T> adapter) {
		super(id, model, adapter, false);
	}
	
	@Override
	protected List<T> getModelObjects() {
		T object = getModelObject();
		if (object == null) {
			return Collections.emptyList();
		}
		return Collections.singletonList(getModelObject());
	}
	
	@Override
	protected String getModelValue() {
		T object = getModelObject();
		if (object == null) {
			return "";
		}
		return getAdapter().getChoiceId(object);
	}
	
	@Override
	protected T convertValue(final String[] value) {
		if (value == null || value.length == 0 || value[0] == null) {
			return null;
		}
		return getAdapter().getChoice(value[0]);
	}
}
