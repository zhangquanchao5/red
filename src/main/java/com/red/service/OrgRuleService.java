package com.red.service;

import com.red.domain.OrgRule;

/**
 * Created by Star on 2015/12/24.
 */
public interface OrgRuleService {

    /**
     * Create org rule integer.
     *
     * @param orgRule the org rule
     * @return the integer
     */
    Integer createOrgRule(OrgRule orgRule) throws Exception;
}
