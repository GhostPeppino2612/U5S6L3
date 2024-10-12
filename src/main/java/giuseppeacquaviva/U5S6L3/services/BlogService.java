package giuseppeacquaviva.U5S6L3.services;

import giuseppeacquaviva.U5S6L3.entities.Author;
import giuseppeacquaviva.U5S6L3.entities.BlogPost;
import giuseppeacquaviva.U5S6L3.exceptions.NotFoundException;
import giuseppeacquaviva.U5S6L3.payloads.NewBlogPost;
import giuseppeacquaviva.U5S6L3.repositories.BlogPostRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class BlogService {
    @Autowired
    private BlogPostRepository blogPostRepository;

    @Autowired
    private AuthorService authorsService;

    public BlogPost save(NewBlogPost body) {
        Author autore = authorsService.findById(body.getAuthorId());
        BlogPost blog = new BlogPost();
        blog.setAutore(autore);
        blog.setTempoDiLettura(body.getReadingTime());
        blog.setCategoria(body.getTitle());
        blog.setTitolo(body.getTitle());
        blog.setCover("http://picsum.photos/200/300");
        blog.setContent(body.getContent());
        return blogPostRepository.save(blog);
    }

    public List<BlogPost> getBlogs() {
        return blogPostRepository.findAll();
    }

    public BlogPost findById(long id) {
        return blogPostRepository.findById(id).orElseThrow(() -> new NotFoundException(id));
    }

    public void findByIdAndDelete(long id) {
        BlogPost found = this.findById(id);
        blogPostRepository.delete(found);
    }

    public BlogPost findByIdAndUpdate(long id, NewBlogPost body) {
        BlogPost found = this.findById(id);

        if(found.getAutore().getId() != body.getAuthorId()) {
            Author newAuthor = authorsService.findById(body.getAuthorId());
            found.setAutore(newAuthor);
        }

        found.setCategoria(body.getCategory());
        found.setTitolo(body.getTitle());
        found.setTempoDiLettura(body.getReadingTime());

        return blogPostRepository.save(found);
    }

    public List<BlogPost> findByAuthor(long id) {
        Author author = authorsService.findById(id);
        return blogPostRepository.findByAuthor(author);
    }
}
