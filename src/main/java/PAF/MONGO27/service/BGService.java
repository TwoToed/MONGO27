package PAF.MONGO27.service;

import org.bson.types.ObjectId;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import PAF.MONGO27.model.Comment;
import PAF.MONGO27.repo.BGRepo;

@Service
public class BGService {

    @Autowired
    private BGRepo repo;

    public ObjectId insertComment(Comment com){

        return repo.insertComment(com);

    }
}
