package cn.com.bootdemo6.controller;

import cn.com.bootdemo6.util.AlipayConfig;
import com.alibaba.fastjson.JSONObject;
import com.alipay.api.AlipayApiException;
import com.alipay.api.AlipayClient;
import com.alipay.api.DefaultAlipayClient;
import com.alipay.api.internal.util.AlipaySignature;
import com.alipay.api.request.AlipayTradePagePayRequest;
import com.alipay.api.response.AlipayTradePagePayResponse;
import org.apache.tomcat.util.buf.StringUtils;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.io.PrintWriter;
import java.util.HashMap;
import java.util.Iterator;
import java.util.Map;

@RestController
public class AlipayController {

    @RequestMapping("/test")
    public String testone(){
        return "123";
    }

    @RequestMapping("/zf")
    public void zf(HttpServletResponse response, HttpServletRequest request) throws IOException {
        AlipayClient alipayClient = new DefaultAlipayClient(AlipayConfig.gatewayUrl,AlipayConfig.app_id,AlipayConfig.merchant_private_key,"json",AlipayConfig.charset,AlipayConfig.alipay_public_key,AlipayConfig.sign_type);
        AlipayTradePagePayRequest alirequest = new AlipayTradePagePayRequest();
        alirequest.setNotifyUrl(AlipayConfig.notify_url);
        alirequest.setReturnUrl(AlipayConfig.return_url);
        JSONObject bizContent = new JSONObject();
        bizContent.put("out_trade_no", "20210817010101004");
        bizContent.put("total_amount", 16.81);
        bizContent.put("subject", "测试商品");
        bizContent.put("product_code", "FAST_INSTANT_TRADE_PAY");
        bizContent.put("body", "测试商品内容");

        //// 商品明细信息，按需传入
        //JSONArray goodsDetail = new JSONArray();
        //JSONObject goods1 = new JSONObject();
        //goods1.put("goods_id", "goodsNo1");
        //goods1.put("goods_name", "子商品1");
        //goods1.put("quantity", 1);
        //goods1.put("price", 0.01);
        //goodsDetail.add(goods1);
        //bizContent.put("goods_detail", goodsDetail);

        //// 扩展信息，按需传入
        //JSONObject extendParams = new JSONObject();
        //extendParams.put("sys_service_provider_id", "2088511833207846");
        //bizContent.put("extend_params", extendParams);

        alirequest.setBizContent(bizContent.toString());
        AlipayTradePagePayResponse aliresponse = null;
        try {
            aliresponse = alipayClient.pageExecute(alirequest);
        } catch (AlipayApiException e) {
            e.printStackTrace();
        }
        if(aliresponse.isSuccess()){
            System.out.println("调用成功");
            String result=aliresponse.getBody();
            response.setContentType("text/html");
            response.setCharacterEncoding("UTF-8");
            response.getWriter().write(result);
            response.getWriter().close();
        } else {
            System.out.println("调用失败");
        }
    }

    //同步方法
    @RequestMapping("/tb")
    public void tb(HttpServletRequest request,HttpServletResponse response) throws AlipayApiException, IOException {
        System.out.println("异步信息回调");
        Map<String,String> params=new HashMap<String,String>();
        Map<String,String[]> requestParams = request.getParameterMap();
        for (Iterator<String> iter = requestParams.keySet().iterator(); iter.hasNext();) {
            String name = (String) iter.next();
            String[] values = (String[]) requestParams.get(name);
            //乱码解决，这段代码在出现乱码时使用
            //valueStr = new String(valueStr.getBytes("ISO-8859-1"), "utf-8");
            params.put(name, StringUtils.join(values));
        }

        //调用SDK验证签名
        boolean signVerified = AlipaySignature.rsaCheckV1(params, AlipayConfig.alipay_public_key, AlipayConfig.charset, AlipayConfig.sign_type);

        //——请在这里编写您的程序（以下代码仅作参考）——
        response.setContentType("text/html");
        response.setCharacterEncoding("UTF-8");
        PrintWriter out = response.getWriter();
        out.println("<h1>支付完成</h1>");

        signVerified =true;
        if(signVerified) {
            //商户订单号
            String out_trade_no = new String(request.getParameter("out_trade_no").getBytes("ISO-8859-1"),"UTF-8");

            //支付宝交易号
            String trade_no = new String(request.getParameter("trade_no").getBytes("ISO-8859-1"),"UTF-8");

            //付款金额
            String total_amount = new String(request.getParameter("total_amount").getBytes("ISO-8859-1"),"UTF-8");


            out.println("支付宝交易号:"+trade_no+"<br/>商户订单号:"+out_trade_no+"<br/>付款金额:"+total_amount);
        }else {
            out.println("验签失败");
        }
        out.close();
    }

    //异步方法
    @RequestMapping("/yb")
    public void yb(HttpServletRequest request,HttpServletResponse response) throws IOException, AlipayApiException {
        System.out.println("异步信息回调");
        Map<String,String> params=new HashMap<String,String>();
        Map<String,String[]> requestParams = request.getParameterMap();
        for (Iterator<String> iter = requestParams.keySet().iterator(); iter.hasNext();) {
            String name = (String) iter.next();
            String[] values = (String[]) requestParams.get(name);
//            String valueStr = "";
//            for (int i = 0; i < values.length; i++) {
//                valueStr = (i == values.length - 1) ? valueStr + values[i]
//                        : valueStr + values[i] + ",";
//            }
            //乱码解决，这段代码在出现乱码时使用
            //valueStr = new String(valueStr.getBytes("ISO-8859-1"), "utf-8");
            params.put(name, StringUtils.join(values));
        }

        //调用SDK验证签名
        boolean signVerified = AlipaySignature.rsaCheckV1(params, AlipayConfig.alipay_public_key, AlipayConfig.charset, AlipayConfig.sign_type);

        //——请在这里编写您的程序（以下代码仅作参考）——
        response.setContentType("text/html");
        response.setCharacterEncoding("UTF-8");
        PrintWriter out = response.getWriter();
        out.println("<h1>支付完成</h1>");

        signVerified =true;
        if(signVerified) {
            //商户订单号
            String out_trade_no = new String(request.getParameter("out_trade_no").getBytes("ISO-8859-1"),"UTF-8");

            //支付宝交易号
            String trade_no = new String(request.getParameter("trade_no").getBytes("ISO-8859-1"),"UTF-8");

            //付款金额
            String total_amount = new String(request.getParameter("total_amount").getBytes("ISO-8859-1"),"UTF-8");


            out.println("支付宝交易号:"+trade_no+"<br/>商户订单号:"+out_trade_no+"<br/>付款金额:"+total_amount);
        }else {
            out.println("验签失败");
        }
        out.close();




    }
}
