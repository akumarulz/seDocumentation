package com.michael.documentation.resources.model.pojo;

public class NavPojo {

	private String href;
	private String label;

	public String getHref() {
		return href;
	}

	public void setHref(String href) {
		this.href = href;
	}

	public String getLabel() {
		return label;
	}

	public void setLabel(String label) {
		this.label = label;
	}

	@Override
	public String toString() {
		return "NavPojo [href=" + href + ", label=" + label + "]";
	}
	
	
}
