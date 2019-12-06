package pinball.pinball.Comment;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;

import java.util.List;


public interface CommentRepository extends JpaRepository<Comment, Long> {

        @Query(value = "SELECT avg(rating) FROM Comment ")
        double average();

        @Query(value = "SELECT COUNT(id) FROM Comment")
    int quantityComment();

}




