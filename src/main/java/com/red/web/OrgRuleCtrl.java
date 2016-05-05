package com.red.web;

import com.alibaba.fastjson.JSON;
import com.red.common.apibean.OrgRuleReq;
import com.red.common.apibean.RedAddReq;
import com.red.common.apibean.UserHistoryPageReq;
import com.red.common.apibean.UserHistoryReq;
import com.red.common.apibean.response.OrgRuleResponse;
import com.red.common.apibean.response.RedDetailResponse;
import com.red.common.bean.ResponseMessage;
import com.red.common.code.EntityCode;
import com.red.common.code.ErrorCode;
import com.red.common.exception.CustomException;
import com.red.common.page.PageResponse;
import com.red.domain.OrgRule;
import com.red.domain.RedDetail;
import com.red.service.OrgRuleService;
import com.red.service.RedDetailService;
import com.red.service.UserHistoryService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.*;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

/**
 * Created by Star on 2015/12/24.
 */
@Controller
public class OrgRuleCtrl extends BasicCtrl {

    @Autowired
    private OrgRuleService orgRuleService;

    @Autowired
    private RedDetailService redDetailService;

    @Autowired
    private UserHistoryService userHistoryService;

    @RequestMapping(value = "/api/reds", method = RequestMethod.POST)
    public @ResponseBody ResponseMessage create(@RequestBody OrgRule orgRule) {
        logger.info("request body ---> " + JSON.toJSONString(orgRule));
        ResponseMessage message = new ResponseMessage();
        try {
            Integer redId = orgRuleService.createOrgRule(orgRule);
            message.setData(redId);
            message.setCode(ErrorCode.SUCCESS);
            message.setMsg(messageUtil.getMessage("msg.process.succ"));
        } catch (CustomException e) {
            message.setCode(e.getErrorCode());
            message.setMsg(e.getMessage());
            logger.error(e.getMessage(), e);
        } catch (Exception e) {
            message.setCode(ErrorCode.ERROR);
            message.setMsg(messageUtil.getMessage("msg.process.fail"));
            logger.error(e.getMessage(), e);
        }
        return message;
    }

    @RequestMapping(value = "/api/reds/{redId}", method = RequestMethod.POST)
    public @ResponseBody ResponseMessage redGo(@PathVariable("redId") Integer redId,@RequestBody  RedAddReq redAddReq) {
        logger.info("/api/reds/{redId} request body ---> " + JSON.toJSONString(redAddReq));
        ResponseMessage message = new ResponseMessage();
        try {
            message = orgRuleService.updateOrgRule(redAddReq, message);
//            message.setMsg(messageUtil.getMessage("msg.process.succ"));
        } catch (CustomException e) {
            message.setCode(e.getErrorCode());
            message.setMsg(e.getMessage());
            logger.error(e.getMessage(), e);
        } catch (Exception e) {
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
            message.setCode(ErrorCode.SUCCESS);
            message.setMsg(messageUtil.getMessage("msg.process.succ"));
        } catch (CustomException e) {
            message.setCode(e.getErrorCode());
            message.setMsg(e.getMessage());
            logger.error(e.getMessage(), e);
        } catch (Exception e) {
            message.setCode(ErrorCode.ERROR);
            message.setMsg(messageUtil.getMessage("msg.process.fail"));
            logger.error(e.getMessage(), e);
        }
        return message;
    }


    @RequestMapping(value = "/api/reds/batch", method = RequestMethod.POST)
    public @ResponseBody ResponseMessage batch(@RequestBody OrgRuleReq orgRuleReq) {
        ResponseMessage message = new ResponseMessage();
        try {
            List<OrgRuleResponse> orgRuleList=orgRuleService.findByQuery(orgRuleReq);
            Map<String,OrgRuleResponse> orgRuleMap=new HashMap<String,OrgRuleResponse>();
            if(orgRuleList!=null&&orgRuleList.size()>0){
                for(OrgRuleResponse orgRule:orgRuleList){
                    orgRuleMap.put(orgRule.getId().toString(),orgRule);
                }
            }
            message.setData(orgRuleMap);
            message.setCode(ErrorCode.SUCCESS);
            message.setMsg(messageUtil.getMessage("msg.process.succ"));
        } catch (CustomException e) {
            message.setCode(e.getErrorCode());
            message.setMsg(e.getMessage());
            logger.error(e.getMessage(), e);
        } catch (Exception e) {
            message.setCode(ErrorCode.ERROR);
            message.setMsg(messageUtil.getMessage("msg.process.fail"));
            logger.error(e.getMessage(), e);
        }
        return message;
    }

    @RequestMapping(value = "/api/reds", method = RequestMethod.GET)
    public @ResponseBody ResponseMessage list(Integer orgId) {
        logger.info("request body ---> " + orgId);
        ResponseMessage message = new ResponseMessage();
        try {
            message.setDatas(orgRuleService.getOrgRules(orgId));
            message.setCode(ErrorCode.SUCCESS);
            message.setMsg(messageUtil.getMessage("msg.process.succ"));
        } catch (CustomException e) {
            message.setCode(e.getErrorCode());
            message.setMsg(e.getMessage());
            logger.error(e.getMessage(), e);
        } catch (Exception e) {
            message.setCode(ErrorCode.ERROR);
            message.setMsg(messageUtil.getMessage("msg.process.fail"));
            logger.error(e.getMessage(), e);
        }
        return message;
    }

    @RequestMapping(value = "/api/reds/{id}/user/{phone}", method = RequestMethod.GET)
    public @ResponseBody ResponseMessage getRedMoney(@PathVariable("id") Integer id, @PathVariable("phone") String phone,UserHistoryReq userHistoryReq) {
        logger.info("request body ---> " + JSON.toJSONString(userHistoryReq));
        ResponseMessage message = new ResponseMessage();
        try {
            //RedDetail redDetail = redDetailService.getRedMoney(id);
            RedDetailResponse redDetailResponse=new RedDetailResponse();
            redDetailResponse=redDetailService.saveHistory(id, phone, userHistoryReq,redDetailResponse);

            message.setData(redDetailResponse);
            message.setCode(ErrorCode.SUCCESS);
            message.setMsg(messageUtil.getMessage("msg.process.succ"));
        } catch (CustomException e) {
            message.setCode(e.getErrorCode());
            message.setMsg(e.getMessage());
            logger.error(e.getMessage(), e);
        } catch (Exception e) {
            message.setCode(ErrorCode.ERROR);
            message.setMsg(messageUtil.getMessage("msg.process.fail"));
            logger.error(e.getMessage(), e);
        }
        return message;
    }

    @RequestMapping(value = "/api/reds/{id}/history", method = RequestMethod.GET)
    public @ResponseBody ResponseMessage getRedHistory(@PathVariable Integer id, Integer currentPage, Integer currentSize) {
        ResponseMessage message = new ResponseMessage();
        try {
            UserHistoryPageReq req = new UserHistoryPageReq(id);
            if (null != currentPage && currentPage > 0) {
                req.setCurrentPage(currentPage);
            }

            if (null != currentSize && currentSize > 0) {
                req.setCurrentSize(currentSize);
            }

            PageResponse pageResponse = userHistoryService.findUserHistorys(req);
            message.setData(pageResponse);
            message.setCode(ErrorCode.SUCCESS);
            message.setMsg(messageUtil.getMessage("msg.process.succ"));
        } catch (Exception e) {
            message.setCode(ErrorCode.ERROR);
            message.setMsg(messageUtil.getMessage("msg.process.fail"));
            logger.error(e.getMessage(), e);
        }
        return message;
    }
}
