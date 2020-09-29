package ir.maktab.entities;

import javax.persistence.*;

@Entity
@Table(name = "websiteinfo")
public class WebsiteInfo {
    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    @Column(name = "id" , updatable = false , nullable = false , unique = true)
    private Integer id;

    @Column(name = "info")
    private String info;

    @OneToOne(fetch = FetchType.LAZY, cascade=CascadeType.ALL,
            mappedBy = "websiteInfo")
    private Website website;

    public Integer getId() {
        return id;
    }

    public void setId(Integer id) {
        this.id = id;
    }

    public String getInfo() {
        return info;
    }

    public void setInfo(String info) {
        this.info = info;
    }

    public Website getWebsite() {
        return website;
    }

    public void setWebsite(Website website) {
        this.website = website;
    }
}
