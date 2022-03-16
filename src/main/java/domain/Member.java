package domain;

import java.io.Serializable;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.EntityListeners;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;
import javax.persistence.Table;

import org.hibernate.annotations.GenericGenerator;
import org.springframework.data.jpa.domain.support.AuditingEntityListener;

import lombok.Data;

/**
 * @author Ray Wu
 * @createDate 2020年12月8日 
 * Member檔
 */
@Entity
@Table(name = "Member")
@EntityListeners(AuditingEntityListener.class)
@Data
@org.hibernate.annotations.Table(appliesTo = "Member", comment = "Member檔")
public class Member implements Serializable {

	/**
	 * 
	 */
	private static final long serialVersionUID = 737314261702627340L;

	//COMMENT 'UUID'
	@Id
	@GeneratedValue(generator = "uuid")
	@GenericGenerator(name = "uuid", strategy = "uuid2")
	@Column(name = "UUID", length = 36, columnDefinition = "varchar(36) ")
	private String uuid;
	@Column(name = "Member_NAME", length = 100, nullable = false , columnDefinition = "NVARCHAR(100) ")
	private String MemberName;
}
