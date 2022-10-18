package cn.com.bootdemo6.util;

import java.io.FileWriter;
import java.io.IOException;

/* *
 *类名：AlipayConfig
 *功能：基础配置类
 *详细：设置帐户有关信息及返回路径
 *修改日期：2017-04-05
 *说明：
 *以下代码只是为了方便商户测试而提供的样例代码，商户可以根据自己网站的需要，按照技术文档编写,并非一定要使用该代码。
 *该代码仅供学习和研究支付宝接口使用，只是提供一个参考。
 */

public class AlipayConfig {
	
//↓↓↓↓↓↓↓↓↓↓请在这里配置您的基本信息↓↓↓↓↓↓↓↓↓↓↓↓↓↓↓

	// 应用ID,您的APPID，收款账号既是您的APPID对应支付宝账号
	public static String app_id = "2021000120616274";
	
	// 商户私钥，您的PKCS8格式RSA2私钥
    public static String merchant_private_key = "MIIEvgIBADANBgkqhkiG9w0BAQEFAASCBKgwggSkAgEAAoIBAQC1nhA89UrN7ylEljfClZblIBAz7o+WZtNbqI5ris9w8SOc9GHRZfPlue+wCsYPYTAEtWusoBQhEkpDl1bT8qDLPROKdPIXlY9amFE8Wt3TBBXi8HKiKG2HzMLWiE1gWdLPbwPw89nz7QZYQRWtUTi+PKek7YER1oke5evXF9hjM+eYXPmG5TojFOeRUGuTVXHaKyxv5dd1csKx9tmG5i6LrfHdXay9PVEyeB5vBnFB4v3sshLnvFS4CQlXpqza+obP/NxL7oh5gvvzthlDHELv1XsrCh8zdWkBUMrZmYDAEUzKBSfPfwmWLm+qyeZwgnfXVGqmmD1f7GgAr8rgx013AgMBAAECggEAEeybpah/zXtBE2fP8QBaeBqF/toYibt0OKOWCr8UYg0qep8T2Fq7i3yiTzmA+puY5Z4SFmNmGS147trM0vYrMGPCvfN0wScdks9dlRtSsTTCBDe+2Ios5S4sPIgPOuciix5lwS4P4KcP7T3fKc/OTdgmo3xdNKl7tvTyJbRQwtl94Kq6sADDtUDqk1AbPTNwhZJab1SFdtIk+DLxtDNC4OUTVbHBCioTDRr4ZfK5UtQU2HDbeIW+6nSmRTQVLaFJkMQk7OHfubaRLa/xUWBm1pedidRnMYkrhOohygnFE1J8rSB0TjRYw3HXSuuXL0aMEVsbLJczdmHRbbTp42F7AQKBgQDoEiKuULe1+OhxDozxGONqLjzmS6R0+8rSyhF6BcOXgUcKWY43tdzNiKdmFMosQwxem5Tk6wi1e0xu/JZO5wQIdqnUzieQ6EHp1SSAIBKtfN6/0K8gBJTXzZ9DgWJBSx3rKLngoXhAA8LoDQTdd12kvDkC2PB2/w7wUTke7RCilwKBgQDIWCIYwicJY3XFWBHnMOFCXhU4TSSKFKopcNgoSQ1pcFxJuHy0/hJB1efu75BdeSmYA3iIVP6v2ke8UhrhSf19yu3MFNwoWHxTVhgMqB1dccIqRs42M3Q8a1sjcHS11dvKxLZnDgU+vEVs1JeDtuX/mNgZcq/Y1sLgq0EtILNoIQKBgQDMY+yXig/C/U37BM2tKjZgtgh86/IT9g9URxPNXHY/YWSoAOf4uPuXKdwFVBobkZG7YVtponcxHz7aISxgoaYzrg81lbvTLDuhLk/PaHAKzAeC+NDEakSNLFQwqCtZRkc7bcsHxC6ccrOenJjEVPTsubmcgGSI6WrGzUJ5smEMUQKBgQDE4wgvOK7QZTRaSqDdiYX2iStldzqPkJthuopTnOdXVZr8wBeeb4pVtPgIMtq3YdlkkRSlNpJNE8En67CK8wHx27oys9VguQUFiTh9R8IEEUX18ju5vHtg3Uhl3aB1CsTD0upHANpauv+nYo28XcROmlDVQYZbHUPLM8ILrXViwQKBgDQ4sv7gcoipqgN/jVVZbMAaQzNJsdMnat4gQRaKAMcyubi+h8PvJzdGfJkwAgyDj3hljhzxJdV7aDFYUCBOgZIokxr9+1qg4QvOSH6NiCYHIthq3NPnOmCLqc8rPHAuzF86lcZpKND18HdHW2sG1ydFxOufCMepY37mEerzpUaw";
	
	// 支付宝公钥,查看地址：https://openhome.alipay.com/platform/keyManage.htm 对应APPID下的支付宝公钥。
    public static String alipay_public_key = "MIIBIjANBgkqhkiG9w0BAQEFAAOCAQ8AMIIBCgKCAQEAgUo14hCLKnkSN7eGzauE6xMxlw1gj8T52BsUlwk7MGt57/nNzTtKfvfm0/11TgptulSukHl1RhBb6d/Qg6NTDutacLZYnxp9gTxVjKjD1fZEHt/p7czWPpnk22PHG/XMWcolb6fHvDHauppAMMvqH4zaNOvFW1Bdg6BAd5b78pJqj3dLvxhUXy4EJTjBclP8x0yt67oLGdclgZFQM61x3BJ9EwQ0+oQHsK5HjZcRezuSRoNj9r9krVsHDOze2sU+zXgxAwjWJQJ55zc/qmUGp+Pj6nHWDoO2l9mnD5ekPEWbh4hKbZRV8GsRxXiG11rCzQnIsBZuu7bpGmzMOcETJwIDAQAB";

	// 服务器异步通知页面路径  需http://格式的完整路径，不能加?id=123这类自定义参数，必须外网可以正常访问
	public static String notify_url = " https://1d49-175-7-126-213.jp.ngrok.io/yb";

	// 页面跳转同步通知页面路径 需http://格式的完整路径，不能加?id=123这类自定义参数，必须外网可以正常访问
	public static String return_url = " https://1d49-175-7-126-213.jp.ngrok.io/tb";

	// 签名方式
	public static String sign_type = "RSA2";
	
	// 字符编码格式
	public static String charset = "utf-8";
	
	// 支付宝网关
	public static String gatewayUrl = "https://openapi.alipaydev.com/gateway.do";
	
	// 支付宝网关
	//public static String log_path = "C:\\";


//↑↑↑↑↑↑↑↑↑↑请在这里配置您的基本信息↑↑↑↑↑↑↑↑↑↑↑↑↑↑↑

    /** 
     * 写日志，方便测试（看网站需求，也可以改成把记录存入数据库）
     * @param sWord 要写入日志里的文本内容
     */
    public static void logResult(String sWord) {
        FileWriter writer = null;
        try {
            writer = new FileWriter(gatewayUrl + "alipay_log_" + System.currentTimeMillis()+".txt");
            writer.write(sWord);
        } catch (Exception e) {
            e.printStackTrace();
        } finally {
            if (writer != null) {
                try {
                    writer.close();
                } catch (IOException e) {
                    e.printStackTrace();
                }
            }
        }
    }
}

