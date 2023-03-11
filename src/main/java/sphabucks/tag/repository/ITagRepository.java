package sphabucks.tag.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import sphabucks.tag.model.Tag;

import java.util.List;

public interface ITagRepository extends JpaRepository<Tag, Long> {
    Tag findAllById(Long tagId);
}
