package com.rabbit.fitness.user.util;

import java.util.Random;

public class IDUtil {
    public static String getItemID( )
    {
        String val = "";
        Random random = new Random();
        for ( int i = 0; i <11; i++ )
        {
            String str = random.nextInt( 2 ) % 2 == 0 ? "num" : "char";
            if ( "char".equalsIgnoreCase( str ) )
            { // 产生字母
                int nextInt = random.nextInt( 2 ) % 2 == 0 ? 65 : 97;
                // System.out.println(nextInt + "!!!!"); 1,0,1,1,1,0,0
                val += (char) ( nextInt + random.nextInt( 26 ) );
            }
            else if ( "num".equalsIgnoreCase( str ) )
            { // 产生数字
                val += String.valueOf( random.nextInt( 10 ) );
            }
        }
        return val;
    }

    public static void main(String[] args) {
        System.out.println(IDUtil.getItemID());
    }
}
