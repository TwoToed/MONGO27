package PAF.MONGO27.controller;

import org.bson.types.ObjectId;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatusCode;
import org.springframework.util.MultiValueMap;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.servlet.ModelAndView;

import PAF.MONGO27.model.Comment;
import PAF.MONGO27.service.BGService;

@RestController
@RequestMapping
public class BGControl {
    
    @Autowired
    private BGService ser;

    @PostMapping("/review")
    public ModelAndView insertComment(@RequestBody MultiValueMap<String, String> form) {
        ModelAndView mav = new ModelAndView();
        String user = form.getFirst("user");
        Integer rating = Integer.parseInt(form.getFirst("rating"));
        String comment = form.getFirst("comment");
        Integer id = Integer.parseInt(form.getFirst("id"));

        Comment com = new Comment("", user, rating, comment, id);
        ObjectId result = ser.insertComment(com);
        if (result != null){
            mav.setViewName("success");
            mav.setStatus(HttpStatusCode.valueOf(200));
            return mav;
        }
        mav.setViewName("failure");
        mav.setStatus(HttpStatusCode.valueOf(404));
        return mav;
        
    }


}
