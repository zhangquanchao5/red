package com.red.web;

import com.red.domain.RedDetail;
import com.red.service.RedDetailService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;

/**
 * Created by Star on 2015/12/24.
 */
@Controller
public class RedDetailCtrl {

    @Autowired
    private RedDetailService redDetailService;

    @RequestMapping(value = "/api/detail", method = RequestMethod.POST)
    public void create(RedDetail redDetail) {

    }

    @RequestMapping(value = "/api/detail", method = RequestMethod.PUT)
    public void update(RedDetail redDetail) {

    }

    @RequestMapping(value = "/api/detail/{id}", method = RequestMethod.DELETE)
    public void delete(@PathVariable Integer id) {

    }

    @RequestMapping(value = "/api/detail/{id}", method = RequestMethod.GET)
    public void get(@PathVariable Integer id) {

    }

    @RequestMapping(value = "/api/details", method = RequestMethod.GET)
    public void list() {

    }
}
