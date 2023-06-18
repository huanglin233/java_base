package com.hl.javase.base.annotation.processor;

/**
 * 
 * @author huanglin 2023/04/29 下午9:36:34
 *
 */
@Factory(id = "Calzone", type = Meal.class)
public class CalzonePizza implements Meal{

	@Override
	public float getPrice() {
		return 8.5f;
	}

}