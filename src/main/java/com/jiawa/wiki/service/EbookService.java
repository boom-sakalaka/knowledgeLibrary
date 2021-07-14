package com.jiawa.wiki.service;

import com.github.pagehelper.PageHelper;
import com.github.pagehelper.PageInfo;
import com.jiawa.wiki.domain.Ebook;
import com.jiawa.wiki.domain.EbookExample;
import com.jiawa.wiki.mapper.EbookMapper;
import com.jiawa.wiki.req.EbookReq;
import com.jiawa.wiki.resp.EbookResp;
import com.jiawa.wiki.util.CopyUtil;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Service;
import org.springframework.util.ObjectUtils;

import javax.annotation.Resource;
import java.util.List;

@Service
public class EbookService {
    private static final Logger LOG = LoggerFactory.getLogger(EbookService.class);
    @Resource
    private EbookMapper ebookMapper;

    public List<EbookResp> list(EbookReq req) {

        EbookExample ebookExample = new EbookExample();
        EbookExample.Criteria criteria = ebookExample.createCriteria();
        if(!ObjectUtils.isEmpty(req.getName())){
            criteria.andNameLike("%" + req.getName() + "%");
        }
        PageHelper.startPage(1, 3);
        List<Ebook> ebooks = ebookMapper.selectByExample(ebookExample);

        PageInfo<Ebook> pageInfo = new PageInfo<>(ebooks);
        LOG.info("总行数：{}", pageInfo.getTotal());
        LOG.info("总页数：{}", pageInfo.getPages());

/*
         List<EbookResp> respList = new ArrayList<>();
        for (Ebook ebook : ebooks){
//            EbookResp ebookResp = new EbookResp();
//            BeanUtils.copyProperties(ebook,ebookResp);

            EbookResp ebookResp = CopyUtil.copy(ebook, EbookResp.class);
            respList.add(ebookResp);
        }
*/

        return CopyUtil.copyList(ebooks, EbookResp.class);
    }
}
