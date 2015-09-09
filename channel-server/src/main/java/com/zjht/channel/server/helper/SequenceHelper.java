/** 
 * Project Name:channel-server 
 * File Name:SequenceHelper.java 
 * Package Name:com.zjht.channel.server.helper 
 * Date:2015年9月2日下午4:42:07 
 * 
 */

package com.zjht.channel.server.helper;

import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.List;
import java.util.concurrent.TimeUnit;
import java.util.concurrent.atomic.AtomicLong;

import com.google.common.collect.Lists;
import com.zjht.channel.common.constant.Symbol;
import com.zjht.channel.common.helper.StringHelper;

/**
 * ClassName: SequenceHelper <br/>
 * Function: 生成请求唯一标识. <br/>
 * 生成格式：
 * <p>
 * PREFIX + 17位时间(yyyyMMddhhmmssmi,精确到毫秒)+10位序列 date: 2015年9月2日 下午4:42:07 <br/>
 * 
 * @author jun yangwenjun@chinaexpresscard.com
 * @version v0.0.1
 * @since JDK 1.8
 */
public class SequenceHelper {
  
    /** 机器编号 **/
    private static String WORKERID = "M1";
    
    /** 序列前缀  **/
    private static String PREFIX = "CHANNEL";
    
    /** 尾部长度 **/
    private static int TAIL_LENGTH = 5;
    
    /** 尾部最小值 **/
    private static long TAIL_MIN_VALUE = 1L;
    
    /** 尾部填充字符 **/
    private static String TIAL_PAD_SYMBOL="0";
    
    private static AtomicLong SEQUENCE_LONG = new AtomicLong(TAIL_MIN_VALUE);
    
    private final static SimpleDateFormat DATE_FORMAT = new SimpleDateFormat("yyyyMMddhhmmssSSS"); 
    
    /**
     * 生成唯一序列号. <br/> 
     * <b>格式:</b><br/>
     * WORKERID+PREFIX+17位时间(精确到毫秒)+后缀(TAIL_LENGTH长度的数字序列,不足TAIL_LENGTH长度位左边补0)<br>
     * <br>
     * <b>注意:</b> <br/>
     * <p>该序列最大支持1毫秒内产生99999(TAIL_LENGTH长度)个序列的并发,超过这个数则可能产生重复的序列</p>
     * 
     * @author jun yangwenjun@chinaexpresscard.com
     * @return 
     * @since JDK 1.8
     */
    public  static  String next(){
        String tail = "";
        String str = "";
        for (;;) {
            long currLong = SEQUENCE_LONG.get();
            long nextLong =  String.valueOf(currLong + 1).length()>TAIL_LENGTH?TAIL_MIN_VALUE:(currLong + 1);
            if(SEQUENCE_LONG.compareAndSet(currLong, nextLong)){
                tail = String.valueOf(nextLong);
                break;
            }
        }
        tail  = StringHelper.leftPad(tail,TAIL_LENGTH-tail.length(),TIAL_PAD_SYMBOL);

        str = WORKERID.concat(Symbol.HYPHEN.code()).concat(PREFIX).concat(DATE_FORMAT.format(new Date())).concat(tail);
        return str;
    }
   
    public static void main(String[] args) throws Exception {
        List<String> seqs = Lists.newArrayList();
        
        for (int i = 0; i < 10; i++) {
            new Thread(new Runnable(){
                @Override
                public void run() {
                    for (int j = 0; j < 50; j++) {
                        String s = next();
                        if(seqs.contains(s)){
                            System.err.println("SEQUENCE ALREADY EXISTS! SEQUENCE=["+s+"]");
                            System.exit(0);
                        }
                        seqs.add(s);
                        try {
                            TimeUnit.MILLISECONDS.sleep(2L);
                        } catch (InterruptedException e) {
                            e.printStackTrace();
                        }
                    }
                }}).start();
            try {
                TimeUnit.MILLISECONDS.sleep(2L);
            } catch (InterruptedException e) {
                e.printStackTrace();
            }
        }
    }
}
