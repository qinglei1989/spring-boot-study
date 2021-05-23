package com.rrc.util;

import org.apache.commons.lang3.StringUtils;

import java.math.BigDecimal;
import java.text.DecimalFormat;

/**
 * BigDecimal类型的操作
 * BigDecimal类型的比较、加、减、乘、除等功能
 * @author zhanghongyu
 * 
 * */
public class BigDecimalUtil {
	
	public static final int DEFAULT_SCALE = 2;
	/**
	 * 转换为万元
	 */
	public static final BigDecimal MILLION_UNIT = new BigDecimal(10000);
	/**
	 * BigDecimal类型的比较大小
	 * @param valueOne
	 * @param valueTwo
	 * @return result(true:大于等于;false:小于)
	 * */
	public static boolean compareTo(BigDecimal valueOne,BigDecimal valueTwo){
		boolean result =false;
		if(valueOne.compareTo(valueTwo) >= 0){
			result = true;
		}
		return result;
	}
	/**
	 * BigDecimal类型的格式化方法
	 * @param formatValue 格式化的BigDecimal对象
	 * 
	 * @return String 保留两位小数的向下舍入的移除所有尾部零的String类型
	 * */
	public static String format(BigDecimal formatValue){
		if(formatValue == null){
			return "";
		}
		return format(formatValue, DEFAULT_SCALE);
	}
	
	/**
	 * BigDecimal类型的格式化方法
	 * @param formatValue 格式化的BigDecimal对象
	 * @param scale 保留位数
	 * 
	 * @return String 保留指定小数位的向下舍入的移除所有尾部零的String类型
	 * */
	public static String format(BigDecimal formatValue, int scale){
		
		return format(formatValue, scale, BigDecimal.ROUND_DOWN);
	}
	
	/**
	 * BigDecimal类型的格式化方法
	 * @param formatValue 格式化的BigDecimal对象
	 * @param scale 保留位数
	 * @param round 取舍模式
	 * @return String 保留指定小数位的指定舍入方式的移除所有尾部零的String类型
	 * */
	public static String format(BigDecimal formatValue, int scale, int round){
		
		return format(formatValue, scale, round, true);
	}
	
	/**
	 * BigDecimal类型的格式化方法
	 * @param formatValue 格式化的BigDecimal对象
	 * @param scale 保留位数
	 * @param round 取舍模式
	 * @param stripZeros 保留尾部零
	 * 
	 * @return String 保留指定小数位的指定舍入方式的指定是否移除尾部零的String类型
	 * */
	public static String format(BigDecimal formatValue, int scale, int round, boolean stripZeros){
		if(formatValue == null){
			formatValue = BigDecimal.ZERO;
		}
		if (round < BigDecimal.ROUND_UP || round > BigDecimal.ROUND_UNNECESSARY){
			round = BigDecimal.ROUND_DOWN;
		}
		if(stripZeros){
			return formatValue.setScale(scale, round).stripTrailingZeros().toPlainString();
		}else{
			return formatValue.setScale(scale, round).toPlainString();
		}
	}
	
	/**
	 * 将元转换为万元
	 * @param maxMoney 元
	 * @return String 万元的字符串
	 */
	public static BigDecimal changeMillionToYuan(BigDecimal money) {
		if(money == null || !compareTo(money, BigDecimal.ZERO)){
			return BigDecimal.ZERO;
		}
		
		return money.multiply(MILLION_UNIT);
	}
	
	/**
	 * 将万元转换为元
	 * @param money 元
	 * @return String 万元的字符串
	 */
	public static String changeToMillion(BigDecimal money) {
		if(money == null || !compareTo(money, BigDecimal.ZERO)){
			return StringUtils.EMPTY;
		}
		
		return format(money.divide(MILLION_UNIT), 0);
	}
	
	/**
	 * 格式化为保留两位小数的字符串.
	 * @param formatValue BigDecimal类型的对象
	 * @return String 保留两位小数的字符串
	 */
	public static String formatTwoDecimal(BigDecimal formatValue){
		if(formatValue == null){
			return "0.00";
		}
		return format(formatValue, DEFAULT_SCALE, BigDecimal.ROUND_DOWN, false);
	}
	
	/**
	 * 将bigdecimal的数字用逗号每三个分割
	 * */
	public static String formatTosepara(BigDecimal money){
		if(money == null || BigDecimal.ZERO.equals(money)){
			return "0";
		}
		DecimalFormat df = new DecimalFormat("#,###.00");
		return df.format(money);
	}
	
	
	public static void main(String[] args) {
		System.out.println(changeToMillion(new BigDecimal(100000000)));
	}
}
