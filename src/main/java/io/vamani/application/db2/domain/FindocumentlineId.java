package io.vamani.application.db2.domain;

import javax.persistence.Column;
import javax.persistence.Id;
import java.io.Serializable;
import java.util.Objects;

public class FindocumentlineId implements Serializable {
    private String findocumentcompanycode;
    private String findocumentbusinessunitcode;
    private int findocumentfinancialyearcode;
    private String findocdocumenttemplatecode;
    private String findocstatisticalgroupcode;
    private String findocumentcode;
    private Integer linenumber;

    @Column(name = "FINDOCUMENTCOMPANYCODE", nullable = false, length = 3)
    @Id
    public String getFindocumentcompanycode() {
        return findocumentcompanycode;
    }

    public void setFindocumentcompanycode(String findocumentcompanycode) {
        this.findocumentcompanycode = findocumentcompanycode;
    }

    @Column(name = "FINDOCUMENTBUSINESSUNITCODE", nullable = false, length = 10)
    @Id
    public String getFindocumentbusinessunitcode() {
        return findocumentbusinessunitcode;
    }

    public void setFindocumentbusinessunitcode(String findocumentbusinessunitcode) {
        this.findocumentbusinessunitcode = findocumentbusinessunitcode;
    }

    @Column(name = "FINDOCUMENTFINANCIALYEARCODE", nullable = false, precision = 0)
    @Id
    public int getFindocumentfinancialyearcode() {
        return findocumentfinancialyearcode;
    }

    public void setFindocumentfinancialyearcode(int findocumentfinancialyearcode) {
        this.findocumentfinancialyearcode = findocumentfinancialyearcode;
    }

    @Column(name = "FINDOCDOCUMENTTEMPLATECODE", nullable = false, length = 3)
    @Id
    public String getFindocdocumenttemplatecode() {
        return findocdocumenttemplatecode;
    }

    public void setFindocdocumenttemplatecode(String findocdocumenttemplatecode) {
        this.findocdocumenttemplatecode = findocdocumenttemplatecode;
    }

    @Column(name = "FINDOCSTATISTICALGROUPCODE", nullable = false, length = 6)
    @Id
    public String getFindocstatisticalgroupcode() {
        return findocstatisticalgroupcode;
    }

    public void setFindocstatisticalgroupcode(String findocstatisticalgroupcode) {
        this.findocstatisticalgroupcode = findocstatisticalgroupcode;
    }

    @Column(name = "FINDOCUMENTCODE", nullable = false, length = 15)
    @Id
    public String getFindocumentcode() {
        return findocumentcode;
    }

    public void setFindocumentcode(String findocumentcode) {
        this.findocumentcode = findocumentcode;
    }

    @Column(name = "LINENUMBER", nullable = false, precision = 0)
    @Id
    public Integer getLinenumber() {
        return linenumber;
    }

    public void setLinenumber(Integer linenumber) {
        this.linenumber = linenumber;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        FindocumentlineId that = (FindocumentlineId) o;
        return findocumentfinancialyearcode == that.findocumentfinancialyearcode && linenumber == that.linenumber && Objects.equals(findocumentcompanycode, that.findocumentcompanycode) && Objects.equals(findocumentbusinessunitcode, that.findocumentbusinessunitcode) && Objects.equals(findocdocumenttemplatecode, that.findocdocumenttemplatecode) && Objects.equals(findocstatisticalgroupcode, that.findocstatisticalgroupcode) && Objects.equals(findocumentcode, that.findocumentcode);
    }

    @Override
    public int hashCode() {
        return Objects.hash(findocumentcompanycode, findocumentbusinessunitcode, findocumentfinancialyearcode, findocdocumenttemplatecode, findocstatisticalgroupcode, findocumentcode, linenumber);
    }
}
