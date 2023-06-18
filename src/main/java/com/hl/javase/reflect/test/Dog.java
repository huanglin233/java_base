package com.hl.javase.reflect.test;

/**
 * 
 * @author huanglin 2023/05/10 下午10:07:23
 *
 */
public class Dog extends Animal implements I1, I2{

	private   int mDogPrivate;
	public    int mDogPublic;
    protected int mDogProtected;
    private   int mDogDefault;
    private   static int sDogPrivate;
    protected static int sDogProtected;
    public    static int sDogPublic;
    static    int        sDogDefault;

    public void eat(int num, String food) {
        System.out.println("food: " + food + "; num: " + num);
    }

    private void eatTo() {
        System.out.println("eat to ...");
    }

    public Integer getCount() {
        return 35;
    }

    public int getmDogPrivate() {
        return mDogPrivate;
    }

    public void setmDogPrivate(int mDogPrivate) {
        this.mDogPrivate = mDogPrivate;
    }

    public int getmDogPublic() {
        return mDogPublic;
    }

    public void setmDogPublic(int mDogPublic) {
        this.mDogPublic = mDogPublic;
    }

    public int getmDogProtected() {
        return mDogProtected;
    }

    public void setmDogProtected(int mDogProtected) {
        this.mDogProtected = mDogProtected;
    }

    public int getmDogDefault() {
        return mDogDefault;
    }

    public void setmDogDefault(int mDogDefault) {
        this.mDogDefault = mDogDefault;
    }

    public static int getsDogPrivate() {
        return sDogPrivate;
    }

    public static void setsDogPrivate(int sDogPrivate) {
        Dog.sDogPrivate = sDogPrivate;
    }

    public static int getsDogProtected() {
        return sDogProtected;
    }

    public static void setsDogProtected(int sDogProtected) {
        Dog.sDogProtected = sDogProtected;
    }

    public static int getsDogPublic() {
        return sDogPublic;
    }

    public static void setsDogPublic(int sDogPublic) {
        Dog.sDogPublic = sDogPublic;
    }

    public static int getsDogDefault() {
        return sDogDefault;
    }

    public static void setsDogDefault(int sDogDefault) {
        Dog.sDogDefault = sDogDefault;
    }
}
