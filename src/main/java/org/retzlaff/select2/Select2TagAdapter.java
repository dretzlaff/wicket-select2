package org.retzlaff.select2;

import java.util.ArrayList;
import java.util.List;

import org.apache.wicket.model.IModel;
import org.apache.wicket.model.Model;
import org.apache.wicket.util.string.Strings;

/**
 * Simple ISelect2AjaxAdapter for tags.
 * 
 * @author dan
 */
public class Select2TagAdapter implements ISelect2AjaxAdapter<String> {
	
	private IModel<List<String>> knownTags;
	
	public Select2TagAdapter() {
		this(new ArrayList<String>());
	}
	
	@SuppressWarnings({"unchecked", "rawtypes"})
	public Select2TagAdapter(List<String> knownTags) {
		this(new Model(new ArrayList(knownTags)));
	}
	
	public Select2TagAdapter(IModel<List<String>> knownTags) {
		this.knownTags = knownTags;
	}
	
	/**
	 * All known tags available for selection
	 */
	public void setKnownTags(IModel<List<String>> knownTags) {
		this.knownTags = knownTags;
	}
	public IModel<List<String>> getKnownTags() {
		return knownTags;
	}
	
	/**
	 * Returns a page of tags that contain the filter (case insensitive)
	 */
	public List<String> getChoices(int start, int count, String filter) {
		
		if (Strings.isEmpty(filter)) {
			filter = "";
		}
		else {
			filter = filter.toLowerCase();
		}
		
		List<String> tags = new ArrayList<String>();
		for (String tag : knownTags.getObject()) {
			if (tag.toLowerCase().contains(filter)) {
				if (start == 0 || --start == 0) {
					tags.add(tag);
					if (--count == 0) {
						break;
					}
				}
			}
		}
		return tags;
	}

	/**
	 * Notification of a new tag's creation.
	 */
	protected void onTagCreated(String tag) {
		knownTags.getObject().add(tag);
	}

	@Override
	public String getChoice(String id) {
		if (!knownTags.getObject().contains(id)) {
			onTagCreated(id);
		}
		return id;
	}

	@Override
	public String getChoiceId(String tag) {
		return tag;
	}

	@Override
	public Object getDisplayValue(String tag) {
		return tag;
	}
}
