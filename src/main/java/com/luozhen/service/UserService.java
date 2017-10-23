package com.luozhen.service;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.alipay.api.AlipayApiException;
import com.alipay.api.AlipayClient;
import com.alipay.api.DefaultAlipayClient;
import com.alipay.api.request.AlipayEbppBillAddRequest;
import com.alipay.api.request.AlipayEbppBillGetRequest;
import com.alipay.api.response.AlipayEbppBillAddResponse;
import com.alipay.api.response.AlipayEbppBillGetResponse;
import com.luozhen.dao.UserMapper;
import com.luozhen.entity.User;

@Service
public class UserService {

	@Autowired
	private UserMapper userMapper;
	
	private String URL="https://openapi.alipay.com/gateway.do";
	private String APP_ID="2017102209447122";
	private String APP_PRIVATE_KEY="MIIEvgIBADANBgkqhkiG9w0BAQEFAASCBKgwggSkAgEAAoIBAQCRn8k9PsyUsAvKNcgeF818GFBI1zBNKPSvqaSwkUhiNQOIARTM7SA8gmYi4mlydVwC1nJXemVdIdDNXqEzuFUISix8FOrWzK4825md4EuNgvcQqJ04voHJPytNfdFCGLAPgrH5zWxdLxLv3x8mQNdXxYRM+qDA4l5uP1TsaboLkKtER4bTALfVzwY0hcWC5oTzT2n16k3wvlrOMgu0DUOMA2Bu5/0eTXmmJo2D/15He4d7ttF+I8QIJiP+5qHNDImlMe4X9Q9mAkD5f5Zf70yBdVf987NXYY1aGIZQYpdMn7GLQL5RqHJDSPNhewjPSHEZvRk9mOIi6JFmDe5IUd+FAgMBAAECggEAOxp2C8R8NfDJqDVx1jzk6nPXyeiEsxxnSxB4Ri2BClLn7wA5V/758XUfTRNFpu3Y/VHaMIT9tPHvlBrPJHWxFV89dNLsiWejZBHg5EHPiEzaR8mXnhs8A9j+k0H4d3Ibqv/pijCExcFAV1jghJ8Wh4oj6zZhnkeB+g78QcObfeuIUBYszfpwYUb/fTHYBVIcXoszFeLFHxPO0qfR+BqY6/PaBYGLeRzjallR57vnK2oXvqGVVpMsdfTkbwEu6ICv7RFp53czVwNW2a9lvpe7Ib+oqyW3zMkMmbVqvz9bcDbOJ03tElL4HWmA5yJDb78TFSI79V6Oa3z7G3k6U2Ao1QKBgQDdkL8YRX7zD5Wm5tp4Ut8Yq1Tj499OnvTKcEJy1ZxjnSDO6ON47vBKGgnRTW/dDirka9DUHOihbGCBRPHUb1wpYhoNz6s6DvftmscbCvk0LuEd6GeC2doCYCZJwOG+Q8nmuHlc5P0lfthFOrvg+ZO5jmbvhukAQ9Ce4lRCDIR9vwKBgQCoQZ+Kh4wLSRpdWQhKZRs+HbtGjGjEnKqBwyFqsRDhaPagQUs3sYL3VCsD8YRNiaXDcPoOljVhIxj9L/SScsK+swMsmu6sNnlw4NSv0tWk5Gt+CNIGVJ4UXgfKd11ABRuuaTeny70hNBs7TAe1aPHuEIuO7/7aS+6plf+ztzE7uwKBgQCnP/9wU+17jxUZkwPxdiUgWxzOlhHYKFeChPEz/Bl8fHzqk5Q6kqtiVHt6umipxmsMWHKf216jk8rZafDeul0og/xE1rFbNzdh7cdEm6j4Ajc+u1N/y/Esf+YHIImHdWZwOmqqe9S3vCOHRsnSDrO7i0oan408/PP7K8AMx6QvwwKBgCwQO4Lg6pkOBPyLU3OANPk4ehIItWtACBtBid/SDTSq82OWlec48q5o+Xbn6hkQQjwDu0jUp5gPw/yScX9k1IFyihNFY9nvzi/0gV6mXVL4ivoPv0jvIhL9aRbp2Z7pd41eerfT0G0Ao10NFTu6BxmsdY/yZBCxMAhzguqCwD7/AoGBANbz/dw/fEXcFT4Wsh8mlrQ6+ObfrXUYWLQQt2y6xPiML1F6LLhJI+i++fbZzMzMTtEKczs+2voTabTiy5Jo4s01ozmQ0/Ym5IMiVtYeOc/gqMUeice5hpkDt3Qvnrf+mGPjm9Ew/5EuqJl1OZe1YIDLMnD5h5H7XtEkOo8ZfRNj";
	private String FORMAT="json";
	private String CHARSET="UTF-8";
	private String ALIPAY_PUBLIC_KEY="MIIBIjANBgkqhkiG9w0BAQEFAAOCAQ8AMIIBCgKCAQEAvLXzVXhwzjzG7phNmhb9citFLQFoXr2g6jEewtUHPPoLysqUFETix1P8j1fC7erH6ki3rT4bl0Zz1TjmN7rfFBmPJ+uyL9eyw6ijMV1Mm+GZpqdhz18IgD3apWEy/3SWdMGcdP+bIsSISItnn6a23hWcoQl3WIoSnTKRYxA8xOmqGXiftQadKD0OwNf/fV55P8MWM/snybRaiADBlczljoGtqRuJs+Dt604blkr0xPY/s8JXFnGK2Pt2cqlHVCPaAwzzqHMUk1I0GfY+nyMvm5wJHkDL4H2Qgjeb+QwemwQgd9Q8jQbHa5bVWXMgVrDWVAOxaTwqD7rReunOVaMLywIDAQAB";
	private String SIGN_TYPE="RSA2";
	
