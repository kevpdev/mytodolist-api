package fr.digitaldragon.mytodolistapi.dto;

import fr.digitaldragon.mytodolistapi.model.ETaskState;
import lombok.Data;

import java.util.Date;

@Data
public class TaskDTO {

    private Long id;

    private String title;

    private String content;

    private Date dateCreation;

    private Date dateModification;

    private ETaskState taskState;

    private Long userId;

}
