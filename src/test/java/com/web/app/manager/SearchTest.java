package com.web.app.manager;
import com.web.app.Search.LuceneIndexWriter;
import com.web.app.dto.UserInfoDTO;
import lombok.Synchronized;
import org.apache.lucene.queryparser.classic.ParseException;
import org.json.simple.JSONArray;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;
import org.mockito.InjectMocks;
import org.mockito.Spy;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;

import java.io.IOException;
import java.util.List;

@SpringBootTest
public class SearchTest {
    @Spy
    @InjectMocks
    private SearchManager searchManager;

    @Spy
    @InjectMocks
    private LuceneIndexWriter writer = new LuceneIndexWriter("/indexDir","/users.json");

    @Test
    public void searchTest() throws IOException, ParseException {
        searchManager.dataManager = new DataManager();
        List<UserInfoDTO> users = searchManager.search("FName");
        Assertions.assertEquals(5, users.size());
    }

    @Test
    public void indexTest()
    {
        Assertions.assertTrue(writer.openIndex());
    }

    @Test
    public void jsonParser()
    {
        JSONArray array = writer.parseJSONFile();
        Assertions.assertEquals(20, array.size());
    }
}
