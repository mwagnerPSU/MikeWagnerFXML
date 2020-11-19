/*
 * Filename: MedicalProfessionalModel.java
 * Short description: 
 * @author Mike Wagner
 * @version Nov 3, 2020
 */

package model;

import java.io.Serializable;
import javax.persistence.Basic;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.Id;
import javax.persistence.NamedQueries;
import javax.persistence.NamedQuery;
import javax.persistence.Table;
import javax.xml.bind.annotation.XmlRootElement;


/**
 *
 * @author Owner
 */
@Entity
@Table(name = "MEDICALPROFESSIONALMODEL")
@XmlRootElement
@NamedQueries({
    @NamedQuery(name = "MedicalProfessionalModel.findAll", query = "SELECT m FROM MedicalProfessionalModel m")
    , @NamedQuery(name = "MedicalProfessionalModel.findById", query = "SELECT m FROM MedicalProfessionalModel m WHERE m.id = :id")
    , @NamedQuery(name = "MedicalProfessionalModel.findByFirstname", query = "SELECT m FROM MedicalProfessionalModel m WHERE m.firstname = :firstname")
    , @NamedQuery(name = "MedicalProfessionalModel.findByLastname", query = "SELECT m FROM MedicalProfessionalModel m WHERE m.lastname = :lastname")
    , @NamedQuery(name = "MedicalProfessionalModel.findByCredentials", query = "SELECT m FROM MedicalProfessionalModel m WHERE m.credentials = :credentials")
    , @NamedQuery(name = "MedicalProfessionalModel.findByFullName", query = "SELECT m FROM MedicalProfessionalModel m WHERE m.firstname = :firstname and m.lastname = :lastname")
    , @NamedQuery(name = "MedicalProfessionalModel.findByFirstNameAndCredentials", query = "SELECT m FROM MedicalProfessionalModel m WHERE m.firstname = :firstname and m.credentials = :credentials")
    , @NamedQuery(name = "MedicalProfessionalModel.findByNameAdvanced", query = "SELECT m FROM MedicalProfessionalModel m WHERE LOWER(m.firstname) LIKE CONCAT('%', LOWER(:firstname), '%')")})
public class MedicalProfessionalModel implements Serializable {

    private static final long serialVersionUID = 1L;
    @Id
    @Basic(optional = false)
    @Column(name = "ID")
    private Integer id;
    @Column(name = "FIRSTNAME")
    private String firstname;
    @Column(name = "LASTNAME")
    private String lastname;
    @Column(name = "CREDENTIALS")
    private String credentials;

    public MedicalProfessionalModel() {
    }

    public MedicalProfessionalModel(Integer id) {
        this.id = id;
    }

    public Integer getId() {
        return id;
    }

    public void setId(Integer id) {
        this.id = id;
    }

    public String getFirstname() {
        return firstname;
    }

    public void setFirstname(String firstname) {
        this.firstname = firstname;
    }

    public String getLastname() {
        return lastname;
    }

    public void setLastname(String lastname) {
        this.lastname = lastname;
    }

    public String getCredentials() {
        return credentials;
    }

    public void setCredentials(String credentials) {
        this.credentials = credentials;
    }

    @Override
    public int hashCode() {
        int hash = 0;
        hash += (id != null ? id.hashCode() : 0);
        return hash;
    }

    @Override
    public boolean equals(Object object) {
        // TODO: Warning - this method won't work in the case the id fields are not set
        if (!(object instanceof MedicalProfessionalModel)) {
            return false;
        }
        MedicalProfessionalModel other = (MedicalProfessionalModel) object;
        if ((this.id == null && other.id != null) || (this.id != null && !this.id.equals(other.id))) {
            return false;
        }
        return true;
    }

    @Override
    public String toString() {
        return "model.MedicalProfessionalModel[ id=" + id + " ]";
    }

}
