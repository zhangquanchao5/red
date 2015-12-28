package com.red.service;

import com.red.domain.OrgRule;

import java.util.List;

/**
 * Created by Star on 2015/12/24.
 */
public interface OrgRuleService {

    /**
     * Create org rule integer.
     *
     * @param orgRule the org rule
     * @return the integer
     * @throws Exception the exception
     */
    Integer createOrgRule(OrgRule orgRule) throws Exception;

    /**
     * Gets org rules.
     *
     * @param orgId the org id
     * @return the org rules
     * @throws Exception the exception
     */
    List<OrgRule> getOrgRules(Integer orgId) throws Exception;

    /**
     * Gets org rule.
     *
     * @param id the id
     * @return the org rule
     * @throws Exception the exception
     */
    OrgRule getOrgRule(Integer id) throws Exception;
}
