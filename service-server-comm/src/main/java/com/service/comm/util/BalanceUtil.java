package com.service.comm.util;

import org.springframework.stereotype.Component;

import java.math.BigInteger;

@Component
public class BalanceUtil
{
    // 数值格式化
    private double webTogether(BigInteger value)
    {
        return BigDecimalUtil.div(value.doubleValue(), Math.pow(10, 18), 6);
    }

    /*代币余额处理*/
    private double getTokenBalanceFromResult(String data, int decimal)
    {
        if (data.startsWith("0x")) {
            data = data.substring(2);
        }
        long value = new BigInteger(data, 16).longValue();
        return BigDecimalUtil.div(value, Math.pow(10, decimal), 6);
    }

    /*代币数额转化*/
    private long convertTokenValue(double value, int decimal)
    {
        return (long) BigDecimalUtil.mul(Math.pow(10, decimal), value);
    }

    /**
     * generate a sequence of character specific by bit
     * @param bit
     * @return
     */
    public static String generateRandomCharacter(int bit){

        if (bit <15) {
            bit = 15;
        }

        StringBuffer buf = new StringBuffer("a,b,c,d,e,f,g,h,i,g,k,l,m,n,o,p,q,r,s,t,u,v,w,x,y,z");
        buf.append(",A,B,C,D,E,F,G,H,I,G,K,L,M,N,O,P,Q,R,S,T,U,V,W,X,Y,Z");
        buf.append(",~,@,#,$,%,^,&,*,(,),_,+,|,`,.");
        buf.append(",1,2,3,4,5,6,7,8,9,0");
        String[] arr = buf.toString().split(",");

        StringBuffer b = new StringBuffer();
        java.util.Random r;
        int k ;
        for(int i=0;i<bit;i++){
            r = new java.util.Random();
            k = r.nextInt();
            b.append(String.valueOf(arr[Math.abs(k % 76)]));
        }

        return b.toString();
    }
}
