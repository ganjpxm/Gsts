package org.ganjp.gmsw.demo.model;

import java.util.List;
import java.util.Map;

public class FirstNestedBean {
	
	private String foo;

	private List<FirstNestedBean> list;
	
	private Map<String, FirstNestedBean> map;
	
	public String getFoo() {
		return foo;
	}

	public void setFoo(String foo) {
		this.foo = foo;
	}

	public List<FirstNestedBean> getList() {
		return list;
	}

	public void setList(List<FirstNestedBean> list) {
		this.list = list;
	}

	public Map<String, FirstNestedBean> getMap() {
		return map;
	}

	public void setMap(Map<String, FirstNestedBean> map) {
		this.map = map;
	}

	public String toString() {
        StringBuilder sb = new StringBuilder();
        sb.append("NestedBean");
        if (foo != null) {
        	sb.append(" foo=").append(foo);
        }
        if (list != null) {
        	sb.append(" list=").append(list);
        }
        if (map != null) {
        	sb.append(" map=").append(map);
        }
        return sb.toString();
    }
}