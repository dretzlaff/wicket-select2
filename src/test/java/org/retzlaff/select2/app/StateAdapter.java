package org.retzlaff.select2.app;

import java.util.ArrayList;
import java.util.List;

import org.retzlaff.select2.ISelect2AjaxAdapter;

/**
 * Simple Select2 adapter for State enum.
 * <p>
 * The "id" is the enum's ordinal value (index).
 * The "display value" uses {@link State#toString()}.
 * Choices are filtered using case-insensitive {@link String#contains(CharSequence)}.
 * 
 * @author dan
 */
public class StateAdapter implements ISelect2AjaxAdapter<State> {
	@Override
	public String getChoiceId(State object) {
		return String.valueOf(object.ordinal());
	}
	@Override
	public State getChoice(String id) {
		return State.values()[Integer.valueOf(id)];
	}
	@Override
	public List<State> getChoices(int start, int count, String filter) {
		filter = filter.toUpperCase();
		
		List<State> states = new ArrayList<State>();
		for (int i = 0; states.size() < count && i + start < State.values().length; ++i) {
			State state = State.values()[i + start];
			if (state.toString().toUpperCase().contains(filter)) {
				states.add(state);
			}
		}
		return states;
	}
	@Override
	public Object getDisplayValue(State state) {
		return state;
	}
}