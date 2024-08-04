package org.education.hillel_springhomework.dto;

import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import org.education.hillel_springhomework.model.Implementation;

import java.util.Date;

@AllArgsConstructor
@NoArgsConstructor
@Data
@Entity
@Table(name = "statistics")
public class StatisticsCollection {

    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    private int id;

    @Column(name = "method")
    private String method;

    @Column(name = "implementation")
    @Enumerated(EnumType.STRING)
    private Implementation implementation;

    @Column(name = "date")
    @Temporal(TemporalType.TIMESTAMP)
    private Date date;

    public StatisticsCollection(String method, Implementation implementation, Date date) {
        this.method = method;
        this.implementation = implementation;
        this.date = date;
    }
}
