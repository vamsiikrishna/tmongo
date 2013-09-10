package com.nighthawkapps.tmongo;

import com.mongodb.DB;
import com.mongodb.DBCollection;
import com.mongodb.DBObject;
import com.mongodb.MongoClient;
import com.mongodb.util.JSON;
import twitter4j.*;
import twitter4j.conf.ConfigurationBuilder;
import twitter4j.json.DataObjectFactory;

import java.io.IOException;

public class App 
{
public static void main(String[] args)  throws TwitterException, IOException {

		// Host and Port 
        MongoClient m = new MongoClient( "localhost" , 27017 );


        // DB Name
        DB db = m.getDB("Foo");

        // Collection Name
        final DBCollection coll = db.getCollection("Bar");


        ConfigurationBuilder cb = new ConfigurationBuilder();
        cb.setDebugEnabled(true);
        cb.setJSONStoreEnabled(true);

        // Get them from twitter dev site
        cb.setOAuthConsumerKey("");
        cb.setOAuthConsumerSecret("");
        cb.setOAuthAccessToken("");
        cb.setOAuthAccessTokenSecret("");
        TwitterStream twitterStream = new TwitterStreamFactory(cb.build()).getInstance();

        StatusListener listener = new StatusListener() {

            @Override
            public void onDeletionNotice(StatusDeletionNotice statusDeletionNotice) {
                //To change body of implemented methods use File | Settings | File Templates.
            }

            @Override
            public void onScrubGeo(long l, long l2) {
                //To change body of implemented methods use File | Settings | File Templates.
            }

            @Override
            public void onStallWarning(StallWarning stallWarning) {
                //To change body of implemented methods use File | Settings | File Templates.
            }

            @Override
            public void onException(Exception e) {
                //To change body of implemented methods use File | Settings | File Templates.
            }

            @Override
            public void onTrackLimitationNotice(int arg0) {
                // TODO Auto-generated method stub
            }

            @Override
            public void onStatus(Status status) {
                String json = DataObjectFactory.getRawJSON(status);
                //System.out.println(json);
                DBObject doc = (DBObject) JSON.parse(json);
                coll.insert(doc);

            }

        };

        FilterQuery fq = new FilterQuery();
        // upto 400 CSV keywords,#hashtags
        String tacking_strings[] = {"#android,app,#android app"};
        fq.track(tacking_strings);
        twitterStream.addListener(listener);
        twitterStream.filter(fq);

    }

}
