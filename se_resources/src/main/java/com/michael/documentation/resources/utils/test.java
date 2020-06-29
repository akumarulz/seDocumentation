package com.michael.documentation.resources.utils;

import com.michael.documentation.resources.model.pojo.NavigationWrapper;

public class test {

	public static void main(String[] args) {
		NavigationWrapper vw = ResourceUtils.<NavigationWrapper>getJsonObj(NavigationWrapper.class, "navPages.json").get();
		
		System.out.println(vw);
	}

}
