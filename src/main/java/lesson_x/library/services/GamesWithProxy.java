package lesson_x.library.services;

import java.lang.annotation.ElementType;
import java.lang.annotation.Retention;
import java.lang.annotation.RetentionPolicy;
import java.lang.annotation.Target;
import java.lang.reflect.InvocationHandler;
import java.lang.reflect.Method;
import java.lang.reflect.Proxy;

class MyInvocationHandler implements InvocationHandler {
    private Object obj;

    public MyInvocationHandler(Object f1) {
        obj = f1;
    }

    @Override
    public Object invoke(Object proxy, Method method, Object[] args) throws Throwable {
        System.out.println("SomeInvocationHandler invoke : " + method.getName() );
        return method.invoke(obj, args);
    }
}

interface Game {
    void play();
}

interface Game1 extends Game {
    void fall(String scream);

    void jump();
}

interface Game2 extends Game {
    void fight();

    void die(String whisper);
}

class GameImpl implements Game1, Game2 {

    @Override
    public void fall(String scream) {
        System.out.println("When I fall - I scream:" + scream);
    }

    @Override
    public void jump() {
        System.out.println("jump");
    }

    @Override
    public void fight() {
        System.out.println("fight");
    }

    @Override
    public void die(String whisper) {
        System.out.println("die" + whisper);
    }

    @Override
    public void play() {
        System.out.println("play");
    }
}


public class GamesWithProxy {
    public static void main(String[] args) {
        Game1 game = (Game1) Proxy.newProxyInstance(GameImpl.class.getClassLoader(), GameImpl.class.getInterfaces(), new MyInvocationHandler(new GameImpl()));
        game.fall("AAAAAAAAAAAA");

        InvocationHandler springLikeHandler = new InvocationHandler() {
            @Override
            public Object invoke(Object proxy, Method method, Object[] args) throws Throwable {
                Class<?> clazz = proxy.getClass();
                Class<?>[] interfaces = clazz.getInterfaces();
                for (Class<?> i : interfaces) {
                    MyAnn annotation = i.getAnnotation(MyAnn.class);
                    System.out.println("Info from proxy - " + annotation.value());
                }

                return clazz;
            }
        };

        A aProxy = (A) Proxy.newProxyInstance(GamesWithProxy.class.getClassLoader(), new Class<?>[]{A.class}, springLikeHandler);
        aProxy.doJobA();
        B bProxy = (B) Proxy.newProxyInstance(GamesWithProxy.class.getClassLoader(), new Class<?>[]{B.class}, springLikeHandler);
        bProxy.doJobB();

    }
}


@MyAnn("Annotation of class A")
interface A {
    void doJobA();
}

@MyAnn("Annotation of class B")
interface B {
    void doJobB();
}

@Retention(RetentionPolicy.RUNTIME)
@Target(ElementType.TYPE)
@interface MyAnn {
    String value();
}