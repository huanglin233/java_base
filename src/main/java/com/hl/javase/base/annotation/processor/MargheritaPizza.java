package com.hl.javase.base.annotation.processor;

/**
 * 
 * @author huanglin 2023/04/29 下午9:35:07
 *
 */
@Factory(id = "Margherita", type = Meal.class)
public class MargheritaPizza implements Meal {

	@Factory(id = "Margherita", type = Meal.class)
	@Override
	public float getPrice() {
		return 6.0f;
	}
}