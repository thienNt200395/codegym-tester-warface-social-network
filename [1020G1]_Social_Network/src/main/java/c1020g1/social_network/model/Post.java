package c1020g1.social_network.model;

import javax.validation.constraints.NotBlank;
import com.fasterxml.jackson.annotation.JsonIdentityInfo;
import com.fasterxml.jackson.annotation.JsonManagedReference;
import com.fasterxml.jackson.annotation.ObjectIdGenerators;
import javax.persistence.*;
<<<<<<< HEAD
import java.util.Date;
=======
import java.sql.Timestamp;
>>>>>>> post_management
import java.util.List;

@Entity
@Table(name = "post")
//@JsonIdentityInfo(
//        generator = ObjectIdGenerators.PropertyGenerator.class,
//        property = "postId")
public class Post {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "post_id")
    private Integer postId;
<<<<<<< HEAD
=======

>>>>>>> post_management
    @Column(name = "post_content")
    @NotBlank(message = "Content not blank!!")
    private String postContent;

    @Column(name = "post_status")
    @NotBlank(message = "Status not blank!!")
    private String postStatus;

    @Column(name = "post_published")
    private Timestamp postPublished;

    @ManyToOne
    @JoinColumn(referencedColumnName = "user_id", name = "user_id")
    private User user;

    @ManyToOne
    @JoinColumn(name = "group_id", referencedColumnName = "group_id")
    private GroupSocial groupSocial;

    @OneToMany(mappedBy = "post")
    private List<ParentComment> parentComments;

<<<<<<< HEAD
    public void setPostId(Integer postId) {
        this.postId = postId;
    }
    @OneToMany(mappedBy = "post")
    List<PostImage> postImages;


    public List<PostImage> getPostImages() {
        return postImages;
    }

    public void setPostImages(List<PostImage> postImages) {
        this.postImages = postImages;
    }

    public int getPostId() {
=======
    public Integer getPostId() {
>>>>>>> post_management
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

    public Timestamp getPostPublished() {
        return postPublished;
    }

    public void setPostPublished(Timestamp postPublished) {
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

    public List<ParentComment> getParentComments() {
        return parentComments;
    }

    public void setParentComments(List<ParentComment> parentComments) {
        this.parentComments = parentComments;

    }
}
