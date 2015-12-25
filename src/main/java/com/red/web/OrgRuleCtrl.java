package com.red.web;

import com.red.common.bean.ResponseMessage;
import com.red.common.code.ErrorCode;
import com.red.common.exception.CustomException;
import com.red.domain.OrgRule;
import com.red.service.OrgRuleService;
import com.red.service.RedDetailService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.ResponseBody;

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
    public @ResponseBody ResponseMessage create(OrgRule orgRule) {
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
    public void get(@PathVariable Integer id) {

    }

    @RequestMapping(value = "/api/reds", method = RequestMethod.GET)
    public void list() {

    }

    @RequestMapping(value = "/api/reds/{id}/user/{phone}", method = RequestMethod.GET)
    public void getRedMoney(@PathVariable("id") Integer id, @PathVariable("phone") String phone) {
        redDetailService.
    }
}
