package com.crawler.gsxt.domain.json.zhejiang;

import java.util.List;

/**
 * Created by zmy on 16-6-3.
 */
public class PaginationBeen {

        int total;
        List<ExtInvest> dataList;
        int pages;
        int pageSize;
        int skipResult;
        int currentPage;
        int maxResult;

        public PaginationBeen() {
        }

        public List<ExtInvest> getDataList() {
            return dataList;
        }

        public void setDataList(List<ExtInvest> dataList) {
            this.dataList = dataList;
        }

        public int getTotal() {
            return total;
        }

        public void setTotal(int total) {
            this.total = total;
        }

        public int getPages() {
            return pages;
        }

        public void setPages(int pages) {
            this.pages = pages;
        }

        public int getPageSize() {
            return pageSize;
        }

        public void setPageSize(int pageSize) {
            this.pageSize = pageSize;
        }

        public int getSkipResult() {
            return skipResult;
        }

        public void setSkipResult(int skipResult) {
            this.skipResult = skipResult;
        }

        public int getCurrentPage() {
            return currentPage;
        }

        public void setCurrentPage(int currentPage) {
            this.currentPage = currentPage;
        }

        public int getMaxResult() {
            return maxResult;
        }

        public void setMaxResult(int maxResult) {
            this.maxResult = maxResult;
        }

        @Override
        public String toString() {
            return "PaginationBeen{" +
                    "total=" + total +
                    ", dataList=" + dataList +
                    ", pages=" + pages +
                    ", pageSize=" + pageSize +
                    ", skipResult=" + skipResult +
                    ", currentPage=" + currentPage +
                    ", maxResult=" + maxResult +
                    '}';
        }
}
