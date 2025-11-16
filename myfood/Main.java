package myfood;

import easyaccept.EasyAccept;

public class Main {
    public static void main(String[] args) {
        String facade = "myfood.Facade";
        EasyAccept.main(new String[]{facade, "test/milestone1/us1_1.txt"});
        EasyAccept.main(new String[]{facade, "test/milestone1/us1_2.txt"});
    }
}

