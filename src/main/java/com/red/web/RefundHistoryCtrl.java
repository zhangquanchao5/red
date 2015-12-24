package com.red.web;

import com.red.domain.RefundHistory;
import com.red.service.RefundHistoryService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;

/**
 * Created by Star on 2015/12/24.
 */
@Controller
public class RefundHistoryCtrl {

    @Autowired
    private RefundHistoryService refundHistoryService;

    @RequestMapping(value = "/api/refundHistory", method = RequestMethod.POST)
    public void create(RefundHistory refundHistory) {

    }

    @RequestMapping(value = "/api/refundHistory", method = RequestMethod.PUT)
    public void update(RefundHistory refundHistory) {

    }

    @RequestMapping(value = "/api/refundHistory/{id}", method = RequestMethod.DELETE)
    public void delete(@PathVariable Integer id) {

    }

    @RequestMapping(value = "/api/refundHistory/{id}", method = RequestMethod.GET)
    public void get(@PathVariable Integer id) {

    }

    @RequestMapping(value = "/api/refundHistorys", method = RequestMethod.GET)
    public void list() {

    }
}
