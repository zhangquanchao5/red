package com.red.web;

import com.alibaba.fastjson.JSON;
import com.red.common.bean.ResponseMessage;
import com.red.common.code.EntityCode;
import com.red.common.code.ErrorCode;
import com.red.common.exception.CustomException;
import com.red.domain.OrgRule;
import com.red.domain.RedDetail;
import com.red.service.OrgRuleService;
import com.red.service.RedDetailService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.*;

/**
 * Created by Star on 2015/12/24.
 */
@Controller
public class OrgRuleCtrl extends BasicCtrl {

    @Autowired
    private OrgRuleService orgRuleService;

    @Autowired
    private RedDetailService redDetailService;

    @RequestMapping(value = "/api/reds", method = RequestMethod.POST)
    public @ResponseBody ResponseMessage create(@RequestBody OrgRule orgRule) {
        logger.info("request body ---> " + JSON.toJSONString(orgRule));
        ResponseMessage message = new ResponseMessage();
        try {
            Integer redId = orgRuleService.createOrgRule(orgRule);
            message.setData(redId);
            message.setSuccess(true);
            message.setCode(ErrorCode.SUCCESS);
            message.setMsg(messageUtil.getMessage("msg.process.succ"));
        } catch (CustomException e) {
            message.setSuccess(false);
            message.setCode(e.getErrorCode());
            message.setMsg(e.getMessage());
            logger.error(e.getMessage(), e);
        } catch (Exception e) {
            message.setSuccess(false);
            message.setCode(ErrorCode.ERROR);
            message.setMsg(messageUtil.getMessage("msg.process.fail"));
            logger.error(e.getMessage(), e);
        }
        return message;
    }

    @RequestMapping(value = "/api/reds", method = RequestMethod.PUT)
    public void update(OrgRule orgRule) {

    }

    @RequestMapping(value = "/api/reds/{id}", method = RequestMethod.DELETE)
    public void delete(@PathVariable Integer id) {

    }

    @RequestMapping(value = "/api/reds/{id}", method = RequestMethod.GET)
    public @ResponseBody ResponseMessage get(@PathVariable Integer id) {
        ResponseMessage message = new ResponseMessage();
        try {
            message.setData(orgRuleService.getOrgRule(id));
            message.setSuccess(true);
            message.setCode(ErrorCode.SUCCESS);
            message.setMsg(messageUtil.getMessage("msg.process.succ"));
        } catch (CustomException e) {
            message.setSuccess(false);
            message.setCode(e.getErrorCode());
            message.setMsg(e.getMessage());
            logger.error(e.getMessage(), e);
        } catch (Exception e) {
            message.setSuccess(false);
            message.setCode(ErrorCode.ERROR);
            message.setMsg(messageUtil.getMessage("msg.process.fail"));
            logger.error(e.getMessage(), e);
        }
        return message;
    }

    @RequestMapping(value = "/api/reds", method = RequestMethod.GET)
    public @ResponseBody ResponseMessage list(@RequestBody Integer orgId) {
        logger.info("request body ---> " + orgId);
        ResponseMessage message = new ResponseMessage();
        try {
            message.setDatas(orgRuleService.getOrgRules(orgId));
            message.setSuccess(true);
            message.setCode(ErrorCode.SUCCESS);
            message.setMsg(messageUtil.getMessage("msg.process.succ"));
        } catch (CustomException e) {
            message.setSuccess(false);
            message.setCode(e.getErrorCode());
            message.setMsg(e.getMessage());
            logger.error(e.getMessage(), e);
        } catch (Exception e) {
            message.setSuccess(false);
            message.setCode(ErrorCode.ERROR);
            message.setMsg(messageUtil.getMessage("msg.process.fail"));
            logger.error(e.getMessage(), e);
        }
        return message;
    }

    @RequestMapping(value = "/api/reds/{id}/user/{phone}", method = RequestMethod.GET)
    public @ResponseBody ResponseMessage getRedMoney(@PathVariable("id") Integer id, @PathVariable("phone") String phone, @RequestBody Integer type) {
        logger.info("request body ---> " + type);
        ResponseMessage message = new ResponseMessage();
        try {
            RedDetail redDetail = redDetailService.getRedMoney(id);
            redDetailService.saveHistory(redDetail, phone, type);
            //判断是否第一次获取这个红包规则的分享者，是的话发送短信
            if(type.intValue()== EntityCode.USER_HISTORY_FAIR.intValue()){
                redDetailService.sendSms(redDetail,phone);
            }
            message.setData(redDetail.getMoney());
            message.setSuccess(true);
            message.setCode(ErrorCode.SUCCESS);
            message.setMsg(messageUtil.getMessage("msg.process.succ"));
        } catch (CustomException e) {
            message.setSuccess(false);
            message.setCode(e.getErrorCode());
            message.setMsg(e.getMessage());
            logger.error(e.getMessage(), e);
        } catch (Exception e) {
            message.setSuccess(false);
            message.setCode(ErrorCode.ERROR);
            message.setMsg(messageUtil.getMessage("msg.process.fail"));
            logger.error(e.getMessage(), e);
        }
        return message;
    }
}