	public User selectTest(Integer id) {
		User user = userMapper.selectByPrimaryKey(id);
		return user;
	}
	
	public void createAlipay() {
		AlipayClient alipayClient = new DefaultAlipayClient(URL, APP_ID, APP_PRIVATE_KEY, FORMAT, CHARSET, ALIPAY_PUBLIC_KEY, SIGN_TYPE);
		AlipayEbppBillAddRequest alipayRequest = new AlipayEbppBillAddRequest();
		alipayRequest.setMerchantOrderNo("201507250000001"); //外部订单号，需要唯一
		alipayRequest.setOrderType("JF"); //缴费类型
		alipayRequest.setSubOrderType("GAS"); //缴费子类型
		alipayRequest.setChargeInst("WHSTRQ2150"); //机构代码
		alipayRequest.setBillKey("2015072001"); //户号
		alipayRequest.setOwnerName("张三"); //户主姓名
		alipayRequest.setPayAmount("91.00"); //账单金额
		alipayRequest.setServiceAmount("9.00"); //服务费
		alipayRequest.setBillDate("201607"); //账单日期
		alipayRequest.setMobile("139XXXXXXXX"); //手机号码
		alipayRequest.setBankBillNo("20160712"); //银行单号
		alipayRequest.setExtendField("{\"channel\":\"EXTERNAL_BANGDAO\"}"); //机构扩展属性
		AlipayEbppBillAddResponse response;
		try {
			response = alipayClient.execute(alipayRequest);
			if(response.isSuccess()){
				System.out.println("createAlipay调用成功:"+response);
				} else {
				System.out.println("createAlipay调用失败:"+response);
				}
		} catch (AlipayApiException e) {
			e.printStackTrace();
		}
		
	}
	
	public void selectAlipay(){
		AlipayClient alipayClient = new DefaultAlipayClient(URL, APP_ID, APP_PRIVATE_KEY, FORMAT, CHARSET, ALIPAY_PUBLIC_KEY, SIGN_TYPE);
		AlipayEbppBillGetRequest alipayRequest = new AlipayEbppBillGetRequest();
		alipayRequest.setOrderType("JF");
		alipayRequest.setMerchantOrderNo("201507250000001");
		try {
			AlipayEbppBillGetResponse alipayResponse = alipayClient.execute(alipayRequest);
			if(alipayResponse.isSuccess()){
				System.out.println("selectAlipay调用成功:"+alipayResponse);
				} else {
				System.out.println("selectAlipay调用失败:"+alipayResponse);
				}
		} catch (AlipayApiException e) {
			e.printStackTrace();
		}
	}
}
