package PAF.MONGO27.repo;

import java.util.Date;

import org.bson.Document;
import org.bson.types.ObjectId;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.mongodb.core.MongoTemplate;
import org.springframework.data.mongodb.core.query.Criteria;
import org.springframework.data.mongodb.core.query.Query;
import org.springframework.stereotype.Repository;


import PAF.MONGO27.model.Comment;

@Repository
public class BGRepo {
    @Autowired
    private MongoTemplate template;
    
    public String returnGameByGid(Integer gid){
        //db.games.find({gid:2719})
        Criteria cri = Criteria.where(Constants.A_GID).is(gid);
        Query que = Query.query(cri);
        return template.findOne(que, Document.class, Constants.C_GAMES).getString("name");
    }

    public ObjectId insertComment(Comment com){
        Document doc = new Document();
        doc.put(Constants.A_CID, com.c_id());
        doc.put(Constants.A_USER, com.user());
        doc.put(Constants.A_RATING, com.rating());
        doc.put(Constants.A_CTEXT, com.c_text());
        doc.put(Constants.A_GID, com.gid());
        doc.put("posted", new Date().getTime());
        doc.put("name", returnGameByGid(com.gid()));

        // updoc is near same as doc, except doc also contains the _id 
        // automatically made by MongoDb
        // note that ObjectId can return null, so that's good for checking errors or smth
        Document updoc = template.insert(doc, Constants.C_COMMENTS);
        return updoc.getObjectId(Constants.A_ID);
    }
}
