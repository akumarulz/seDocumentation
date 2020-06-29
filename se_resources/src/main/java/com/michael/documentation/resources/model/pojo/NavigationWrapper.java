package com.michael.documentation.resources.model.pojo;

import java.util.List;

public class NavigationWrapper {

	private List<NavPojo> navList;

	public List<NavPojo> getNavList() {
		return navList;
	}

	public void setNavList(List<NavPojo> navList) {
		this.navList = navList;
	}

	@Override
	public String toString() {
		return "NavigationWrapper [navList=" + navList + "]";
	}
	
}
