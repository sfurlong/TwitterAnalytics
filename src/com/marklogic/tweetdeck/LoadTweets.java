package com.marklogic.tweetdeck;


import java.io.FileInputStream;
import java.io.InputStream;

import com.marklogic.client.DatabaseClient;
import com.marklogic.client.DatabaseClientFactory;
import com.marklogic.client.DatabaseClientFactory.Authentication;
import com.marklogic.client.io.InputStreamHandle;
import com.marklogic.client.io.SearchHandle;
import com.marklogic.client.query.MatchDocumentSummary;
import com.marklogic.client.query.MatchLocation;
import com.marklogic.client.query.MatchSnippet;
import com.marklogic.client.query.QueryManager;
import com.marklogic.client.query.StringQueryDefinition;
import com.marklogic.client.document.*;

public class LoadTweets {
  public LoadTweets() {
    super();
  }

  public void queryMarkLogic() {
	  
	try {
    DatabaseClient client =
      DatabaseClientFactory.newClient("localhost", 8003, "admin", "marklogic1",
                                      Authentication.DIGEST);

    JSONDocumentManager jsonDocMgr = client.newJSONDocumentManager();
    
    InputStream docStream = new FileInputStream("/Users/sfurlong/ML-Dev/twitter4j/572773090364674048.json");
	InputStreamHandle handle = new InputStreamHandle(docStream);

    jsonDocMgr.write("/example/572773090364674048.json", handle);
    
	} catch (Exception e) {
		e.printStackTrace();
	}
    
  }

  public static void main(String[] args) {
    LoadTweets markLogicClient = new LoadTweets();
    System.out.println("Query Results:");
    markLogicClient.queryMarkLogic();
  }
}
