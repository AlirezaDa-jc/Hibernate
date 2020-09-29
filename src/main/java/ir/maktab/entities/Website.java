package ir.maktab.entities;

import javax.persistence.*;

@Entity
@Table(name = "website")
public class Website {
    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    @Column(name = "id" , updatable = false , nullable = false , unique = true)
    private Integer id;
    @Column(name = "address" , updatable = false , nullable = false , unique = true)
    private String address;
    @Column(name = "allow" , nullable = false)
    private boolean allow;

    @OneToOne(fetch = FetchType.LAZY, cascade = CascadeType.ALL)
    private WebsiteInfo websiteInfo;

    public Integer getId() {
        return id;
    }

    public void setId(Integer id) {
        this.id = id;
    }

    public String getAddress() {
        return address;
    }

    public void setAddress(String address) {
        this.address = address;
    }

    public WebsiteInfo getWebsiteInfo() {
        return websiteInfo;
    }

    public void setWebsiteInfo(WebsiteInfo websiteInfo) {
        this.websiteInfo = websiteInfo;
    }

    public boolean isAllow() {
        return allow;
    }

    public void setAllow(boolean permission) {
        this.allow = permission;
    }
}
