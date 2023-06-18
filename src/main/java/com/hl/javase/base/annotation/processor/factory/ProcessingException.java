package com.hl.javase.base.annotation.processor.factory;

import javax.lang.model.element.Element;

/**
 * 
 * @author huanglin 2023/05/02 下午6:06:32
 *
 */
public class ProcessingException extends Exception{

	Element element;
	
	public ProcessingException(Element element, String msg, Object... args) {
		super(String.format(msg, args));
		this.element = element;
	}
	
	public Element getElement() {
		return element;
	}
}