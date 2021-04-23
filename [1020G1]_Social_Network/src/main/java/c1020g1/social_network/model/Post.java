package c1020g1.social_network.model;

import javax.persistence.*;
import java.util.Date;

@Entity
@Table(name = "post")
public class Post {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "post_id")
    private Integer postId;
    @Column(name = "post_content")
    private String postContent;
    @Column(name = "post_status")
    private String postStatus;
    @Column(name = "post_published")
    private Date postPublished;
    @ManyToOne
    @JoinColumn(referencedColumnName = "user_id", name = "user_id")
    private User user;
    @ManyToOne
    @JoinColumn(name = "group_id", referencedColumnName = "group_id")
    private GroupSocial groupSocial;

    public Integer getPostId() {
        return postId;
    }

    public void setPostId(Integer postId) {
        this.postId = postId;
    }

    public String getPostContent() {
        return postContent;
    }

    public void setPostContent(String postContent) {
        this.postContent = postContent;
    }

    public String getPostStatus() {
        return postStatus;
    }

    public void setPostStatus(String postStatus) {
        this.postStatus = postStatus;
    }

    public Date getPostPublished() {
        return postPublished;
    }

    public void setPostPublished(Date postPublished) {
        this.postPublished = postPublished;
    }

    public User getUser() {
        return user;
    }

    public void setUser(User user) {
        this.user = user;
    }

    public GroupSocial getGroupSocial() {
        return groupSocial;
    }

    public void setGroupSocial(GroupSocial groupSocial) {
        this.groupSocial = groupSocial;
    }
}
