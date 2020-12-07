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
import org.springframework.stereotype.Service;

import java.io.IOException;
import java.nio.file.Paths;
import java.util.ArrayList;
import java.util.List;

@Service
public class SearchManager {

    public final DataManager dataManager;

    private final String INDEX_PATH = "indexDir";
    private final String JSON_FILE_PATH = "data/users.json";
    private final int QUERY_SIZE = 5;
    private IndexSearcher indexSearcher;
    private IndexReader indexReader;

    public SearchManager(DataManager dataManager) throws IOException {
        createAndOpenIndex();
        this.dataManager = dataManager;
    }

    private void createAndOpenIndex() throws IOException {
        LuceneIndexWriter lw = new LuceneIndexWriter(INDEX_PATH, JSON_FILE_PATH);
        lw.createIndex();
        Directory indexDirectory = FSDirectory.open(Paths.get(INDEX_PATH));
        indexReader = DirectoryReader.open(indexDirectory);
        indexSearcher = new IndexSearcher(indexReader);
    }

    public List<UserInfoDTO> search(String query) throws ParseException, IOException {
        List<UserInfoDTO> userList = new ArrayList<>(QUERY_SIZE);

        QueryParser qp = new QueryParser(LuceneConstants.CONTENTS, new StandardAnalyzer());
        Query q = qp.parse("firstName:" + query + "*" + " OR lastName:" + query + "*" + " OR position:" + query + "*" + " OR department:" + query + "*" + " OR location:" + query + "*");
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
