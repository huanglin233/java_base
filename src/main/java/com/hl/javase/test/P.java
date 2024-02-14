package com.hl.javase.test;

import javax.servlet.http.HttpSessionActivationListener;
import javax.servlet.http.HttpSessionEvent;

/**
 * @author huanglin
 * @date 2023/12/05 21:05
 */
public class P implements HttpSessionActivationListener {
    @Override
    public void sessionDidActivate(HttpSessionEvent se) {
        HttpSessionActivationListener.super.sessionDidActivate(se);
    }



    @Override
    public void sessionWillPassivate(HttpSessionEvent se) {
        HttpSessionActivationListener.super.sessionWillPassivate(se);
    }

    public B getA() {
        return new B();
    }
}
