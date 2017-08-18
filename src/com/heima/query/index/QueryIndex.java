package com.heima.query.index;

import org.apache.lucene.document.Document;
import org.apache.lucene.index.DirectoryReader;
import org.apache.lucene.queryparser.classic.ParseException;
import org.apache.lucene.queryparser.classic.QueryParser;
import org.apache.lucene.search.IndexSearcher;
import org.apache.lucene.search.Query;
import org.apache.lucene.search.ScoreDoc;
import org.apache.lucene.search.TopDocs;
import org.apache.lucene.store.FSDirectory;
import org.junit.Test;
import org.wltea.analyzer.lucene.IKAnalyzer;

import java.io.File;
import java.io.IOException;

/**
 * Created by xmy on 2017/8/18.
 */
public class QueryIndex {
    /**
     * 需求:通过索引查询到关键词信息
     */
    @Test
    public void queryIndex() throws IOException, ParseException {
        //1.获得索引库位置
        String path = "E:\\就业班视频\\加密视频及课件\\lucene_day01\\索引查看工具\\indexs";
        //2.读取索引库
        DirectoryReader reader = DirectoryReader.open(FSDirectory.open(new File(path)));
        //3.创建索引核心搜索对象
        IndexSearcher searcher = new IndexSearcher(reader);
        //4.需要查询的词语
        String qName = "测试";
        //5.创建查询解析器对象
        QueryParser queryParser = new QueryParser("title",new IKAnalyzer());
        //6.通过解析器获得查询对象
        Query parse = queryParser.parse(qName);
        //7.通过核心搜索对象获得前10条文档对象
        TopDocs docs = searcher.search(parse, 10);
        //8.获得总命中数
        int totalHits = docs.totalHits;
        System.out.println("总命中数:"+totalHits);
        //9.遍历所获得的文档对象
        ScoreDoc[] scoreDocs = docs.scoreDocs;
        for (ScoreDoc scoreDoc : scoreDocs) {
            int docId = scoreDoc.doc;
            System.out.println("文档id:"+docId);
            float score = scoreDoc.score;
            System.out.println("文档得分(通过算法得到):"+score);

            //10.使用文档对象
            Document document = searcher.doc(docId);
            //11.
            String id = document.get("id");
            System.out.println("文档域id:"+id);
            String title = document.get("title");
            System.out.println("文档标题:"+title);
            String desc = document.get("desc");
            System.out.println("文档描述:"+desc);
            String content = document.get("content");
            System.out.println("文档内容:"+content);
        }
    }
}
