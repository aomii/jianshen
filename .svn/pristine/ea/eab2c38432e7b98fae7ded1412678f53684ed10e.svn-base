package com.rabbit.fitness.coach.coachService.serviceImpl;

import com.alipay.api.AlipayApiException;
import com.rabbit.fitness.alipay.Alipay;
import com.rabbit.fitness.alipay.AlipayBean;
import com.rabbit.fitness.coach.coachService.PayService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class PayServiceImpl implements PayService {

    @Autowired
    private Alipay alipay;

    @Override
    public String aliPay(AlipayBean alipayBean) throws AlipayApiException {
        return alipay.pay(alipayBean);
    }
}
