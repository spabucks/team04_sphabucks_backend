package sphabucks.tag.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import sphabucks.tag.model.Tag;

public interface ITagRepository extends JpaRepository<Tag, Long> {

}
