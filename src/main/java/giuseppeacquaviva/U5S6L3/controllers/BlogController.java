package giuseppeacquaviva.U5S6L3.controllers;

import giuseppeacquaviva.U5S6L3.entities.BlogPost;
import giuseppeacquaviva.U5S6L3.payloads.NewBlogPost;
import giuseppeacquaviva.U5S6L3.services.BlogService;
import org.hibernate.query.Page;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/blogs")
public class BlogController {
    @Autowired
    private BlogService blogService;

    @GetMapping("")
    public List<BlogPost> getBlogs() {
        return blogService.getBlogs();
    }

    @GetMapping("/{blogId}")
    public BlogPost findById(@PathVariable long blogId) {
        return blogService.findById(blogId);
    }

    @PostMapping("")
    public BlogPost save(@RequestBody NewBlogPost body) {
        return blogService.save(body);
    }

    @PutMapping("/{blogId}")
    public BlogPost findByIdAndUpdate(@PathVariable long blogId, @RequestBody NewBlogPost body) {
        return blogService.findByIdAndUpdate(blogId, body);
    }

    @DeleteMapping("{blogId}")
    @ResponseStatus(HttpStatus.NO_CONTENT)
    public void findByIdAndDelete(@PathVariable long blodId) {
        blogService.findByIdAndDelete(blodId);
    }
}
