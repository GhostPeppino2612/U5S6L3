package giuseppeacquaviva.U5S6L3.repositories;

import giuseppeacquaviva.U5S6L3.entities.Author;
import giuseppeacquaviva.U5S6L3.entities.BlogPost;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface BlogPostRepository extends JpaRepository<BlogPost, Long> {
    List<BlogPost> findByAuthor(Author author);
}