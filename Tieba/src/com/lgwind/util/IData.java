package com.lgwind.util;

import java.util.Calendar;

public class IData {
    
    /**
     * 日历表时间，年，临时变量
     */
    public static int year=year();
    /**
     * 日历表时间，月，临时变量
     */
    public static int month=month();
    
    /**
     * 获取当前年份
     * @return
     */
    public static int year(){
        //Calendar类的静态方法getInstance()可以初始化一个日历对象
        Calendar cal = Calendar.getInstance();
        //返回当前年份
        return cal.get(Calendar.YEAR);
    }
    
    /**
     * 获取当前月份
     * @return
     */
    public static int month(){
        Calendar cal = Calendar.getInstance();
        //获取当前月份
        int month = cal.get(Calendar.MONTH) + 1;
//        if(month<10){
//            return "0"+month;
//        }
        return month;
    }
    
    /**
     * 获取当前天数
     * @return
     */
    public static int day(){
        Calendar cal = Calendar.getInstance();
        return cal.get(Calendar.DATE);
    }
    
    /**
     * 获取当前天是星期几
     * @return
     */
    public static int weekDay(){
        Calendar cal = Calendar.getInstance();
        return cal.get(Calendar.DAY_OF_WEEK);
        //https://bbs.csdn.net/topics/391937179
    }
    
    /**
     * 获取某年某月某日是星期几
     * @return
     */
    public static int weekOfDay(int year, int month, int day){
        
        Calendar time =Calendar.getInstance();
        //下面代码设置开始日期，注：不要设置为周末
        //假设设置年(2018)月(4)日(1)，注：如果是4月，设置时候要减1，如下：
        time.set(year, month-1, day);
        int weekOfDay=time.get(Calendar.DAY_OF_WEEK)-1;
        //一周第一天是在java里是星期天，所以要减1
        //System.out.println(year+"年"+month+"月"+day+"日是"+numDay(weekOfDay));
        
        return weekOfDay;
    }
    
    /**
     * 数字改为星期
     * @param day
     * @return
     */
    public static String numDay(int day){
        String daystr="星期";
        if(day==0){daystr+="天";}
        else if(day==1){daystr+="一";}
        else if(day==2){daystr+="二";}
        else if(day==3){daystr+="三";}
        else if(day==4){daystr+="四";}
        else if(day==5){daystr+="五";}
        else if(day==6){daystr+="六";}
        return daystr;
    }
    
    /**
     * 获取某年某月有几天？
     * @param year
     * @param month
     * @return
     */
    public static int daysOfMonth(int year, int month){
        Calendar c = Calendar.getInstance();
        c.set(year, month, 0); //输入类型为int类型

        int daysOfMonth = c.get(Calendar.DAY_OF_MONTH);
        System.out.println(year + "年" + month + "月有" + daysOfMonth + "天");
        return daysOfMonth;
    }
    
    
    /**
     * 获取某年某月显示的日历字符串数组
     * @return
     */
    public static String[] days(int year, int month){
        //获取某年某月的第一天是星期几
        int weekOfDay = weekOfDay(year, month, 1);
        //判断当前月有几天
        int daysOfMonth = daysOfMonth(year, month);
        
        String [] days;
        if(weekOfDay+daysOfMonth<=28){
            days=new String[28];
        }else if (weekOfDay+daysOfMonth<=35){
            days=new String[35];
        }else{
            days=new String[42];
        }
        //插入空格
        int i=0;
        for(; i<weekOfDay; i++){
            days[i]="";
        }
        //插入日期
        for(int j=1;j<=daysOfMonth;i++,j++){
            days[i]=""+j;
        }
        //插入空格
        for(;i<days.length;i++){
            days[i]="";
        }        
        //返回得到的日期表
        return days;
    }    

}
