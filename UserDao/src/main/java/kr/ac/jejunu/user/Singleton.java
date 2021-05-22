package kr.ac.jejunu.user;

public class Singleton {
    private static Singleton singleton;

    public static Singleton newInstance() {
        if (singleton == null) {
            singleton = new Singleton();
        }
        return singleton;
    }
}
