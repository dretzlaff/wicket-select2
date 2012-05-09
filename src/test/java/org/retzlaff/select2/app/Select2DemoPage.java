package org.retzlaff.select2.app;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.Collection;

import org.apache.wicket.ajax.AjaxRequestTarget;
import org.apache.wicket.ajax.form.OnChangeAjaxBehavior;
import org.apache.wicket.markup.html.WebPage;
import org.apache.wicket.markup.html.form.DropDownChoice;
import org.apache.wicket.markup.html.form.Form;
import org.apache.wicket.markup.html.form.FormComponent;
import org.apache.wicket.markup.html.form.ListMultipleChoice;
import org.apache.wicket.markup.html.panel.FeedbackPanel;
import org.apache.wicket.model.IModel;
import org.apache.wicket.model.Model;
import org.apache.wicket.model.util.CollectionModel;
import org.apache.wicket.request.mapper.parameter.PageParameters;
import org.retzlaff.select2.ISelect2AjaxAdapter;
import org.retzlaff.select2.Select2Behavior;
import org.retzlaff.select2.Select2MultipleChoice;
import org.retzlaff.select2.Select2SingleChoice;
import org.retzlaff.select2.Select2TagAdapter;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

/**
 * This page contains a bunch of different Select2-enhanced components.
 * 
 * @author dan
 */
public class Select2DemoPage extends WebPage {
	private static final long serialVersionUID = 1L;
	private static final Logger LOG = LoggerFactory.getLogger(Select2DemoPage.class);
	
    public Select2DemoPage(final PageParameters parameters) {
    	LOG.info("constructing home page");
    	
    	add(new FeedbackPanel("feedback"));
    	
    	ISelect2AjaxAdapter<State> adapter = new StateAdapter();

    	//
    	// Simple, static content
    	//
    	DropDownChoice<State> e1 = new DropDownChoice<State>("e1") {
    		protected void onSelectionChanged(State newSelection) {
    			LOG.info("e1 selection changed: {}", newSelection);
    		}
    		@Override
    		protected boolean wantOnSelectionChangedNotifications() {
    			return true;
    		}
    	};
    	e1.add(Select2Behavior.forChoice(e1));
    	e1.setModel(Model.of(State.NY));
    	e1.setChoices(Arrays.asList(State.values()));
    	add(newLoggingForm("f1", e1));

    	//
    	// AJAX content
    	//
    	final Select2SingleChoice<State> e2 = new Select2SingleChoice<State>("e2", adapter);
    	e2.setModel(Model.of(State.CO));
    	e2.getSettings().setPageLimit(8);
    	e2.getSettings().setAllowClear(true);
    	add(newLoggingForm("f2", e2));
    	e2.add(new OnChangeAjaxBehavior() {
			@Override
			protected void onUpdate(AjaxRequestTarget target) {
				LOG.info("e2 selection changed: {}", e2.getModelObject());
			}
		});
    	
    	//
    	// Message customization
    	//
    	Select2SingleChoice<State> e3 = new Select2SingleChoice<State>("e3", adapter);
    	e3.setModel(new Model<State>());
    	e3.getSettings().setMinimumInputLength(2);
    	e3.getSettings().setInputTooShortKey("too_short");
    	e3.getSettings().setNoMatchesKey("no_matches");
    	add(newLoggingForm("f3", e3));
    	
    	//
    	// Fewer than minimum results
    	//
    	DropDownChoice<State> e4 = new DropDownChoice<State>("e4");
    	Select2Behavior<State, State> e4b = Select2Behavior.forChoice(e4);
    	e4.add(e4b);
    	e4b.getSettings().setMinimumResultsForSearch(4);
    	e4.setModel(Model.of(State.CO));
    	e4.setChoices(Arrays.asList(State.CO, State.AZ));
    	add(newLoggingForm("f4", e4));
    	
    	//
    	// Static multiple-select
    	//
    	ListMultipleChoice<State> e5 = new ListMultipleChoice<State>("e5");
    	e5.add(Select2Behavior.forChoice(e5));
    	e5.setModel(newCollectionModel(State.CA, State.AZ, State.NM));
    	e5.setChoices(Arrays.asList(State.values()));
    	add(newLoggingForm("f5", e5));

    	//
    	// AJAX multiple-select
    	//
    	ISelect2AjaxAdapter<State> fancyAdapter = new StateAdapter() {
    		@Override
    		public Object getDisplayValue(State state) {
    			return super.getDisplayValue(state) + " - <b>" + state.name() + "</b>";
    		}
    	};
    	Select2MultipleChoice<State> e6 = new Select2MultipleChoice<State>("e6", fancyAdapter);
    	e6.setModel(newCollectionModel(State.WA, State.OR, State.CA));
    	add(newLoggingForm("f6", e6));
    	
    	//
    	// Tags
    	//
    	Select2TagAdapter tagAdapter = new Select2TagAdapter(Arrays.asList("red", "green", "blue")) {
    		@Override
    		protected void onTagCreated(String tag) {
    			LOG.info("creating tag: {}", tag);
    			super.onTagCreated(tag);
    		}
    	};
    	Select2MultipleChoice<String> e7 = new Select2MultipleChoice<String>("e7", tagAdapter);
    	e7.getSettings().setTagging(true);
    	e7.setModel(newCollectionModel("green"));
    	add(newLoggingForm("f7", e7));
    }
    
    private <T> IModel<Collection<T>> newCollectionModel(T... objects) {
    	ArrayList<T> list = new ArrayList<T>(Arrays.asList(objects));
    	return new CollectionModel<T>(list);
    }
    
    private <T> Form<T> newLoggingForm(String id, FormComponent<T> child) {
    	Form<T> form = new Form<T>(id, child.getModel()) {
        	@Override
        	protected void onSubmit() {
        		LOG.info("{} submitted {}", getId(), getModelObject());
        	}
    	};
    	form.add(child);
    	return form;
    }
}
