package com.red.common.page;

/**
 * Created by huichao on 2015/10/23.
 */
public class PageRequest {

    /**
     * The Current page.
     */
    public Integer currentPage;
    /**
     * The Current size.
     */
    public Integer currentSize;

    /**
     * The Page start.
     */
    public Integer pageStart;

    /**
     * Gets current page.
     *
     * @return the current page
     */
    public Integer getCurrentPage() {
        return currentPage==null?1:currentPage;
    }

    /**
     * Sets current page.
     *
     * @param currentPage the current page
     */
    public void setCurrentPage(Integer currentPage) {
        this.currentPage = currentPage;
    }

    /**
     * Gets current size.
     *
     * @return the current size
     */
    public Integer getCurrentSize() {
        return currentSize==null?10:currentSize;
    }

    /**
     * Sets current size.
     *
     * @param currentSize the current size
     */
    public void setCurrentSize(Integer currentSize) {
        this.currentSize = currentSize;
    }

    /**
     * Gets page start.
     *
     * @return the page start
     */
    public Integer getPageStart() {
        return pageStart;
    }

    /**
     * Sets page start.
     *
     * @param pageStart the page start
     */
    public void setPageStart(Integer pageStart) {
        this.pageStart = pageStart;
    }


}
