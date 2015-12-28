package com.red.service;

import com.red.domain.RedDetail;

/**
 * The interface Red detail service.
 */
public interface RedDetailService {
    /**
     * Gets red money.
     *
     * @param redId the red id
     * @return the red money
     * @throws Exception the exception
     */
    RedDetail getRedMoney(Integer redId) throws Exception;

    /**
     * Save history.
     *
     * @param redDetail the red detail
     * @param phone     the phone
     * @param type      the type
     * @throws Exception the exception
     */
    void saveHistory(RedDetail redDetail, String phone, Integer type) throws Exception;
}
