package org.retzlaff.select2;

import org.apache.wicket.util.io.IClusterable;
import org.apache.wicket.util.time.Duration;

/**
 * Select2 Settings
 * 
 * @author dan
 */
public class Select2Settings implements IClusterable {
	private static final long serialVersionUID = 5430777801377122502L;
	
	private Duration cacheDuration = Duration.NONE;
	private int pageLimit = 10;
	private int quietMillis;
	private boolean allowClear;
	private int minimumInputLength;
	private int minimumResultsForSearch;
	private String placeholderKey;
	private String noMatchesKey;
	private String inputTooShortKey;
	private boolean tagging;
	private String width = "resolve";
	
	
	/**
	 * If set, defines the method used to set the width of the element.
	 * 
	 * <p>
	 * Can be either of, element, copy, resolve or another value used verbatim.
	 */
	public String getWidth() {
		return width;
	}
	public void setWidth(String width) {
		this.width = width;
	}
	
	// AJAX settings
	
	/**
	 * For AJAX population, how long browser can cache results.
	 */
	public void setCacheDuration(Duration cacheDuration) {
		this.cacheDuration = cacheDuration;
	}
	public Duration getAjaxCacheDuration() {
		return cacheDuration;
	}

	/**
	 * For AJAX population, show this many choices per page. 
	 */
	public void setPageLimit(int pageLimit) {
		this.pageLimit = pageLimit;
	}
	public int getPageLimit() {
		return pageLimit;
	}

	/**
	 * For AJAX population, number of milliseconds to wait for the user to stop typing before issuing the request.
	 */
	public void setQuietMillis(int quietMillis) {
		this.quietMillis = quietMillis;
	}
	public int getQuietMillis() {
		return quietMillis;
	}
	
	// Shared settings
	
	/**
	 * Allow clear
	 */
	public void setAllowClear(boolean allowClear) {
		this.allowClear = allowClear;
	}
	public boolean isAllowClear() {
		return allowClear;
	}

	/**
	 * Number of character necessary to start a search.
	 */
	public void setMinimumInputLength(int minimumInputLength) {
		this.minimumInputLength = minimumInputLength;
	}
	public int getMinimumInputLength() {
		return minimumInputLength;
	}

	/**
	 * The minimum number of results that must be initially populated in order to keep the search field.
	 */
	public void setMinimumResultsForSearch(int minimumResultsForSearch) {
		this.minimumResultsForSearch = minimumResultsForSearch;
	}
	public int getMinimumResultsForSearch() {
		return minimumResultsForSearch;
	}

	/**
	 * Localization key for "choose one" message.
	 */
	public void setPlaceholderKey(String placeholderKey) {
		this.placeholderKey = placeholderKey;
	}
	public String getPlaceholderKey() {
		if (placeholderKey != null) {
			return placeholderKey;
		}
		if (isAllowClear()) {
			return "null"; // AbstractSingleSelectChoice default: Choose One
		}
		return null;
	}

	/**
	 * Localization key for "no matches" message.
	 */
	public void setNoMatchesKey(String noMatchesKey) {
		this.noMatchesKey = noMatchesKey;
	}
	public String getNoMatchesKey() {
		return noMatchesKey;
	}

	/**
	 * Localization key for "input too short" message. The minimum length is available as ${}.
	 */
	public void setInputTooShortKey(String inputTooShortKey) {
		this.inputTooShortKey = inputTooShortKey;
	}
	public String getInputTooShortKey() {
		return inputTooShortKey;
	}
	
	/**
	 * If set, the search string will be available for selection.
	 * <p>
	 * Selection of a new tag name will cause {@link ISelect2AjaxAdapter#getChoice(String)} invocations with new IDs.
	 * <p>
	 * Only available with AJAX components.
	 */
	public void setTagging(boolean tagging) {
		this.tagging = tagging;
	}
	public boolean isTagging() {
		return tagging;
	}
	
}
