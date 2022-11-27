package be.condorcet.projetapi3.entities;

import lombok.*;

import javax.persistence.*;
import java.math.BigDecimal;
import java.sql.Date;

@Data
@NoArgsConstructor
@AllArgsConstructor
@RequiredArgsConstructor
@ToString
@Entity
@Table(name = "PROJET", schema = "ORA12", catalog = "ORCL")

public class Projet {

    @Id
    @GeneratedValue(strategy = GenerationType.SEQUENCE,generator = "projet_generator")
    @SequenceGenerator(name="projet_generator",sequenceName = "PROJET_SEQ",allocationSize = 1)
    private Integer Id_projet;
    private String titre;
    @NonNull
    private Date date_demarrage;
    @NonNull
    private Date date_butoir;
    @NonNull
    private BigDecimal cout;
    @NonNull
    @ManyToOne
    @JoinColumn(name = "Id_employe")
    private Employe employe;

}
