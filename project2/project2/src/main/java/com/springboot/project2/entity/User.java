package com.springboot.project2.entity;

import lombok.Data;
import org.springframework.data.annotation.CreatedDate;
import org.springframework.data.jpa.domain.support.AuditingEntityListener;
import org.springframework.format.annotation.DateTimeFormat;
import org.springframework.web.multipart.MultipartFile;

import javax.persistence.*;
import javax.validation.constraints.NotBlank;
import java.util.Date;

@Entity
@Data
@EntityListeners(AuditingEntityListener.class)
public class User {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Integer id;

    @NotBlank
    private String name;

    @Column(name = "avatar")
    private String avatar;// URL

    @Column(unique = true)
    private String username;
    private String password;

    @DateTimeFormat(pattern = "dd/MM/yyyy")
    private Date birthdate;

    //do dung chung 1 class giua entity va model
    //anotation nay se ko luu fiedl nay vao db
    //tach User va UserDTO nen ko can field nay trong User
    //@Transient // field is not persistent.
    //private MultipartFile file;

    @CreatedDate // tu gen
//	@DateTimeFormat(pattern = "dd/MM/yyyy HH:mm")
    private Date createdAt;

//	@ElementCollection
//	@CollectionTable(name = "user_role",
//		joinColumns = @JoinColumn(name = "user_id"))
//	@Column(name = "role")
//	private List<String> roles;

//    @OneToMany(mappedBy = "user", fetch = FetchType.EAGER)
//    private List<UserRole> userRoles;
}
