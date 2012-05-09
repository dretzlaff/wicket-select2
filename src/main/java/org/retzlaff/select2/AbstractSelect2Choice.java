package org.retzlaff.select2;

import java.util.Collection;
import java.util.List;

import org.apache.wicket.IResourceListener;
import org.apache.wicket.markup.html.form.HiddenField;
import org.apache.wicket.model.IModel;
import org.apache.wicket.request.IRequestParameters;
import org.apache.wicket.request.cycle.RequestCycle;
import org.apache.wicket.request.resource.AbstractResource;
import org.apache.wicket.request.resource.IResource.Attributes;

/**
 * Select2 drop down component that populates its choices with AJAX.
 * <p>
 * This component must be attached to a hidden input field.
 * 
 * @author dan
 * @param <T> model type (E or Collection<E>)
 * @param <E> element type
 */
public abstract class AbstractSelect2Choice<T, E> extends HiddenField<T> implements IResourceListener {
	
	private final Select2Behavior<T, E> behavior;
	
	private ISelect2AjaxAdapter<E> adapter;
	
	/**
	 * Constructor.
	 * 
	 * @param id
	 * @param adapter
	 */
	protected AbstractSelect2Choice(String id, IModel<T> model, ISelect2AjaxAdapter<E> adapter, boolean multiple) {
		super(id, model);
		this.behavior = new Select2Behavior<T, E>(multiple, this);
		this.adapter = adapter;
		add(behavior);
	}
	
	// accessors

	public void setSettings(Select2Settings settings) {
		behavior.setSettings(settings);
	}
	public Select2Settings getSettings() {
		return behavior.getSettings();
	}
	
	/**
	 * Provides results for AJAX responses
	 */
	public void setAdapter(ISelect2AjaxAdapter<E> adapter) {
		this.adapter = adapter;
	}
	public ISelect2AjaxAdapter<E> getAdapter() {
		return adapter;
	}
	
	/**
	 * Returns a non-null list of currently selected objects.
	 */
	protected abstract Collection<E> getModelObjects();
	
	@Override
	public void onResourceRequested() {
		RequestCycle rc = RequestCycle.get();
		Attributes a = new Attributes(rc.getRequest(), rc.getResponse(), null);
		new AjaxChoiceResource().respond(a);
	}
	
	private class AjaxChoiceResource extends AbstractResource {
		@Override
		protected ResourceResponse newResourceResponse(Attributes attributes) {
			ResourceResponse response = new ResourceResponse();
			response.setContentType("text/javascript");
			response.setCacheDuration(getSettings().getAjaxCacheDuration());
			response.setWriteCallback(new WriteCallback() {
				@Override
				public void writeData(Attributes attributes) {
					IRequestParameters params = RequestCycle.get().getRequest().getQueryParameters();
					int page = params.getParameterValue("page").toInt(1) - 1;
					String filter = params.getParameterValue("q").toString();
					String callback = params.getParameterValue("callback").toString();
					
					int pageLimit = getSettings().getPageLimit();
					List<E> choices = adapter.getChoices(page * pageLimit, pageLimit + 1, filter);
					
					boolean more = choices.size() > pageLimit;
					if (more) {
						choices = choices.subList(0, pageLimit);
					}
					CharSequence results = behavior.renderChoices(choices, 0);
					
					StringBuilder sb = new StringBuilder();
					sb.append(callback).append("(\n");
					sb.append("{results:").append(results).append(",more:").append(more).append('}');
					sb.append("\n)");
					
					attributes.getResponse().write(sb);
				}
			});
			return response;
		}
	}
}
