package com.sifcoapp.test;

import com.sifcoapp.objects.utilities.PasswordService;

public class TestPassword {

    public static void main(String[] args) throws Exception {

        System.out.println("jc = "
                + PasswordService.getInstance().encrypt("jc"));

        System.out.println("admin123 = "
                + PasswordService.getInstance().encrypt("admin123"));

    }
}