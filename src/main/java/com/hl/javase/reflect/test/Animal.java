package com.hl.javase.reflect.test;

/**
 * 
 * @author huanglin 2023/05/10 下午9:52:18
 *
 */
public class Animal extends Cell{

	private   int mAnimalPrivate;
	protected int mAnimalProtected;
	public    int mAnimalPublic;
	int           mAnimalDefualt;
	
	private   static int sAnimalPrivate;
	protected static int sAnimalProtected;
	public    static int sAnimalPublic;
	static    int        sAnimalDefault;

	public void eatf() {
		System.out.println("eatf");
	}

	public int getmAnimalPrivate() {
		return mAnimalPrivate;
	}

	public void setmAnimalPrivate(int mAnimalPrivate) {
		this.mAnimalPrivate = mAnimalPrivate;
	}

	public int getmAnimalProtected() {
		return mAnimalProtected;
	}

	public void setmAnimalProtected(int mAnimalProtected) {
		this.mAnimalProtected = mAnimalProtected;
	}

	public int getmAnimalPublic() {
		return mAnimalPublic;
	}

	public void setmAnimalPublic(int mAnimalPublic) {
		this.mAnimalPublic = mAnimalPublic;
	}

	public int getmAnimalDefualt() {
		return mAnimalDefualt;
	}

	public void setmAnimalDefualt(int mAnimalDefualt) {
		this.mAnimalDefualt = mAnimalDefualt;
	}

	public static int getsAnimalPrivate() {
		return sAnimalPrivate;
	}

	public static void setsAnimalPrivate(int sAnimalPrivate) {
		Animal.sAnimalPrivate = sAnimalPrivate;
	}

	public static int getsAnimalProtected() {
		return sAnimalProtected;
	}

	public static void setsAnimalProtected(int sAnimalProtected) {
		Animal.sAnimalProtected = sAnimalProtected;
	}

	public static int getsAnimalPublic() {
		return sAnimalPublic;
	}

	public static void setsAnimalPublic(int sAnimalPublic) {
		Animal.sAnimalPublic = sAnimalPublic;
	}

	public static int getsAnimalDefault() {
		return sAnimalDefault;
	}

	public static void setsAnimalDefault(int sAnimalDefault) {
		Animal.sAnimalDefault = sAnimalDefault;
	}
}