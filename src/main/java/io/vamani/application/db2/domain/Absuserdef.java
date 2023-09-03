package io.vamani.application.db2.domain;

import javax.persistence.*;
import java.util.Objects;

@Entity
@Table(name = "absuserdef")
public class Absuserdef {
    private String userid;
    private String fullname;
    private String sendersmtpid;

    @Id
    @Column(name = "USERID", nullable = false, length = 25)
    public String getUserid() {
        return userid;
    }

    public void setUserid(String userid) {
        this.userid = userid;
    }

    @Basic
    @Column(name = "FULLNAME", nullable = true, length = 50)
    public String getFullname() {
        return fullname;
    }

    public void setFullname(String fullname) {
        this.fullname = fullname;
    }

    @Basic
    @Column(name = "SENDERSMTPID", nullable = true, length = 65)
    public String getSendersmtpid() {
        return sendersmtpid;
    }

    public void setSendersmtpid(String sendersmtpid) {
        this.sendersmtpid = sendersmtpid;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        Absuserdef that = (Absuserdef) o;
        return Objects.equals(userid, that.userid) && Objects.equals(fullname, that.fullname) && Objects.equals(sendersmtpid, that.sendersmtpid);
    }

    @Override
    public int hashCode() {
        return Objects.hash(userid, fullname, sendersmtpid);
    }
}
