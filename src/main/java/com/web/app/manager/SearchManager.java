package com.web.app.manager;

import com.web.app.Search.LuceneConstants;
import com.web.app.Search.LuceneIndexWriter;
import com.web.app.dto.UserInfoDTO;
import org.apache.lucene.analysis.standard.StandardAnalyzer;
import org.apache.lucene.document.Document;
import org.apache.lucene.index.DirectoryReader;
import org.apache.lucene.index.IndexReader;
import org.apache.lucene.queryparser.classic.ParseException;
import org.apache.lucene.queryparser.classic.QueryParser;
import org.apache.lucene.search.IndexSearcher;
import org.apache.lucene.search.Query;
import org.apache.lucene.search.ScoreDoc;
import org.apache.lucene.search.TopDocs;
import org.apache.lucene.store.Directory;
import org.apache.lucene.store.FSDirectory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import java.io.IOException;
import java.nio.file.Paths;
import java.util.ArrayList;
import java.util.List;

@Component
public class SearchManager {

    @Autowired
    public DataManager dataManager;

    private final String INDEX_PATH = "indexDir";
    private final String JSON_FILE_PATH = "/users.json";
    private final int QUERY_SIZE = 5;

    public SearchManager() {
        createIndex();
    }

    private void createIndex() {
        LuceneIndexWriter lw = new LuceneIndexWriter(INDEX_PATH, JSON_FILE_PATH);
        lw.createIndex();

    }

    public List<UserInfoDTO> search(String query) throws IOException, ParseException {
        List<UserInfoDTO> userList = new ArrayList<>(QUERY_SIZE);
        Directory indexDirectory = FSDirectory.open(Paths.get(INDEX_PATH));
        IndexReader indexReader = DirectoryReader.open(indexDirectory);
        final IndexSearcher indexSearcher = new IndexSearcher(indexReader);
        QueryParser qp = new QueryParser(LuceneConstants.CONTENTS, new StandardAnalyzer());
        Query q = qp.parse("firstName:" + query + "*" + " OR lastName:" + query + "*" + " OR position" + query + "*" + " OR department" + query + "*" + " OR location" + query + "*");
        TopDocs topDocs = indexSearcher.search(q, QUERY_SIZE);

        final ScoreDoc[] scoreDocs = topDocs.scoreDocs;
        final int numDocs = Math.min(scoreDocs.length, QUERY_SIZE);

        for (int i = numDocs - 1; i >= 0; i--) {
            final int docId = scoreDocs[i].doc;
            final Document d = indexReader.document(docId);
            userList.add(dataManager.getUserInfo(d.getField("username").stringValue()));
        }

        return userList;
    }
}
