package com.red.web;

import com.red.domain.UserHistory;
import com.red.service.UserHistoryService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;

/**
 * Created by Star on 2015/12/24.
 */
@Controller
public class UserHistoryCtrl {

    @Autowired
    private UserHistoryService userHistoryService;

    @RequestMapping(value = "/api/userHistory", method = RequestMethod.POST)
    public void create(UserHistory userHistory) {

    }

    @RequestMapping(value = "/api/userHistory", method = RequestMethod.PUT)
    public void update(UserHistory userHistory) {

    }

    @RequestMapping(value = "/api/userHistory/{id}", method = RequestMethod.DELETE)
    public void delete(@PathVariable Integer id) {

    }

    @RequestMapping(value = "/api/userHistory/{id}", method = RequestMethod.GET)
    public void get(@PathVariable Integer id) {

    }

    @RequestMapping(value = "/api/userHistorys", method = RequestMethod.GET)
    public void list() {

    }
}
