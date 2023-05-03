package fr.digitaldragon.mytodolistapi.model;


import jakarta.persistence.*;
import lombok.Data;
import java.util.Date;


@Data
@Entity
@Table(name="tasks")
public class Task {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    private String title;

    private String content;

    @Temporal(TemporalType.DATE)
    @Column(name ="date_creation")
    private Date dateCreation;

    @Temporal(TemporalType.DATE)
    @Column(name ="date_modification")
    private Date dateModification;

    @Enumerated(EnumType.STRING)
    @Column (name = "state")
    private ETaskState taskState;

    @ManyToOne
    @JoinColumn(name="user_id", nullable = false)
    private User user;
}
