package classes.com.cn.bean;

import java.util.ArrayList;
import java.util.List;

//web树实体类
public class Location {
	private int id;
	private String name;
	private int rgt;
	private int lft;
	public Location(){
		
	}
	public int getId() {
		return id;
	}
	public void setId(int id) {
		this.id = id;
	}
	public String getName() {
		return name;
	}
	public void setName(String name) {
		this.name = name;
	}
	public int getRgt() {
		return rgt;
	}
	public void setRgt(int rgt) {
		this.rgt = rgt;
	}
	
	@Override
	public String toString() {
		return "Location [id=" + id + ", name=" + name + ", rgt=" + rgt
				+ ", lft=" + lft + "]";
	}
	public int getLft() {
		return lft;
	}
	public void setLft(int lft) {
		this.lft = lft;
	}
	
}
