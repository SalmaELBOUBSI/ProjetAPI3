package be.condorcet.projetapi3.entities;

import lombok.*;
import javax.persistence.*;
import java.util.List;

@Data @NoArgsConstructor @AllArgsConstructor @RequiredArgsConstructor
@ToString
@Entity
@Table(name="EMPLOYE",schema ="ORA12",catalog ="ORCL")
public class Employe {
    @Id @GeneratedValue(strategy = GenerationType.SEQUENCE, generator = "employe_generator")
    @SequenceGenerator(name = "employe_generator", sequenceName = "EMPLOYE_SEQ", allocationSize = 1)
    private int idemploye;
    @NonNull
    private String matricule;
    @NonNull
    private String nom;
    @NonNull
    private String prenom;
    private  String telephone;
    private String email;

}
