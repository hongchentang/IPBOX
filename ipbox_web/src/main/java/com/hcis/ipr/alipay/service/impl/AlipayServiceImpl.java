package com.hcis.ipr.alipay.service.impl;

import com.alipay.api.AlipayApiException;
import com.alipay.api.internal.util.AlipaySignature;
import com.alipay.api.response.AlipayTradeQueryResponse;
import com.hcis.ipanther.common.login.vo.LoginUser;
import com.hcis.ipanther.core.persistence.dao.MyBatisDao;
import com.hcis.ipanther.core.service.impl.mybatis.BaseServiceImpl;
import com.hcis.ipr.alipay.config.AlipayConfig;
import com.hcis.ipr.alipay.utils.AlipayUtils;
import com.hcis.ipr.intellectual.cost.dao.PatentCostDao;
import com.hcis.ipr.intellectual.cost.entity.PatentCost;
import com.hcis.ipr.intellectual.cost.entity.PatentCostPayment;
import com.hcis.ipr.intellectual.cost.service.PatentCostService;
import org.apache.shiro.SecurityUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import javax.annotation.Resource;
import javax.servlet.http.HttpServletRequest;
import java.util.Date;
import java.util.HashMap;
import java.util.Map;
import java.util.Set;

/**
 * @author zhw
 * @date 2019/10/21
 **/

@Service
public class AlipayServiceImpl extends BaseServiceImpl implements AlipayService {

    @Resource
    private PatentCostService patentCostService;

    @Autowired
    private PatentCostDao patentCostDao;

    @Override
    public void payCallback(HttpServletRequest request) throws AlipayApiException {

        Map<String, String> params = convertRequestParamsToMap(request);

        //调用SDK验证签名
        boolean signVerified = AlipaySignature.rsaCheckV1(params, AlipayConfig.alipay_public_key, AlipayConfig.charset,AlipayConfig.sign_type);
        if(signVerified){

            String costId = params.get("out_trade_no");
            PatentCost cost = patentCostService.read(costId);
            if(cost != null){
                AlipayUtils utils = new AlipayUtils();
                AlipayTradeQueryResponse response =  utils.query(cost);
                if(response.getTradeStatus().equals("TRADE_SUCCESS") || response.getTradeStatus().equals("TRADE_FINISHED")){
                    LoginUser loginUser = (LoginUser) SecurityUtils.getSubject().getPrincipal();
                    PatentCostPayment payment = new PatentCostPayment();
                    payment.setPatentCostId(cost.getId());
                    payment.setPaymentAmount(cost.getFeePayable());
                    payment.setCreator(loginUser.getId());
                    payment.setPaymenter(loginUser.getId());
                    payment.setPaymentDate(new Date());

                    patentCostService.payable(payment);

                    System.out.println("调用回调, 交易成功!");
                }else {
                    System.out.println("调用回调, 交易未成功!");
                }
            }else {
                failed();
            }
        }else {
            System.out.println("调用回调, 验证失败!");
        }

    }

    /**
     * 将request中的参数转换成Map
     * @param request
     * @return
     */
    private static Map<String, String> convertRequestParamsToMap(HttpServletRequest request) {
        Map<String, String> retMap = new HashMap<String, String>();
        Map map = request.getParameterMap();
        Set<Map.Entry<String, String[]>> entrySet = map.entrySet();

        for (Map.Entry<String, String[]> entry : entrySet) {
            String name = entry.getKey();
            String[] values = entry.getValue();
            int valLen = values.length;

            if (valLen == 1) {
                retMap.put(name, values[0]);
            } else if (valLen > 1) {
                StringBuilder sb = new StringBuilder();
                for (String val : values) {
                    sb.append(",").append(val);
                }
                retMap.put(name, sb.toString().substring(1));
            } else {
                retMap.put(name, "");
            }
        }

        return retMap;
    }

    public void failed(){
        System.out.println("调用回调, 交易失败!");
    }

    @Override
    public MyBatisDao getBaseDao() {
        return patentCostDao;
    }
}
