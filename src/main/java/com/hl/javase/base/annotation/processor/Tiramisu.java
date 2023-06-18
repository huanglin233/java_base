package com.hl.javase.base.annotation.processor;

/**
 * 
 * @author huanglin 2023/04/29 下午9:37:17
 *
 */
@Factory(id = "Tiramisu", type = Meal.class)
public class Tiramisu implements Meal{

	@Override
	public float getPrice() {
		return 4.5f;
	}

}
