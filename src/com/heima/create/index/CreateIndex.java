package com.heima.create.index;

import org.apache.lucene.analysis.Analyzer;
import org.apache.lucene.document.Document;
import org.apache.lucene.document.Field;
import org.apache.lucene.document.StringField;
import org.apache.lucene.document.TextField;
import org.apache.lucene.index.IndexWriter;
import org.apache.lucene.index.IndexWriterConfig;
import org.apache.lucene.store.FSDirectory;
import org.apache.lucene.util.Version;
import org.junit.Test;
import org.wltea.analyzer.lucene.IKAnalyzer;

import java.io.File;
import java.io.IOException;
import java.lang.annotation.Documented;

/**
 * Created by xmy on 2017/8/17.
 */
public class CreateIndex {

    @Test
    public void createIndex() throws IOException {
        /**
         * 需求:创建新的索引路径
         */
        //1.创建路径
        String path = "E:\\就业班视频\\加密视频及课件\\lucene_day01\\索引查看工具\\indexs";
        //2.设置索引路径
        FSDirectory fsDirectory = FSDirectory.open(new File(path));
        //3.创建中文分词器
        Analyzer analyzer = new IKAnalyzer();
        //4.创建索引配置文件
        IndexWriterConfig config = new IndexWriterConfig(Version.LUCENE_4_10_3,analyzer);
        //5.创建索引
        IndexWriter indexWriter = new IndexWriter(fsDirectory,config);
        //6.创建文档
        Document document = new Document();
        document.add(new StringField("id","110", Field.Store.NO));
        document.add(new TextField("title","测试的", Field.Store.YES));
        document.add(new TextField("desc","就是依旧合适的交流方式看得见", Field.Store.YES));
        document.add(new TextField("content","这就是一边文字", Field.Store.NO));
        //7.
        indexWriter.addDocument(document);
        //8.
        indexWriter.commit();
    }
}
