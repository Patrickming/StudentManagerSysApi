package com.pdm.sms.utils.page;

import com.fasterxml.jackson.annotation.JsonInclude;

import java.util.Collections;
import java.util.Iterator;
import java.util.List;

/**
 * @author xrm
 * @date 2024/1/19 10:53
 * @description 分页工具类
 **/
//在序列化为JSON时，如果字段的值为null，那么这个字段就不会被序列化到JSON中。
//有助于减少JSON的大小，提高效率
@JsonInclude(JsonInclude.Include.NON_NULL)
public class PagingResult<T> implements Iterable<T> {
    private static final long serialVersionUID = 1L;

    private final long totalCount;
    private final List<T> items;

    public PagingResult(List<T> items,long totalCount){
        this.totalCount = totalCount;
        this.items = items;
    }

    public long getTotalCount(){
        return totalCount;
    }

    public List<T> getItems(){
        return items;
    }

    @Override
    public Iterator<T> iterator() {
        return this.items.iterator();
    }
}

