package com.gitintern.internships.models;

import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

// this class is used to store resume data
@AllArgsConstructor
@NoArgsConstructor
@Getter
@Setter
@Entity
@Table(name = "resume")
public class Resume {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "resume_id")
    private Long ResumeId;

    @OneToOne(targetEntity = User.class, fetch = FetchType.EAGER)
    @JoinColumn(name = "user_id")
    private Intern intern;

    @Column(name = "file_name")
    private String fileName;

    @Column(name = "file_type")
    private String fileType;

    @Lob
    @Column(name = "data")
    private byte[] data;

    public Resume(Intern intern, String fileName, String fileType, byte[] data) {
        this.intern = intern;
        this.fileName = fileName;
        this.fileType = fileType;
        this.data = data;
    }

    public Resume(Intern intern) {
        this.intern = intern;
    }

}
