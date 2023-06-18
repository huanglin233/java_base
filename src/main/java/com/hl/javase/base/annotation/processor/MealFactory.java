package com.hl.javase.base.annotation.processor;

/**
 * 
 * @author huanglin 2023/04/29 下午9:42:54
 *
 */
public class MealFactory {

	public Meal create(String id) {
	    if (id == null) {
	      throw new IllegalArgumentException("id is null!");
	    }
	    if ("Calzone".equals(id)) {
	      return new CalzonePizza();
	    }

	    if ("Tiramisu".equals(id)) {
	      return new Tiramisu();
	    }

	    if ("Margherita".equals(id)) {
	      return new MargheritaPizza();
	    }

	    throw new IllegalArgumentException("Unknown id = " + id);
	  }
}
