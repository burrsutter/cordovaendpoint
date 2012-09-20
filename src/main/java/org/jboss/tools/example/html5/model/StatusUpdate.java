package org.jboss.tools.example.html5.model;

import javax.persistence.Entity;
import java.io.Serializable;
import javax.persistence.Id;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Column;
import javax.persistence.Version;
import java.lang.Override;
import java.util.Date;
import javax.persistence.Temporal;
import javax.persistence.TemporalType;
import javax.xml.bind.annotation.XmlRootElement;

@XmlRootElement
@Entity
public class StatusUpdate implements java.io.Serializable
{

   @Id
   private @GeneratedValue(strategy = GenerationType.AUTO)
   @Column(name = "id", updatable = false, nullable = false)
   Long id = null;
   @Version
   private @Column(name = "version")
   int version = 0;

   public Long getId()
   {
      return this.id;
   }

   public void setId(final Long id)
   {
      this.id = id;
   }

   public int getVersion()
   {
      return this.version;
   }

   public void setVersion(final int version)
   {
      this.version = version;
   }

   @Override
   public boolean equals(Object that)
   {
      if (this == that)
      {
         return true;
      }
      if (that == null)
      {
         return false;
      }
      if (getClass() != that.getClass())
      {
         return false;
      }
      if (id != null)
      {
         return id.equals(((StatusUpdate) that).id);
      }
      return super.equals(that);
   }

   @Override
   public int hashCode()
   {
      if (id != null)
      {
         return id.hashCode();
      }
      return super.hashCode();
   }

   @Column
   private String first_name;

   public String getFirst_name()
   {
      return this.first_name;
   }

   public void setFirst_name(final String first_name)
   {
      this.first_name = first_name;
   }

   @Column
   private String last_name;

   public String getLast_name()
   {
      return this.last_name;
   }

   public void setLast_name(final String last_name)
   {
      this.last_name = last_name;
   }

   @Column
   private String clanname;

   public String getClanname()
   {
      return this.clanname;
   }

   public void setClanname(final String clanname)
   {
      this.clanname = clanname;
   }

   @Column
   private String status_text;

   public String getStatus_text()
   {
      return this.status_text;
   }

   public void setStatus_text(final String status_text)
   {
      this.status_text = status_text;
   }

   private @Temporal(TemporalType.TIMESTAMP)
   Date create_time;

   public Date getCreate_time()
   {
      return this.create_time;
   }

   public void setCreate_time(final Date create_time)
   {
      this.create_time = create_time;
   }

   public String toString()
   {
      String result = "";
      if (first_name != null && !first_name.trim().isEmpty())
         result += first_name;
      if (last_name != null && !last_name.trim().isEmpty())
         result += " " + last_name;
      if (clanname != null && !clanname.trim().isEmpty())
         result += " " + clanname;
      if (status_text != null && !status_text.trim().isEmpty())
         result += " " + status_text;
      return result;
   }
}