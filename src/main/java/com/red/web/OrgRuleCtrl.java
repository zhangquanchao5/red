package com.red.web;

import com.red.domain.OrgRule;
import com.red.service.OrgRuleService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;

/**
 * Created by Star on 2015/12/24.
 */
@Controller
public class OrgRuleCtrl {

    @Autowired
    private OrgRuleService orgRuleService;

    @RequestMapping(value = "/api/red", method = RequestMethod.POST)
    public void create(OrgRule orgRule) {

    }

    @RequestMapping(value = "/api/red", method = RequestMethod.PUT)
    public void update(OrgRule orgRule) {

    }

    @RequestMapping(value = "/api/red/{id}", method = RequestMethod.DELETE)
    public void delete(@PathVariable Integer id) {

    }

    @RequestMapping(value = "/api/red/{id}", method = RequestMethod.GET)
    public void get(@PathVariable Integer id) {

    }

    @RequestMapping(value = "/api/reds", method = RequestMethod.GET)
    public void list() {

    }
}
