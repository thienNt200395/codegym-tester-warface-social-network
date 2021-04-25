package c1020g1.social_network.model;

import javax.persistence.*;

@Entity
@Table(name = "friend_request")
public class FriendRequest {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "friend_request_id")
    private Integer friendRequestId;

    @ManyToOne
    @JoinColumn(name = "receiver_user_id", referencedColumnName = "user_id")
    private User receiveUser;

    @ManyToOne
    @JoinColumn(name = "send_user_id", referencedColumnName = "user_id")
    private User sendUser;

    public Integer getFriendRequestId() {
        return friendRequestId;
    }

    public void setFriendRequestId(Integer friendRequestId) {
        this.friendRequestId = friendRequestId;
    }

    public User getReceiveUser() {
        return receiveUser;
    }

    public void setReceiveUser(User receiveUser) {
        this.receiveUser = receiveUser;
    }

    public User getSendUser() {
        return sendUser;
    }

    public void setSendUser(User sendUser) {
        this.sendUser = sendUser;
    }
}
