package site.achun.gallery.app.utils;

import com.baomidou.mybatisplus.core.metadata.IPage;
import com.baomidou.mybatisplus.extension.plugins.pagination.Page;
import site.achun.support.api.request.ReqPage;
import site.achun.support.api.response.RspPage;

import java.util.List;
import java.util.function.Function;
import java.util.stream.Collectors;

/**
 * Description
 *
 * @Author: Heiffeng
 * @Date: 2022/1/19 11:23
 */
public class PageUtil {

    public static Page<?> parse(ReqPage reqPage){
        return Page.of(reqPage.getPage(),reqPage.getSize());
    }

    public static <T> RspPage<T> parse(IPage<T> pageResult, ReqPage reqPage){
        RspPage rspPage = reqPage.createPageRsp();
        rspPage.setRows(pageResult.getRecords());
        rspPage.setTotal(pageResult.getTotal());
        return rspPage;
    }

    public static <F,T> RspPage<T> parse(IPage<F> pageResult, Function<F,T> convert, ReqPage reqPage){
        RspPage<T> rspPage = reqPage.createPageRsp();
        rspPage.setRows(pageResult.getRecords().stream()
                .map(convert::apply)
                .collect(Collectors.toList())
        );
        rspPage.setTotal(pageResult.getTotal());
        return rspPage;
    }

    public static <F,T> RspPage<T> batchParse(IPage<F> pageResult, ReqPage reqPage, Function<List<F>,List<T>> batchConvert){
        RspPage<T> rspPage = reqPage.createPageRsp();
        rspPage.setTotal(pageResult.getTotal());
        rspPage.setRows(batchConvert.apply(pageResult.getRecords()));
        return rspPage;
    }
}
