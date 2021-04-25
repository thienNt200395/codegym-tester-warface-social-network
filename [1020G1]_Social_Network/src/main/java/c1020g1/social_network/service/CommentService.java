package c1020g1.social_network.service;

import c1020g1.social_network.model.ChildComment;
import c1020g1.social_network.model.ParentComment;
import c1020g1.social_network.model.Post;

import java.util.List;

public interface CommentService {
    List<ParentComment> findParentCommentByParentCommentId(Integer id);
}
