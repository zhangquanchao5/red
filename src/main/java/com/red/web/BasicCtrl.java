package com.red.web;

import com.red.common.util.MessageUtil;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.propertyeditors.CustomDateEditor;
import org.springframework.web.bind.WebDataBinder;
import org.springframework.web.bind.annotation.InitBinder;

import java.text.SimpleDateFormat;
import java.util.Date;

/**
 * Created by Star on 2015/12/25.
 */
public class BasicCtrl {
    @Autowired
    public MessageUtil messageUtil;

    private static final String FORMAT = "yyyy-MM-dd HH:mm:ss";
    public Logger logger = LogManager.getLogger(this.getClass());

    /**
     * Init binder.
     *
     * @param binder the binder
     */
    @InitBinder
    public void initBinder(WebDataBinder binder) {
        final SimpleDateFormat dateFormat = new SimpleDateFormat(FORMAT);
        dateFormat.setLenient(false);
        binder.registerCustomEditor(Date.class, new CustomDateEditor(dateFormat, false));
    }
}
