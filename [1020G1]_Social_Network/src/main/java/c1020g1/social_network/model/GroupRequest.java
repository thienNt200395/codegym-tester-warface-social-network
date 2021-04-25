package c1020g1.social_network.model;

import javax.persistence.*;

@Entity
@Table(name = "[group_request]")
public class GroupRequest {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "group_request_id")
    private Integer groupRequestId;
    @ManyToOne
    @JoinColumn(name = "group_id", referencedColumnName = "group_id")
    private GroupSocial groupSocial;
    @ManyToOne
    @JoinColumn(name = "user_id", referencedColumnName = "user_id")
    private User user;
<<<<<<< HEAD
    @Column(name = "sender")
    private String sender;

=======

    @Column(name = "sender")
    private String sender;

>>>>>>> dev
    public Integer getGroupRequestId() {
        return groupRequestId;
    }

    public void setGroupRequestId(Integer groupRequestId) {
        this.groupRequestId = groupRequestId;
    }

    public GroupSocial getGroupSocial() {
        return groupSocial;
    }

<<<<<<< HEAD
    public void setGroup(Group groupSocial) {
        this.group = group;
=======
    public void setGroupSocial(GroupSocial groupSocial) {
        this.groupSocial = groupSocial;
>>>>>>> dev
    }

    public User getUser() {
        return user;
    }

    public void setUser(User user) {
        this.user = user;
    }

    public String getSender() {
        return sender;
    }

<<<<<<< HEAD
    public void setSender(String from) {
        this.sender = from;
=======
    public void setSender(String sender) {
        this.sender = sender;
>>>>>>> dev
    }
}
